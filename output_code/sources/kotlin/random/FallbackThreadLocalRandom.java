package kotlin.random;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {

    /* renamed from: f  reason: collision with root package name */
    private final FallbackThreadLocalRandom$implStorage$1 f888f = new ThreadLocal<java.util.Random>() { // from class: kotlin.random.FallbackThreadLocalRandom$implStorage$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public java.util.Random initialValue() {
            return new java.util.Random();
        }
    };

    @Override // kotlin.random.AbstractPlatformRandom
    public java.util.Random c() {
        java.util.Random random = get();
        Intrinsics.e(random, "implStorage.get()");
        return random;
    }
}
