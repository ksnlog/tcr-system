import streamlit as st
import pandas as pd
import zipfile
import chardet

# App title
st.title("Call ID Search App")

# File uploader
uploaded_file = st.file_uploader("Upload a ZIP file containing a CSV", type=["zip"])

if uploaded_file is not None:
    with zipfile.ZipFile(uploaded_file, "r") as zip_ref:
        # Assume only 1 CSV inside
        csv_files = [f for f in zip_ref.namelist() if f.endswith(".csv")]
        if not csv_files:
            st.error("No CSV file found inside the ZIP.")
        else:
            csv_name = csv_files[0]
            with zip_ref.open(csv_name) as csvfile:
                raw_data = csvfile.read()
                # Detect encoding
                result = chardet.detect(raw_data)
                encoding = result["encoding"]
                
                # Load CSV with detected encoding
                df = pd.read_csv(pd.compat.StringIO(raw_data.decode(encoding)))
                
                st.success(f"Loaded {csv_name} with encoding {encoding}")
                
                # Show preview
                st.dataframe(df.head())

                # Search bar
                call_id = st.text_input("Enter Call ID to search")
                if call_id:
                    match = df[df["Call ID"].astype(str).str.strip() == call_id.strip()]
                    if not match.empty:
                        st.write("### Result:")
                        st.write("**Center:**", match.iloc[0]["Center"])
                        st.write("**Call Stage:**", match.iloc[0]["Call Stage"])
                        st.write("**Registration Remarks:**", match.iloc[0]["Registration Remarks"])
                    else:
                        st.error("Call ID not found.")
