package okhttp3;

import java.io.Closeable;
import javax.annotation.Nullable;
import okhttp3.Headers;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Response implements Closeable {

    /* renamed from: d  reason: collision with root package name */
    final Request f2041d;

    /* renamed from: e  reason: collision with root package name */
    final Protocol f2042e;

    /* renamed from: f  reason: collision with root package name */
    final int f2043f;

    /* renamed from: g  reason: collision with root package name */
    final String f2044g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    final Handshake f2045h;

    /* renamed from: i  reason: collision with root package name */
    final Headers f2046i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    final ResponseBody f2047j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    final Response f2048k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    final Response f2049l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    final Response f2050m;

    /* renamed from: n  reason: collision with root package name */
    final long f2051n;

    /* renamed from: o  reason: collision with root package name */
    final long f2052o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    private volatile CacheControl f2053p;

    Response(Builder builder) {
        this.f2041d = builder.f2054a;
        this.f2042e = builder.f2055b;
        this.f2043f = builder.f2056c;
        this.f2044g = builder.f2057d;
        this.f2045h = builder.f2058e;
        this.f2046i = builder.f2059f.d();
        this.f2047j = builder.f2060g;
        this.f2048k = builder.f2061h;
        this.f2049l = builder.f2062i;
        this.f2050m = builder.f2063j;
        this.f2051n = builder.f2064k;
        this.f2052o = builder.f2065l;
    }

    @Nullable
    public ResponseBody b() {
        return this.f2047j;
    }

    public CacheControl c() {
        CacheControl cacheControl = this.f2053p;
        if (cacheControl == null) {
            CacheControl k2 = CacheControl.k(this.f2046i);
            this.f2053p = k2;
            return k2;
        }
        return cacheControl;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ResponseBody responseBody = this.f2047j;
        if (responseBody != null) {
            responseBody.close();
            return;
        }
        throw new IllegalStateException("response is not eligible for a body and must not be closed");
    }

    public int e() {
        return this.f2043f;
    }

    @Nullable
    public Handshake k() {
        return this.f2045h;
    }

    @Nullable
    public String m(String str) {
        return n(str, null);
    }

    @Nullable
    public String n(String str, @Nullable String str2) {
        String c2 = this.f2046i.c(str);
        return c2 != null ? c2 : str2;
    }

    public Headers o() {
        return this.f2046i;
    }

    public Builder p() {
        return new Builder(this);
    }

    @Nullable
    public Response r() {
        return this.f2050m;
    }

    public String toString() {
        return "Response{protocol=" + this.f2042e + ", code=" + this.f2043f + ", message=" + this.f2044g + ", url=" + this.f2041d.h() + '}';
    }

    public long w() {
        return this.f2052o;
    }

    public Request x() {
        return this.f2041d;
    }

    public long z() {
        return this.f2051n;
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        Request f2054a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        Protocol f2055b;

        /* renamed from: c  reason: collision with root package name */
        int f2056c;

        /* renamed from: d  reason: collision with root package name */
        String f2057d;
        @Nullable

        /* renamed from: e  reason: collision with root package name */
        Handshake f2058e;

        /* renamed from: f  reason: collision with root package name */
        Headers.Builder f2059f;
        @Nullable

        /* renamed from: g  reason: collision with root package name */
        ResponseBody f2060g;
        @Nullable

        /* renamed from: h  reason: collision with root package name */
        Response f2061h;
        @Nullable

        /* renamed from: i  reason: collision with root package name */
        Response f2062i;
        @Nullable

        /* renamed from: j  reason: collision with root package name */
        Response f2063j;

        /* renamed from: k  reason: collision with root package name */
        long f2064k;

        /* renamed from: l  reason: collision with root package name */
        long f2065l;

        public Builder() {
            this.f2056c = -1;
            this.f2059f = new Headers.Builder();
        }

        private void e(Response response) {
            if (response.f2047j == null) {
                return;
            }
            throw new IllegalArgumentException("priorResponse.body != null");
        }

        private void f(String str, Response response) {
            if (response.f2047j == null) {
                if (response.f2048k == null) {
                    if (response.f2049l == null) {
                        if (response.f2050m == null) {
                            return;
                        }
                        throw new IllegalArgumentException(str + ".priorResponse != null");
                    }
                    throw new IllegalArgumentException(str + ".cacheResponse != null");
                }
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            throw new IllegalArgumentException(str + ".body != null");
        }

        public Builder a(String str, String str2) {
            this.f2059f.a(str, str2);
            return this;
        }

        public Builder b(@Nullable ResponseBody responseBody) {
            this.f2060g = responseBody;
            return this;
        }

        public Response c() {
            if (this.f2054a != null) {
                if (this.f2055b != null) {
                    if (this.f2056c >= 0) {
                        if (this.f2057d != null) {
                            return new Response(this);
                        }
                        throw new IllegalStateException("message == null");
                    }
                    throw new IllegalStateException("code < 0: " + this.f2056c);
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }

        public Builder d(@Nullable Response response) {
            if (response != null) {
                f("cacheResponse", response);
            }
            this.f2062i = response;
            return this;
        }

        public Builder g(int i2) {
            this.f2056c = i2;
            return this;
        }

        public Builder h(@Nullable Handshake handshake) {
            this.f2058e = handshake;
            return this;
        }

        public Builder i(String str, String str2) {
            this.f2059f.f(str, str2);
            return this;
        }

        public Builder j(Headers headers) {
            this.f2059f = headers.f();
            return this;
        }

        public Builder k(String str) {
            this.f2057d = str;
            return this;
        }

        public Builder l(@Nullable Response response) {
            if (response != null) {
                f("networkResponse", response);
            }
            this.f2061h = response;
            return this;
        }

        public Builder m(@Nullable Response response) {
            if (response != null) {
                e(response);
            }
            this.f2063j = response;
            return this;
        }

        public Builder n(Protocol protocol) {
            this.f2055b = protocol;
            return this;
        }

        public Builder o(long j2) {
            this.f2065l = j2;
            return this;
        }

        public Builder p(Request request) {
            this.f2054a = request;
            return this;
        }

        public Builder q(long j2) {
            this.f2064k = j2;
            return this;
        }

        Builder(Response response) {
            this.f2056c = -1;
            this.f2054a = response.f2041d;
            this.f2055b = response.f2042e;
            this.f2056c = response.f2043f;
            this.f2057d = response.f2044g;
            this.f2058e = response.f2045h;
            this.f2059f = response.f2046i.f();
            this.f2060g = response.f2047j;
            this.f2061h = response.f2048k;
            this.f2062i = response.f2049l;
            this.f2063j = response.f2050m;
            this.f2064k = response.f2051n;
            this.f2065l = response.f2052o;
        }
    }
}
