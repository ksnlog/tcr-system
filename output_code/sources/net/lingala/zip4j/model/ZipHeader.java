package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ZipHeader {

    /* renamed from: a  reason: collision with root package name */
    private HeaderSignature f1590a;

    public HeaderSignature a() {
        return this.f1590a;
    }

    public void b(HeaderSignature headerSignature) {
        this.f1590a = headerSignature;
    }
}
