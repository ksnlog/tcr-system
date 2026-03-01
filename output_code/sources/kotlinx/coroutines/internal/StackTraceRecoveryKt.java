package kotlinx.coroutines.internal;

import _COROUTINE.ArtificialStackFrames;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StackTraceRecoveryKt {

    /* renamed from: a  reason: collision with root package name */
    private static final StackTraceElement f1151a = new ArtificialStackFrames().a();

    /* renamed from: b  reason: collision with root package name */
    private static final String f1152b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f1153c;

    static {
        Object a2;
        Object a3;
        try {
            Result.Companion companion = Result.f752d;
            a2 = Result.a(BaseContinuationImpl.class.getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.f752d;
            a2 = Result.a(ResultKt.a(th));
        }
        if (Result.b(a2) != null) {
            a2 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        f1152b = (String) a2;
        try {
            a3 = Result.a(StackTraceRecoveryKt.class.getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.f752d;
            a3 = Result.a(ResultKt.a(th2));
        }
        if (Result.b(a3) != null) {
            a3 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        f1153c = (String) a3;
    }

    public static final <E extends Throwable> E a(E e2) {
        return e2;
    }
}
