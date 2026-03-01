package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {

    /* renamed from: k  reason: collision with root package name */
    private final int f879k;

    /* renamed from: l  reason: collision with root package name */
    private final int f880l;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FunctionReference(int r9, java.lang.Object r10, java.lang.Class r11, java.lang.String r12, java.lang.String r13, int r14) {
        /*
            r8 = this;
            r0 = r14 & 1
            r1 = 1
            if (r0 != r1) goto L7
            r7 = 1
            goto L9
        L7:
            r0 = 0
            r7 = 0
        L9:
            r2 = r8
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            r8.f879k = r9
            int r9 = r14 >> 1
            r8.f880l = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.FunctionReference.<init>(int, java.lang.Object, java.lang.Class, java.lang.String, java.lang.String, int):void");
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable c() {
        return Reflection.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            if (getName().equals(functionReference.getName()) && i().equals(functionReference.i()) && this.f880l == functionReference.f880l && this.f879k == functionReference.f879k && Intrinsics.a(d(), functionReference.d()) && Intrinsics.a(g(), functionReference.g())) {
                return true;
            }
            return false;
        } else if (!(obj instanceof KFunction)) {
            return false;
        } else {
            return obj.equals(a());
        }
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.f879k;
    }

    public int hashCode() {
        return (((g() == null ? 0 : g().hashCode() * 31) + getName().hashCode()) * 31) + i().hashCode();
    }

    public String toString() {
        KCallable a2 = a();
        if (a2 != this) {
            return a2.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }
}
