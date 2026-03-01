import pandas as pd
import sys
import os
import glob
from datetime import datetime

def clean_data(df):
    """
    Perform common data cleaning operations
    """
    print("Starting data cleaning...")
    
    # Remove duplicate rows
    initial_count = len(df)
    df = df.drop_duplicates()
    final_count = len(df)
    print(f"Removed {initial_count - final_count} duplicate rows")
    
    # Fill missing numeric values with mean
    numeric_cols = df.select_dtypes(include=['number']).columns
    for col in numeric_cols:
        if df[col].isnull().sum() > 0:
            df[col] = df[col].fillna(df[col].mean())
            print(f"Filled missing values in {col} with mean")
    
    # Fill missing text values with 'Unknown'
    text_cols = df.select_dtypes(include=['object']).columns
    for col in text_cols:
        if df[col].isnull().sum() > 0:
            df[col] = df[col].fillna('Unknown')
            print(f"Filled missing values in {col} with 'Unknown'")
    
    # Remove leading/trailing whitespace from text columns
    for col in text_cols:
        df[col] = df[col].str.strip()
    
    print("Data cleaning completed!")
    return df

def merge_excel_files(file_pattern, output_file='merged_output.xlsx'):
    """
    Merge multiple Excel files with the same structure
    """
    print(f"Looking for files matching: {file_pattern}")
    
    # Find all files matching the pattern
    files = glob.glob(file_pattern)
    if not files:
        print("No files found matching the pattern")
        return None
    
    print(f"Found {len(files)} files to merge")
    
    # Read and concatenate all files
    all_data = []
    for file in files:
        try:
            df = pd.read_excel(file)
            df['Source_File'] = os.path.basename(file)  # Add column to track source
            all_data.append(df)
            print(f"✓ Loaded {os.path.basename(file)} with {len(df)} rows")
        except Exception as e:
            print(f"✗ Error loading {file}: {str(e)}")
    
    if not all_data:
        print("No files were successfully loaded")
        return None
    
    # Combine all dataframes
    merged_df = pd.concat(all_data, ignore_index=True)
    merged_df.to_excel(output_file, index=False)
    print(f"✅ Merged {len(files)} files into {output_file} with {len(merged_df)} total rows")
    return merged_df

