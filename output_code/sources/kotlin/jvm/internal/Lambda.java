package kotlin.jvm.internal;

import java.io.Serializable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Lambda<R> implements FunctionBase<R>, Serializable {
    private final int arity;

    public Lambda(int i2) {
        this.arity = i2;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        String g2 = Reflection.g(this);
        Intrinsics.e(g2, "renderLambdaToString(this)");
        return g2;
    }
}
