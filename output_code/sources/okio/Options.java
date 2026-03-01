package okio;

import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* renamed from: g  reason: collision with root package name */
    public static final Companion f2474g = new Companion(null);

    /* renamed from: e  reason: collision with root package name */
    private final ByteString[] f2475e;

    /* renamed from: f  reason: collision with root package name */
    private final int[] f2476f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void a(long j2, Buffer buffer, int i2, List<? extends ByteString> list, int i3, int i4, List<Integer> list2) {
            boolean z2;
            int i5;
            int i6;
            boolean z3;
            int i7;
            int i8;
            Buffer buffer2;
            boolean z4;
            int i9 = i2;
            if (i3 < i4) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                for (int i10 = i3; i10 < i4; i10++) {
                    if (list.get(i10).u() >= i9) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (!z4) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
                ByteString byteString = list.get(i3);
                ByteString byteString2 = list.get(i4 - 1);
                if (i9 == byteString.u()) {
                    int i11 = i3 + 1;
                    i5 = i11;
                    i6 = list2.get(i3).intValue();
                    byteString = list.get(i11);
                } else {
                    i5 = i3;
                    i6 = -1;
                }
                if (byteString.f(i9) != byteString2.f(i9)) {
                    int i12 = 1;
                    for (int i13 = i5 + 1; i13 < i4; i13++) {
                        if (list.get(i13 - 1).f(i9) != list.get(i13).f(i9)) {
                            i12++;
                        }
                    }
                    long c2 = j2 + c(buffer) + 2 + (i12 * 2);
                    buffer.writeInt(i12);
                    buffer.writeInt(i6);
                    for (int i14 = i5; i14 < i4; i14++) {
                        byte f2 = list.get(i14).f(i9);
                        if (i14 == i5 || f2 != list.get(i14 - 1).f(i9)) {
                            buffer.writeInt(f2 & 255);
                        }
                    }
                    Buffer buffer3 = new Buffer();
                    while (i5 < i4) {
                        byte f3 = list.get(i5).f(i9);
                        int i15 = i5 + 1;
                        int i16 = i15;
                        while (true) {
                            if (i16 < i4) {
                                if (f3 != list.get(i16).f(i9)) {
                                    i7 = i16;
                                    break;
                                }
                                i16++;
                            } else {
                                i7 = i4;
                                break;
                            }
                        }
                        if (i15 == i7 && i9 + 1 == list.get(i5).u()) {
                            buffer.writeInt(list2.get(i5).intValue());
                            i8 = i7;
                            buffer2 = buffer3;
                        } else {
                            buffer.writeInt(((int) (c2 + c(buffer3))) * (-1));
                            i8 = i7;
                            buffer2 = buffer3;
                            a(c2, buffer3, i9 + 1, list, i5, i7, list2);
                        }
                        buffer3 = buffer2;
                        i5 = i8;
                    }
                    buffer.V(buffer3);
                    return;
                }
                int min = Math.min(byteString.u(), byteString2.u());
                int i17 = 0;
                for (int i18 = i9; i18 < min && byteString.f(i18) == byteString2.f(i18); i18++) {
                    i17++;
                }
                long c3 = j2 + c(buffer) + 2 + i17 + 1;
                buffer.writeInt(-i17);
                buffer.writeInt(i6);
                int i19 = i9 + i17;
                while (i9 < i19) {
                    buffer.writeInt(byteString.f(i9) & 255);
                    i9++;
                }
                if (i5 + 1 == i4) {
                    if (i19 == list.get(i5).u()) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        buffer.writeInt(list2.get(i5).intValue());
                        return;
                    }
                    throw new IllegalStateException("Check failed.".toString());
                }
                Buffer buffer4 = new Buffer();
                buffer.writeInt(((int) (c(buffer4) + c3)) * (-1));
                a(c3, buffer4, i19, list, i5, i4, list2);
                buffer.V(buffer4);
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        static /* synthetic */ void b(Companion companion, long j2, Buffer buffer, int i2, List list, int i3, int i4, List list2, int i5, Object obj) {
            long j3;
            int i6;
            int i7;
            int i8;
            if ((i5 & 1) != 0) {
                j3 = 0;
            } else {
                j3 = j2;
            }
            if ((i5 & 4) != 0) {
                i6 = 0;
            } else {
                i6 = i2;
            }
            if ((i5 & 16) != 0) {
                i7 = 0;
            } else {
                i7 = i3;
            }
            if ((i5 & 32) != 0) {
                i8 = list.size();
            } else {
                i8 = i4;
            }
            companion.a(j3, buffer, i6, list, i7, i8, list2);
        }

        private final long c(Buffer buffer) {
            return buffer.size() / 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x00e9, code lost:
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final okio.Options d(okio.ByteString... r17) {
            /*
                Method dump skipped, instructions count: 322
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Options.Companion.d(okio.ByteString[]):okio.Options");
        }
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.f2475e = byteStringArr;
        this.f2476f = iArr;
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    public static final Options h(ByteString... byteStringArr) {
        return f2474g.d(byteStringArr);
    }

    @Override // kotlin.collections.AbstractCollection
    public int a() {
        return this.f2475e.length;
    }

    public /* bridge */ boolean b(ByteString byteString) {
        return super.contains(byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: c */
    public ByteString get(int i2) {
        return this.f2475e[i2];
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof ByteString)) {
            return false;
        }
        return b((ByteString) obj);
    }

    public final ByteString[] d() {
        return this.f2475e;
    }

    public final int[] e() {
        return this.f2476f;
    }

    public /* bridge */ int f(ByteString byteString) {
        return super.indexOf(byteString);
    }

    public /* bridge */ int g(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof ByteString)) {
            return -1;
        }
        return f((ByteString) obj);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof ByteString)) {
            return -1;
        }
        return g((ByteString) obj);
    }
}