def generate_report(df, report_file='excel_report.txt'):
    """
    Generate a comprehensive data quality report
    """
    print("Generating report...")
    
    report_lines = []
    
    report_lines.append("=== EXCEL DATA REPORT ===")
    report_lines.append(f"Generated: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    report_lines.append(f"Total rows: {len(df):,}")
    report_lines.append(f"Total columns: {len(df.columns)}")
    report_lines.append("\n--- Column Overview ---")
    
    for col in df.columns:
        report_lines.append(f"\nColumn: {col}")
        report_lines.append(f"  Data type: {df[col].dtype}")
        report_lines.append(f"  Missing values: {df[col].isnull().sum():,}")
        report_lines.append(f"  Unique values: {df[col].nunique():,}")
        
        if pd.api.types.is_numeric_dtype(df[col]):
            report_lines.append(f"  Min: {df[col].min():.2f}")
            report_lines.append(f"  Max: {df[col].max():.2f}")
            report_lines.append(f"  Mean: {df[col].mean():.2f}")
            report_lines.append(f"  Standard deviation: {df[col].std():.2f}")
    
    # Add summary statistics
    report_lines.append("\n--- Summary Statistics ---")
    report_lines.append(f"Total missing values: {df.isnull().sum().sum():,}")
    report_lines.append(f"Total duplicate rows (before cleaning): {df.duplicated().sum():,}")
    
    # Write report to file
    with open(report_file, 'w') as f:
        f.write('\n'.join(report_lines))
    
    print(f"✅ Report generated: {report_file}")
    return report_lines

def add_calculated_column(df, base_column, operation='double', new_column_name=None):
    """
    Add a new calculated column based on existing data
    """
    if base_column not in df.columns:
        print(f"Error: Column '{base_column}' not found in dataframe")
        print(f"Available columns: {list(df.columns)}")
        return df
    
    if not new_column_name:
        new_column_name = f"{base_column}_{operation}"
    
    if operation == 'double':
        df[new_column_name] = df[base_column] * 2
    elif operation == 'square':
        df[new_column_name] = df[base_column] ** 2
    elif operation == 'sqrt':
        df[new_column_name] = df[base_column] ** 0.5
    elif operation == 'percent':
        df[new_column_name] = (df[base_column] / df[base_column].sum()) * 100
    else:
        print(f"Unknown operation: {operation}")
        return df
    
    print(f"✅ Added new column '{new_column_name}' using operation '{operation}' on '{base_column}'")
    return df

def show_menu():
    """
    Display the main menu
    """
    print("\n" + "="*50)
    print("        EXCEL PROCESSING TOOL")
    print("="*50)
    print("1. Clean data in Excel file")
    print("2. Merge multiple Excel files")
    print("3. Generate data quality report")
    print("4. Add calculated column")
    print("5. Process with default settings (clean + report)")
    print("6. Exit")
    print("="*50)

def main():
    """
    Main function with menu-driven interface
    """
    print("Loading Excel Processing Tool...")
    
    while True:
        show_menu()
        choice = input("\nChoose an option (1-6): ").strip()
        
        if choice == '1':
            # Clean data
            input_file = input("Enter input file name [input.xlsx]: ") or 'input.xlsx'
            if os.path.exists(input_file):
                df = pd.read_excel(input_file)
                print(f"Loaded {input_file} with {len(df)} rows")
                
                df_clean = clean_data(df)
                output_file = input_file.replace('.xlsx', '_clean.xlsx')
                df_clean.to_excel(output_file, index=False)
                print(f"✅ Cleaned data saved to {output_file}")
            else:
                print(f"❌ File {input_file} not found")
        
        elif choice == '2':
            # Merge files
            pattern = input("Enter file pattern [*.xlsx]: ") or '*.xlsx'
            output_name = input("Enter output file name [merged_output.xlsx]: ") or 'merged_output.xlsx'
            merge_excel_files(pattern, output_name)
        
        elif choice == '3':
            # Generate report
            input_file = input("Enter input file name [input.xlsx]: ") or 'input.xlsx'
            if os.path.exists(input_file):
                df = pd.read_excel(input_file)
                report_name = input("Enter report file name [excel_report.txt]: ") or 'excel_report.txt'
                generate_report(df, report_name)
                
                # Show a preview of the report
                try:
                    with open(report_name, 'r') as f:
                        lines = f.readlines()[:10]  # Show first 10 lines
                    print("\nReport preview:")
                    print(''.join(lines))
                    if len(lines) == 10:
                        print("... (truncated)")
                except:
                    pass
            else:
                print(f"❌ File {input_file} not found")
        
        elif choice == '4':
            # Add calculated column
            input_file = input("Enter input file name [input.xlsx]: ") or 'input.xlsx'
            if os.path.exists(input_file):
                df = pd.read_excel(input_file)
                print(f"Available columns: {list(df.columns)}")
                
                base_col = input("Enter column name to base calculation on: ").strip()
                operation = input("Enter operation (double/square/sqrt/percent) [double]: ") or 'double'
                new_col = input("Enter new column name [optional]: ").strip() or None
                
                df = add_calculated_column(df, base_col, operation, new_col)
                if df is not None:
                    output_file = input_file.replace('.xlsx', '_with_calc.xlsx')
                    df.to_excel(output_file, index=False)
                    print(f"✅ File with new column saved to {output_file}")
            else:
                print(f"❌ File {input_file} not found")
        
        elif choice == '5':
            # Default processing (clean + report)
            input_file = input("Enter input file name [input.xlsx]: ") or 'input.xlsx'
            if os.path.exists(input_file):
                df = pd.read_excel(input_file)
                print(f"Loaded {input_file} with {len(df)} rows")
                
                # Clean data
                df_clean = clean_data(df)
                clean_file = input_file.replace('.xlsx', '_clean.xlsx')
                df_clean.to_excel(clean_file, index=False)
                print(f"✅ Cleaned data saved to {clean_file}")
                
                # Generate report
                report_file = input_file.replace('.xlsx', '_report.txt')
                generate_report(df_clean, report_file)
            else:
                print(f"❌ File {input_file} not found")
        
        elif choice == '6':
            print("Goodbye!")
            break
        
        else:
            print("❌ Invalid choice. Please enter a number between 1-6.")
        
        # Pause before showing menu again
        input("\nPress Enter to continue...")

if __name__ == "__main__":
    # Check if required packages are installed
    try:
        import pandas as pd
    except ImportError:
        print("Error: pandas is not installed. Install it with: pip install pandas openpyxl")
        sys.exit(1)
    
    main()
