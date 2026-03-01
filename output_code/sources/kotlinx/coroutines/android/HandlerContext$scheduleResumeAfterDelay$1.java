package kotlinx.coroutines.android;

import android.os.Handler;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class HandlerContext$scheduleResumeAfterDelay$1 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ HandlerContext f1025d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Runnable f1026e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerContext$scheduleResumeAfterDelay$1(HandlerContext handlerContext, Runnable runnable) {
        super(1);
        this.f1025d = handlerContext;
        this.f1026e = runnable;
    }

    public final void a(Throwable th) {
        Handler handler;
        handler = this.f1025d.f1019f;
        handler.removeCallbacks(this.f1026e);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(Throwable th) {
        a(th);
        return Unit.f763a;
    }
}
