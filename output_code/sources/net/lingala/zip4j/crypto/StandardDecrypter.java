package net.lingala.zip4j.crypto;

import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StandardDecrypter implements Decrypter {

    /* renamed from: a  reason: collision with root package name */
    private ZipCryptoEngine f1386a = new ZipCryptoEngine();

    public StandardDecrypter(char[] cArr, long j2, long j3, byte[] bArr, boolean z2) {
        b(bArr, cArr, j3, j2, z2);
    }

    private void b(byte[] bArr, char[] cArr, long j2, long j3, boolean z2) {
        byte b2;
        if (cArr != null && cArr.length > 0) {
            this.f1386a.c(cArr, z2);
            int i2 = 0;
            byte b3 = bArr[0];
            while (i2 < 12) {
                i2++;
                if (i2 == 12 && (b2 = (byte) (this.f1386a.b() ^ b3)) != ((byte) (j3 >> 24)) && b2 != ((byte) (j2 >> 8))) {
                    throw new ZipException("Wrong password!", ZipException.Type.WRONG_PASSWORD);
                }
                ZipCryptoEngine zipCryptoEngine = this.f1386a;
                zipCryptoEngine.d((byte) (zipCryptoEngine.b() ^ b3));
                if (i2 != 12) {
                    b3 = bArr[i2];
                }
            }
            return;
        }
        throw new ZipException("Wrong password!", ZipException.Type.WRONG_PASSWORD);
    }

    @Override // net.lingala.zip4j.crypto.Decrypter
    public int a(byte[] bArr, int i2, int i3) {
        if (i2 >= 0 && i3 >= 0) {
            for (int i4 = i2; i4 < i2 + i3; i4++) {
                byte b2 = (byte) (((bArr[i4] & 255) ^ this.f1386a.b()) & 255);
                this.f1386a.d(b2);
                bArr[i4] = b2;
            }
            return i3;
        }
        throw new ZipException("one of the input parameters were null in standard decrypt data");
    }
}
