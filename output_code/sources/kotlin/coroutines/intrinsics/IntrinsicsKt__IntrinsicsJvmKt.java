package kotlin.coroutines.intrinsics;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class IntrinsicsKt__IntrinsicsJvmKt {
    public static <R, T> Continuation<Unit> a(final Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, final R r2, Continuation<? super T> completion) {
        Intrinsics.f(function2, "<this>");
        Intrinsics.f(completion, "completion");
        final Continuation<?> a2 = DebugProbesKt.a(completion);
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).create(r2, a2);
        }
        final CoroutineContext context = a2.getContext();
        if (context == EmptyCoroutineContext.f802d) {
            return new RestrictedContinuationImpl(a2, function2, r2) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3

                /* renamed from: d  reason: collision with root package name */
                private int f807d;

                /* renamed from: e  reason: collision with root package name */
                final /* synthetic */ Function2 f808e;

                /* renamed from: f  reason: collision with root package name */
                final /* synthetic */ Object f809f;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(a2);
                    this.f808e = function2;
                    this.f809f = r2;
                    Intrinsics.d(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                protected Object invokeSuspend(Object obj) {
                    int i2 = this.f807d;
                    if (i2 != 0) {
                        if (i2 == 1) {
                            this.f807d = 2;
                            ResultKt.b(obj);
                            return obj;
                        }
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                    this.f807d = 1;
                    ResultKt.b(obj);
                    Intrinsics.d(this.f808e, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                    return ((Function2) TypeIntrinsics.a(this.f808e, 2)).invoke(this.f809f, this);
                }
            };
        }
        return new ContinuationImpl(a2, context, function2, r2) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4

            /* renamed from: d  reason: collision with root package name */
            private int f810d;

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Function2 f811e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Object f812f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(a2, context);
                this.f811e = function2;
                this.f812f = r2;
                Intrinsics.d(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object invokeSuspend(Object obj) {
                int i2 = this.f810d;
                if (i2 != 0) {
                    if (i2 == 1) {
                        this.f810d = 2;
                        ResultKt.b(obj);
                        return obj;
                    }
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
                this.f810d = 1;
                ResultKt.b(obj);
                Intrinsics.d(this.f811e, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                return ((Function2) TypeIntrinsics.a(this.f811e, 2)).invoke(this.f812f, this);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Continuation<T> b(Continuation<? super T> continuation) {
        ContinuationImpl continuationImpl;
        Continuation<T> continuation2;
        Intrinsics.f(continuation, "<this>");
        if (continuation instanceof ContinuationImpl) {
            continuationImpl = (ContinuationImpl) continuation;
        } else {
            continuationImpl = null;
        }
        if (continuationImpl != null && (continuation2 = (Continuation<T>) continuationImpl.intercepted()) != null) {
            return continuation2;
        }
        return continuation;
    }
}
