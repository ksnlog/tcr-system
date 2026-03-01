package net.lingala.zip4j.model;

import java.nio.charset.Charset;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class Zip4jConfig {

    /* renamed from: a  reason: collision with root package name */
    private final Charset f1570a;

    /* renamed from: b  reason: collision with root package name */
    private final int f1571b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f1572c;

    public Zip4jConfig(Charset charset, int i2, boolean z2) {
        this.f1570a = charset;
        this.f1571b = i2;
        this.f1572c = z2;
    }

    public int a() {
        return this.f1571b;
    }

    public Charset b() {
        return this.f1570a;
    }

    public boolean c() {
        return this.f1572c;
    }
}
