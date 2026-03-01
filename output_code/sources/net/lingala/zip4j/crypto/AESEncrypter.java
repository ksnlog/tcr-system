package net.lingala.zip4j.crypto;

import java.security.SecureRandom;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AESEncrypter implements Encrypter {

    /* renamed from: a  reason: collision with root package name */
    private AESEngine f1365a;

    /* renamed from: b  reason: collision with root package name */
    private MacBasedPRF f1366b;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1368d;

    /* renamed from: g  reason: collision with root package name */
    private final byte[] f1371g;

    /* renamed from: h  reason: collision with root package name */
    private final byte[] f1372h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f1373i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f1374j;

    /* renamed from: c  reason: collision with root package name */
    private final SecureRandom f1367c = new SecureRandom();

    /* renamed from: e  reason: collision with root package name */
    private int f1369e = 1;

    /* renamed from: f  reason: collision with root package name */
    private int f1370f = 0;

    public AESEncrypter(char[] cArr, AesKeyStrength aesKeyStrength, boolean z2) {
        if (cArr != null && cArr.length != 0) {
            if (aesKeyStrength != AesKeyStrength.KEY_STRENGTH_128 && aesKeyStrength != AesKeyStrength.KEY_STRENGTH_256) {
                throw new ZipException("Invalid AES key strength");
            }
            this.f1368d = false;
            this.f1372h = new byte[16];
            this.f1371g = new byte[16];
            f(cArr, aesKeyStrength, z2);
            return;
        }
        throw new ZipException("input password is empty or null");
    }

    private byte[] b(int i2) {
        int i3;
        if (i2 != 8 && i2 != 16) {
            throw new ZipException("invalid salt size, cannot generate salt");
        }
        if (i2 == 8) {
            i3 = 2;
        } else {
            i3 = 4;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i3; i4++) {
            int nextInt = this.f1367c.nextInt();
            int i5 = i4 * 4;
            bArr[i5] = (byte) (nextInt >> 24);
            bArr[i5 + 1] = (byte) (nextInt >> 16);
            bArr[i5 + 2] = (byte) (nextInt >> 8);
            bArr[i5 + 3] = (byte) nextInt;
        }
        return bArr;
    }

    private void f(char[] cArr, AesKeyStrength aesKeyStrength, boolean z2) {
        byte[] b2 = b(aesKeyStrength.e());
        this.f1374j = b2;
        byte[] a2 = AesCipherUtil.a(b2, cArr, aesKeyStrength, z2);
        this.f1373i = AesCipherUtil.b(a2, aesKeyStrength);
        this.f1365a = AesCipherUtil.c(a2, aesKeyStrength);
        this.f1366b = AesCipherUtil.d(a2, aesKeyStrength);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int a(byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        if (!this.f1368d) {
            if (i3 % 16 != 0) {
                this.f1368d = true;
            }
            int i6 = i2;
            while (true) {
                int i7 = i2 + i3;
                if (i6 < i7) {
                    int i8 = i6 + 16;
                    if (i8 <= i7) {
                        i4 = 16;
                    } else {
                        i4 = i7 - i6;
                    }
                    this.f1370f = i4;
                    AesCipherUtil.e(this.f1371g, this.f1369e);
                    this.f1365a.e(this.f1371g, this.f1372h);
                    int i9 = 0;
                    while (true) {
                        i5 = this.f1370f;
                        if (i9 < i5) {
                            int i10 = i6 + i9;
                            bArr[i10] = (byte) (bArr[i10] ^ this.f1372h[i9]);
                            i9++;
                        }
                    }
                    this.f1366b.g(bArr, i6, i5);
                    this.f1369e++;
                    i6 = i8;
                } else {
                    return i3;
                }
            }
        } else {
            throw new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
    }

    public byte[] c() {
        return this.f1373i;
    }

    public byte[] d() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.f1366b.d(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] e() {
        return this.f1374j;
    }
}
