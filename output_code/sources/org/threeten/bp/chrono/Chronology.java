package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Chronology implements Comparable<Chronology> {

    /* renamed from: d  reason: collision with root package name */
    public static final TemporalQuery<Chronology> f3133d = new TemporalQuery<Chronology>() { // from class: org.threeten.bp.chrono.Chronology.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public Chronology a(TemporalAccessor temporalAccessor) {
            return Chronology.g(temporalAccessor);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    private static final ConcurrentHashMap<String, Chronology> f3134e = new ConcurrentHashMap<>();

    /* renamed from: f  reason: collision with root package name */
    private static final ConcurrentHashMap<String, Chronology> f3135f = new ConcurrentHashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private static final Method f3136g;

    static {
        Method method;
        try {
            method = Locale.class.getMethod("getUnicodeLocaleType", String.class);
        } catch (Throwable unused) {
            method = null;
        }
        f3136g = method;
    }

    public static Chronology g(TemporalAccessor temporalAccessor) {
        Jdk8Methods.i(temporalAccessor, "temporal");
        Chronology chronology = (Chronology) temporalAccessor.b(TemporalQueries.a());
        if (chronology == null) {
            return IsoChronology.f3167h;
        }
        return chronology;
    }

    private static void j() {
        ConcurrentHashMap<String, Chronology> concurrentHashMap = f3134e;
        if (concurrentHashMap.isEmpty()) {
            n(IsoChronology.f3167h);
            n(ThaiBuddhistChronology.f3200h);
            n(MinguoChronology.f3191h);
            n(JapaneseChronology.f3172i);
            HijrahChronology hijrahChronology = HijrahChronology.f3137h;
            n(hijrahChronology);
            concurrentHashMap.putIfAbsent("Hijrah", hijrahChronology);
            f3135f.putIfAbsent("islamic", hijrahChronology);
            Iterator it = ServiceLoader.load(Chronology.class, Chronology.class.getClassLoader()).iterator();
            while (it.hasNext()) {
                Chronology chronology = (Chronology) it.next();
                f3134e.putIfAbsent(chronology.i(), chronology);
                String h2 = chronology.h();
                if (h2 != null) {
                    f3135f.putIfAbsent(h2, chronology);
                }
            }
        }
    }

    public static Chronology l(String str) {
        j();
        Chronology chronology = f3134e.get(str);
        if (chronology != null) {
            return chronology;
        }
        Chronology chronology2 = f3135f.get(str);
        if (chronology2 != null) {
            return chronology2;
        }
        throw new DateTimeException("Unknown chronology: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Chronology m(DataInput dataInput) {
        return l(dataInput.readUTF());
    }

    private static void n(Chronology chronology) {
        f3134e.putIfAbsent(chronology.i(), chronology);
        String h2 = chronology.h();
        if (h2 != null) {
            f3135f.putIfAbsent(h2, chronology);
        }
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 11, this);
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(Chronology chronology) {
        return i().compareTo(chronology.i());
    }

    public abstract ChronoLocalDate b(TemporalAccessor temporalAccessor);

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> D c(Temporal temporal) {
        D d2 = (D) temporal;
        if (equals(d2.p())) {
            return d2;
        }
        throw new ClassCastException("Chrono mismatch, expected: " + i() + ", actual: " + d2.p().i());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> ChronoLocalDateTimeImpl<D> d(Temporal temporal) {
        ChronoLocalDateTimeImpl<D> chronoLocalDateTimeImpl = (ChronoLocalDateTimeImpl) temporal;
        if (equals(chronoLocalDateTimeImpl.w().p())) {
            return chronoLocalDateTimeImpl;
        }
        throw new ClassCastException("Chrono mismatch, required: " + i() + ", supplied: " + chronoLocalDateTimeImpl.w().p().i());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <D extends ChronoLocalDate> ChronoZonedDateTimeImpl<D> e(Temporal temporal) {
        ChronoZonedDateTimeImpl<D> chronoZonedDateTimeImpl = (ChronoZonedDateTimeImpl) temporal;
        if (equals(chronoZonedDateTimeImpl.v().p())) {
            return chronoZonedDateTimeImpl;
        }
        throw new ClassCastException("Chrono mismatch, required: " + i() + ", supplied: " + chronoZonedDateTimeImpl.v().p().i());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Chronology) && compareTo((Chronology) obj) == 0) {
            return true;
        }
        return false;
    }

    public abstract Era f(int i2);

    public abstract String h();

    public int hashCode() {
        return getClass().hashCode() ^ i().hashCode();
    }

    public abstract String i();

    public ChronoLocalDateTime<?> k(TemporalAccessor temporalAccessor) {
        try {
            return b(temporalAccessor).n(LocalTime.p(temporalAccessor));
        } catch (DateTimeException e2) {
            throw new DateTimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + temporalAccessor.getClass(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(Map<TemporalField, Long> map, ChronoField chronoField, long j2) {
        Long l2 = map.get(chronoField);
        if (l2 != null && l2.longValue() != j2) {
            throw new DateTimeException("Invalid state, field: " + chronoField + " " + l2 + " conflicts with " + chronoField + " " + j2);
        }
        map.put(chronoField, Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(DataOutput dataOutput) {
        dataOutput.writeUTF(i());
    }

    public ChronoZonedDateTime<?> q(Instant instant, ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.E(this, instant, zoneId);
    }

    public ChronoZonedDateTime<?> r(TemporalAccessor temporalAccessor) {
        try {
            ZoneId k2 = ZoneId.k(temporalAccessor);
            try {
                return q(Instant.o(temporalAccessor), k2);
            } catch (DateTimeException unused) {
                return ChronoZonedDateTimeImpl.D(d(k(temporalAccessor)), k2, null);
            }
        } catch (DateTimeException e2) {
            throw new DateTimeException("Unable to obtain ChronoZonedDateTime from TemporalAccessor: " + temporalAccessor.getClass(), e2);
        }
    }

    public String toString() {
        return i();
    }
}
