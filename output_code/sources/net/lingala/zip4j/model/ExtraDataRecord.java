package net.lingala.zip4j.model;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ExtraDataRecord extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private long f1558b;

    /* renamed from: c  reason: collision with root package name */
    private int f1559c;

    /* renamed from: d  reason: collision with root package name */
    private byte[] f1560d;

    public byte[] c() {
        return this.f1560d;
    }

    public long d() {
        return this.f1558b;
    }

    public int e() {
        return this.f1559c;
    }

    public void f(byte[] bArr) {
        this.f1560d = bArr;
    }

    public void g(long j2) {
        this.f1558b = j2;
    }

    public void h(int i2) {
        this.f1559c = i2;
    }
}
