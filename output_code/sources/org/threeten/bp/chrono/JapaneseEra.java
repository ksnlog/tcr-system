package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.jdk8.DefaultInterfaceEra;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JapaneseEra extends DefaultInterfaceEra implements Serializable {

    /* renamed from: g  reason: collision with root package name */
    public static final JapaneseEra f3182g;

    /* renamed from: h  reason: collision with root package name */
    public static final JapaneseEra f3183h;

    /* renamed from: i  reason: collision with root package name */
    public static final JapaneseEra f3184i;

    /* renamed from: j  reason: collision with root package name */
    public static final JapaneseEra f3185j;

    /* renamed from: k  reason: collision with root package name */
    public static final JapaneseEra f3186k;

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicReference<JapaneseEra[]> f3187l;

    /* renamed from: d  reason: collision with root package name */
    private final int f3188d;

    /* renamed from: e  reason: collision with root package name */
    private final transient LocalDate f3189e;

    /* renamed from: f  reason: collision with root package name */
    private final transient String f3190f;

    static {
        JapaneseEra japaneseEra = new JapaneseEra(-1, LocalDate.U(1868, 9, 8), "Meiji");
        f3182g = japaneseEra;
        JapaneseEra japaneseEra2 = new JapaneseEra(0, LocalDate.U(1912, 7, 30), "Taisho");
        f3183h = japaneseEra2;
        JapaneseEra japaneseEra3 = new JapaneseEra(1, LocalDate.U(1926, 12, 25), "Showa");
        f3184i = japaneseEra3;
        JapaneseEra japaneseEra4 = new JapaneseEra(2, LocalDate.U(1989, 1, 8), "Heisei");
        f3185j = japaneseEra4;
        JapaneseEra japaneseEra5 = new JapaneseEra(3, LocalDate.U(2019, 5, 1), "Reiwa");
        f3186k = japaneseEra5;
        f3187l = new AtomicReference<>(new JapaneseEra[]{japaneseEra, japaneseEra2, japaneseEra3, japaneseEra4, japaneseEra5});
    }

    private JapaneseEra(int i2, LocalDate localDate, String str) {
        this.f3188d = i2;
        this.f3189e = localDate;
        this.f3190f = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra n(LocalDate localDate) {
        if (!localDate.s(f3182g.f3189e)) {
            JapaneseEra[] japaneseEraArr = f3187l.get();
            for (int length = japaneseEraArr.length - 1; length >= 0; length--) {
                JapaneseEra japaneseEra = japaneseEraArr[length];
                if (localDate.compareTo(japaneseEra.f3189e) >= 0) {
                    return japaneseEra;
                }
            }
            return null;
        }
        throw new DateTimeException("Date too early: " + localDate);
    }

    public static JapaneseEra o(int i2) {
        JapaneseEra[] japaneseEraArr = f3187l.get();
        if (i2 >= f3182g.f3188d && i2 <= japaneseEraArr[japaneseEraArr.length - 1].f3188d) {
            return japaneseEraArr[p(i2)];
        }
        throw new DateTimeException("japaneseEra is invalid");
    }

    private static int p(int i2) {
        return i2 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra q(DataInput dataInput) {
        return o(dataInput.readByte());
    }

    private Object readResolve() {
        try {
            return o(this.f3188d);
        } catch (DateTimeException e2) {
            InvalidObjectException invalidObjectException = new InvalidObjectException("Invalid era");
            invalidObjectException.initCause(e2);
            throw invalidObjectException;
        }
    }

    public static JapaneseEra[] s() {
        JapaneseEra[] japaneseEraArr = f3187l.get();
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        ChronoField chronoField = ChronoField.ERA;
        if (temporalField == chronoField) {
            return JapaneseChronology.f3172i.w(chronoField);
        }
        return super.a(temporalField);
    }

    @Override // org.threeten.bp.chrono.Era
    public int getValue() {
        return this.f3188d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate m() {
        int p2 = p(this.f3188d);
        JapaneseEra[] s2 = s();
        if (p2 >= s2.length - 1) {
            return LocalDate.f3026i;
        }
        return s2[p2 + 1].r().R(1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate r() {
        return this.f3189e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(DataOutput dataOutput) {
        dataOutput.writeByte(getValue());
    }

    public String toString() {
        return this.f3190f;
    }
}
