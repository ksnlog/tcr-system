package org.threeten.bp.temporal;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TemporalAdjusters {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class RelativeDayOfWeek implements TemporalAdjuster {

        /* renamed from: d  reason: collision with root package name */
        private final int f3399d;

        /* renamed from: e  reason: collision with root package name */
        private final int f3400e;

        @Override // org.threeten.bp.temporal.TemporalAdjuster
        public Temporal j(Temporal temporal) {
            int i2;
            int i3;
            int f2 = temporal.f(ChronoField.DAY_OF_WEEK);
            int i4 = this.f3399d;
            if (i4 < 2 && f2 == this.f3400e) {
                return temporal;
            }
            if ((i4 & 1) == 0) {
                int i5 = f2 - this.f3400e;
                if (i5 >= 0) {
                    i3 = 7 - i5;
                } else {
                    i3 = -i5;
                }
                return temporal.i(i3, ChronoUnit.DAYS);
            }
            int i6 = this.f3400e - f2;
            if (i6 >= 0) {
                i2 = 7 - i6;
            } else {
                i2 = -i6;
            }
            return temporal.g(i2, ChronoUnit.DAYS);
        }

        private RelativeDayOfWeek(int i2, DayOfWeek dayOfWeek) {
            Jdk8Methods.i(dayOfWeek, "dayOfWeek");
            this.f3399d = i2;
            this.f3400e = dayOfWeek.getValue();
        }
    }

    public static TemporalAdjuster a(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(0, dayOfWeek);
    }

    public static TemporalAdjuster b(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(1, dayOfWeek);
    }
}
