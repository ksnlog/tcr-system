package org.threeten.bp.temporal;

import java.io.Serializable;
import org.threeten.bp.DateTimeException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ValueRange implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    private final long f3408d;

    /* renamed from: e  reason: collision with root package name */
    private final long f3409e;

    /* renamed from: f  reason: collision with root package name */
    private final long f3410f;

    /* renamed from: g  reason: collision with root package name */
    private final long f3411g;

    private ValueRange(long j2, long j3, long j4, long j5) {
        this.f3408d = j2;
        this.f3409e = j3;
        this.f3410f = j4;
        this.f3411g = j5;
    }

    public static ValueRange i(long j2, long j3) {
        if (j2 <= j3) {
            return new ValueRange(j2, j2, j3, j3);
        }
        throw new IllegalArgumentException("Minimum value must be less than maximum value");
    }

    public static ValueRange j(long j2, long j3, long j4) {
        return k(j2, j2, j3, j4);
    }

    public static ValueRange k(long j2, long j3, long j4, long j5) {
        if (j2 <= j3) {
            if (j4 <= j5) {
                if (j3 <= j5) {
                    return new ValueRange(j2, j3, j4, j5);
                }
                throw new IllegalArgumentException("Minimum value must be less than maximum value");
            }
            throw new IllegalArgumentException("Smallest maximum value must be less than largest maximum value");
        }
        throw new IllegalArgumentException("Smallest minimum value must be less than largest minimum value");
    }

    public int a(long j2, TemporalField temporalField) {
        if (g(j2)) {
            return (int) j2;
        }
        throw new DateTimeException("Invalid int value for " + temporalField + ": " + j2);
    }

    public long b(long j2, TemporalField temporalField) {
        if (!h(j2)) {
            if (temporalField != null) {
                throw new DateTimeException("Invalid value for " + temporalField + " (valid values " + this + "): " + j2);
            }
            throw new DateTimeException("Invalid value (valid values " + this + "): " + j2);
        }
        return j2;
    }

    public long c() {
        return this.f3411g;
    }

    public long d() {
        return this.f3408d;
    }

    public boolean e() {
        return this.f3408d == this.f3409e && this.f3410f == this.f3411g;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueRange)) {
            return false;
        }
        ValueRange valueRange = (ValueRange) obj;
        if (this.f3408d == valueRange.f3408d && this.f3409e == valueRange.f3409e && this.f3410f == valueRange.f3410f && this.f3411g == valueRange.f3411g) {
            return true;
        }
        return false;
    }

    public boolean f() {
        return d() >= -2147483648L && c() <= 2147483647L;
    }

    public boolean g(long j2) {
        return f() && h(j2);
    }

    public boolean h(long j2) {
        return j2 >= d() && j2 <= c();
    }

    public int hashCode() {
        long j2 = this.f3408d;
        long j3 = this.f3409e;
        long j4 = this.f3410f;
        long j5 = this.f3411g;
        long j6 = ((((((j2 + j3) << ((int) (j3 + 16))) >> ((int) (j4 + 48))) << ((int) (j4 + 32))) >> ((int) (32 + j5))) << ((int) (j5 + 48))) >> 16;
        return (int) (j6 ^ (j6 >>> 32));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3408d);
        if (this.f3408d != this.f3409e) {
            sb.append('/');
            sb.append(this.f3409e);
        }
        sb.append(" - ");
        sb.append(this.f3410f);
        if (this.f3410f != this.f3411g) {
            sb.append('/');
            sb.append(this.f3411g);
        }
        return sb.toString();
    }
}
