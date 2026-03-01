package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LocalFileHeader extends AbstractFileHeader {

    /* renamed from: t  reason: collision with root package name */
    private boolean f1568t;

    public LocalFileHeader() {
        b(HeaderSignature.LOCAL_FILE_HEADER);
    }

    public boolean M() {
        return this.f1568t;
    }

    public void N(boolean z2) {
        this.f1568t = z2;
    }
}
