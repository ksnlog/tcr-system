from flask import Flask, render_template, request, send_file
import zipfile
import pandas as pd
import chardet
import os
import tempfile
import shutil

app = Flask(__name__)

def detect_encoding(file_path):
    """Detect encoding of a file using chardet"""
    with open(file_path, 'rb') as file:
        raw_data = file.read()
        result = chardet.detect(raw_data)
        return result['encoding']

def extract_zip(zip_path, extract_to):
    """Extract ZIP file and return path to CSV file"""
    if not os.path.exists(extract_to):
        os.makedirs(extract_to)
    
    with zipfile.ZipFile(zip_path, 'r') as zip_ref:
        zip_ref.extractall(extract_to)
        # Get the first CSV file found
        csv_files = [f for f in zip_ref.namelist() if f.lower().endswith('.csv')]
        if not csv_files:
            raise ValueError("No CSV file found in the ZIP archive")
        csv_path = os.path.join(extract_to, csv_files[0])
        return csv_path

def read_csv_with_encoding(csv_path):
    """Read CSV file with auto-detected encoding"""
    encoding = detect_encoding(csv_path)
    print(f"Detected encoding: {encoding}")
    
    try:
        df = pd.read_csv(csv_path, encoding=encoding)
        return df, encoding
    except UnicodeDecodeError:
        # Try fallback encodings
        fallback_encodings = ['utf-8', 'latin1', 'iso-8859-1', 'cp1252']
        for enc in fallback_encodings:
            try:
                df = pd.read_csv(csv_path, encoding=enc)
                print(f"Successfully read with fallback encoding: {enc}")
                return df, enc
            except UnicodeDecodeError:
                continue
        raise ValueError("Could not read CSV with any encoding")

def find_center_column(df):
    """Find center name column (handling spelling variations)"""
    center_variations = ['center', 'centre', 'center name', 'centre name', 
                        'center_name', 'centre_name', 'location', 'branch']
    
    for col in df.columns:
        col_lower = col.lower().strip()
        for variation in center_variations:
            if variation in col_lower:
                return col
    # If no exact match, return the first column that might contain center info
    for col in df.columns:
        if any(word in col.lower() for word in ['center', 'centre', 'name', 'location']):
            return col
    return df.columns[0]  # Return first column as fallback

def find_age_column(df):
    """Find age column"""
    age_variations = ['age', 'aging', 'ageing', 'days', 'age group', 'age_group']
    
    for col in df.columns:
        col_lower = col.lower().strip()
        for variation in age_variations:
            if variation in col_lower:
                return col
    # If no exact match, look for numeric columns that might represent age
    for col in df.columns:
        if pd.api.types.is_numeric_dtype(df[col]):
            return col
    return df.columns[1]  # Return second column as fallback

def find_quantity_column(df):
    """Find quantity/calls column"""
    quantity_variations = ['quantity', 'calls', 'count', 'number', 'volume', 
                          'qty', 'total', 'amount']
    
    for col in df.columns:
        col_lower = col.lower().strip()
        for variation in quantity_variations:
            if variation in col_lower:
                return col
    # If no exact match, look for numeric columns
    numeric_cols = df.select_dtypes(include=['number']).columns
    if len(numeric_cols) > 0:
        return numeric_cols[0]
    return df.columns[2]  # Return third column as fallback

def create_pivot_preview(df):
    """Create pivot table preview"""
    center_col = find_center_column(df)
    age_col = find_age_column(df)
    quantity_col = find_quantity_column(df)
    
    # Clean data - remove rows with missing values in key columns
    df_clean = df.dropna(subset=[center_col, age_col, quantity_col])
    
    # Convert age to integer if possible, otherwise use as is
    try:
        df_clean[age_col] = pd.to_numeric(df_clean[age_col], errors='coerce').fillna(0).astype(int)
    except:
        print("Warning: Could not convert age column to numeric")
    
    # Convert quantity to numeric
    df_clean[quantity_col] = pd.to_numeric(df_clean[quantity_col], errors='coerce').fillna(0)
    
    # Create pivot table
    pivot_df = df_clean.pivot_table(
        index=center_col,
        columns=age_col,
        values=quantity_col,
        aggfunc='sum',
        fill_value=0
    )
    
    # Sort columns numerically if possible
    try:
        pivot_df = pivot_df.reindex(sorted(pivot_df.columns), axis=1)
    except:
        pass
    
    return pivot_df, center_col, age_col, quantity_col

