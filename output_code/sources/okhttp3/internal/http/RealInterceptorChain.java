package okhttp3.internal.http;

import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealInterceptorChain implements Interceptor.Chain {

    /* renamed from: a  reason: collision with root package name */
    private final List<Interceptor> f2171a;

    /* renamed from: b  reason: collision with root package name */
    private final StreamAllocation f2172b;

    /* renamed from: c  reason: collision with root package name */
    private final HttpCodec f2173c;

    /* renamed from: d  reason: collision with root package name */
    private final RealConnection f2174d;

    /* renamed from: e  reason: collision with root package name */
    private final int f2175e;

    /* renamed from: f  reason: collision with root package name */
    private final Request f2176f;

    /* renamed from: g  reason: collision with root package name */
    private final Call f2177g;

    /* renamed from: h  reason: collision with root package name */
    private final EventListener f2178h;

    /* renamed from: i  reason: collision with root package name */
    private final int f2179i;

    /* renamed from: j  reason: collision with root package name */
    private final int f2180j;

    /* renamed from: k  reason: collision with root package name */
    private final int f2181k;

    /* renamed from: l  reason: collision with root package name */
    private int f2182l;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection, int i2, Request request, Call call, EventListener eventListener, int i3, int i4, int i5) {
        this.f2171a = list;
        this.f2174d = realConnection;
        this.f2172b = streamAllocation;
        this.f2173c = httpCodec;
        this.f2175e = i2;
        this.f2176f = request;
        this.f2177g = call;
        this.f2178h = eventListener;
        this.f2179i = i3;
        this.f2180j = i4;
        this.f2181k = i5;
    }

    @Override // okhttp3.Interceptor.Chain
    public int a() {
        return this.f2179i;
    }

    @Override // okhttp3.Interceptor.Chain
    public int b() {
        return this.f2180j;
    }

    @Override // okhttp3.Interceptor.Chain
    public int c() {
        return this.f2181k;
    }

    @Override // okhttp3.Interceptor.Chain
    public Response d(Request request) {
        return j(request, this.f2172b, this.f2173c, this.f2174d);
    }

    @Override // okhttp3.Interceptor.Chain
    public Request e() {
        return this.f2176f;
    }

    public Call f() {
        return this.f2177g;
    }

    public Connection g() {
        return this.f2174d;
    }

    public EventListener h() {
        return this.f2178h;
    }

    public HttpCodec i() {
        return this.f2173c;
    }

    public Response j(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection) {
        if (this.f2175e < this.f2171a.size()) {
            this.f2182l++;
            if (this.f2173c != null && !this.f2174d.s(request.h())) {
                throw new IllegalStateException("network interceptor " + this.f2171a.get(this.f2175e - 1) + " must retain the same host and port");
            } else if (this.f2173c != null && this.f2182l > 1) {
                throw new IllegalStateException("network interceptor " + this.f2171a.get(this.f2175e - 1) + " must call proceed() exactly once");
            } else {
                RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.f2171a, streamAllocation, httpCodec, realConnection, this.f2175e + 1, request, this.f2177g, this.f2178h, this.f2179i, this.f2180j, this.f2181k);
                Interceptor interceptor = this.f2171a.get(this.f2175e);
                Response a2 = interceptor.a(realInterceptorChain);
                if (httpCodec != null && this.f2175e + 1 < this.f2171a.size() && realInterceptorChain.f2182l != 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                } else if (a2 != null) {
                    if (a2.b() != null) {
                        return a2;
                    }
                    throw new IllegalStateException("interceptor " + interceptor + " returned a response with no body");
                } else {
                    throw new NullPointerException("interceptor " + interceptor + " returned null");
                }
            }
        }
        throw new AssertionError();
    }

    public StreamAllocation k() {
        return this.f2172b;
    }
}
