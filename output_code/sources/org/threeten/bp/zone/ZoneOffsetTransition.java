package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZoneOffsetTransition implements Comparable<ZoneOffsetTransition>, Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final LocalDateTime f3428d;

    /* renamed from: e  reason: collision with root package name */
    private final ZoneOffset f3429e;

    /* renamed from: f  reason: collision with root package name */
    private final ZoneOffset f3430f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneOffsetTransition(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.f3428d = localDateTime;
        this.f3429e = zoneOffset;
        this.f3430f = zoneOffset2;
    }

    private int e() {
        return g().x() - h().x();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneOffsetTransition k(DataInput dataInput) {
        long b2 = Ser.b(dataInput);
        ZoneOffset d2 = Ser.d(dataInput);
        ZoneOffset d3 = Ser.d(dataInput);
        if (!d2.equals(d3)) {
            return new ZoneOffsetTransition(b2, d2, d3);
        }
        throw new IllegalArgumentException("Offsets must not be equal");
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(ZoneOffsetTransition zoneOffsetTransition) {
        return f().compareTo(zoneOffsetTransition.f());
    }

    public LocalDateTime b() {
        return this.f3428d.W(e());
    }

    public LocalDateTime c() {
        return this.f3428d;
    }

    public Duration d() {
        return Duration.l(e());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransition)) {
            return false;
        }
        ZoneOffsetTransition zoneOffsetTransition = (ZoneOffsetTransition) obj;
        if (this.f3428d.equals(zoneOffsetTransition.f3428d) && this.f3429e.equals(zoneOffsetTransition.f3429e) && this.f3430f.equals(zoneOffsetTransition.f3430f)) {
            return true;
        }
        return false;
    }

    public Instant f() {
        return this.f3428d.v(this.f3429e);
    }

    public ZoneOffset g() {
        return this.f3430f;
    }

    public ZoneOffset h() {
        return this.f3429e;
    }

    public int hashCode() {
        return (this.f3428d.hashCode() ^ this.f3429e.hashCode()) ^ Integer.rotateLeft(this.f3430f.hashCode(), 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ZoneOffset> i() {
        if (j()) {
            return Collections.emptyList();
        }
        return Arrays.asList(h(), g());
    }

    public boolean j() {
        return g().x() > h().x();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(DataOutput dataOutput) {
        Ser.e(toEpochSecond(), dataOutput);
        Ser.g(this.f3429e, dataOutput);
        Ser.g(this.f3430f, dataOutput);
    }

    public long toEpochSecond() {
        return this.f3428d.u(this.f3429e);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Transition[");
        if (j()) {
            str = "Gap";
        } else {
            str = "Overlap";
        }
        sb.append(str);
        sb.append(" at ");
        sb.append(this.f3428d);
        sb.append(this.f3429e);
        sb.append(" to ");
        sb.append(this.f3430f);
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneOffsetTransition(long j2, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.f3428d = LocalDateTime.Q(j2, 0, zoneOffset);
        this.f3429e = zoneOffset;
        this.f3430f = zoneOffset2;
    }
}