@app.route('/')
def index():
    return '''
    <!DOCTYPE html>
    <html>
    <head>
        <title>CSV Pivot Analyzer</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }
            .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
            .upload-box { border: 2px dashed #ccc; padding: 40px; text-align: center; margin: 20px 0; background-color: #fafafa; }
            .btn { background-color: #007cba; color: white; padding: 12px 24px; border: none; cursor: pointer; border-radius: 5px; font-size: 16px; }
            .btn:hover { background-color: #005a87; }
            .results { margin-top: 30px; }
            table { border-collapse: collapse; width: 100%; margin: 10px 0; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            .info-box { background-color: #e8f4fd; padding: 20px; margin: 20px 0; border-radius: 5px; border-left: 4px solid #007cba; }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>📊 CSV Pivot Analyzer</h1>
            <p>Upload a ZIP file containing a CSV file to generate a pivot table with Center names as rows and Age values as columns.</p>
            
            <div class="info-box">
                <strong>How it works:</strong>
                <ul>
                    <li>Upload a ZIP file containing your CSV</li>
                    <li>Automatically detects encoding and column types</li>
                    <li>Creates pivot table: Centers × Age → Quantity</li>
                    <li>Download the result as CSV</li>
                </ul>
            </div>
            
            <form action="/upload" method="post" enctype="multipart/form-data">
                <div class="upload-box">
                    <h3>📁 Upload ZIP File</h3>
                    <input type="file" name="file" accept=".zip" required style="margin: 20px 0;">
                    <br>
                    <input type="submit" value="Upload and Analyze" class="btn">
                </div>
            </form>
        </div>
    </body>
    </html>
    '''

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return "No file uploaded", 400
    
    file = request.files['file']
    if file.filename == '':
        return "No file selected", 400
    
    if not file.filename.lower().endswith('.zip'):
        return "Please upload a ZIP file", 400
    
    # Create temporary directory
    temp_dir = tempfile.mkdtemp()
    
    try:
        # Save uploaded file
        zip_path = os.path.join(temp_dir, 'uploaded.zip')
        file.save(zip_path)
        
        # Extract and process
        extract_dir = os.path.join(temp_dir, 'extracted')
        csv_path = extract_zip(zip_path, extract_dir)
        
        # Read CSV
        df, encoding = read_csv_with_encoding(csv_path)
        
        # Create pivot table
        pivot_table, center_col, age_col, quantity_col = create_pivot_preview(df)
        
        # Save pivot table
        output_path = os.path.join(temp_dir, 'pivot_table.csv')
        pivot_table.to_csv(output_path)
        
        # Generate HTML preview
        preview_html = pivot_table.head(20).to_html(classes='table table-striped', border=0, index=True)
        
        return f'''
        <!DOCTYPE html>
        <html>
        <head>
            <title>Analysis Results</title>
            <style>
                body {{ font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }}
                .container {{ max-width: 1200px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }}
                .info-box {{ background-color: #e8f4fd; padding: 20px; margin: 20px 0; border-radius: 5px; border-left: 4px solid #007cba; }}
                .btn {{ background-color: #007cba; color: white; padding: 10px 20px; text-decoration: none; display: inline-block; margin: 10px 5px; border-radius: 5px; }}
                .btn:hover {{ background-color: #005a87; }}
                .btn-secondary {{ background-color: #6c757d; }}
                .btn-secondary:hover {{ background-color: #545b62; }}
                table {{ border-collapse: collapse; width: 100%; margin: 10px 0; }}
                th, td {{ border: 1px solid #ddd; padding: 8px; text-align: left; }}
                th {{ background-color: #f2f2f2; }}
                .success {{ color: #28a745; font-weight: bold; }}
            </style>
        </head>
        <body>
            <div class="container">
                <h1>✅ Analysis Complete</h1>
                
                <div class="info-box">
                    <h3>📋 File Information</h3>
                    <p><strong>Detected Encoding:</strong> {encoding}</p>
                    <p><strong>Original Data Shape:</strong> {df.shape[0]} rows × {df.shape[1]} columns</p>
                    <p><strong>Detected Columns:</strong></p>
                    <ul>
                        <li>🏢 Center: <strong>'{center_col}'</strong></li>
                        <li>📅 Age: <strong>'{age_col}'</strong></li>
                        <li>🔢 Quantity: <strong>'{quantity_col}'</strong></li>
                    </ul>
                    <p><strong>Pivot Table Shape:</strong> {pivot_table.shape[0]} centers × {pivot_table.shape[1]} age groups</p>
                    <p><strong>Total Centers:</strong> {len(pivot_table)}</p>
                    <p class="success"><strong>Total Quantity:</strong> {pivot_table.sum().sum():,.0f}</p>
                </div>
                
                <div>
                    <a href="/download/{os.path.basename(temp_dir)}" class="btn">📥 Download Pivot Table CSV</a>
                    <a href="/" class="btn btn-secondary">🔄 Analyze Another File</a>
                </div>
                
                <h3>👀 Pivot Table Preview (First 20 Rows)</h3>
                <div style="overflow-x: auto;">
                    {preview_html}
                </div>
            </div>
        </body>
        </html>
        '''
        
    except Exception as e:
        shutil.rmtree(temp_dir)
        return f'''
        <div style="font-family: Arial, sans-serif; margin: 40px; background-color: #f8d7da; padding: 20px; border-radius: 5px; color: #721c24;">
            <h2>❌ Error Processing File</h2>
            <p><strong>Error:</strong> {str(e)}</p>
            <a href="/" style="background-color: #007cba; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">Try Again</a>
        </div>
        ''', 500

@app.route('/download/<temp_dir_name>')
def download_file(temp_dir_name):
    temp_dir = os.path.join(tempfile.gettempdir(), temp_dir_name)
    output_path = os.path.join(temp_dir, 'pivot_table.csv')
    
    if os.path.exists(output_path):
        response = send_file(output_path, as_attachment=True, download_name='pivot_table_output.csv')
        
        # Clean up temporary files after download
        @response.call_on_close
        def cleanup():
            shutil.rmtree(temp_dir, ignore_errors=True)
        
        return response
    else:
        return "File not found", 404

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
