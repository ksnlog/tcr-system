package net.lingala.zip4j.io.outputstream;

import java.io.OutputStream;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.model.ZipParameters;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class NoCipherOutputStream extends CipherOutputStream<NoEncrypter> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class NoEncrypter implements Encrypter {
        NoEncrypter() {
        }

        @Override // net.lingala.zip4j.crypto.Encrypter
        public int a(byte[] bArr, int i2, int i3) {
            return i3;
        }
    }

    public NoCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr) {
        super(zipEntryOutputStream, zipParameters, cArr, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    /* renamed from: n */
    public NoEncrypter k(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z2) {
        return new NoEncrypter();
    }
}
