package net.lingala.zip4j.crypto;

import java.security.SecureRandom;
import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class StandardEncrypter implements Encrypter {

    /* renamed from: a  reason: collision with root package name */
    private final ZipCryptoEngine f1387a = new ZipCryptoEngine();

    /* renamed from: b  reason: collision with root package name */
    private byte[] f1388b;

    public StandardEncrypter(char[] cArr, long j2, boolean z2) {
        f(cArr, j2, z2);
    }

    private void f(char[] cArr, long j2, boolean z2) {
        if (cArr != null && cArr.length > 0) {
            this.f1387a.c(cArr, z2);
            this.f1388b = d();
            this.f1387a.c(cArr, z2);
            byte[] bArr = this.f1388b;
            bArr[11] = (byte) (j2 >>> 24);
            bArr[10] = (byte) (j2 >>> 16);
            c(bArr);
            return;
        }
        throw new ZipException("input password is null or empty, cannot initialize standard encrypter");
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int a(byte[] bArr, int i2, int i3) {
        if (i3 >= 0) {
            for (int i4 = i2; i4 < i2 + i3; i4++) {
                bArr[i4] = b(bArr[i4]);
            }
            return i3;
        }
        throw new ZipException("invalid length specified to decrpyt data");
    }

    protected byte b(byte b2) {
        byte b3 = (byte) ((this.f1387a.b() & 255) ^ b2);
        this.f1387a.d(b2);
        return b3;
    }

    public int c(byte[] bArr) {
        bArr.getClass();
        return a(bArr, 0, bArr.length);
    }

    protected byte[] d() {
        byte[] bArr = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < 12; i2++) {
            bArr[i2] = b((byte) secureRandom.nextInt(256));
        }
        return bArr;
    }

    public byte[] e() {
        return this.f1388b;
    }
}
