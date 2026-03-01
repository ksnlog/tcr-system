package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class CompletedContinuation {

    /* renamed from: a  reason: collision with root package name */
    public final Object f929a;

    /* renamed from: b  reason: collision with root package name */
    public final CancelHandler f930b;

    /* renamed from: c  reason: collision with root package name */
    public final Function1<Throwable, Unit> f931c;

    /* renamed from: d  reason: collision with root package name */
    public final Object f932d;

    /* renamed from: e  reason: collision with root package name */
    public final Throwable f933e;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        this.f929a = obj;
        this.f930b = cancelHandler;
        this.f931c = function1;
        this.f932d = obj2;
        this.f933e = th;
    }

    public static /* synthetic */ CompletedContinuation b(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i2, Object obj3) {
        if ((i2 & 1) != 0) {
            obj = completedContinuation.f929a;
        }
        if ((i2 & 2) != 0) {
            cancelHandler = completedContinuation.f930b;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        Function1<Throwable, Unit> function12 = function1;
        if ((i2 & 4) != 0) {
            function12 = completedContinuation.f931c;
        }
        Function1 function13 = function12;
        if ((i2 & 8) != 0) {
            obj2 = completedContinuation.f932d;
        }
        Object obj4 = obj2;
        if ((i2 & 16) != 0) {
            th = completedContinuation.f933e;
        }
        return completedContinuation.a(obj, cancelHandler2, function13, obj4, th);
    }

    public final CompletedContinuation a(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        return new CompletedContinuation(obj, cancelHandler, function1, obj2, th);
    }

    public final boolean c() {
        return this.f933e != null;
    }

    public final void d(CancellableContinuationImpl<?> cancellableContinuationImpl, Throwable th) {
        CancelHandler cancelHandler = this.f930b;
        if (cancelHandler != null) {
            cancellableContinuationImpl.m(cancelHandler, th);
        }
        Function1<Throwable, Unit> function1 = this.f931c;
        if (function1 != null) {
            cancellableContinuationImpl.n(function1, th);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return Intrinsics.a(this.f929a, completedContinuation.f929a) && Intrinsics.a(this.f930b, completedContinuation.f930b) && Intrinsics.a(this.f931c, completedContinuation.f931c) && Intrinsics.a(this.f932d, completedContinuation.f932d) && Intrinsics.a(this.f933e, completedContinuation.f933e);
    }

    public int hashCode() {
        Object obj = this.f929a;
        int i2 = 0;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.f930b;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1<Throwable, Unit> function1 = this.f931c;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.f932d;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f933e;
        if (th != null) {
            i2 = th.hashCode();
        }
        return hashCode4 + i2;
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.f929a + ", cancelHandler=" + this.f930b + ", onCancellation=" + this.f931c + ", idempotentResume=" + this.f932d + ", cancelCause=" + this.f933e + ')';
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i2 & 2) != 0 ? null : cancelHandler, (i2 & 4) != 0 ? null : function1, (i2 & 8) != 0 ? null : obj2, (i2 & 16) != 0 ? null : th);
    }
}
