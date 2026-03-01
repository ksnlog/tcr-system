package net.lingala.zip4j.model;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class DataDescriptor extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private long f1545b;

    /* renamed from: c  reason: collision with root package name */
    private long f1546c;

    /* renamed from: d  reason: collision with root package name */
    private long f1547d;

    public long c() {
        return this.f1546c;
    }

    public long d() {
        return this.f1545b;
    }

    public long e() {
        return this.f1547d;
    }

    public void f(long j2) {
        this.f1546c = j2;
    }

    public void g(long j2) {
        this.f1545b = j2;
    }

    public void h(long j2) {
        this.f1547d = j2;
    }
}
