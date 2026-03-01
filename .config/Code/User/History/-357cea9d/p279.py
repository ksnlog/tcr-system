cat > simple_search.py << 'EOF'
import pandas as pd

# Simple version - change these variables as needed
EXCEL_FILE = 'All Part issued File.xlsx'
CALL_ID_COLUMN = 'Call_ID'  # Change this to your actual column name

df = pd.read_excel(EXCEL_FILE)
call_id = input("Enter Call ID: ").strip()

# Search for the call ID
result = df[df[CALL_ID_COLUMN].astype(str) == call_id]

if len(result) == 0:
    print(f"No results found for Call ID: {call_id}")
else:
    print(f"Found {len(result)} record(s):")
    print(result.to_string(index=False))
EOF