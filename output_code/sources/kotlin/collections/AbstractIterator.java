package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractIterator<T> implements Iterator<T> {

    /* renamed from: d  reason: collision with root package name */
    private State f767d = State.NotReady;

    /* renamed from: e  reason: collision with root package name */
    private T f768e;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f769a;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.Done.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[State.Ready.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f769a = iArr;
        }
    }

    private final boolean d() {
        this.f767d = State.Failed;
        a();
        if (this.f767d == State.Ready) {
            return true;
        }
        return false;
    }

    protected abstract void a();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b() {
        this.f767d = State.Done;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(T t2) {
        this.f768e = t2;
        this.f767d = State.Ready;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        boolean z2;
        State state = this.f767d;
        if (state != State.Failed) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            int i2 = WhenMappings.f769a[state.ordinal()];
            if (i2 == 1) {
                return false;
            }
            if (i2 != 2) {
                return d();
            }
            return true;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            this.f767d = State.NotReady;
            return this.f768e;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
