package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.IntIterator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class IntProgressionIterator extends IntIterator {

    /* renamed from: d  reason: collision with root package name */
    private final int f895d;

    /* renamed from: e  reason: collision with root package name */
    private final int f896e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f897f;

    /* renamed from: g  reason: collision with root package name */
    private int f898g;

    public IntProgressionIterator(int i2, int i3, int i4) {
        this.f895d = i4;
        this.f896e = i3;
        boolean z2 = true;
        if (i4 <= 0 ? i2 < i3 : i2 > i3) {
            z2 = false;
        }
        this.f897f = z2;
        this.f898g = !z2 ? i3 : i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f897f;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        int i2 = this.f898g;
        if (i2 == this.f896e) {
            if (this.f897f) {
                this.f897f = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.f898g = this.f895d + i2;
        }
        return i2;
    }
}
