package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
import net.lingala.zip4j.crypto.StandardEncrypter;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jUtil;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class ZipStandardCipherOutputStream extends CipherOutputStream<StandardEncrypter> {
    public ZipStandardCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        super(zipEntryOutputStream, zipParameters, cArr, z2);
    }

    private long n(ZipParameters zipParameters) {
        if (zipParameters.u()) {
            return (Zip4jUtil.h(zipParameters.l()) & 65535) << 16;
        }
        return zipParameters.g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    /* renamed from: o */
    public StandardEncrypter k(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        StandardEncrypter standardEncrypter = new StandardEncrypter(cArr, n(zipParameters), z2);
        m(standardEncrypter.e());
        return standardEncrypter;
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
        super.write(bArr, i2, i3);
    }
}
