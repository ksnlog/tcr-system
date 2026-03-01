package okhttp3;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ResponseBody implements Closeable {
    private Charset c() {
        MediaType k2 = k();
        if (k2 != null) {
            return k2.a(Util.f2090j);
        }
        return Util.f2090j;
    }

    public static ResponseBody m(@Nullable final MediaType mediaType, final long j2, final BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            return new ResponseBody() { // from class: okhttp3.ResponseBody.1
                @Override // okhttp3.ResponseBody
                public long e() {
                    return j2;
                }

                @Override // okhttp3.ResponseBody
                @Nullable
                public MediaType k() {
                    return MediaType.this;
                }

                @Override // okhttp3.ResponseBody
                public BufferedSource o() {
                    return bufferedSource;
                }
            };
        }
        throw new NullPointerException("source == null");
    }

    public static ResponseBody n(@Nullable MediaType mediaType, byte[] bArr) {
        return m(mediaType, bArr.length, new Buffer().write(bArr));
    }

    public final InputStream b() {
        return o().N();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.f(o());
    }

    public abstract long e();

    @Nullable
    public abstract MediaType k();

    public abstract BufferedSource o();

    public final String p() {
        BufferedSource o2 = o();
        try {
            return o2.M(Util.c(o2, c()));
        } finally {
            Util.f(o2);
        }
    }
}
