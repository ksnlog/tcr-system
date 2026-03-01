package org.ligi.passandroid.model.comparator;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassByTypeFirstAndTimeSecondComparator implements Comparator<Pass> {
    private final DirectionAwarePassByTimeComparator passByTimeComparator = new DirectionAwarePassByTimeComparator(1);

    @Override // java.util.Comparator
    public int compare(Pass lhs, Pass rhs) {
        Intrinsics.f(lhs, "lhs");
        Intrinsics.f(rhs, "rhs");
        int compareTo = lhs.getType().compareTo(rhs.getType());
        return compareTo != 0 ? compareTo : this.passByTimeComparator.compare(lhs, rhs);
    }
}
