package okhttp3.internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class NamedRunnable implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    protected final String f2080d;

    public NamedRunnable(String str, Object... objArr) {
        this.f2080d = Util.q(str, objArr);
    }

    protected abstract void k();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f2080d);
        try {
            k();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
