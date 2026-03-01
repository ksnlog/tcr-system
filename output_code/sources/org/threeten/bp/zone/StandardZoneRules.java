package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class StandardZoneRules extends ZoneRules implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final long[] f3414d;

    /* renamed from: e  reason: collision with root package name */
    private final ZoneOffset[] f3415e;

    /* renamed from: f  reason: collision with root package name */
    private final long[] f3416f;

    /* renamed from: g  reason: collision with root package name */
    private final LocalDateTime[] f3417g;

    /* renamed from: h  reason: collision with root package name */
    private final ZoneOffset[] f3418h;

    /* renamed from: i  reason: collision with root package name */
    private final ZoneOffsetTransitionRule[] f3419i;

    /* renamed from: j  reason: collision with root package name */
    private final ConcurrentMap<Integer, ZoneOffsetTransition[]> f3420j = new ConcurrentHashMap();

    private StandardZoneRules(long[] jArr, ZoneOffset[] zoneOffsetArr, long[] jArr2, ZoneOffset[] zoneOffsetArr2, ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr) {
        this.f3414d = jArr;
        this.f3415e = zoneOffsetArr;
        this.f3416f = jArr2;
        this.f3418h = zoneOffsetArr2;
        this.f3419i = zoneOffsetTransitionRuleArr;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < jArr2.length) {
            int i3 = i2 + 1;
            ZoneOffsetTransition zoneOffsetTransition = new ZoneOffsetTransition(jArr2[i2], zoneOffsetArr2[i2], zoneOffsetArr2[i3]);
            if (zoneOffsetTransition.j()) {
                arrayList.add(zoneOffsetTransition.c());
                arrayList.add(zoneOffsetTransition.b());
            } else {
                arrayList.add(zoneOffsetTransition.b());
                arrayList.add(zoneOffsetTransition.c());
            }
            i2 = i3;
        }
        this.f3417g = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
    }

    private Object g(LocalDateTime localDateTime, ZoneOffsetTransition zoneOffsetTransition) {
        LocalDateTime c2 = zoneOffsetTransition.c();
        if (zoneOffsetTransition.j()) {
            if (localDateTime.r(c2)) {
                return zoneOffsetTransition.h();
            }
            if (localDateTime.r(zoneOffsetTransition.b())) {
                return zoneOffsetTransition;
            }
            return zoneOffsetTransition.g();
        } else if (!localDateTime.r(c2)) {
            return zoneOffsetTransition.g();
        } else {
            if (localDateTime.r(zoneOffsetTransition.b())) {
                return zoneOffsetTransition.h();
            }
            return zoneOffsetTransition;
        }
    }

    private ZoneOffsetTransition[] h(int i2) {
        Integer valueOf = Integer.valueOf(i2);
        ZoneOffsetTransition[] zoneOffsetTransitionArr = this.f3420j.get(valueOf);
        if (zoneOffsetTransitionArr != null) {
            return zoneOffsetTransitionArr;
        }
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = this.f3419i;
        ZoneOffsetTransition[] zoneOffsetTransitionArr2 = new ZoneOffsetTransition[zoneOffsetTransitionRuleArr.length];
        for (int i3 = 0; i3 < zoneOffsetTransitionRuleArr.length; i3++) {
            zoneOffsetTransitionArr2[i3] = zoneOffsetTransitionRuleArr[i3].b(i2);
        }
        if (i2 < 2100) {
            this.f3420j.putIfAbsent(valueOf, zoneOffsetTransitionArr2);
        }
        return zoneOffsetTransitionArr2;
    }

    private int i(long j2, ZoneOffset zoneOffset) {
        return LocalDate.W(Jdk8Methods.e(j2 + zoneOffset.x(), 86400L)).M();
    }

    private Object j(LocalDateTime localDateTime) {
        int i2 = 0;
        if (this.f3419i.length > 0) {
            LocalDateTime[] localDateTimeArr = this.f3417g;
            if (localDateTimeArr.length == 0 || localDateTime.q(localDateTimeArr[localDateTimeArr.length - 1])) {
                ZoneOffsetTransition[] h2 = h(localDateTime.K());
                int length = h2.length;
                Object obj = null;
                while (i2 < length) {
                    ZoneOffsetTransition zoneOffsetTransition = h2[i2];
                    Object g2 = g(localDateTime, zoneOffsetTransition);
                    if (!(g2 instanceof ZoneOffsetTransition) && !g2.equals(zoneOffsetTransition.h())) {
                        i2++;
                        obj = g2;
                    } else {
                        return g2;
                    }
                }
                return obj;
            }
        }
        int binarySearch = Arrays.binarySearch(this.f3417g, localDateTime);
        if (binarySearch == -1) {
            return this.f3418h[0];
        }
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        } else {
            LocalDateTime[] localDateTimeArr2 = this.f3417g;
            if (binarySearch < localDateTimeArr2.length - 1) {
                int i3 = binarySearch + 1;
                if (localDateTimeArr2[binarySearch].equals(localDateTimeArr2[i3])) {
                    binarySearch = i3;
                }
            }
        }
        if ((binarySearch & 1) == 0) {
            LocalDateTime[] localDateTimeArr3 = this.f3417g;
            LocalDateTime localDateTime2 = localDateTimeArr3[binarySearch];
            LocalDateTime localDateTime3 = localDateTimeArr3[binarySearch + 1];
            ZoneOffset[] zoneOffsetArr = this.f3418h;
            int i4 = binarySearch / 2;
            ZoneOffset zoneOffset = zoneOffsetArr[i4];
            ZoneOffset zoneOffset2 = zoneOffsetArr[i4 + 1];
            if (zoneOffset2.x() > zoneOffset.x()) {
                return new ZoneOffsetTransition(localDateTime2, zoneOffset, zoneOffset2);
            }
            return new ZoneOffsetTransition(localDateTime3, zoneOffset, zoneOffset2);
        }
        return this.f3418h[(binarySearch / 2) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StandardZoneRules k(DataInput dataInput) {
        int readInt = dataInput.readInt();
        long[] jArr = new long[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            jArr[i2] = Ser.b(dataInput);
        }
        int i3 = readInt + 1;
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            zoneOffsetArr[i4] = Ser.d(dataInput);
        }
        int readInt2 = dataInput.readInt();
        long[] jArr2 = new long[readInt2];
        for (int i5 = 0; i5 < readInt2; i5++) {
            jArr2[i5] = Ser.b(dataInput);
        }
        int i6 = readInt2 + 1;
        ZoneOffset[] zoneOffsetArr2 = new ZoneOffset[i6];
        for (int i7 = 0; i7 < i6; i7++) {
            zoneOffsetArr2[i7] = Ser.d(dataInput);
        }
        int readByte = dataInput.readByte();
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = new ZoneOffsetTransitionRule[readByte];
        for (int i8 = 0; i8 < readByte; i8++) {
            zoneOffsetTransitionRuleArr[i8] = ZoneOffsetTransitionRule.c(dataInput);
        }
        return new StandardZoneRules(jArr, zoneOffsetArr, jArr2, zoneOffsetArr2, zoneOffsetTransitionRuleArr);
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffset a(Instant instant) {
        ZoneOffset[] zoneOffsetArr;
        long p2 = instant.p();
        if (this.f3419i.length > 0) {
            long[] jArr = this.f3416f;
            if (jArr.length == 0 || p2 > jArr[jArr.length - 1]) {
                ZoneOffsetTransition[] h2 = h(i(p2, this.f3418h[zoneOffsetArr.length - 1]));
                ZoneOffsetTransition zoneOffsetTransition = null;
                for (int i2 = 0; i2 < h2.length; i2++) {
                    zoneOffsetTransition = h2[i2];
                    if (p2 < zoneOffsetTransition.toEpochSecond()) {
                        return zoneOffsetTransition.h();
                    }
                }
                return zoneOffsetTransition.g();
            }
        }
        int binarySearch = Arrays.binarySearch(this.f3416f, p2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return this.f3418h[binarySearch + 1];
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffsetTransition b(LocalDateTime localDateTime) {
        Object j2 = j(localDateTime);
        if (j2 instanceof ZoneOffsetTransition) {
            return (ZoneOffsetTransition) j2;
        }
        return null;
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public List<ZoneOffset> c(LocalDateTime localDateTime) {
        Object j2 = j(localDateTime);
        if (j2 instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition) j2).i();
        }
        return Collections.singletonList((ZoneOffset) j2);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean d() {
        return this.f3416f.length == 0 && this.f3419i.length == 0 && this.f3418h[0].equals(this.f3415e[0]);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean e(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return c(localDateTime).contains(zoneOffset);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StandardZoneRules) {
            StandardZoneRules standardZoneRules = (StandardZoneRules) obj;
            if (Arrays.equals(this.f3414d, standardZoneRules.f3414d) && Arrays.equals(this.f3415e, standardZoneRules.f3415e) && Arrays.equals(this.f3416f, standardZoneRules.f3416f) && Arrays.equals(this.f3418h, standardZoneRules.f3418h) && Arrays.equals(this.f3419i, standardZoneRules.f3419i)) {
                return true;
            }
            return false;
        }
        if ((obj instanceof ZoneRules.Fixed) && d()) {
            Instant instant = Instant.f3017f;
            if (a(instant).equals(((ZoneRules.Fixed) obj).a(instant))) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.f3414d) ^ Arrays.hashCode(this.f3415e)) ^ Arrays.hashCode(this.f3416f)) ^ Arrays.hashCode(this.f3418h)) ^ Arrays.hashCode(this.f3419i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(DataOutput dataOutput) {
        dataOutput.writeInt(this.f3414d.length);
        for (long j2 : this.f3414d) {
            Ser.e(j2, dataOutput);
        }
        for (ZoneOffset zoneOffset : this.f3415e) {
            Ser.g(zoneOffset, dataOutput);
        }
        dataOutput.writeInt(this.f3416f.length);
        for (long j3 : this.f3416f) {
            Ser.e(j3, dataOutput);
        }
        for (ZoneOffset zoneOffset2 : this.f3418h) {
            Ser.g(zoneOffset2, dataOutput);
        }
        dataOutput.writeByte(this.f3419i.length);
        for (ZoneOffsetTransitionRule zoneOffsetTransitionRule : this.f3419i) {
            zoneOffsetTransitionRule.d(dataOutput);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StandardZoneRules[currentStandardOffset=");
        ZoneOffset[] zoneOffsetArr = this.f3415e;
        sb.append(zoneOffsetArr[zoneOffsetArr.length - 1]);
        sb.append("]");
        return sb.toString();
    }
}
