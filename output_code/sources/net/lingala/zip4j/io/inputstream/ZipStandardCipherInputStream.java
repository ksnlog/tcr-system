package net.lingala.zip4j.io.inputstream;

import net.lingala.zip4j.crypto.StandardDecrypter;
import net.lingala.zip4j.model.LocalFileHeader;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class ZipStandardCipherInputStream extends CipherInputStream<StandardDecrypter> {
    public ZipStandardCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i2, boolean z2) {
        super(zipEntryInputStream, localFileHeader, cArr, i2, z2);
    }

    private byte[] o() {
        byte[] bArr = new byte[12];
        n(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: p */
    public StandardDecrypter m(LocalFileHeader localFileHeader, char[] cArr, boolean z2) {
        return new StandardDecrypter(cArr, localFileHeader.f(), localFileHeader.m(), o(), z2);
    }
}
