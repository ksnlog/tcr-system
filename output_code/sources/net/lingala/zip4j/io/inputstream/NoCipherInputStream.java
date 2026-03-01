package net.lingala.zip4j.io.inputstream;

import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.model.LocalFileHeader;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class NoCipherInputStream extends CipherInputStream<NoDecrypter> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class NoDecrypter implements Decrypter {
        NoDecrypter() {
        }

        @Override // net.lingala.zip4j.crypto.Decrypter
        public int a(byte[] bArr, int i2, int i3) {
            return i3;
        }
    }

    public NoCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i2) {
        super(zipEntryInputStream, localFileHeader, cArr, i2, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: o */
    public NoDecrypter m(LocalFileHeader localFileHeader, char[] cArr, boolean z2) {
        return new NoDecrypter();
    }
}
