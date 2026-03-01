package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.zone.ZoneRules;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZoneOffset extends ZoneId implements TemporalAccessor, TemporalAdjuster, Comparable<ZoneOffset> {

    /* renamed from: h  reason: collision with root package name */
    public static final TemporalQuery<ZoneOffset> f3105h = new TemporalQuery<ZoneOffset>() { // from class: org.threeten.bp.ZoneOffset.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneOffset a(TemporalAccessor temporalAccessor) {
            return ZoneOffset.w(temporalAccessor);
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private static final ConcurrentMap<Integer, ZoneOffset> f3106i = new ConcurrentHashMap(16, 0.75f, 4);

    /* renamed from: j  reason: collision with root package name */
    private static final ConcurrentMap<String, ZoneOffset> f3107j = new ConcurrentHashMap(16, 0.75f, 4);

    /* renamed from: k  reason: collision with root package name */
    public static final ZoneOffset f3108k = A(0);

    /* renamed from: l  reason: collision with root package name */
    public static final ZoneOffset f3109l = A(-64800);

    /* renamed from: m  reason: collision with root package name */
    public static final ZoneOffset f3110m = A(64800);

    /* renamed from: f  reason: collision with root package name */
    private final int f3111f;

    /* renamed from: g  reason: collision with root package name */
    private final transient String f3112g;

    private ZoneOffset(int i2) {
        this.f3111f = i2;
        this.f3112g = u(i2);
    }

    public static ZoneOffset A(int i2) {
        if (Math.abs(i2) <= 64800) {
            if (i2 % 900 == 0) {
                Integer valueOf = Integer.valueOf(i2);
                ConcurrentMap<Integer, ZoneOffset> concurrentMap = f3106i;
                ZoneOffset zoneOffset = concurrentMap.get(valueOf);
                if (zoneOffset == null) {
                    concurrentMap.putIfAbsent(valueOf, new ZoneOffset(i2));
                    ZoneOffset zoneOffset2 = concurrentMap.get(valueOf);
                    f3107j.putIfAbsent(zoneOffset2.m(), zoneOffset2);
                    return zoneOffset2;
                }
                return zoneOffset;
            }
            return new ZoneOffset(i2);
        }
        throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
    }

    private static int B(CharSequence charSequence, int i2, boolean z2) {
        if (z2 && charSequence.charAt(i2 - 1) != ':') {
            throw new DateTimeException("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) charSequence));
        }
        char charAt = charSequence.charAt(i2);
        char charAt2 = charSequence.charAt(i2 + 1);
        if (charAt >= '0' && charAt <= '9' && charAt2 >= '0' && charAt2 <= '9') {
            return ((charAt - '0') * 10) + (charAt2 - '0');
        }
        throw new DateTimeException("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneOffset C(DataInput dataInput) {
        byte readByte = dataInput.readByte();
        if (readByte == Byte.MAX_VALUE) {
            return A(dataInput.readInt());
        }
        return A(readByte * 900);
    }

    private static int D(int i2, int i3, int i4) {
        return (i2 * 3600) + (i3 * 60) + i4;
    }

    private static void E(int i2, int i3, int i4) {
        if (i2 >= -18 && i2 <= 18) {
            if (i2 > 0) {
                if (i3 < 0 || i4 < 0) {
                    throw new DateTimeException("Zone offset minutes and seconds must be positive because hours is positive");
                }
            } else if (i2 < 0) {
                if (i3 > 0 || i4 > 0) {
                    throw new DateTimeException("Zone offset minutes and seconds must be negative because hours is negative");
                }
            } else if ((i3 > 0 && i4 < 0) || (i3 < 0 && i4 > 0)) {
                throw new DateTimeException("Zone offset minutes and seconds must have the same sign");
            }
            if (Math.abs(i3) <= 59) {
                if (Math.abs(i4) <= 59) {
                    if (Math.abs(i2) == 18) {
                        if (Math.abs(i3) > 0 || Math.abs(i4) > 0) {
                            throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
                        }
                        return;
                    }
                    return;
                }
                throw new DateTimeException("Zone offset seconds not in valid range: abs(value) " + Math.abs(i4) + " is not in the range 0 to 59");
            }
            throw new DateTimeException("Zone offset minutes not in valid range: abs(value) " + Math.abs(i3) + " is not in the range 0 to 59");
        }
        throw new DateTimeException("Zone offset hours not in valid range: value " + i2 + " is not in the range -18 to 18");
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private static String u(int i2) {
        String str;
        String str2;
        String str3;
        if (i2 == 0) {
            return "Z";
        }
        int abs = Math.abs(i2);
        StringBuilder sb = new StringBuilder();
        int i3 = abs / 3600;
        int i4 = (abs / 60) % 60;
        if (i2 < 0) {
            str = "-";
        } else {
            str = "+";
        }
        sb.append(str);
        if (i3 < 10) {
            str2 = "0";
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(i3);
        String str4 = ":0";
        if (i4 >= 10) {
            str3 = ":";
        } else {
            str3 = str4;
        }
        sb.append(str3);
        sb.append(i4);
        int i5 = abs % 60;
        if (i5 != 0) {
            if (i5 >= 10) {
                str4 = ":";
            }
            sb.append(str4);
            sb.append(i5);
        }
        return sb.toString();
    }

    public static ZoneOffset w(TemporalAccessor temporalAccessor) {
        ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.b(TemporalQueries.d());
        if (zoneOffset != null) {
            return zoneOffset;
        }
        throw new DateTimeException("Unable to obtain ZoneOffset from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    private Object writeReplace() {
        return new Ser((byte) 8, this);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0099 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.threeten.bp.ZoneOffset y(java.lang.String r7) {
        /*
            java.lang.String r0 = "offsetId"
            org.threeten.bp.jdk8.Jdk8Methods.i(r7, r0)
            java.util.concurrent.ConcurrentMap<java.lang.String, org.threeten.bp.ZoneOffset> r0 = org.threeten.bp.ZoneOffset.f3107j
            java.lang.Object r0 = r0.get(r7)
            org.threeten.bp.ZoneOffset r0 = (org.threeten.bp.ZoneOffset) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            int r0 = r7.length()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L6d
            r1 = 3
            if (r0 == r1) goto L89
            r4 = 5
            if (r0 == r4) goto L64
            r5 = 6
            r6 = 4
            if (r0 == r5) goto L5b
            r5 = 7
            if (r0 == r5) goto L4e
            r1 = 9
            if (r0 != r1) goto L37
            int r0 = B(r7, r2, r3)
            int r1 = B(r7, r6, r2)
            int r2 = B(r7, r5, r2)
            goto L8f
        L37:
            org.threeten.bp.DateTimeException r0 = new org.threeten.bp.DateTimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid ID for ZoneOffset, invalid format: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        L4e:
            int r0 = B(r7, r2, r3)
            int r1 = B(r7, r1, r3)
            int r2 = B(r7, r4, r3)
            goto L8f
        L5b:
            int r0 = B(r7, r2, r3)
            int r1 = B(r7, r6, r2)
            goto L8e
        L64:
            int r0 = B(r7, r2, r3)
            int r1 = B(r7, r1, r3)
            goto L8e
        L6d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            char r1 = r7.charAt(r3)
            r0.append(r1)
            java.lang.String r1 = "0"
            r0.append(r1)
            char r7 = r7.charAt(r2)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
        L89:
            int r0 = B(r7, r2, r3)
            r1 = 0
        L8e:
            r2 = 0
        L8f:
            char r3 = r7.charAt(r3)
            r4 = 43
            r5 = 45
            if (r3 == r4) goto Lb3
            if (r3 != r5) goto L9c
            goto Lb3
        L9c:
            org.threeten.bp.DateTimeException r0 = new org.threeten.bp.DateTimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid ID for ZoneOffset, plus/minus not found when expected: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        Lb3:
            if (r3 != r5) goto Lbd
            int r7 = -r0
            int r0 = -r1
            int r1 = -r2
            org.threeten.bp.ZoneOffset r7 = z(r7, r0, r1)
            return r7
        Lbd:
            org.threeten.bp.ZoneOffset r7 = z(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.ZoneOffset.y(java.lang.String):org.threeten.bp.ZoneOffset");
    }

    public static ZoneOffset z(int i2, int i3, int i4) {
        E(i2, i3, i4);
        return A(D(i2, i3, i4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(DataOutput dataOutput) {
        int i2;
        int i3 = this.f3111f;
        if (i3 % 900 == 0) {
            i2 = i3 / 900;
        } else {
            i2 = 127;
        }
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i3);
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return temporalField.f();
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.d(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R b(TemporalQuery<R> temporalQuery) {
        if (temporalQuery != TemporalQueries.d() && temporalQuery != TemporalQueries.f()) {
            if (temporalQuery != TemporalQueries.b() && temporalQuery != TemporalQueries.c() && temporalQuery != TemporalQueries.e() && temporalQuery != TemporalQueries.a() && temporalQuery != TemporalQueries.g()) {
                return temporalQuery.a(this);
            }
            return null;
        }
        return this;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean d(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField == ChronoField.OFFSET_SECONDS) {
                return true;
            }
            return false;
        } else if (temporalField != null && temporalField.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    @Override // org.threeten.bp.ZoneId
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ZoneOffset) && this.f3111f == ((ZoneOffset) obj).f3111f) {
            return true;
        }
        return false;
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int f(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return this.f3111f;
        }
        if (!(temporalField instanceof ChronoField)) {
            return a(temporalField).a(h(temporalField), temporalField);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return this.f3111f;
        }
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.c(this);
        }
        throw new DateTimeException("Unsupported field: " + temporalField);
    }

    @Override // org.threeten.bp.ZoneId
    public int hashCode() {
        return this.f3111f;
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal j(Temporal temporal) {
        return temporal.e(ChronoField.OFFSET_SECONDS, this.f3111f);
    }

    @Override // org.threeten.bp.ZoneId
    public String m() {
        return this.f3112g;
    }

    @Override // org.threeten.bp.ZoneId
    public ZoneRules n() {
        return ZoneRules.f(this);
    }

    @Override // org.threeten.bp.ZoneId
    void t(DataOutput dataOutput) {
        dataOutput.writeByte(8);
        F(dataOutput);
    }

    @Override // org.threeten.bp.ZoneId
    public String toString() {
        return this.f3112g;
    }

    @Override // java.lang.Comparable
    /* renamed from: v */
    public int compareTo(ZoneOffset zoneOffset) {
        return zoneOffset.f3111f - this.f3111f;
    }

    public int x() {
        return this.f3111f;
    }
}
