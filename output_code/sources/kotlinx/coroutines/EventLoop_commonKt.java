package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class EventLoop_commonKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Symbol f975a = new Symbol("REMOVED_TASK");

    /* renamed from: b  reason: collision with root package name */
    private static final Symbol f976b = new Symbol("CLOSED_EMPTY");

    public static final long c(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        if (j2 < 9223372036854L) {
            return 1000000 * j2;
        }
        return Long.MAX_VALUE;
    }
}
