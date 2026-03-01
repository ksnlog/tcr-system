package kotlinx.coroutines.scheduling;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NanoTimeSource extends SchedulerTimeSource {

    /* renamed from: a  reason: collision with root package name */
    public static final NanoTimeSource f1200a = new NanoTimeSource();

    private NanoTimeSource() {
    }

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public long a() {
        return System.nanoTime();
    }
}
