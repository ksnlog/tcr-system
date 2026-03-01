package org.ligi.passandroid.model.comparator;

import java.util.Comparator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PassByTimeComparator implements Comparator<Pass> {
    private final ZonedDateTime extractPassDate(Pass pass) {
        if (pass.getCalendarTimespan() != null) {
            PassImpl.TimeSpan calendarTimespan = pass.getCalendarTimespan();
            Intrinsics.c(calendarTimespan);
            if (calendarTimespan.getFrom() != null) {
                PassImpl.TimeSpan calendarTimespan2 = pass.getCalendarTimespan();
                Intrinsics.c(calendarTimespan2);
                return calendarTimespan2.getFrom();
            }
        }
        if (pass.getValidTimespans() != null) {
            List<PassImpl.TimeSpan> validTimespans = pass.getValidTimespans();
            Intrinsics.c(validTimespans);
            if (!validTimespans.isEmpty()) {
                List<PassImpl.TimeSpan> validTimespans2 = pass.getValidTimespans();
                Intrinsics.c(validTimespans2);
                return validTimespans2.get(0).getFrom();
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int calculateCompareForNullValues(Pass lhs, Pass rhs, Function2<? super ZonedDateTime, ? super ZonedDateTime, Integer> foo) {
        Intrinsics.f(lhs, "lhs");
        Intrinsics.f(rhs, "rhs");
        Intrinsics.f(foo, "foo");
        ZonedDateTime extractPassDate = extractPassDate(lhs);
        ZonedDateTime extractPassDate2 = extractPassDate(rhs);
        if (Intrinsics.a(extractPassDate, extractPassDate2)) {
            return 0;
        }
        if (extractPassDate == null) {
            return 1;
        }
        if (extractPassDate2 == null) {
            return -1;
        }
        return foo.invoke(extractPassDate, extractPassDate2).intValue();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Comparator
    public int compare(Pass lhs, Pass rhs) {
        Intrinsics.f(lhs, "lhs");
        Intrinsics.f(rhs, "rhs");
        return calculateCompareForNullValues(lhs, rhs, PassByTimeComparator$compare$1.INSTANCE);
    }
}
