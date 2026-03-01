package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LockFreeLinkedListKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1122a = new Symbol("CONDITION_FALSE");

    public static final Object a() {
        return f1122a;
    }

    public static final LockFreeLinkedListNode b(Object obj) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed = obj instanceof Removed ? (Removed) obj : null;
        if (removed == null || (lockFreeLinkedListNode = removed.f1147a) == null) {
            Intrinsics.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            return (LockFreeLinkedListNode) obj;
        }
        return lockFreeLinkedListNode;
    }
}
