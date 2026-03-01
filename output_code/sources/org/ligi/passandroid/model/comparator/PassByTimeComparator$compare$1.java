package org.ligi.passandroid.model.comparator;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.threeten.bp.ZonedDateTime;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassByTimeComparator$compare$1 extends Lambda implements Function2<ZonedDateTime, ZonedDateTime, Integer> {
    public static final PassByTimeComparator$compare$1 INSTANCE = new PassByTimeComparator$compare$1();

    PassByTimeComparator$compare$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Integer invoke(ZonedDateTime leftDate, ZonedDateTime rightDate) {
        Intrinsics.f(leftDate, "leftDate");
        Intrinsics.f(rightDate, "rightDate");
        return Integer.valueOf(leftDate.compareTo(rightDate));
    }
}
