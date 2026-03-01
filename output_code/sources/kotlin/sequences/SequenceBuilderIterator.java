package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SequenceBuilderIterator<T> extends SequenceScope<T> implements Iterator<T>, Continuation<Unit> {

    /* renamed from: d  reason: collision with root package name */
    private int f902d;

    /* renamed from: e  reason: collision with root package name */
    private T f903e;

    /* renamed from: f  reason: collision with root package name */
    private Iterator<? extends T> f904f;

    /* renamed from: g  reason: collision with root package name */
    private Continuation<? super Unit> f905g;

    private final Throwable f() {
        int i2 = this.f902d;
        if (i2 != 4) {
            if (i2 != 5) {
                return new IllegalStateException("Unexpected state of the iterator: " + this.f902d);
            }
            return new IllegalStateException("Iterator has failed.");
        }
        return new NoSuchElementException();
    }

    private final T i() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    @Override // kotlin.sequences.SequenceScope
    public Object b(T t2, Continuation<? super Unit> continuation) {
        Object c2;
        Object c3;
        Object c4;
        this.f903e = t2;
        this.f902d = 3;
        this.f905g = continuation;
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        c3 = IntrinsicsKt__IntrinsicsKt.c();
        if (c2 == c3) {
            DebugProbesKt.c(continuation);
        }
        c4 = IntrinsicsKt__IntrinsicsKt.c();
        if (c2 == c4) {
            return c2;
        }
        return Unit.f763a;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object c(Iterator<? extends T> it, Continuation<? super Unit> continuation) {
        Object c2;
        Object c3;
        Object c4;
        if (!it.hasNext()) {
            return Unit.f763a;
        }
        this.f904f = it;
        this.f902d = 2;
        this.f905g = continuation;
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        c3 = IntrinsicsKt__IntrinsicsKt.c();
        if (c2 == c3) {
            DebugProbesKt.c(continuation);
        }
        c4 = IntrinsicsKt__IntrinsicsKt.c();
        if (c2 == c4) {
            return c2;
        }
        return Unit.f763a;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.f802d;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            int i2 = this.f902d;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        return true;
                    }
                    if (i2 == 4) {
                        return false;
                    }
                    throw f();
                }
                Iterator<? extends T> it = this.f904f;
                Intrinsics.c(it);
                if (it.hasNext()) {
                    this.f902d = 2;
                    return true;
                }
                this.f904f = null;
            }
            this.f902d = 5;
            Continuation<? super Unit> continuation = this.f905g;
            Intrinsics.c(continuation);
            this.f905g = null;
            Result.Companion companion = Result.f752d;
            continuation.resumeWith(Result.a(Unit.f763a));
        }
    }

    public final void j(Continuation<? super Unit> continuation) {
        this.f905g = continuation;
    }

    @Override // java.util.Iterator
    public T next() {
        int i2 = this.f902d;
        if (i2 != 0 && i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    this.f902d = 0;
                    T t2 = this.f903e;
                    this.f903e = null;
                    return t2;
                }
                throw f();
            }
            this.f902d = 1;
            Iterator<? extends T> it = this.f904f;
            Intrinsics.c(it);
            return it.next();
        }
        return i();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        ResultKt.b(obj);
        this.f902d = 4;
    }
}
