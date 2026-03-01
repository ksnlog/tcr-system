package org.threeten.bp.zone;

import androidx.lifecycle.b;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ZoneRulesInitializer {

    /* renamed from: a  reason: collision with root package name */
    public static final ZoneRulesInitializer f3446a = new DoNothingZoneRulesInitializer();

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicBoolean f3447b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicReference<ZoneRulesInitializer> f3448c = new AtomicReference<>();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class DoNothingZoneRulesInitializer extends ZoneRulesInitializer {
        DoNothingZoneRulesInitializer() {
        }

        @Override // org.threeten.bp.zone.ZoneRulesInitializer
        protected void b() {
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class ServiceLoaderZoneRulesInitializer extends ZoneRulesInitializer {
        ServiceLoaderZoneRulesInitializer() {
        }

        @Override // org.threeten.bp.zone.ZoneRulesInitializer
        protected void b() {
            Iterator it = ServiceLoader.load(ZoneRulesProvider.class, ZoneRulesProvider.class.getClassLoader()).iterator();
            while (it.hasNext()) {
                try {
                    ZoneRulesProvider.f((ZoneRulesProvider) it.next());
                } catch (ServiceConfigurationError e2) {
                    if (!(e2.getCause() instanceof SecurityException)) {
                        throw e2;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        if (!f3447b.getAndSet(true)) {
            AtomicReference<ZoneRulesInitializer> atomicReference = f3448c;
            b.a(atomicReference, (Object) null, new ServiceLoaderZoneRulesInitializer());
            atomicReference.get().b();
            return;
        }
        throw new IllegalStateException("Already initialized");
    }

    public static void c(ZoneRulesInitializer zoneRulesInitializer) {
        if (!f3447b.get()) {
            if (b.a(f3448c, (Object) null, zoneRulesInitializer)) {
                return;
            }
            throw new IllegalStateException("Initializer was already set, possibly with a default during initialization");
        }
        throw new IllegalStateException("Already initialized");
    }

    protected abstract void b();
}
