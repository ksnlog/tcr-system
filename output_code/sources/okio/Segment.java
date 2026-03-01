package okio;

import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Segment {

    /* renamed from: h  reason: collision with root package name */
    public static final Companion f2486h = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f2487a;

    /* renamed from: b  reason: collision with root package name */
    public int f2488b;

    /* renamed from: c  reason: collision with root package name */
    public int f2489c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f2490d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f2491e;

    /* renamed from: f  reason: collision with root package name */
    public Segment f2492f;

    /* renamed from: g  reason: collision with root package name */
    public Segment f2493g;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Segment() {
        this.f2487a = new byte[8192];
        this.f2491e = true;
        this.f2490d = false;
    }

    public final void a() {
        boolean z2;
        Segment segment = this.f2493g;
        int i2 = 0;
        if (segment != this) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            Intrinsics.c(segment);
            if (!segment.f2491e) {
                return;
            }
            int i3 = this.f2489c - this.f2488b;
            Segment segment2 = this.f2493g;
            Intrinsics.c(segment2);
            int i4 = 8192 - segment2.f2489c;
            Segment segment3 = this.f2493g;
            Intrinsics.c(segment3);
            if (!segment3.f2490d) {
                Segment segment4 = this.f2493g;
                Intrinsics.c(segment4);
                i2 = segment4.f2488b;
            }
            if (i3 > i4 + i2) {
                return;
            }
            Segment segment5 = this.f2493g;
            Intrinsics.c(segment5);
            f(segment5, i3);
            b();
            SegmentPool.b(this);
            return;
        }
        throw new IllegalStateException("cannot compact".toString());
    }

    public final Segment b() {
        Segment segment = this.f2492f;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.f2493g;
        Intrinsics.c(segment2);
        segment2.f2492f = this.f2492f;
        Segment segment3 = this.f2492f;
        Intrinsics.c(segment3);
        segment3.f2493g = this.f2493g;
        this.f2492f = null;
        this.f2493g = null;
        return segment;
    }

    public final Segment c(Segment segment) {
        Intrinsics.f(segment, "segment");
        segment.f2493g = this;
        segment.f2492f = this.f2492f;
        Segment segment2 = this.f2492f;
        Intrinsics.c(segment2);
        segment2.f2493g = segment;
        this.f2492f = segment;
        return segment;
    }

    public final Segment d() {
        this.f2490d = true;
        return new Segment(this.f2487a, this.f2488b, this.f2489c, true, false);
    }

    public final Segment e(int i2) {
        boolean z2;
        Segment c2;
        if (i2 > 0 && i2 <= this.f2489c - this.f2488b) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i2 >= 1024) {
                c2 = d();
            } else {
                c2 = SegmentPool.c();
                byte[] bArr = this.f2487a;
                byte[] bArr2 = c2.f2487a;
                int i3 = this.f2488b;
                ArraysKt___ArraysJvmKt.f(bArr, bArr2, 0, i3, i3 + i2, 2, null);
            }
            c2.f2489c = c2.f2488b + i2;
            this.f2488b += i2;
            Segment segment = this.f2493g;
            Intrinsics.c(segment);
            segment.c(c2);
            return c2;
        }
        throw new IllegalArgumentException("byteCount out of range".toString());
    }

    public final void f(Segment sink, int i2) {
        Intrinsics.f(sink, "sink");
        if (sink.f2491e) {
            int i3 = sink.f2489c;
            if (i3 + i2 > 8192) {
                if (!sink.f2490d) {
                    int i4 = sink.f2488b;
                    if ((i3 + i2) - i4 <= 8192) {
                        byte[] bArr = sink.f2487a;
                        ArraysKt___ArraysJvmKt.f(bArr, bArr, 0, i4, i3, 2, null);
                        sink.f2489c -= sink.f2488b;
                        sink.f2488b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            byte[] bArr2 = this.f2487a;
            byte[] bArr3 = sink.f2487a;
            int i5 = sink.f2489c;
            int i6 = this.f2488b;
            ArraysKt___ArraysJvmKt.d(bArr2, bArr3, i5, i6, i6 + i2);
            sink.f2489c += i2;
            this.f2488b += i2;
            return;
        }
        throw new IllegalStateException("only owner can write".toString());
    }

    public Segment(byte[] data, int i2, int i3, boolean z2, boolean z3) {
        Intrinsics.f(data, "data");
        this.f2487a = data;
        this.f2488b = i2;
        this.f2489c = i3;
        this.f2490d = z2;
        this.f2491e = z3;
    }
}
