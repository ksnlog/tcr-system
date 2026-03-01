import streamlit as st
import pandas as pd
import zipfile
import chardet
import io

st.title("📂 Call ID Search App")

# Upload ZIP
uploaded_file = st.file_uploader("Upload a ZIP file containing a CSV", type=["zip"])

if uploaded_file is not None:
    with zipfile.ZipFile(uploaded_file, "r") as zip_ref:
        # Find CSV files in ZIP
        csv_files = [f for f in zip_ref.namelist() if f.endswith(".csv")]
        if not csv_files:
            st.error("❌ No CSV file found inside the ZIP.")
        else:
            csv_name = csv_files[0]
            with zip_ref.open(csv_name) as csvfile:
                raw_data = csvfile.read()

                # Detect encoding
                result = chardet.detect(raw_data)
                encoding = result["encoding"]

                # Read CSV with detected encoding
                df = pd.read_csv(io.StringIO(raw_data.decode(encoding)))

                # Normalize column names
                df.columns = [col.strip().lower() for col in df.columns]

                st.success(f"✅ Loaded {csv_name} (encoding: {encoding})")
                st.dataframe(df.head())

                # Search Call ID
                call_id = st.text_input("🔍 Enter Call ID to search (e.g., KOL128082500012)")
                if call_id:
                    match = df[df["call id"].astype(str).str.strip() == call_id.strip()]
                    if not match.empty:
                        st.write("### 🎯 Result:")
                        st.write("**Center:**", match.iloc[0].get("center", "Not Found"))
                        st.write("**Call Stage:**", match.iloc[0].get("call stage", "Not Found"))
                        st.write("**Registration Remarks:**", match.iloc[0].get("registration remarks", "Not Found"))
                    else:
                        st.error("❌ Call ID not found.")
