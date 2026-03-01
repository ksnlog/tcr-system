package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SelectKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Function3<Object, Object, Object, Object> f1236a = SelectKt$DUMMY_PROCESS_RESULT_FUNCTION$1.f1242d;

    /* renamed from: b  reason: collision with root package name */
    private static final Symbol f1237b = new Symbol("STATE_REG");

    /* renamed from: c  reason: collision with root package name */
    private static final Symbol f1238c = new Symbol("STATE_COMPLETED");

    /* renamed from: d  reason: collision with root package name */
    private static final Symbol f1239d = new Symbol("STATE_CANCELLED");

    /* renamed from: e  reason: collision with root package name */
    private static final Symbol f1240e = new Symbol("NO_RESULT");

    /* renamed from: f  reason: collision with root package name */
    private static final Symbol f1241f = new Symbol("PARAM_CLAUSE_0");

    /* JADX INFO: Access modifiers changed from: private */
    public static final TrySelectDetailedResult a(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return TrySelectDetailedResult.ALREADY_SELECTED;
                    }
                    throw new IllegalStateException(("Unexpected internal result: " + i2).toString());
                }
                return TrySelectDetailedResult.CANCELLED;
            }
            return TrySelectDetailedResult.REREGISTER;
        }
        return TrySelectDetailedResult.SUCCESSFUL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean h(CancellableContinuation<? super Unit> cancellableContinuation, Function1<? super Throwable, Unit> function1) {
        Object a2 = cancellableContinuation.a(Unit.f763a, null, function1);
        if (a2 == null) {
            return false;
        }
        cancellableContinuation.h(a2);
        return true;
    }
}
