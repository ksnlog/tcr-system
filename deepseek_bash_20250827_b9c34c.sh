cat > call_id_search.py << 'EOF'
#!/usr/bin/env python3
import pandas as pd
import os

def search_by_call_id():
    """Search for specific Call ID and display its data"""
    
    # File name - change this if your file has a different name
    excel_file = 'All Part Issued File.xlsx'
    
    if not os.path.exists(excel_file):
        print(f"❌ Error: File '{excel_file}' not found!")
        print("Please make sure the Excel file is in the same directory.")
        return
    
    try:
        # Read the Excel file
        df = pd.read_excel(excel_file)
        print(f"✅ Successfully loaded: {excel_file}")
        print(f"   Total records: {len(df)}")
        print(f"   Columns available: {len(df.columns)}")
        
        # Show available columns
        print("\n📋 Available columns:")
        for i, col in enumerate(df.columns, 1):
            print(f"   {i:2d}. {col}")
        
        # Ask for Call ID column
        print("\n" + "="*50)
        call_id_col = input("Enter the name of the Call ID column (or press Enter for auto-detection): ").strip()
        
        # Auto-detect Call ID column if not specified
        if not call_id_col:
            possible_names = ['call', 'id', 'callid', 'call_id', 'call ID', 'CallID', 'Call_ID']
            for col in df.columns:
                if any(name in col.lower() for name in possible_names):
                    call_id_col = col
                    print(f"🔍 Auto-detected Call ID column: '{call_id_col}'")
                    break
            
            if not call_id_col:
                print("❌ Could not auto-detect Call ID column. Please specify it manually.")
                return
        
        if call_id_col not in df.columns:
            print(f"❌ Column '{call_id_col}' not found in the Excel file!")
            print(f"   Available columns: {list(df.columns)}")
            return
        
        # Main search loop
        while True:
            print("\n" + "="*50)
            print("🔍 SEARCH BY CALL ID")
            print("="*50)
            
            # Show some sample Call IDs
            sample_ids = df[call_id_col].dropna().unique()[:5]
            if len(sample_ids) > 0:
                print(f"Sample Call IDs: {sample_ids.tolist()}")
            
            call_id = input("\nEnter Call ID to search (or 'quit' to exit): ").strip()
            
            if call_id.lower() in ['quit', 'exit', 'q']:
                print("👋 Goodbye!")
                break
            
            if not call_id:
                print("❌ Please enter a Call ID")
                continue
            
            # Search for the Call ID
            results = df[df[call_id_col].astype(str).str.lower() == call_id.lower()]
            
            if len(results) == 0:
                print(f"❌ No records found for Call ID: {call_id}")
                continue
            
            print(f"\n✅ Found {len(results)} record(s) for Call ID: {call_id}")
            
            # Display all columns for the found record(s)
            for idx, result in results.iterrows():
                print(f"\n📄 Record {idx + 1}:")
                print("-" * 40)
                for col_name, value in result.items():
                    if pd.notna(value):  # Only show non-empty values
                        print(f"  {col_name}: {value}")
            
            # Option to show specific columns only
            print(f"\n⭐ Want to see specific columns only?")
            specific_cols = input("Enter column names (comma-separated) or press Enter for all: ").strip()
            
            if specific_cols:
                columns_to_show = [col.strip() for col in specific_cols.split(',')]
                valid_columns = [col for col in columns_to_show if col in df.columns]
                
                if valid_columns:
                    print(f"\n📋 Showing only {valid_columns}:")
                    for idx, result in results.iterrows():
                        print(f"\nRecord {idx + 1}:")
                        for col in valid_columns:
                            print(f"  {col}: {result[col]}")
                else:
                    print("❌ None of the specified columns were found!")
    
    except Exception as e:
        print(f"❌ Error: {e}")

def quick_search():
    """Quick search without all the prompts"""
    try:
        df = pd.read_excel('All Part Issued File.xlsx')
        
        # Try to auto-detect Call ID column
        call_id_col = None
        possible_names = ['call', 'id', 'callid', 'call_id', 'call ID', 'CallID', 'Call_ID']
        for col in df.columns:
            if any(name in col.lower() for name in possible_names):
                call_id_col = col
                break
        
        if not call_id_col:
            print("❌ Could not auto-detect Call ID column")
            return
        
        call_id = input("Enter Call ID to search: ").strip()
        results = df[df[call_id_col].astype(str).str.lower() == call_id.lower()]
        
        if len(results) == 0:
            print(f"❌ No records found for Call ID: {call_id}")
            return
        
        print(f"\n✅ Found {len(results)} record(s):")
        print(results.to_string(index=False))
        
    except Exception as e:
        print(f"❌ Error: {e}")

if __name__ == "__main__":
    print("📞 CALL ID SEARCH TOOL")
    print("="*50)
    
    choice = input("Choose mode:\n1. Interactive search (recommended)\n2. Quick search\nEnter choice (1 or 2): ").strip()
    
    if choice == '2':
        quick_search()
    else:
        search_by_call_id()
EOF