package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LazyKt__LazyJVMKt {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f745a;

        static {
            int[] iArr = new int[LazyThreadSafetyMode.values().length];
            try {
                iArr[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LazyThreadSafetyMode.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f745a = iArr;
        }
    }

    public static <T> Lazy<T> a(LazyThreadSafetyMode mode, Function0<? extends T> initializer) {
        Intrinsics.f(mode, "mode");
        Intrinsics.f(initializer, "initializer");
        int i2 = WhenMappings.f745a[mode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return new UnsafeLazyImpl(initializer);
                }
                throw new NoWhenBranchMatchedException();
            }
            return new SafePublicationLazyImpl(initializer);
        }
        return new SynchronizedLazyImpl(initializer, null, 2, null);
    }

    public static <T> Lazy<T> b(Function0<? extends T> initializer) {
        Intrinsics.f(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, null, 2, null);
    }
}
