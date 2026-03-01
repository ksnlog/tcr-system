package org.ligi.passandroid.model.comparator;

import java.util.Comparator;
import kotlin.NoWhenBranchMatchedException;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum PassSortOrder {
    DATE_DESC(0),
    DATE_ASC(-1),
    TYPE(1),
    DATE_DIFF(2);
    

    /* renamed from: int  reason: not valid java name */
    private final int f0int;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PassSortOrder.values().length];
            try {
                iArr[PassSortOrder.TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PassSortOrder.DATE_DESC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PassSortOrder.DATE_DIFF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PassSortOrder.DATE_ASC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    PassSortOrder(int i2) {
        this.f0int = i2;
    }

    public final int getInt() {
        return this.f0int;
    }

    public final Comparator<Pass> toComparator() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        return new DirectionAwarePassByTimeComparator(1);
                    }
                    throw new NoWhenBranchMatchedException();
                }
                return new PassTemporalDistanceComparator();
            }
            return new DirectionAwarePassByTimeComparator(-1);
        }
        return new PassByTypeFirstAndTimeSecondComparator();
    }
}
