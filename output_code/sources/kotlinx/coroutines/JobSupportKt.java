package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JobSupportKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Symbol f1003a = new Symbol("COMPLETING_ALREADY");

    /* renamed from: b  reason: collision with root package name */
    public static final Symbol f1004b = new Symbol("COMPLETING_WAITING_CHILDREN");

    /* renamed from: c  reason: collision with root package name */
    private static final Symbol f1005c = new Symbol("COMPLETING_RETRY");

    /* renamed from: d  reason: collision with root package name */
    private static final Symbol f1006d = new Symbol("TOO_LATE_TO_CANCEL");

    /* renamed from: e  reason: collision with root package name */
    private static final Symbol f1007e = new Symbol("SEALED");

    /* renamed from: f  reason: collision with root package name */
    private static final Empty f1008f = new Empty(false);

    /* renamed from: g  reason: collision with root package name */
    private static final Empty f1009g = new Empty(true);

    public static final Object g(Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    public static final Object h(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.f981a) == null) ? obj : incomplete;
    }
}
