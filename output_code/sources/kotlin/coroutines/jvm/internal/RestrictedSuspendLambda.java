package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class RestrictedSuspendLambda extends RestrictedContinuationImpl implements FunctionBase<Object> {

    /* renamed from: d  reason: collision with root package name */
    private final int f820d;

    public RestrictedSuspendLambda(int i2, Continuation<Object> continuation) {
        super(continuation);
        this.f820d = i2;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.f820d;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if (getCompletion() == null) {
            String f2 = Reflection.f(this);
            Intrinsics.e(f2, "renderLambdaToString(this)");
            return f2;
        }
        return super.toString();
    }
}
