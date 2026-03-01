package org.ligi.passandroid.functions;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.ligi.passandroid.model.pass.PassType;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CategoryHelperKt {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2671a;

        static {
            int[] iArr = new int[PassType.values().length];
            try {
                iArr[PassType.BOARDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PassType.PKBOARDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PassType.EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PassType.COUPON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PassType.LOYALTY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PassType.GENERIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PassType.VOUCHER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            f2671a = iArr;
        }
    }

    public static final int a(PassType category) {
        Intrinsics.f(category, "category");
        long j2 = 4282217449L;
        switch (WhenMappings.f2671a[category.ordinal()]) {
            case 1:
            case 2:
                break;
            case 3:
                j2 = 4288626128L;
                break;
            case 4:
                j2 = 4288465669L;
                break;
            case 5:
                j2 = 4294089505L;
                break;
            case R$styleable.f1342r /* 6 */:
                j2 = 4293540936L;
                break;
            case R$styleable.f1343s /* 7 */:
                j2 = 4280952615L;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return (int) j2;
    }

    public static final int b(PassType type) {
        Intrinsics.f(type, "type");
        switch (WhenMappings.f2671a[type.ordinal()]) {
            case 1:
            case 2:
                return 2131230850;
            case 3:
                return 2131230852;
            case 4:
                return 2131230851;
            case 5:
                return 2131230855;
            case R$styleable.f1342r /* 6 */:
                return 2131230853;
            case R$styleable.f1343s /* 7 */:
                return 2131230854;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final int c(PassType fromPass) {
        Intrinsics.f(fromPass, "fromPass");
        switch (WhenMappings.f2671a[fromPass.ordinal()]) {
            case 1:
            case 2:
                return 2131755059;
            case 3:
                return 2131755077;
            case 4:
                return 2131755076;
            case 5:
                return 2131755080;
            case R$styleable.f1342r /* 6 */:
                return 2131755078;
            case R$styleable.f1343s /* 7 */:
                return 2131755075;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
