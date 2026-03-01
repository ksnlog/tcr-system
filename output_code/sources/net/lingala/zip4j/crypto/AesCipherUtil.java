package net.lingala.zip4j.crypto;

import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AesCipherUtil {
    public static byte[] a(byte[] bArr, char[] cArr, AesKeyStrength aesKeyStrength, boolean z2) {
        PBKDF2Engine pBKDF2Engine = new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", "ISO-8859-1", bArr, 1000));
        int b2 = aesKeyStrength.b();
        int c2 = aesKeyStrength.c();
        int i2 = b2 + c2 + 2;
        byte[] f2 = pBKDF2Engine.f(cArr, i2, z2);
        if (f2 != null && f2.length == i2) {
            return f2;
        }
        throw new ZipException(String.format("Derived Key invalid for Key Length [%d] MAC Length [%d]", Integer.valueOf(b2), Integer.valueOf(c2)));
    }

    public static byte[] b(byte[] bArr, AesKeyStrength aesKeyStrength) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, aesKeyStrength.b() + aesKeyStrength.c(), bArr2, 0, 2);
        return bArr2;
    }

    public static AESEngine c(byte[] bArr, AesKeyStrength aesKeyStrength) {
        int b2 = aesKeyStrength.b();
        byte[] bArr2 = new byte[b2];
        System.arraycopy(bArr, 0, bArr2, 0, b2);
        return new AESEngine(bArr2);
    }

    public static MacBasedPRF d(byte[] bArr, AesKeyStrength aesKeyStrength) {
        int c2 = aesKeyStrength.c();
        byte[] bArr2 = new byte[c2];
        System.arraycopy(bArr, aesKeyStrength.b(), bArr2, 0, c2);
        MacBasedPRF macBasedPRF = new MacBasedPRF("HmacSHA1");
        macBasedPRF.c(bArr2);
        return macBasedPRF;
    }

    public static void e(byte[] bArr, int i2) {
        bArr[0] = (byte) i2;
        bArr[1] = (byte) (i2 >> 8);
        bArr[2] = (byte) (i2 >> 16);
        bArr[3] = (byte) (i2 >> 24);
        for (int i3 = 4; i3 <= 15; i3++) {
            bArr[i3] = 0;
        }
    }
}
