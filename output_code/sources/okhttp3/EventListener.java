package okhttp3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class EventListener {

    /* renamed from: a  reason: collision with root package name */
    public static final EventListener f1928a = new EventListener() { // from class: okhttp3.EventListener.1
    };

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Factory {
        EventListener a(Call call);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Factory k(EventListener eventListener) {
        return new Factory() { // from class: okhttp3.EventListener.2
            @Override // okhttp3.EventListener.Factory
            public EventListener a(Call call) {
                return EventListener.this;
            }
        };
    }

    public void a(Call call) {
    }

    public void b(Call call, IOException iOException) {
    }

    public void c(Call call) {
    }

    public void d(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
    }

    public void e(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException iOException) {
    }

    public void f(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void g(Call call, Connection connection) {
    }

    public void h(Call call, Connection connection) {
    }

    public void i(Call call, String str, List<InetAddress> list) {
    }

    public void j(Call call, String str) {
    }

    public void l(Call call, long j2) {
    }

    public void m(Call call) {
    }

    public void n(Call call, Request request) {
    }

    public void o(Call call) {
    }

    public void p(Call call, long j2) {
    }

    public void q(Call call) {
    }

    public void r(Call call, Response response) {
    }

    public void s(Call call) {
    }

    public void t(Call call, @Nullable Handshake handshake) {
    }

    public void u(Call call) {
    }
}
