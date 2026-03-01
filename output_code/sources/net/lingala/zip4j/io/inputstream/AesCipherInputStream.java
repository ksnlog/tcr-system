package net.lingala.zip4j.io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class AesCipherInputStream extends CipherInputStream<AESDecrypter> {

    /* renamed from: i  reason: collision with root package name */
    private byte[] f1440i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f1441j;

    /* renamed from: k  reason: collision with root package name */
    private int f1442k;

    /* renamed from: l  reason: collision with root package name */
    private int f1443l;

    /* renamed from: m  reason: collision with root package name */
    private int f1444m;

    /* renamed from: n  reason: collision with root package name */
    private int f1445n;

    /* renamed from: o  reason: collision with root package name */
    private int f1446o;

    /* renamed from: p  reason: collision with root package name */
    private int f1447p;

    /* renamed from: q  reason: collision with root package name */
    private int f1448q;

    public AesCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i2, boolean z2) {
        super(zipEntryInputStream, localFileHeader, cArr, i2, z2);
        this.f1440i = new byte[1];
        this.f1441j = new byte[16];
        this.f1442k = 0;
        this.f1443l = 0;
        this.f1444m = 0;
        this.f1445n = 0;
        this.f1446o = 0;
        this.f1447p = 0;
        this.f1448q = 0;
    }

    private void D(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[10];
        System.arraycopy(e().b(i2), 0, bArr2, 0, 10);
        if (Arrays.equals(bArr, bArr2)) {
            return;
        }
        throw new IOException("Reached end of data for this entry, but aes verification failed");
    }

    private void o(byte[] bArr, int i2) {
        int i3 = this.f1444m;
        int i4 = this.f1443l;
        if (i3 >= i4) {
            i3 = i4;
        }
        this.f1447p = i3;
        System.arraycopy(this.f1441j, this.f1442k, bArr, i2, i3);
        x(this.f1447p);
        p(this.f1447p);
        int i5 = this.f1446o;
        int i6 = this.f1447p;
        this.f1446o = i5 + i6;
        this.f1444m -= i6;
        this.f1445n += i6;
    }

    private void p(int i2) {
        int i3 = this.f1443l - i2;
        this.f1443l = i3;
        if (i3 <= 0) {
            this.f1443l = 0;
        }
    }

    private byte[] r() {
        byte[] bArr = new byte[2];
        n(bArr);
        return bArr;
    }

    private byte[] w(LocalFileHeader localFileHeader) {
        if (localFileHeader.c() != null) {
            AESExtraDataRecord c2 = localFileHeader.c();
            if (c2.c() != null) {
                byte[] bArr = new byte[c2.c().e()];
                n(bArr);
                return bArr;
            }
            throw new IOException("Invalid aes key strength in aes extra data record");
        }
        throw new IOException("invalid aes extra data record");
    }

    private void x(int i2) {
        int i3 = this.f1442k + i2;
        this.f1442k = i3;
        if (i3 >= 15) {
            this.f1442k = 15;
        }
    }

    protected byte[] B(InputStream inputStream) {
        byte[] bArr = new byte[10];
        if (Zip4jUtil.l(inputStream, bArr) == 10) {
            return bArr;
        }
        throw new ZipException("Invalid AES Mac bytes. Could not read sufficient data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    public void c(InputStream inputStream, int i2) {
        D(B(inputStream), i2);
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read() {
        if (read(this.f1440i) == -1) {
            return -1;
        }
        return this.f1440i[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: z */
    public AESDecrypter m(LocalFileHeader localFileHeader, char[] cArr, boolean z2) {
        return new AESDecrypter(localFileHeader.c(), cArr, w(localFileHeader), r(), z2);
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        this.f1444m = i3;
        this.f1445n = i2;
        this.f1446o = 0;
        if (this.f1443l != 0) {
            o(bArr, i2);
            int i4 = this.f1446o;
            if (i4 == i3) {
                return i4;
            }
        }
        if (this.f1444m < 16) {
            byte[] bArr2 = this.f1441j;
            int read = super.read(bArr2, 0, bArr2.length);
            this.f1448q = read;
            this.f1442k = 0;
            if (read == -1) {
                this.f1443l = 0;
                int i5 = this.f1446o;
                if (i5 <= 0) {
                    return -1;
                }
                return i5;
            }
            this.f1443l = read;
            o(bArr, this.f1445n);
            int i6 = this.f1446o;
            if (i6 == i3) {
                return i6;
            }
        }
        int i7 = this.f1445n;
        int i8 = this.f1444m;
        int read2 = super.read(bArr, i7, i8 - (i8 % 16));
        if (read2 == -1) {
            int i9 = this.f1446o;
            if (i9 <= 0) {
                return -1;
            }
            return i9;
        }
        return read2 + this.f1446o;
    }
}
