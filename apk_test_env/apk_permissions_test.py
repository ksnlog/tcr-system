import streamlit as st
from androguard.core.bytecodes.apk import APK

st.title("APK Permissions Checker (Safe)")

# Upload APK file
apk_file = st.file_uploader("Upload APK file", type="apk")

if apk_file is not None:
    # Save uploaded file temporarily
    with open("temp.apk", "wb") as f:
        f.write(apk_file.getbuffer())
    
    # Load APK safely
    apk = APK("temp.apk")
    
    # Get permissions
    permissions = apk.get_permissions()
    
    if permissions:
        st.subheader("Declared Permissions in APK")
        for p in permissions:
            st.write(p)
    else:
        st.write("No permissions found or manifest is obfuscated/encrypted.")

    # Highlight potentially dangerous ones
    dangerous = [
        "ACCEPT_HANDOVER", "READ_CALL_LOG", "READ_PHONE_NUMBERS", 
        "SYSTEM_ALERT_WINDOW", "ACCESS_BACKGROUND_LOCATION"
    ]
    
    st.subheader("Potentially Dangerous Permissions")
    for p in permissions:
        for d in dangerous:
            if d in p:
                st.write(p)
