package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http2.ConnectionShutdownException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private final OkHttpClient f2186a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f2187b;

    /* renamed from: c  reason: collision with root package name */
    private volatile StreamAllocation f2188c;

    /* renamed from: d  reason: collision with root package name */
    private Object f2189d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f2190e;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z2) {
        this.f2186a = okHttpClient;
        this.f2187b = z2;
    }

    private Address c(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.m()) {
            sSLSocketFactory = this.f2186a.B();
            hostnameVerifier = this.f2186a.n();
            certificatePinner = this.f2186a.c();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.l(), httpUrl.w(), this.f2186a.i(), this.f2186a.A(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.f2186a.w(), this.f2186a.v(), this.f2186a.u(), this.f2186a.f(), this.f2186a.x());
    }

    private Request d(Response response, Route route) {
        String m2;
        HttpUrl z2;
        if (response != null) {
            int e2 = response.e();
            String f2 = response.x().f();
            RequestBody requestBody = null;
            if (e2 != 307 && e2 != 308) {
                if (e2 != 401) {
                    if (e2 != 503) {
                        if (e2 != 407) {
                            if (e2 != 408) {
                                switch (e2) {
                                    case 300:
                                    case 301:
                                    case 302:
                                    case 303:
                                        break;
                                    default:
                                        return null;
                                }
                            } else if (!this.f2186a.z()) {
                                return null;
                            } else {
                                response.x().a();
                                if ((response.r() != null && response.r().e() == 408) || i(response, 0) > 0) {
                                    return null;
                                }
                                return response.x();
                            }
                        } else if (route.b().type() == Proxy.Type.HTTP) {
                            return this.f2186a.w().a(route, response);
                        } else {
                            throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                        }
                    } else if ((response.r() != null && response.r().e() == 503) || i(response, Integer.MAX_VALUE) != 0) {
                        return null;
                    } else {
                        return response.x();
                    }
                } else {
                    return this.f2186a.a().a(route, response);
                }
            } else if (!f2.equals("GET") && !f2.equals("HEAD")) {
                return null;
            }
            if (!this.f2186a.l() || (m2 = response.m("Location")) == null || (z2 = response.x().h().z(m2)) == null) {
                return null;
            }
            if (!z2.A().equals(response.x().h().A()) && !this.f2186a.m()) {
                return null;
            }
            Request.Builder g2 = response.x().g();
            if (HttpMethod.a(f2)) {
                boolean c2 = HttpMethod.c(f2);
                if (HttpMethod.b(f2)) {
                    g2.e("GET", null);
                } else {
                    if (c2) {
                        requestBody = response.x().a();
                    }
                    g2.e(f2, requestBody);
                }
                if (!c2) {
                    g2.f("Transfer-Encoding");
                    g2.f("Content-Length");
                    g2.f("Content-Type");
                }
            }
            if (!j(response, z2)) {
                g2.f("Authorization");
            }
            return g2.i(z2).b();
        }
        throw new IllegalStateException();
    }

    private boolean f(IOException iOException, boolean z2) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z2) {
                return false;
            }
            return true;
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean g(IOException iOException, StreamAllocation streamAllocation, boolean z2, Request request) {
        streamAllocation.q(iOException);
        if (!this.f2186a.z()) {
            return false;
        }
        if ((z2 && h(iOException, request)) || !f(iOException, z2) || !streamAllocation.h()) {
            return false;
        }
        return true;
    }

    private boolean h(IOException iOException, Request request) {
        request.a();
        return iOException instanceof FileNotFoundException;
    }

    private int i(Response response, int i2) {
        String m2 = response.m("Retry-After");
        if (m2 == null) {
            return i2;
        }
        if (m2.matches("\\d+")) {
            return Integer.valueOf(m2).intValue();
        }
        return Integer.MAX_VALUE;
    }

    private boolean j(Response response, HttpUrl httpUrl) {
        HttpUrl h2 = response.x().h();
        if (h2.l().equals(httpUrl.l()) && h2.w() == httpUrl.w() && h2.A().equals(httpUrl.A())) {
            return true;
        }
        return false;
    }

    @Override // okhttp3.Interceptor
    public Response a(Interceptor.Chain chain) {
        boolean z2;
        Response j2;
        Request d2;
        Request e2 = chain.e();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Call f2 = realInterceptorChain.f();
        EventListener h2 = realInterceptorChain.h();
        StreamAllocation streamAllocation = new StreamAllocation(this.f2186a.e(), c(e2.h()), f2, h2, this.f2189d);
        this.f2188c = streamAllocation;
        Response response = null;
        int i2 = 0;
        while (!this.f2190e) {
            try {
                try {
                    j2 = realInterceptorChain.j(e2, streamAllocation, null, null);
                    if (response != null) {
                        j2 = j2.p().m(response.p().b(null).c()).c();
                    }
                    try {
                        d2 = d(j2, streamAllocation.o());
                    } catch (IOException e3) {
                        streamAllocation.k();
                        throw e3;
                    }
                } catch (IOException e4) {
                    if (!(e4 instanceof ConnectionShutdownException)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!g(e4, streamAllocation, z2, e2)) {
                        throw e4;
                    }
                } catch (RouteException e5) {
                    if (!g(e5.c(), streamAllocation, false, e2)) {
                        throw e5.b();
                    }
                }
                if (d2 == null) {
                    streamAllocation.k();
                    return j2;
                }
                Util.f(j2.b());
                int i3 = i2 + 1;
                if (i3 <= 20) {
                    d2.a();
                    if (!j(j2, d2.h())) {
                        streamAllocation.k();
                        streamAllocation = new StreamAllocation(this.f2186a.e(), c(d2.h()), f2, h2, this.f2189d);
                        this.f2188c = streamAllocation;
                    } else if (streamAllocation.c() != null) {
                        throw new IllegalStateException("Closing the body of " + j2 + " didn't close its backing stream. Bad interceptor?");
                    }
                    response = j2;
                    e2 = d2;
                    i2 = i3;
                } else {
                    streamAllocation.k();
                    throw new ProtocolException("Too many follow-up requests: " + i3);
                }
            } catch (Throwable th) {
                streamAllocation.q(null);
                streamAllocation.k();
                throw th;
            }
        }
        streamAllocation.k();
        throw new IOException("Canceled");
    }

    public void b() {
        this.f2190e = true;
        StreamAllocation streamAllocation = this.f2188c;
        if (streamAllocation != null) {
            streamAllocation.b();
        }
    }

    public boolean e() {
        return this.f2190e;
    }

    public void k(Object obj) {
        this.f2189d = obj;
    }
}
