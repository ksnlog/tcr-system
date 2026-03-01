import streamlit as st
import zipfile
import re

st.title("Safe APK Static Analyzer (No Androguard)")

apk_file = st.file_uploader("Upload APK file", type="apk")

if apk_file is not None:
    # Save uploaded APK temporarily
    with open("temp.apk", "wb") as f:
        f.write(apk_file.getbuffer())
    
    permissions = set()
    dex_strings = []

    # Open APK as zip
    with zipfile.ZipFile("temp.apk", "r") as zipf:
        # Try reading AndroidManifest.xml
        try:
            manifest = zipf.read("AndroidManifest.xml").decode(errors="ignore")
            perms = re.findall(r'android\.permission\.[A-Z_]+', manifest)
            permissions.update(perms)
        except KeyError:
            st.warning("AndroidManifest.xml not found or obfuscated")
        
        # Read classes.dex strings
        try:
            dex_data = zipf.read("classes.dex")
            strings_found = re.findall(rb'[\x20-\x7E]{4,}', dex_data)
            dex_strings = [s.decode(errors="ignore") for s in strings_found]
        except KeyError:
            st.warning("classes.dex not found")

    # Display permissions
    if permissions:
        st.subheader("Declared Permissions")
        for p in permissions:
            st.write(p)

    # Highlight dangerous permissions
    dangerous = [
        "ACCEPT_HANDOVER", "READ_CALL_LOG", "READ_PHONE_NUMBERS",
        "SYSTEM_ALERT_WINDOW", "ACCESS_BACKGROUND_LOCATION"
    ]
    st.subheader("Potentially Dangerous Permissions")
    for p in permissions:
        for d in dangerous:
            if d in p:
                st.write(p)

    # Search for sensitive API references
    sensitive_apis = [
        "TelecomManager", "acceptHandover", "Firebase", "SmsManager",
        "getLastKnownLocation", "WindowManager"
    ]
    found_apis = [s for s in dex_strings if any(api in s for api in sensitive_apis)]

    if found_apis:
        st.subheader("Potential Sensitive API References in classes.dex")
        for f in found_apis[:50]:  # limit output
            st.write(f)
