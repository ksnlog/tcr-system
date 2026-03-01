package okhttp3.internal.http2;

import java.io.IOException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StreamResetException extends IOException {

    /* renamed from: d  reason: collision with root package name */
    public final ErrorCode f2392d;

    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + errorCode);
        this.f2392d = errorCode;
    }
}
