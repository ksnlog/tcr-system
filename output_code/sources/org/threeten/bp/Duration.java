package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Duration implements TemporalAmount, Comparable<Duration>, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final Duration f3012f = new Duration(0, 0);

    /* renamed from: g  reason: collision with root package name */
    private static final BigInteger f3013g = BigInteger.valueOf(1000000000);

    /* renamed from: h  reason: collision with root package name */
    private static final Pattern f3014h = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?", 2);

    /* renamed from: d  reason: collision with root package name */
    private final long f3015d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3016e;

    private Duration(long j2, int i2) {
        this.f3015d = j2;
        this.f3016e = i2;
    }

    public static Duration c(Temporal temporal, Temporal temporal2) {
        ChronoUnit chronoUnit = ChronoUnit.SECONDS;
        long l2 = temporal.l(temporal2, chronoUnit);
        ChronoField chronoField = ChronoField.NANO_OF_SECOND;
        long j2 = 0;
        if (temporal.d(chronoField) && temporal2.d(chronoField)) {
            try {
                long h2 = temporal.h(chronoField);
                long h3 = temporal2.h(chronoField) - h2;
                if (l2 > 0 && h3 < 0) {
                    h3 += 1000000000;
                } else if (l2 < 0 && h3 > 0) {
                    h3 -= 1000000000;
                } else if (l2 == 0 && h3 != 0) {
                    try {
                        l2 = temporal.l(temporal2.e(chronoField, h2), chronoUnit);
                    } catch (ArithmeticException | DateTimeException unused) {
                    }
                }
                j2 = h3;
            } catch (ArithmeticException | DateTimeException unused2) {
            }
        }
        return m(l2, j2);
    }

    private static Duration e(long j2, int i2) {
        if ((i2 | j2) == 0) {
            return f3012f;
        }
        return new Duration(j2, i2);
    }

    private static Duration f(BigDecimal bigDecimal) {
        BigInteger bigIntegerExact = bigDecimal.movePointRight(9).toBigIntegerExact();
        BigInteger[] divideAndRemainder = bigIntegerExact.divideAndRemainder(f3013g);
        if (divideAndRemainder[0].bitLength() <= 63) {
            return m(divideAndRemainder[0].longValue(), divideAndRemainder[1].intValue());
        }
        throw new ArithmeticException("Exceeds capacity of Duration: " + bigIntegerExact);
    }

    public static Duration k(long j2) {
        long j3 = j2 / 1000000000;
        int i2 = (int) (j2 % 1000000000);
        if (i2 < 0) {
            i2 += 1000000000;
            j3--;
        }
        return e(j3, i2);
    }

    public static Duration l(long j2) {
        return e(j2, 0);
    }

    public static Duration m(long j2, long j3) {
        return e(Jdk8Methods.k(j2, Jdk8Methods.e(j3, 1000000000L)), Jdk8Methods.g(j3, 1000000000));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Duration n(DataInput dataInput) {
        return m(dataInput.readLong(), dataInput.readInt());
    }

    private BigDecimal p() {
        return BigDecimal.valueOf(this.f3015d).add(BigDecimal.valueOf(this.f3016e, 9));
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    @Override // org.threeten.bp.temporal.TemporalAmount
    public Temporal a(Temporal temporal) {
        long j2 = this.f3015d;
        if (j2 != 0) {
            temporal = temporal.i(j2, ChronoUnit.SECONDS);
        }
        int i2 = this.f3016e;
        if (i2 != 0) {
            return temporal.i(i2, ChronoUnit.NANOS);
        }
        return temporal;
    }

    public Duration b() {
        return h() ? j() : this;
    }

    @Override // java.lang.Comparable
    /* renamed from: d */
    public int compareTo(Duration duration) {
        int b2 = Jdk8Methods.b(this.f3015d, duration.f3015d);
        if (b2 != 0) {
            return b2;
        }
        return this.f3016e - duration.f3016e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        if (this.f3015d == duration.f3015d && this.f3016e == duration.f3016e) {
            return true;
        }
        return false;
    }

    public long g() {
        return this.f3015d;
    }

    public boolean h() {
        return this.f3015d < 0;
    }

    public int hashCode() {
        long j2 = this.f3015d;
        return ((int) (j2 ^ (j2 >>> 32))) + (this.f3016e * 51);
    }

    public Duration i(long j2) {
        if (j2 == 0) {
            return f3012f;
        }
        if (j2 == 1) {
            return this;
        }
        return f(p().multiply(BigDecimal.valueOf(j2)));
    }

    public Duration j() {
        return i(-1L);
    }

    public long o() {
        return Jdk8Methods.k(Jdk8Methods.l(this.f3015d, 1000000000), this.f3016e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(DataOutput dataOutput) {
        dataOutput.writeLong(this.f3015d);
        dataOutput.writeInt(this.f3016e);
    }

    public String toString() {
        if (this == f3012f) {
            return "PT0S";
        }
        long j2 = this.f3015d;
        long j3 = j2 / 3600;
        int i2 = (int) ((j2 % 3600) / 60);
        int i3 = (int) (j2 % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j3 != 0) {
            sb.append(j3);
            sb.append('H');
        }
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        if (i3 == 0 && this.f3016e == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (i3 < 0 && this.f3016e > 0) {
            if (i3 == -1) {
                sb.append("-0");
            } else {
                sb.append(i3 + 1);
            }
        } else {
            sb.append(i3);
        }
        if (this.f3016e > 0) {
            int length = sb.length();
            if (i3 < 0) {
                sb.append(2000000000 - this.f3016e);
            } else {
                sb.append(this.f3016e + 1000000000);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, '.');
        }
        sb.append('S');
        return sb.toString();
    }
}
