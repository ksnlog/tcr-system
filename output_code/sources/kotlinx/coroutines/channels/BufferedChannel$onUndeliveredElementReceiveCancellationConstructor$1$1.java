package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.selects.SelectInstance;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1 extends Lambda implements Function3<SelectInstance<?>, Object, Object, Function1<? super Throwable, ? extends Unit>> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ BufferedChannel<E> f1063d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlinx.coroutines.channels.BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ Object f1064d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ BufferedChannel<E> f1065e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ SelectInstance<?> f1066f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Object obj, BufferedChannel<E> bufferedChannel, SelectInstance<?> selectInstance) {
            super(1);
            this.f1064d = obj;
            this.f1065e = bufferedChannel;
            this.f1066f = selectInstance;
        }

        public final void a(Throwable th) {
            if (this.f1064d != BufferedChannelKt.z()) {
                OnUndeliveredElementKt.b(this.f1065e.f1056b, this.f1064d, this.f1066f.getContext());
            }
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit f(Throwable th) {
            a(th);
            return Unit.f763a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1(BufferedChannel<E> bufferedChannel) {
        super(3);
        this.f1063d = bufferedChannel;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: a */
    public final Function1<Throwable, Unit> b(SelectInstance<?> selectInstance, Object obj, Object obj2) {
        return new AnonymousClass1(obj2, this.f1063d, selectInstance);
    }
}
