package net.lingala.zip4j.model;

import java.util.List;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractFileHeader extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private int f1525b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f1526c;

    /* renamed from: d  reason: collision with root package name */
    private CompressionMethod f1527d;

    /* renamed from: e  reason: collision with root package name */
    private long f1528e;

    /* renamed from: i  reason: collision with root package name */
    private int f1532i;

    /* renamed from: j  reason: collision with root package name */
    private int f1533j;

    /* renamed from: k  reason: collision with root package name */
    private String f1534k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f1535l;

    /* renamed from: n  reason: collision with root package name */
    private boolean f1537n;

    /* renamed from: o  reason: collision with root package name */
    private Zip64ExtendedInfo f1538o;

    /* renamed from: p  reason: collision with root package name */
    private AESExtraDataRecord f1539p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1540q;

    /* renamed from: r  reason: collision with root package name */
    private List<ExtraDataRecord> f1541r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f1542s;

    /* renamed from: f  reason: collision with root package name */
    private long f1529f = 0;

    /* renamed from: g  reason: collision with root package name */
    private long f1530g = 0;

    /* renamed from: h  reason: collision with root package name */
    private long f1531h = 0;

    /* renamed from: m  reason: collision with root package name */
    private EncryptionMethod f1536m = EncryptionMethod.NONE;

    public void A(boolean z2) {
        this.f1535l = z2;
    }

    public void B(EncryptionMethod encryptionMethod) {
        this.f1536m = encryptionMethod;
    }

    public void C(List<ExtraDataRecord> list) {
        this.f1541r = list;
    }

    public void D(int i2) {
        this.f1533j = i2;
    }

    public void E(String str) {
        this.f1534k = str;
    }

    public void F(int i2) {
        this.f1532i = i2;
    }

    public void G(boolean z2) {
        this.f1540q = z2;
    }

    public void H(byte[] bArr) {
        this.f1526c = bArr;
    }

    public void I(long j2) {
        this.f1528e = j2;
    }

    public void J(long j2) {
        this.f1531h = j2;
    }

    public void K(int i2) {
        this.f1525b = i2;
    }

    public void L(Zip64ExtendedInfo zip64ExtendedInfo) {
        this.f1538o = zip64ExtendedInfo;
    }

    public AESExtraDataRecord c() {
        return this.f1539p;
    }

    public long d() {
        return this.f1530g;
    }

    public CompressionMethod e() {
        return this.f1527d;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AbstractFileHeader)) {
            return false;
        }
        return j().equals(((AbstractFileHeader) obj).j());
    }

    public long f() {
        return this.f1529f;
    }

    public EncryptionMethod g() {
        return this.f1536m;
    }

    public List<ExtraDataRecord> h() {
        return this.f1541r;
    }

    public int i() {
        return this.f1533j;
    }

    public String j() {
        return this.f1534k;
    }

    public int k() {
        return this.f1532i;
    }

    public byte[] l() {
        return this.f1526c;
    }

    public long m() {
        return this.f1528e;
    }

    public long n() {
        return this.f1531h;
    }

    public int o() {
        return this.f1525b;
    }

    public Zip64ExtendedInfo p() {
        return this.f1538o;
    }

    public boolean q() {
        return this.f1537n;
    }

    public boolean r() {
        return this.f1542s;
    }

    public boolean s() {
        return this.f1535l;
    }

    public boolean t() {
        return this.f1540q;
    }

    public void u(AESExtraDataRecord aESExtraDataRecord) {
        this.f1539p = aESExtraDataRecord;
    }

    public void v(long j2) {
        this.f1530g = j2;
    }

    public void w(CompressionMethod compressionMethod) {
        this.f1527d = compressionMethod;
    }

    public void x(long j2) {
        this.f1529f = j2;
    }

    public void y(boolean z2) {
        this.f1537n = z2;
    }

    public void z(boolean z2) {
        this.f1542s = z2;
    }
}
