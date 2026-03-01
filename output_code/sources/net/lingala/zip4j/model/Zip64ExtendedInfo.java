package net.lingala.zip4j.model;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Zip64ExtendedInfo extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private long f1586b = -1;

    /* renamed from: c  reason: collision with root package name */
    private long f1587c = -1;

    /* renamed from: d  reason: collision with root package name */
    private long f1588d = -1;

    /* renamed from: e  reason: collision with root package name */
    private int f1589e = -1;

    public long c() {
        return this.f1586b;
    }

    public int d() {
        return this.f1589e;
    }

    public long e() {
        return this.f1588d;
    }

    public long f() {
        return this.f1587c;
    }

    public void g(long j2) {
        this.f1586b = j2;
    }

    public void h(int i2) {
        this.f1589e = i2;
    }

    public void i(long j2) {
        this.f1588d = j2;
    }

    public void j(long j2) {
        this.f1587c = j2;
    }
}
