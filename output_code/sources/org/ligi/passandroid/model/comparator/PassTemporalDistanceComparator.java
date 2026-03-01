package org.ligi.passandroid.model.comparator;

import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassTemporalDistanceComparator extends PassByTimeComparator {
    @Override // org.ligi.passandroid.model.comparator.PassByTimeComparator, java.util.Comparator
    public int compare(Pass lhs, Pass rhs) {
        Intrinsics.f(lhs, "lhs");
        Intrinsics.f(rhs, "rhs");
        return calculateCompareForNullValues(lhs, rhs, PassTemporalDistanceComparator$compare$1.INSTANCE);
    }
}
