package okhttp3;

import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class RequestBody {
    public static RequestBody c(@Nullable MediaType mediaType, byte[] bArr) {
        return d(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody d(@Nullable final MediaType mediaType, final byte[] bArr, final int i2, final int i3) {
        if (bArr != null) {
            Util.e(bArr.length, i2, i3);
            return new RequestBody() { // from class: okhttp3.RequestBody.2
                @Override // okhttp3.RequestBody
                public long a() {
                    return i3;
                }

                @Override // okhttp3.RequestBody
                @Nullable
                public MediaType b() {
                    return MediaType.this;
                }

                @Override // okhttp3.RequestBody
                public void e(BufferedSink bufferedSink) {
                    bufferedSink.write(bArr, i2, i3);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public abstract long a();

    @Nullable
    public abstract MediaType b();

    public abstract void e(BufferedSink bufferedSink);
}
