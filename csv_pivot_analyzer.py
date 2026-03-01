import zipfile
import pandas as pd
import chardet
import os
from io import StringIO

def detect_encoding(file_path):
    """Detect encoding of a file using chardet"""
    with open(file_path, 'rb') as file:
        raw_data = file.read()
        result = chardet.detect(raw_data)
        return result['encoding']

def extract_zip(zip_path, extract_to='extracted_files'):
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
        return df
    except UnicodeDecodeError:
        # Try fallback encodings
        fallback_encodings = ['utf-8', 'latin1', 'iso-8859-1', 'cp1252']
        for enc in fallback_encodings:
            try:
                df = pd.read_csv(csv_path, encoding=enc)
                print(f"Successfully read with fallback encoding: {enc}")
                return df
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
    
    print(f"Using columns:")
    print(f"  Center: '{center_col}'")
    print(f"  Age: '{age_col}'")
    print(f"  Quantity: '{quantity_col}'")
    print(f"\nOriginal data shape: {df.shape}")
    print(f"\nFirst 5 rows of original data:")
    print(df.head())
    
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
    
    return pivot_df

def main():
    # Configuration
    zip_file_path = input("Enter the path to your ZIP file: ").strip()
    
    if not os.path.exists(zip_file_path):
        print(f"Error: File '{zip_file_path}' not found!")
        return
    
    try:
        print("Step 1: Extracting ZIP file...")
        csv_path = extract_zip(zip_file_path)
        print(f"Extracted CSV: {csv_path}")
        
        print("\nStep 2: Reading CSV file...")
        df = read_csv_with_encoding(csv_path)
        
        print("\nStep 3: Creating pivot table preview...")
        pivot_table = create_pivot_preview(df)
        
        print("\n" + "="*60)
        print("PIVOT TABLE PREVIEW")
        print("="*60)
        print(f"Shape: {pivot_table.shape}")
        print(f"\nPivot Table (showing first 20 rows):")
        print(pivot_table.head(20))
        
        print(f"\nAge columns: {list(pivot_table.columns)}")
        print(f"\nTotal centers: {len(pivot_table)}")
        print(f"Total quantity: {pivot_table.sum().sum():,.0f}")
        
        # Save pivot table to CSV
        output_file = "pivot_table_output.csv"
        pivot_table.to_csv(output_file)
        print(f"\nPivot table saved to: {output_file}")
        
    except Exception as e:
        print(f"Error: {str(e)}")

if __name__ == "__main__":
    main()
