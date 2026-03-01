package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class SupervisorJobImpl extends JobImpl {
    public SupervisorJobImpl(Job job) {
        super(job);
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean A(Throwable th) {
        return false;
    }
}
