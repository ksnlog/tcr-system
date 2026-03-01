package org.threeten.bp.zone;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ZoneRules {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class Fixed extends ZoneRules implements Serializable {

        /* renamed from: d  reason: collision with root package name */
        private final ZoneOffset f3445d;

        Fixed(ZoneOffset zoneOffset) {
            this.f3445d = zoneOffset;
        }

        @Override // org.threeten.bp.zone.ZoneRules
        public ZoneOffset a(Instant instant) {
            return this.f3445d;
        }

        @Override // org.threeten.bp.zone.ZoneRules
        public ZoneOffsetTransition b(LocalDateTime localDateTime) {
            return null;
        }

        @Override // org.threeten.bp.zone.ZoneRules
        public List<ZoneOffset> c(LocalDateTime localDateTime) {
            return Collections.singletonList(this.f3445d);
        }

        @Override // org.threeten.bp.zone.ZoneRules
        public boolean d() {
            return true;
        }

        @Override // org.threeten.bp.zone.ZoneRules
        public boolean e(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
            return this.f3445d.equals(zoneOffset);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Fixed) {
                return this.f3445d.equals(((Fixed) obj).f3445d);
            }
            if (!(obj instanceof StandardZoneRules)) {
                return false;
            }
            StandardZoneRules standardZoneRules = (StandardZoneRules) obj;
            if (standardZoneRules.d() && this.f3445d.equals(standardZoneRules.a(Instant.f3017f))) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((((this.f3445d.hashCode() + 31) ^ 1) ^ 1) ^ (this.f3445d.hashCode() + 31)) ^ 1;
        }

        public String toString() {
            return "FixedRules:" + this.f3445d;
        }
    }

    public static ZoneRules f(ZoneOffset zoneOffset) {
        Jdk8Methods.i(zoneOffset, "offset");
        return new Fixed(zoneOffset);
    }

    public abstract ZoneOffset a(Instant instant);

    public abstract ZoneOffsetTransition b(LocalDateTime localDateTime);

    public abstract List<ZoneOffset> c(LocalDateTime localDateTime);

    public abstract boolean d();

    public abstract boolean e(LocalDateTime localDateTime, ZoneOffset zoneOffset);
}
