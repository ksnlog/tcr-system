package okhttp3.internal.http;

import java.net.Proxy;
import okhttp3.HttpUrl;
import okhttp3.Request;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RequestLine {
    public static String a(Request request, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.f());
        sb.append(' ');
        if (b(request, type)) {
            sb.append(request.h());
        } else {
            sb.append(c(request.h()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(Request request, Proxy.Type type) {
        return !request.e() && type == Proxy.Type.HTTP;
    }

    public static String c(HttpUrl httpUrl) {
        String g2 = httpUrl.g();
        String i2 = httpUrl.i();
        if (i2 != null) {
            return g2 + '?' + i2;
        }
        return g2;
    }
}
