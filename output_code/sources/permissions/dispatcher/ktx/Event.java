package permissions.dispatcher.ktx;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Event<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f3453a;

    /* renamed from: b  reason: collision with root package name */
    private final T f3454b;

    public Event(T t2) {
        this.f3454b = t2;
    }

    public final T a() {
        if (this.f3453a) {
            return null;
        }
        this.f3453a = true;
        return this.f3454b;
    }
}
