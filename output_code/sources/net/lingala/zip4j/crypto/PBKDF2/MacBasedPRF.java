package net.lingala.zip4j.crypto.PBKDF2;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class MacBasedPRF implements PRF {

    /* renamed from: a  reason: collision with root package name */
    private Mac f1375a;

    /* renamed from: b  reason: collision with root package name */
    private int f1376b;

    /* renamed from: c  reason: collision with root package name */
    private String f1377c;

    /* renamed from: d  reason: collision with root package name */
    private ByteArrayOutputStream f1378d = new ByteArrayOutputStream(4096);

    public MacBasedPRF(String str) {
        this.f1377c = str;
        try {
            Mac mac = Mac.getInstance(str);
            this.f1375a = mac;
            this.f1376b = mac.getMacLength();
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void f(int i2) {
        int i3;
        byte[] byteArray = this.f1378d.toByteArray();
        int length = byteArray.length - i2;
        int i4 = 0;
        while (i4 < length) {
            int i5 = i4 + 16;
            if (i5 <= length) {
                i3 = 16;
            } else {
                i3 = length - i4;
            }
            this.f1375a.update(byteArray, i4, i3);
            i4 = i5;
        }
        this.f1378d.reset();
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public byte[] a(byte[] bArr) {
        if (this.f1378d.size() > 0) {
            f(0);
        }
        return this.f1375a.doFinal(bArr);
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public int b() {
        return this.f1376b;
    }

    @Override // net.lingala.zip4j.crypto.PBKDF2.PRF
    public void c(byte[] bArr) {
        try {
            this.f1375a.init(new SecretKeySpec(bArr, this.f1377c));
        } catch (InvalidKeyException e2) {
            throw new RuntimeException(e2);
        }
    }

    public byte[] d() {
        return e(0);
    }

    public byte[] e(int i2) {
        if (this.f1378d.size() > 0) {
            f(i2);
        }
        return this.f1375a.doFinal();
    }

    public void g(byte[] bArr, int i2, int i3) {
        try {
            if (this.f1378d.size() + i3 > 4096) {
                f(0);
            }
            this.f1378d.write(bArr, i2, i3);
        } catch (IllegalStateException e2) {
            throw new RuntimeException(e2);
        }
    }
}
