package okhttp3;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Request {

    /* renamed from: a  reason: collision with root package name */
    final HttpUrl f2026a;

    /* renamed from: b  reason: collision with root package name */
    final String f2027b;

    /* renamed from: c  reason: collision with root package name */
    final Headers f2028c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    final RequestBody f2029d;

    /* renamed from: e  reason: collision with root package name */
    final Map<Class<?>, Object> f2030e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    private volatile CacheControl f2031f;

    Request(Builder builder) {
        this.f2026a = builder.f2032a;
        this.f2027b = builder.f2033b;
        this.f2028c = builder.f2034c.d();
        this.f2029d = builder.f2035d;
        this.f2030e = Util.u(builder.f2036e);
    }

    @Nullable
    public RequestBody a() {
        return this.f2029d;
    }

    public CacheControl b() {
        CacheControl cacheControl = this.f2031f;
        if (cacheControl == null) {
            CacheControl k2 = CacheControl.k(this.f2028c);
            this.f2031f = k2;
            return k2;
        }
        return cacheControl;
    }

    @Nullable
    public String c(String str) {
        return this.f2028c.c(str);
    }

    public Headers d() {
        return this.f2028c;
    }

    public boolean e() {
        return this.f2026a.m();
    }

    public String f() {
        return this.f2027b;
    }

    public Builder g() {
        return new Builder(this);
    }

    public HttpUrl h() {
        return this.f2026a;
    }

    public String toString() {
        return "Request{method=" + this.f2027b + ", url=" + this.f2026a + ", tags=" + this.f2030e + '}';
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        HttpUrl f2032a;

        /* renamed from: b  reason: collision with root package name */
        String f2033b;

        /* renamed from: c  reason: collision with root package name */
        Headers.Builder f2034c;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        RequestBody f2035d;

        /* renamed from: e  reason: collision with root package name */
        Map<Class<?>, Object> f2036e;

        public Builder() {
            this.f2036e = Collections.emptyMap();
            this.f2033b = "GET";
            this.f2034c = new Headers.Builder();
        }

        public Builder a(String str, String str2) {
            this.f2034c.a(str, str2);
            return this;
        }

        public Request b() {
            if (this.f2032a != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }

        public Builder c(String str, String str2) {
            this.f2034c.f(str, str2);
            return this;
        }

        public Builder d(Headers headers) {
            this.f2034c = headers.f();
            return this;
        }

        public Builder e(String str, @Nullable RequestBody requestBody) {
            if (str != null) {
                if (str.length() != 0) {
                    if (requestBody != null && !HttpMethod.a(str)) {
                        throw new IllegalArgumentException("method " + str + " must not have a request body.");
                    } else if (requestBody == null && HttpMethod.d(str)) {
                        throw new IllegalArgumentException("method " + str + " must have a request body.");
                    } else {
                        this.f2033b = str;
                        this.f2035d = requestBody;
                        return this;
                    }
                }
                throw new IllegalArgumentException("method.length() == 0");
            }
            throw new NullPointerException("method == null");
        }

        public Builder f(String str) {
            this.f2034c.e(str);
            return this;
        }

        public Builder g(String str) {
            if (str != null) {
                if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                    str = "http:" + str.substring(3);
                } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    str = "https:" + str.substring(4);
                }
                return i(HttpUrl.k(str));
            }
            throw new NullPointerException("url == null");
        }

        public Builder h(URL url) {
            if (url != null) {
                return i(HttpUrl.k(url.toString()));
            }
            throw new NullPointerException("url == null");
        }

        public Builder i(HttpUrl httpUrl) {
            if (httpUrl != null) {
                this.f2032a = httpUrl;
                return this;
            }
            throw new NullPointerException("url == null");
        }

        Builder(Request request) {
            Map<Class<?>, Object> linkedHashMap;
            this.f2036e = Collections.emptyMap();
            this.f2032a = request.f2026a;
            this.f2033b = request.f2027b;
            this.f2035d = request.f2029d;
            if (request.f2030e.isEmpty()) {
                linkedHashMap = Collections.emptyMap();
            } else {
                linkedHashMap = new LinkedHashMap<>(request.f2030e);
            }
            this.f2036e = linkedHashMap;
            this.f2034c = request.f2028c.f();
        }
    }
}
