package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class EndOfCentralDirectoryRecord extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private int f1550b;

    /* renamed from: c  reason: collision with root package name */
    private int f1551c;

    /* renamed from: d  reason: collision with root package name */
    private int f1552d;

    /* renamed from: e  reason: collision with root package name */
    private int f1553e;

    /* renamed from: f  reason: collision with root package name */
    private int f1554f;

    /* renamed from: g  reason: collision with root package name */
    private long f1555g;

    /* renamed from: h  reason: collision with root package name */
    private long f1556h;

    /* renamed from: i  reason: collision with root package name */
    private String f1557i = "";

    public EndOfCentralDirectoryRecord() {
        b(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
    }

    public String c() {
        return this.f1557i;
    }

    public int d() {
        return this.f1550b;
    }

    public int e() {
        return this.f1551c;
    }

    public long f() {
        return this.f1556h;
    }

    public long g() {
        return this.f1555g;
    }

    public int h() {
        return this.f1553e;
    }

    public int i() {
        return this.f1552d;
    }

    public void j(String str) {
        if (str != null) {
            this.f1557i = str;
        }
    }

    public void k(int i2) {
        this.f1550b = i2;
    }

    public void l(int i2) {
        this.f1551c = i2;
    }

    public void m(long j2) {
        this.f1556h = j2;
    }

    public void n(long j2) {
        this.f1555g = j2;
    }

    public void o(int i2) {
        this.f1554f = i2;
    }

    public void p(int i2) {
        this.f1553e = i2;
    }

    public void q(int i2) {
        this.f1552d = i2;
    }
}
