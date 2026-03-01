package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    @Override // kotlin.random.AbstractPlatformRandom
    public Random c() {
        ThreadLocalRandom current;
        current = ThreadLocalRandom.current();
        Intrinsics.e(current, "current()");
        return current;
    }
}
