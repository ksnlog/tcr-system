package net.lingala.zip4j.crypto.PBKDF2;

import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PBKDF2Engine {

    /* renamed from: a  reason: collision with root package name */
    private PBKDF2Parameters f1379a;

    /* renamed from: b  reason: collision with root package name */
    private PRF f1380b;

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters) {
        this(pBKDF2Parameters, null);
    }

    private byte[] b(PRF prf, byte[] bArr, int i2, int i3) {
        byte[] bArr2;
        if (bArr == null) {
            bArr2 = new byte[0];
        } else {
            bArr2 = bArr;
        }
        int b2 = prf.b();
        int e2 = e(i3, b2);
        int i4 = i3 - ((e2 - 1) * b2);
        byte[] bArr3 = new byte[e2 * b2];
        int i5 = 0;
        for (int i6 = 1; i6 <= e2; i6++) {
            c(bArr3, i5, prf, bArr2, i2, i6);
            i5 += b2;
        }
        if (i4 < b2) {
            byte[] bArr4 = new byte[i3];
            System.arraycopy(bArr3, 0, bArr4, 0, i3);
            return bArr4;
        }
        return bArr3;
    }

    private void c(byte[] bArr, int i2, PRF prf, byte[] bArr2, int i3, int i4) {
        int b2 = prf.b();
        byte[] bArr3 = new byte[b2];
        byte[] bArr4 = new byte[bArr2.length + 4];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        a(bArr4, bArr2.length, i4);
        for (int i5 = 0; i5 < i3; i5++) {
            bArr4 = prf.a(bArr4);
            g(bArr3, bArr4);
        }
        System.arraycopy(bArr3, 0, bArr, i2, b2);
    }

    private void d(byte[] bArr) {
        if (this.f1380b == null) {
            this.f1380b = new MacBasedPRF(this.f1379a.a());
        }
        this.f1380b.c(bArr);
    }

    private int e(int i2, int i3) {
        int i4;
        if (i2 % i3 > 0) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        return (i2 / i3) + i4;
    }

    private void g(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    protected void a(byte[] bArr, int i2, int i3) {
        bArr[i2] = (byte) (i3 / 16777216);
        bArr[i2 + 1] = (byte) (i3 / 65536);
        bArr[i2 + 2] = (byte) (i3 / 256);
        bArr[i2 + 3] = (byte) i3;
    }

    public byte[] f(char[] cArr, int i2, boolean z2) {
        cArr.getClass();
        d(Zip4jUtil.a(cArr, z2));
        if (i2 == 0) {
            i2 = this.f1380b.b();
        }
        return b(this.f1380b, this.f1379a.c(), this.f1379a.b(), i2);
    }

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters, PRF prf) {
        this.f1379a = pBKDF2Parameters;
        this.f1380b = prf;
    }
}
