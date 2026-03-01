package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SupervisorKt {
    public static final CompletableJob a(Job job) {
        return new SupervisorJobImpl(job);
    }

    public static /* synthetic */ CompletableJob b(Job job, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            job = null;
        }
        return a(job);
    }
}
