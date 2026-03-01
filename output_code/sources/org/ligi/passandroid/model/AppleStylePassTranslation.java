package org.ligi.passandroid.model;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AppleStylePassTranslation extends HashMap<String, String> {
    public static String readFileAsStringGuessEncoding(File file) {
        try {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            dataInputStream.readFully(bArr);
            dataInputStream.close();
            if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
                int i2 = length - 3;
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 3, bArr2, 0, i2);
                return new String(bArr2, "utf-8");
            }
            CharsetMatch b2 = new CharsetDetector().d(bArr).b();
            if (b2 != null) {
                try {
                    return new String(bArr, b2.b());
                } catch (UnsupportedEncodingException unused) {
                }
            }
            return new String(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String removeLeadingClutter(String str) {
        if (!str.startsWith("\"") && !str.startsWith("\n") && !str.startsWith("\r") && !str.startsWith(" ")) {
            return str;
        }
        return removeLeadingClutter(str.substring(1));
    }

    public void loadFromFile(File file) {
        loadFromString(readFileAsStringGuessEncoding(file));
    }

    public void loadFromString(String str) {
        if (str == null) {
            return;
        }
        for (String str2 : str.split("\";")) {
            String[] split = str2.split("\" ?= ?\"");
            if (split.length == 2) {
                put(removeLeadingClutter(split[0]), split[1]);
            }
        }
    }

    public String translate(String str) {
        if (containsKey(str)) {
            return get(str);
        }
        return str;
    }
}
