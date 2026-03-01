package okio;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SegmentPool {

    /* renamed from: a  reason: collision with root package name */
    public static final SegmentPool f2494a = new SegmentPool();

    /* renamed from: b  reason: collision with root package name */
    private static final int f2495b = 65536;

    /* renamed from: c  reason: collision with root package name */
    private static final Segment f2496c = new Segment(new byte[0], 0, 0, false, false);

    /* renamed from: d  reason: collision with root package name */
    private static final int f2497d;

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReference<Segment>[] f2498e;

    static {
        int highestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f2497d = highestOneBit;
        AtomicReference<Segment>[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i2 = 0; i2 < highestOneBit; i2++) {
            atomicReferenceArr[i2] = new AtomicReference<>();
        }
        f2498e = atomicReferenceArr;
    }

    private SegmentPool() {
    }

    private final AtomicReference<Segment> a() {
        return f2498e[(int) (Thread.currentThread().getId() & (f2497d - 1))];
    }

    public static final void b(Segment segment) {
        boolean z2;
        AtomicReference<Segment> a2;
        Segment segment2;
        Segment andSet;
        int i2;
        Intrinsics.f(segment, "segment");
        if (segment.f2492f == null && segment.f2493g == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (segment.f2490d || (andSet = (a2 = f2494a.a()).getAndSet((segment2 = f2496c))) == segment2) {
                return;
            }
            if (andSet != null) {
                i2 = andSet.f2489c;
            } else {
                i2 = 0;
            }
            if (i2 >= f2495b) {
                a2.set(andSet);
                return;
            }
            segment.f2492f = andSet;
            segment.f2488b = 0;
            segment.f2489c = i2 + 8192;
            a2.set(segment);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static final Segment c() {
        AtomicReference<Segment> a2 = f2494a.a();
        Segment segment = f2496c;
        Segment andSet = a2.getAndSet(segment);
        if (andSet == segment) {
            return new Segment();
        }
        if (andSet == null) {
            a2.set(null);
            return new Segment();
        }
        a2.set(andSet.f2492f);
        andSet.f2492f = null;
        andSet.f2489c = 0;
        return andSet;
    }
}
