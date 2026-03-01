package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.util.regex.Pattern;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZoneRegion extends ZoneId {

    /* renamed from: h  reason: collision with root package name */
    private static final Pattern f3113h = Pattern.compile("[A-Za-z][A-Za-z0-9~/._+-]+");

    /* renamed from: f  reason: collision with root package name */
    private final String f3114f;

    /* renamed from: g  reason: collision with root package name */
    private final transient ZoneRules f3115g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneRegion(String str, ZoneRules zoneRules) {
        this.f3114f = str;
        this.f3115g = zoneRules;
    }

    private Object readResolve() {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneRegion u(String str, boolean z2) {
        ZoneRules zoneRules;
        Jdk8Methods.i(str, "zoneId");
        if (str.length() >= 2 && f3113h.matcher(str).matches()) {
            try {
                zoneRules = ZoneRulesProvider.c(str, true);
            } catch (ZoneRulesException e2) {
                if (str.equals("GMT0")) {
                    zoneRules = ZoneOffset.f3108k.n();
                } else if (!z2) {
                    zoneRules = null;
                } else {
                    throw e2;
                }
            }
            return new ZoneRegion(str, zoneRules);
        }
        throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
    }

    private static ZoneRegion v(String str) {
        if (!str.equals("Z") && !str.startsWith("+") && !str.startsWith("-")) {
            if (!str.equals("UTC") && !str.equals("GMT") && !str.equals("UT")) {
                if (!str.startsWith("UTC+") && !str.startsWith("GMT+") && !str.startsWith("UTC-") && !str.startsWith("GMT-")) {
                    if (!str.startsWith("UT+") && !str.startsWith("UT-")) {
                        return u(str, false);
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
        throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneId w(DataInput dataInput) {
        return v(dataInput.readUTF());
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    @Override // org.threeten.bp.ZoneId
    public String m() {
        return this.f3114f;
    }

    @Override // org.threeten.bp.ZoneId
    public ZoneRules n() {
        ZoneRules zoneRules = this.f3115g;
        return zoneRules != null ? zoneRules : ZoneRulesProvider.c(this.f3114f, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.ZoneId
    public void t(DataOutput dataOutput) {
        dataOutput.writeByte(7);
        x(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(DataOutput dataOutput) {
        dataOutput.writeUTF(this.f3114f);
    }
}
