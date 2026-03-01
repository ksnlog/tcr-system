package okhttp3.internal.connection;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ConnectInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public final OkHttpClient f2116a;

    public ConnectInterceptor(OkHttpClient okHttpClient) {
        this.f2116a = okHttpClient;
    }

    @Override // okhttp3.Interceptor
    public Response a(Interceptor.Chain chain) {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request e2 = realInterceptorChain.e();
        StreamAllocation k2 = realInterceptorChain.k();
        return realInterceptorChain.j(e2, k2, k2.i(this.f2116a, chain, !e2.f().equals("GET")), k2.d());
    }
}
