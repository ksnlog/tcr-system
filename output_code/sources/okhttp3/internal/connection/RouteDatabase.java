package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.Route;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RouteDatabase {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Route> f2135a = new LinkedHashSet();

    public synchronized void a(Route route) {
        this.f2135a.remove(route);
    }

    public synchronized void b(Route route) {
        this.f2135a.add(route);
    }

    public synchronized boolean c(Route route) {
        return this.f2135a.contains(route);
    }
}
