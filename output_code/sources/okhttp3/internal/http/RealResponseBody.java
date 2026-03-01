package okhttp3.internal.http;

import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealResponseBody extends ResponseBody {
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    private final String f2183d;

    /* renamed from: e  reason: collision with root package name */
    private final long f2184e;

    /* renamed from: f  reason: collision with root package name */
    private final BufferedSource f2185f;

    public RealResponseBody(@Nullable String str, long j2, BufferedSource bufferedSource) {
        this.f2183d = str;
        this.f2184e = j2;
        this.f2185f = bufferedSource;
    }

    @Override // okhttp3.ResponseBody
    public long e() {
        return this.f2184e;
    }

    @Override // okhttp3.ResponseBody
    public MediaType k() {
        String str = this.f2183d;
        if (str != null) {
            return MediaType.c(str);
        }
        return null;
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource o() {
        return this.f2185f;
    }
}
