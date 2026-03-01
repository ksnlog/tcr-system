package net.lingala.zip4j.crypto.PBKDF2;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PBKDF2Parameters {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f1381a;

    /* renamed from: b  reason: collision with root package name */
    protected int f1382b;

    /* renamed from: c  reason: collision with root package name */
    protected String f1383c;

    /* renamed from: d  reason: collision with root package name */
    protected String f1384d;

    /* renamed from: e  reason: collision with root package name */
    protected byte[] f1385e;

    public PBKDF2Parameters(String str, String str2, byte[] bArr, int i2) {
        this(str, str2, bArr, i2, null);
    }

    public String a() {
        return this.f1383c;
    }

    public int b() {
        return this.f1382b;
    }

    public byte[] c() {
        return this.f1381a;
    }

    public PBKDF2Parameters(String str, String str2, byte[] bArr, int i2, byte[] bArr2) {
        this.f1383c = str;
        this.f1384d = str2;
        this.f1381a = bArr;
        this.f1382b = i2;
        this.f1385e = bArr2;
    }
}
