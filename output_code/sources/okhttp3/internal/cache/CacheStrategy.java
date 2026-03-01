package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CacheStrategy {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Request f2102a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final Response f2103b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Factory {

        /* renamed from: a  reason: collision with root package name */
        final long f2104a;

        /* renamed from: b  reason: collision with root package name */
        final Request f2105b;

        /* renamed from: c  reason: collision with root package name */
        final Response f2106c;

        /* renamed from: d  reason: collision with root package name */
        private Date f2107d;

        /* renamed from: e  reason: collision with root package name */
        private String f2108e;

        /* renamed from: f  reason: collision with root package name */
        private Date f2109f;

        /* renamed from: g  reason: collision with root package name */
        private String f2110g;

        /* renamed from: h  reason: collision with root package name */
        private Date f2111h;

        /* renamed from: i  reason: collision with root package name */
        private long f2112i;

        /* renamed from: j  reason: collision with root package name */
        private long f2113j;

        /* renamed from: k  reason: collision with root package name */
        private String f2114k;

        /* renamed from: l  reason: collision with root package name */
        private int f2115l;

        public Factory(long j2, Request request, Response response) {
            this.f2115l = -1;
            this.f2104a = j2;
            this.f2105b = request;
            this.f2106c = response;
            if (response != null) {
                this.f2112i = response.z();
                this.f2113j = response.w();
                Headers o2 = response.o();
                int g2 = o2.g();
                for (int i2 = 0; i2 < g2; i2++) {
                    String e2 = o2.e(i2);
                    String h2 = o2.h(i2);
                    if ("Date".equalsIgnoreCase(e2)) {
                        this.f2107d = HttpDate.b(h2);
                        this.f2108e = h2;
                    } else if ("Expires".equalsIgnoreCase(e2)) {
                        this.f2111h = HttpDate.b(h2);
                    } else if ("Last-Modified".equalsIgnoreCase(e2)) {
                        this.f2109f = HttpDate.b(h2);
                        this.f2110g = h2;
                    } else if ("ETag".equalsIgnoreCase(e2)) {
                        this.f2114k = h2;
                    } else if ("Age".equalsIgnoreCase(e2)) {
                        this.f2115l = HttpHeaders.d(h2, -1);
                    }
                }
            }
        }

        private long a() {
            Date date = this.f2107d;
            long j2 = 0;
            if (date != null) {
                j2 = Math.max(0L, this.f2113j - date.getTime());
            }
            int i2 = this.f2115l;
            if (i2 != -1) {
                j2 = Math.max(j2, TimeUnit.SECONDS.toMillis(i2));
            }
            long j3 = this.f2113j;
            return j2 + (j3 - this.f2112i) + (this.f2104a - j3);
        }

        private long b() {
            long j2;
            long j3;
            CacheControl c2 = this.f2106c.c();
            if (c2.d() != -1) {
                return TimeUnit.SECONDS.toMillis(c2.d());
            }
            if (this.f2111h != null) {
                Date date = this.f2107d;
                if (date != null) {
                    j3 = date.getTime();
                } else {
                    j3 = this.f2113j;
                }
                long time = this.f2111h.getTime() - j3;
                if (time <= 0) {
                    return 0L;
                }
                return time;
            } else if (this.f2109f == null || this.f2106c.x().h().x() != null) {
                return 0L;
            } else {
                Date date2 = this.f2107d;
                if (date2 != null) {
                    j2 = date2.getTime();
                } else {
                    j2 = this.f2112i;
                }
                long time2 = j2 - this.f2109f.getTime();
                if (time2 <= 0) {
                    return 0L;
                }
                return time2 / 10;
            }
        }

        private CacheStrategy d() {
            long j2;
            String str;
            if (this.f2106c == null) {
                return new CacheStrategy(this.f2105b, null);
            }
            if (this.f2105b.e() && this.f2106c.k() == null) {
                return new CacheStrategy(this.f2105b, null);
            }
            if (!CacheStrategy.a(this.f2106c, this.f2105b)) {
                return new CacheStrategy(this.f2105b, null);
            }
            CacheControl b2 = this.f2105b.b();
            if (!b2.h() && !e(this.f2105b)) {
                CacheControl c2 = this.f2106c.c();
                long a2 = a();
                long b3 = b();
                if (b2.d() != -1) {
                    b3 = Math.min(b3, TimeUnit.SECONDS.toMillis(b2.d()));
                }
                long j3 = 0;
                if (b2.f() != -1) {
                    j2 = TimeUnit.SECONDS.toMillis(b2.f());
                } else {
                    j2 = 0;
                }
                if (!c2.g() && b2.e() != -1) {
                    j3 = TimeUnit.SECONDS.toMillis(b2.e());
                }
                if (!c2.h()) {
                    long j4 = j2 + a2;
                    if (j4 < j3 + b3) {
                        Response.Builder p2 = this.f2106c.p();
                        if (j4 >= b3) {
                            p2.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                        }
                        if (a2 > 86400000 && f()) {
                            p2.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                        }
                        return new CacheStrategy(null, p2.c());
                    }
                }
                String str2 = this.f2114k;
                if (str2 != null) {
                    str = "If-None-Match";
                } else {
                    if (this.f2109f != null) {
                        str2 = this.f2110g;
                    } else if (this.f2107d != null) {
                        str2 = this.f2108e;
                    } else {
                        return new CacheStrategy(this.f2105b, null);
                    }
                    str = "If-Modified-Since";
                }
                Headers.Builder f2 = this.f2105b.d().f();
                Internal.f2079a.b(f2, str, str2);
                return new CacheStrategy(this.f2105b.g().d(f2.d()).b(), this.f2106c);
            }
            return new CacheStrategy(this.f2105b, null);
        }

        private static boolean e(Request request) {
            if (request.c("If-Modified-Since") == null && request.c("If-None-Match") == null) {
                return false;
            }
            return true;
        }

        private boolean f() {
            return this.f2106c.c().d() == -1 && this.f2111h == null;
        }

        public CacheStrategy c() {
            CacheStrategy d2 = d();
            if (d2.f2102a != null && this.f2105b.b().j()) {
                return new CacheStrategy(null, null);
            }
            return d2;
        }
    }

    CacheStrategy(Request request, Response response) {
        this.f2102a = request;
        this.f2103b = response;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
        if (r3.c().b() == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(okhttp3.Response r3, okhttp3.Request r4) {
        /*
            int r0 = r3.e()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L5a
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L5a
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L5a
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L5a
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L5a
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L5a
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L31
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L5a
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L5a
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L5a
            switch(r0) {
                case 300: goto L5a;
                case 301: goto L5a;
                case 302: goto L31;
                default: goto L30;
            }
        L30:
            goto L59
        L31:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.m(r0)
            if (r0 != 0) goto L5a
            okhttp3.CacheControl r0 = r3.c()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            okhttp3.CacheControl r0 = r3.c()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            okhttp3.CacheControl r0 = r3.c()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            okhttp3.CacheControl r3 = r3.c()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            okhttp3.CacheControl r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.a(okhttp3.Response, okhttp3.Request):boolean");
    }
}
