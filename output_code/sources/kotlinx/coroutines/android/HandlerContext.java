package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HandlerContext extends HandlerDispatcher {
    private volatile HandlerContext _immediate;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f1019f;

    /* renamed from: g  reason: collision with root package name */
    private final String f1020g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f1021h;

    /* renamed from: i  reason: collision with root package name */
    private final HandlerContext f1022i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private HandlerContext(Handler handler, String str, boolean z2) {
        super(null);
        HandlerContext handlerContext = null;
        this.f1019f = handler;
        this.f1020g = str;
        this.f1021h = z2;
        this._immediate = z2 ? this : handlerContext;
        HandlerContext handlerContext2 = this._immediate;
        if (handlerContext2 == null) {
            handlerContext2 = new HandlerContext(handler, str, true);
            this._immediate = handlerContext2;
        }
        this.f1022i = handlerContext2;
    }

    private final void W(CoroutineContext coroutineContext, Runnable runnable) {
        JobKt.a(coroutineContext, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Dispatchers.b().Q(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void Q(CoroutineContext coroutineContext, Runnable runnable) {
        if (!this.f1019f.post(runnable)) {
            W(coroutineContext, runnable);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean R(CoroutineContext coroutineContext) {
        return !this.f1021h || !Intrinsics.a(Looper.myLooper(), this.f1019f.getLooper());
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    /* renamed from: X */
    public HandlerContext T() {
        return this.f1022i;
    }

    public boolean equals(Object obj) {
        return (obj instanceof HandlerContext) && ((HandlerContext) obj).f1019f == this.f1019f;
    }

    public int hashCode() {
        return System.identityHashCode(this.f1019f);
    }

    @Override // kotlinx.coroutines.Delay
    public void p(long j2, final CancellableContinuation<? super Unit> cancellableContinuation) {
        long d2;
        Runnable runnable = new Runnable() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                CancellableContinuation.this.d(this, Unit.f763a);
            }
        };
        Handler handler = this.f1019f;
        d2 = RangesKt___RangesKt.d(j2, 4611686018427387903L);
        if (handler.postDelayed(runnable, d2)) {
            cancellableContinuation.g(new HandlerContext$scheduleResumeAfterDelay$1(this, runnable));
        } else {
            W(cancellableContinuation.getContext(), runnable);
        }
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String U = U();
        if (U == null) {
            String str = this.f1020g;
            if (str == null) {
                str = this.f1019f.toString();
            }
            if (this.f1021h) {
                return str + ".immediate";
            }
            return str;
        }
        return U;
    }

    public /* synthetic */ HandlerContext(Handler handler, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler, (i2 & 2) != 0 ? null : str);
    }

    public HandlerContext(Handler handler, String str) {
        this(handler, str, false);
    }
}
