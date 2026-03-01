package okhttp3;

import java.util.Collections;
import java.util.List;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface CookieJar {

    /* renamed from: a  reason: collision with root package name */
    public static final CookieJar f1919a = new CookieJar() { // from class: okhttp3.CookieJar.1
        @Override // okhttp3.CookieJar
        public List<Cookie> a(HttpUrl httpUrl) {
            return Collections.emptyList();
        }

        @Override // okhttp3.CookieJar
        public void b(HttpUrl httpUrl, List<Cookie> list) {
        }
    };

    List<Cookie> a(HttpUrl httpUrl);

    void b(HttpUrl httpUrl, List<Cookie> list);
}
