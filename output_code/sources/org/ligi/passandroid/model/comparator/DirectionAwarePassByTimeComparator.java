package org.ligi.passandroid.model.comparator;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DirectionAwarePassByTimeComparator extends PassByTimeComparator {
    public static final Companion Companion = new Companion(null);
    public static final int DIRECTION_ASC = 1;
    public static final int DIRECTION_DESC = -1;
    private final int direction;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DirectionAwarePassByTimeComparator(int i2) {
        this.direction = i2;
    }

    @Override // org.ligi.passandroid.model.comparator.PassByTimeComparator, java.util.Comparator
    public int compare(Pass lhs, Pass rhs) {
        Intrinsics.f(lhs, "lhs");
        Intrinsics.f(rhs, "rhs");
        return super.compare(lhs, rhs) * this.direction;
    }
}
