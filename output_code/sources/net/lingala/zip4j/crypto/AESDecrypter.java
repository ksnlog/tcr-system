package net.lingala.zip4j.crypto;

import java.util.Arrays;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.enums.AesKeyStrength;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AESDecrypter implements Decrypter {

    /* renamed from: a  reason: collision with root package name */
    private AESEngine f1360a;

    /* renamed from: b  reason: collision with root package name */
    private MacBasedPRF f1361b;

    /* renamed from: c  reason: collision with root package name */
    private int f1362c = 1;

    /* renamed from: d  reason: collision with root package name */
    private byte[] f1363d = new byte[16];

    /* renamed from: e  reason: collision with root package name */
    private byte[] f1364e = new byte[16];

    public AESDecrypter(AESExtraDataRecord aESExtraDataRecord, char[] cArr, byte[] bArr, byte[] bArr2, boolean z2) {
        c(bArr, bArr2, cArr, aESExtraDataRecord, z2);
    }

    private void c(byte[] bArr, byte[] bArr2, char[] cArr, AESExtraDataRecord aESExtraDataRecord, boolean z2) {
        if (cArr != null && cArr.length > 0) {
            AesKeyStrength c2 = aESExtraDataRecord.c();
            byte[] a2 = AesCipherUtil.a(bArr, cArr, c2, z2);
            if (Arrays.equals(bArr2, AesCipherUtil.b(a2, c2))) {
                this.f1360a = AesCipherUtil.c(a2, c2);
                this.f1361b = AesCipherUtil.d(a2, c2);
                return;
            }
            throw new ZipException("Wrong Password", ZipException.Type.WRONG_PASSWORD);
        }
        throw new ZipException("empty or null password provided for AES decryption", ZipException.Type.WRONG_PASSWORD);
    }

    @Override // net.lingala.zip4j.crypto.Decrypter
    public int a(byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = i2;
        while (true) {
            int i6 = i2 + i3;
            if (i5 < i6) {
                int i7 = i5 + 16;
                if (i7 <= i6) {
                    i4 = 16;
                } else {
                    i4 = i6 - i5;
                }
                this.f1361b.g(bArr, i5, i4);
                AesCipherUtil.e(this.f1363d, this.f1362c);
                this.f1360a.e(this.f1363d, this.f1364e);
                for (int i8 = 0; i8 < i4; i8++) {
                    int i9 = i5 + i8;
                    bArr[i9] = (byte) (bArr[i9] ^ this.f1364e[i8]);
                }
                this.f1362c++;
                i5 = i7;
            } else {
                return i3;
            }
        }
    }

    public byte[] b(int i2) {
        return this.f1361b.e(i2);
    }
}
