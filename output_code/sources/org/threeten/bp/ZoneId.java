package org.threeten.bp;

import java.io.DataOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class ZoneId implements Serializable {

    /* renamed from: d  reason: collision with root package name */
    public static final TemporalQuery<ZoneId> f3103d = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.ZoneId.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: b */
        public ZoneId a(TemporalAccessor temporalAccessor) {
            return ZoneId.k(temporalAccessor);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    public static final Map<String, String> f3104e;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("ACT", "Australia/Darwin");
        hashMap.put("AET", "Australia/Sydney");
        hashMap.put("AGT", "America/Argentina/Buenos_Aires");
        hashMap.put("ART", "Africa/Cairo");
        hashMap.put("AST", "America/Anchorage");
        hashMap.put("BET", "America/Sao_Paulo");
        hashMap.put("BST", "Asia/Dhaka");
        hashMap.put("CAT", "Africa/Harare");
        hashMap.put("CNT", "America/St_Johns");
        hashMap.put("CST", "America/Chicago");
        hashMap.put("CTT", "Asia/Shanghai");
        hashMap.put("EAT", "Africa/Addis_Ababa");
        hashMap.put("ECT", "Europe/Paris");
        hashMap.put("IET", "America/Indiana/Indianapolis");
        hashMap.put("IST", "Asia/Kolkata");
        hashMap.put("JST", "Asia/Tokyo");
        hashMap.put("MIT", "Pacific/Apia");
        hashMap.put("NET", "Asia/Yerevan");
        hashMap.put("NST", "Pacific/Auckland");
        hashMap.put("PLT", "Asia/Karachi");
        hashMap.put("PNT", "America/Phoenix");
        hashMap.put("PRT", "America/Puerto_Rico");
        hashMap.put("PST", "America/Los_Angeles");
        hashMap.put("SST", "Pacific/Guadalcanal");
        hashMap.put("VST", "Asia/Ho_Chi_Minh");
        hashMap.put("EST", "-05:00");
        hashMap.put("MST", "-07:00");
        hashMap.put("HST", "-10:00");
        f3104e = Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != ZoneRegion.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public static ZoneId k(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.b(TemporalQueries.f());
        if (zoneId != null) {
            return zoneId;
        }
        throw new DateTimeException("Unable to obtain ZoneId from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    public static ZoneId p(String str) {
        Jdk8Methods.i(str, "zoneId");
        if (str.equals("Z")) {
            return ZoneOffset.f3108k;
        }
        if (str.length() != 1) {
            if (!str.startsWith("+") && !str.startsWith("-")) {
                if (!str.equals("UTC") && !str.equals("GMT") && !str.equals("UT")) {
                    if (!str.startsWith("UTC+") && !str.startsWith("GMT+") && !str.startsWith("UTC-") && !str.startsWith("GMT-")) {
                        if (!str.startsWith("UT+") && !str.startsWith("UT-")) {
                            return ZoneRegion.u(str, true);
                        }
                        ZoneOffset y2 = ZoneOffset.y(str.substring(2));
                        if (y2.x() == 0) {
                            return new ZoneRegion("UT", y2.n());
                        }
                        return new ZoneRegion("UT" + y2.m(), y2.n());
                    }
                    ZoneOffset y3 = ZoneOffset.y(str.substring(3));
                    if (y3.x() == 0) {
                        return new ZoneRegion(str.substring(0, 3), y3.n());
                    }
                    return new ZoneRegion(str.substring(0, 3) + y3.m(), y3.n());
                }
                return new ZoneRegion(str, ZoneOffset.f3108k.n());
            }
            return ZoneOffset.y(str);
        }
        throw new DateTimeException("Invalid zone: " + str);
    }

    public static ZoneId q(String str, Map<String, String> map) {
        Jdk8Methods.i(str, "zoneId");
        Jdk8Methods.i(map, "aliasMap");
        String str2 = map.get(str);
        if (str2 != null) {
            str = str2;
        }
        return p(str);
    }

    public static ZoneId r(String str, ZoneOffset zoneOffset) {
        Jdk8Methods.i(str, "prefix");
        Jdk8Methods.i(zoneOffset, "offset");
        if (str.length() == 0) {
            return zoneOffset;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("Invalid prefix, must be GMT, UTC or UT: " + str);
        } else if (zoneOffset.x() == 0) {
            return new ZoneRegion(str, zoneOffset.n());
        } else {
            return new ZoneRegion(str + zoneOffset.m(), zoneOffset.n());
        }
    }

    public static ZoneId s() {
        return q(TimeZone.getDefault().getID(), f3104e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return m().equals(((ZoneId) obj).m());
        }
        return false;
    }

    public int hashCode() {
        return m().hashCode();
    }

    public abstract String m();

    public abstract ZoneRules n();

    public ZoneId o() {
        try {
            ZoneRules n2 = n();
            if (n2.d()) {
                return n2.a(Instant.f3017f);
            }
        } catch (ZoneRulesException unused) {
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void t(DataOutput dataOutput);

    public String toString() {
        return m();
    }
}
