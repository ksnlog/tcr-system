package okhttp3.internal.http;

import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HttpHeaders {

    /* renamed from: a  reason: collision with root package name */
    private static final ByteString f2169a = ByteString.e("\"\\");

    /* renamed from: b  reason: collision with root package name */
    private static final ByteString f2170b = ByteString.e("\t ,=");

    public static long a(Headers headers) {
        return h(headers.c("Content-Length"));
    }

    public static long b(Response response) {
        return a(response.o());
    }

    public static boolean c(Response response) {
        if (response.x().f().equals("HEAD")) {
            return false;
        }
        int e2 = response.e();
        if (((e2 >= 100 && e2 < 200) || e2 == 204 || e2 == 304) && b(response) == -1 && !"chunked".equalsIgnoreCase(response.m("Transfer-Encoding"))) {
            return false;
        }
        return true;
    }

    public static int d(String str, int i2) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong >= 0) {
                return (int) parseLong;
            }
            return 0;
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    public static void e(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar == CookieJar.f1919a) {
            return;
        }
        List<Cookie> f2 = Cookie.f(httpUrl, headers);
        if (f2.isEmpty()) {
            return;
        }
        cookieJar.b(httpUrl, f2);
    }

    public static int f(String str, int i2, String str2) {
        while (i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            i2++;
        }
        return i2;
    }

    public static int g(String str, int i2) {
        char charAt;
        while (i2 < str.length() && ((charAt = str.charAt(i2)) == ' ' || charAt == '\t')) {
            i2++;
        }
        return i2;
    }

    private static long h(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}
