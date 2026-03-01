package org.threeten.bp.zone;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ZoneRulesProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final CopyOnWriteArrayList<ZoneRulesProvider> f3449a = new CopyOnWriteArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentMap<String, ZoneRulesProvider> f3450b = new ConcurrentHashMap(512, 0.75f, 2);

    static {
        ZoneRulesInitializer.a();
    }

    public static Set<String> a() {
        return Collections.unmodifiableSet(f3450b.keySet());
    }

    private static ZoneRulesProvider b(String str) {
        ConcurrentMap<String, ZoneRulesProvider> concurrentMap = f3450b;
        ZoneRulesProvider zoneRulesProvider = concurrentMap.get(str);
        if (zoneRulesProvider == null) {
            if (concurrentMap.isEmpty()) {
                throw new ZoneRulesException("No time-zone data files registered");
            }
            throw new ZoneRulesException("Unknown time-zone ID: " + str);
        }
        return zoneRulesProvider;
    }

    public static ZoneRules c(String str, boolean z2) {
        Jdk8Methods.i(str, "zoneId");
        return b(str).d(str, z2);
    }

    public static void f(ZoneRulesProvider zoneRulesProvider) {
        Jdk8Methods.i(zoneRulesProvider, "provider");
        g(zoneRulesProvider);
        f3449a.add(zoneRulesProvider);
    }

    private static void g(ZoneRulesProvider zoneRulesProvider) {
        for (String str : zoneRulesProvider.e()) {
            Jdk8Methods.i(str, "zoneId");
            if (f3450b.putIfAbsent(str, zoneRulesProvider) != null) {
                throw new ZoneRulesException("Unable to register zone as one already registered with that ID: " + str + ", currently loading from provider: " + zoneRulesProvider);
            }
        }
    }

    protected abstract ZoneRules d(String str, boolean z2);

    protected abstract Set<String> e();
}
