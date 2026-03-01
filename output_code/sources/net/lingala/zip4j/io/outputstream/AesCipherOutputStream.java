package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
import net.lingala.zip4j.crypto.AESEncrypter;
import net.lingala.zip4j.model.ZipParameters;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class AesCipherOutputStream extends CipherOutputStream<AESEncrypter> {

    /* renamed from: f  reason: collision with root package name */
    private byte[] f1488f;

    /* renamed from: g  reason: collision with root package name */
    private int f1489g;

    public AesCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        super(zipEntryOutputStream, zipParameters, cArr, z2);
        this.f1488f = new byte[16];
        this.f1489g = 0;
    }

    private void o(AESEncrypter aESEncrypter) {
        m(aESEncrypter.e());
        m(aESEncrypter.c());
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    public void b() {
        int i2 = this.f1489g;
        if (i2 != 0) {
            super.write(this.f1488f, 0, i2);
            this.f1489g = 0;
        }
        m(c().d());
        super.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    /* renamed from: n */
    public AESEncrypter k(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        AESEncrypter aESEncrypter = new AESEncrypter(cArr, zipParameters.a(), z2);
        o(aESEncrypter);
        return aESEncrypter;
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2});
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = this.f1489g;
        if (i3 >= 16 - i5) {
            System.arraycopy(bArr, i2, this.f1488f, i5, 16 - i5);
            byte[] bArr2 = this.f1488f;
            super.write(bArr2, 0, bArr2.length);
            int i6 = 16 - this.f1489g;
            int i7 = i3 - i6;
            this.f1489g = 0;
            if (i7 != 0 && (i4 = i7 % 16) != 0) {
                System.arraycopy(bArr, (i7 + i6) - i4, this.f1488f, 0, i4);
                this.f1489g = i4;
                i7 -= i4;
            }
            super.write(bArr, i6, i7);
            return;
        }
        System.arraycopy(bArr, i2, this.f1488f, i5, i3);
        this.f1489g += i3;
    }
}
