package okhttp3.internal.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Sink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface HttpCodec {
    void a();

    void b(Request request);

    ResponseBody c(Response response);

    void cancel();

    void d();

    Sink e(Request request, long j2);

    Response.Builder f(boolean z2);
}
