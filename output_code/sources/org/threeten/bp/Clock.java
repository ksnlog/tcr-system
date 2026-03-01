package org.threeten.bp;

import java.io.Serializable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Clock {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class SystemClock extends Clock implements Serializable {

        /* renamed from: d  reason: collision with root package name */
        private final ZoneId f3001d;

        SystemClock(ZoneId zoneId) {
            this.f3001d = zoneId;
        }

        @Override // org.threeten.bp.Clock
        public ZoneId a() {
            return this.f3001d;
        }

        @Override // org.threeten.bp.Clock
        public Instant b() {
            return Instant.t(d());
        }

        public long d() {
            return System.currentTimeMillis();
        }

        public boolean equals(Object obj) {
            if (obj instanceof SystemClock) {
                return this.f3001d.equals(((SystemClock) obj).f3001d);
            }
            return false;
        }

        public int hashCode() {
            return this.f3001d.hashCode() + 1;
        }

        public String toString() {
            return "SystemClock[" + this.f3001d + "]";
        }
    }

    protected Clock() {
    }

    public static Clock c() {
        return new SystemClock(ZoneId.s());
    }

    public abstract ZoneId a();

    public abstract Instant b();
}
