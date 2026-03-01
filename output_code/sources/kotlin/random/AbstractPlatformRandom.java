package kotlin.random;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractPlatformRandom extends Random {
    @Override // kotlin.random.Random
    public int b() {
        return c().nextInt();
    }

    public abstract java.util.Random c();
}
