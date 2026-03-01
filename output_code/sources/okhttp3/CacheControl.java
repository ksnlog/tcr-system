package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CacheControl {

    /* renamed from: n  reason: collision with root package name */
    public static final CacheControl f1818n = new Builder().c().a();

    /* renamed from: o  reason: collision with root package name */
    public static final CacheControl f1819o = new Builder().d().b(Integer.MAX_VALUE, TimeUnit.SECONDS).a();

    /* renamed from: a  reason: collision with root package name */
    private final boolean f1820a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f1821b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1822c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1823d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f1824e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f1825f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f1826g;

    /* renamed from: h  reason: collision with root package name */
    private final int f1827h;

    /* renamed from: i  reason: collision with root package name */
    private final int f1828i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f1829j;

    /* renamed from: k  reason: collision with root package name */
    private final boolean f1830k;

    /* renamed from: l  reason: collision with root package name */
    private final boolean f1831l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    String f1832m;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        boolean f1833a;

        /* renamed from: b  reason: collision with root package name */
        boolean f1834b;

        /* renamed from: c  reason: collision with root package name */
        int f1835c = -1;

        /* renamed from: d  reason: collision with root package name */
        int f1836d = -1;

        /* renamed from: e  reason: collision with root package name */
        int f1837e = -1;

        /* renamed from: f  reason: collision with root package name */
        boolean f1838f;

        /* renamed from: g  reason: collision with root package name */
        boolean f1839g;

        /* renamed from: h  reason: collision with root package name */
        boolean f1840h;

        public CacheControl a() {
            return new CacheControl(this);
        }

        public Builder b(int i2, TimeUnit timeUnit) {
            int i3;
            if (i2 >= 0) {
                long seconds = timeUnit.toSeconds(i2);
                if (seconds > 2147483647L) {
                    i3 = Integer.MAX_VALUE;
                } else {
                    i3 = (int) seconds;
                }
                this.f1836d = i3;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i2);
        }

        public Builder c() {
            this.f1833a = true;
            return this;
        }

        public Builder d() {
            this.f1838f = true;
            return this;
        }
    }

    private CacheControl(boolean z2, boolean z3, int i2, int i3, boolean z4, boolean z5, boolean z6, int i4, int i5, boolean z7, boolean z8, boolean z9, @Nullable String str) {
        this.f1820a = z2;
        this.f1821b = z3;
        this.f1822c = i2;
        this.f1823d = i3;
        this.f1824e = z4;
        this.f1825f = z5;
        this.f1826g = z6;
        this.f1827h = i4;
        this.f1828i = i5;
        this.f1829j = z7;
        this.f1830k = z8;
        this.f1831l = z9;
        this.f1832m = str;
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f1820a) {
            sb.append("no-cache, ");
        }
        if (this.f1821b) {
            sb.append("no-store, ");
        }
        if (this.f1822c != -1) {
            sb.append("max-age=");
            sb.append(this.f1822c);
            sb.append(", ");
        }
        if (this.f1823d != -1) {
            sb.append("s-maxage=");
            sb.append(this.f1823d);
            sb.append(", ");
        }
        if (this.f1824e) {
            sb.append("private, ");
        }
        if (this.f1825f) {
            sb.append("public, ");
        }
        if (this.f1826g) {
            sb.append("must-revalidate, ");
        }
        if (this.f1827h != -1) {
            sb.append("max-stale=");
            sb.append(this.f1827h);
            sb.append(", ");
        }
        if (this.f1828i != -1) {
            sb.append("min-fresh=");
            sb.append(this.f1828i);
            sb.append(", ");
        }
        if (this.f1829j) {
            sb.append("only-if-cached, ");
        }
        if (this.f1830k) {
            sb.append("no-transform, ");
        }
        if (this.f1831l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okhttp3.CacheControl k(okhttp3.Headers r22) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.k(okhttp3.Headers):okhttp3.CacheControl");
    }

    public boolean b() {
        return this.f1824e;
    }

    public boolean c() {
        return this.f1825f;
    }

    public int d() {
        return this.f1822c;
    }

    public int e() {
        return this.f1827h;
    }

    public int f() {
        return this.f1828i;
    }

    public boolean g() {
        return this.f1826g;
    }

    public boolean h() {
        return this.f1820a;
    }

    public boolean i() {
        return this.f1821b;
    }

    public boolean j() {
        return this.f1829j;
    }

    public String toString() {
        String str = this.f1832m;
        if (str == null) {
            String a2 = a();
            this.f1832m = a2;
            return a2;
        }
        return str;
    }

    CacheControl(Builder builder) {
        this.f1820a = builder.f1833a;
        this.f1821b = builder.f1834b;
        this.f1822c = builder.f1835c;
        this.f1823d = -1;
        this.f1824e = false;
        this.f1825f = false;
        this.f1826g = false;
        this.f1827h = builder.f1836d;
        this.f1828i = builder.f1837e;
        this.f1829j = builder.f1838f;
        this.f1830k = builder.f1839g;
        this.f1831l = builder.f1840h;
    }
}
