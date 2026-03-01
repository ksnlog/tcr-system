package net.lingala.zip4j.model;

import java.util.Arrays;
import net.lingala.zip4j.headers.HeaderSignature;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FileHeader extends AbstractFileHeader {

    /* renamed from: t  reason: collision with root package name */
    private int f1561t;

    /* renamed from: u  reason: collision with root package name */
    private int f1562u = 0;

    /* renamed from: v  reason: collision with root package name */
    private int f1563v;

    /* renamed from: w  reason: collision with root package name */
    private byte[] f1564w;

    /* renamed from: x  reason: collision with root package name */
    private byte[] f1565x;

    /* renamed from: y  reason: collision with root package name */
    private long f1566y;

    /* renamed from: z  reason: collision with root package name */
    private String f1567z;

    public FileHeader() {
        b(HeaderSignature.CENTRAL_DIRECTORY);
    }

    private long M(FileHeader fileHeader) {
        if (fileHeader.p() != null) {
            return fileHeader.p().e();
        }
        return fileHeader.Q();
    }

    public int N() {
        return this.f1563v;
    }

    public byte[] O() {
        return this.f1565x;
    }

    public String P() {
        return this.f1567z;
    }

    public long Q() {
        return this.f1566y;
    }

    public int R() {
        return this.f1561t;
    }

    public void S(int i2) {
        this.f1563v = i2;
    }

    public void T(byte[] bArr) {
        this.f1565x = bArr;
    }

    public void U(String str) {
        this.f1567z = str;
    }

    public void V(int i2) {
        this.f1562u = i2;
    }

    public void W(byte[] bArr) {
        this.f1564w = bArr;
    }

    public void X(long j2) {
        this.f1566y = j2;
    }

    public void Y(int i2) {
        this.f1561t = i2;
    }

    @Override // net.lingala.zip4j.model.AbstractFileHeader
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj) && M(this) == M((FileHeader) obj)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{j(), Long.valueOf(M(this))});
    }

    public String toString() {
        return j();
    }
}
