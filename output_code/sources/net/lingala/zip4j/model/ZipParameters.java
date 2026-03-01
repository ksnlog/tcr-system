package net.lingala.zip4j.model;

import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipParameters {

    /* renamed from: a  reason: collision with root package name */
    private CompressionMethod f1602a;

    /* renamed from: b  reason: collision with root package name */
    private CompressionLevel f1603b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1604c;

    /* renamed from: d  reason: collision with root package name */
    private EncryptionMethod f1605d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1606e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1607f;

    /* renamed from: g  reason: collision with root package name */
    private AesKeyStrength f1608g;

    /* renamed from: h  reason: collision with root package name */
    private AesVersion f1609h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f1610i;

    /* renamed from: j  reason: collision with root package name */
    private long f1611j;

    /* renamed from: k  reason: collision with root package name */
    private String f1612k;

    /* renamed from: l  reason: collision with root package name */
    private String f1613l;

    /* renamed from: m  reason: collision with root package name */
    private long f1614m;

    /* renamed from: n  reason: collision with root package name */
    private long f1615n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f1616o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f1617p;

    /* renamed from: q  reason: collision with root package name */
    private String f1618q;

    /* renamed from: r  reason: collision with root package name */
    private String f1619r;

    /* renamed from: s  reason: collision with root package name */
    private SymbolicLinkAction f1620s;

    /* renamed from: t  reason: collision with root package name */
    private boolean f1621t;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum SymbolicLinkAction {
        INCLUDE_LINK_ONLY,
        INCLUDE_LINKED_FILE_ONLY,
        INCLUDE_LINK_AND_LINKED_FILE
    }

    public ZipParameters() {
        this.f1602a = CompressionMethod.DEFLATE;
        this.f1603b = CompressionLevel.NORMAL;
        this.f1604c = false;
        this.f1605d = EncryptionMethod.NONE;
        this.f1606e = true;
        this.f1607f = true;
        this.f1608g = AesKeyStrength.KEY_STRENGTH_256;
        this.f1609h = AesVersion.TWO;
        this.f1610i = true;
        this.f1614m = 0L;
        this.f1615n = -1L;
        this.f1616o = true;
        this.f1617p = true;
        this.f1620s = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
    }

    public void A(long j2) {
        this.f1611j = j2;
    }

    public void B(long j2) {
        this.f1615n = j2;
    }

    public void C(String str) {
        this.f1613l = str;
    }

    public void D(boolean z2) {
        this.f1610i = z2;
    }

    public void E(long j2) {
        if (j2 < 0) {
            this.f1614m = 0L;
        } else {
            this.f1614m = j2;
        }
    }

    public void F(boolean z2) {
        this.f1616o = z2;
    }

    public AesKeyStrength a() {
        return this.f1608g;
    }

    public AesVersion b() {
        return this.f1609h;
    }

    public CompressionLevel c() {
        return this.f1603b;
    }

    public CompressionMethod d() {
        return this.f1602a;
    }

    public String e() {
        return this.f1612k;
    }

    public EncryptionMethod f() {
        return this.f1605d;
    }

    public long g() {
        return this.f1611j;
    }

    public long h() {
        return this.f1615n;
    }

    public ExcludeFileFilter i() {
        return null;
    }

    public String j() {
        return this.f1619r;
    }

    public String k() {
        return this.f1613l;
    }

    public long l() {
        return this.f1614m;
    }

    public String m() {
        return this.f1618q;
    }

    public SymbolicLinkAction n() {
        return this.f1620s;
    }

    public boolean o() {
        return this.f1604c;
    }

    public boolean p() {
        return this.f1610i;
    }

    public boolean q() {
        return this.f1617p;
    }

    public boolean r() {
        return this.f1606e;
    }

    public boolean s() {
        return this.f1607f;
    }

    public boolean t() {
        return this.f1621t;
    }

    public boolean u() {
        return this.f1616o;
    }

    public void v(CompressionLevel compressionLevel) {
        this.f1603b = compressionLevel;
    }

    public void w(CompressionMethod compressionMethod) {
        this.f1602a = compressionMethod;
    }

    public void x(String str) {
        this.f1612k = str;
    }

    public void y(boolean z2) {
        this.f1604c = z2;
    }

    public void z(EncryptionMethod encryptionMethod) {
        this.f1605d = encryptionMethod;
    }

    public ZipParameters(ZipParameters zipParameters) {
        this.f1602a = CompressionMethod.DEFLATE;
        this.f1603b = CompressionLevel.NORMAL;
        this.f1604c = false;
        this.f1605d = EncryptionMethod.NONE;
        this.f1606e = true;
        this.f1607f = true;
        this.f1608g = AesKeyStrength.KEY_STRENGTH_256;
        this.f1609h = AesVersion.TWO;
        this.f1610i = true;
        this.f1614m = 0L;
        this.f1615n = -1L;
        this.f1616o = true;
        this.f1617p = true;
        this.f1620s = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
        this.f1602a = zipParameters.d();
        this.f1603b = zipParameters.c();
        this.f1604c = zipParameters.o();
        this.f1605d = zipParameters.f();
        this.f1606e = zipParameters.r();
        this.f1607f = zipParameters.s();
        this.f1608g = zipParameters.a();
        this.f1609h = zipParameters.b();
        this.f1610i = zipParameters.p();
        this.f1611j = zipParameters.g();
        this.f1612k = zipParameters.e();
        this.f1613l = zipParameters.k();
        this.f1614m = zipParameters.l();
        this.f1615n = zipParameters.h();
        this.f1616o = zipParameters.u();
        this.f1617p = zipParameters.q();
        this.f1618q = zipParameters.m();
        this.f1619r = zipParameters.j();
        this.f1620s = zipParameters.n();
        zipParameters.i();
        this.f1621t = zipParameters.t();
    }
}
