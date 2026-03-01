package org.ligi.passandroid.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.pass.PassField;
import org.ligi.passandroid.model.pass.PassImpl;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ApplePassbookQuirkCorrector {
    private final Tracker tracker;

    public ApplePassbookQuirkCorrector(Tracker tracker) {
        Intrinsics.f(tracker, "tracker");
        this.tracker = tracker;
    }

    private final void careForAirBerlin(PassImpl passImpl) {
        if (!Intrinsics.a(passImpl.getDescription(), "boardcard")) {
            return;
        }
        PassField passFieldThatMatchesLabel = getPassFieldThatMatchesLabel(passImpl, "\\b\\w{1,3}\\d{3,4}\\b");
        if (passFieldThatMatchesLabel == null) {
            passFieldThatMatchesLabel = getPassFieldThatMatchesLabel(passImpl, "\\b\\w{1,3}\\d{3,4}\\b");
        }
        PassField passFieldForKey = getPassFieldForKey(passImpl, "seat");
        PassField passFieldForKey2 = getPassFieldForKey(passImpl, "boardingGroup");
        if (passFieldThatMatchesLabel != null && passFieldForKey != null && passFieldForKey2 != null) {
            this.tracker.b("quirk_fix", "description_replace", "air_berlin", 0L);
            passImpl.setDescription(((passFieldThatMatchesLabel.getLabel() + ' ' + passFieldThatMatchesLabel.getValue()) + " | " + passFieldForKey.getLabel() + ' ' + passFieldForKey.getValue()) + " | " + passFieldForKey2.getLabel() + ' ' + passFieldForKey2.getValue());
        }
    }

    private final void careForAirCanada(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "Air Canada")) {
            this.tracker.b("quirk_fix", "description_replace", "air_canada", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "depart");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "arrive");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "air_canada", 1L);
                passImpl.setDescription(passFieldForKey.getLabel() + " -> " + passFieldForKey2.getLabel());
            }
        }
    }

    private final void careForCathayPacific(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "Cathay Pacific")) {
            this.tracker.b("quirk_fix", "description_replace", "cathay_pacific", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "departure");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "arrival");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "cathay_pacific", 1L);
                passImpl.setDescription(passFieldForKey.getLabel() + " -> " + passFieldForKey2.getLabel());
            }
        }
    }

    private final void careForRenfe(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "RENFE OPERADORA")) {
            this.tracker.b("quirk_fix", "description_replace", "RENFE OPERADORA", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "boardingTime");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "destino");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "RENFE OPERADORA", 1L);
                passImpl.setDescription(passFieldForKey.getLabel() + " -> " + passFieldForKey2.getLabel());
            }
        }
    }

    private final void careForSWISS(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "SWISS")) {
            this.tracker.b("quirk_fix", "description_replace", "SWISS", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "depart");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "destination");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "SWISS", 1L);
                passImpl.setDescription(passFieldForKey.getValue() + " -> " + passFieldForKey2.getValue());
            }
        }
    }

    private final void careForTUIFlight(PassImpl passImpl) {
        if (!Intrinsics.a(passImpl.getDescription(), "TUIfly pass")) {
            return;
        }
        this.tracker.b("quirk_fix", "description_replace", "tuiflight", 0L);
        PassField passFieldForKey = getPassFieldForKey(passImpl, "Origin");
        PassField passFieldForKey2 = getPassFieldForKey(passImpl, "Des");
        PassField passFieldForKey3 = getPassFieldForKey(passImpl, "SeatNumber");
        if (passFieldForKey != null && passFieldForKey2 != null) {
            this.tracker.b("quirk_fix", "description_replace", "tuiflight", 1L);
            String str = passFieldForKey.getValue() + "->" + passFieldForKey2.getValue();
            if (passFieldForKey3 != null) {
                this.tracker.b("quirk_fix", "description_replace", "tuiflight", 2L);
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" @");
                String value = passFieldForKey3.getValue();
                Intrinsics.c(value);
                sb.append(value);
                str = sb.toString();
            }
            passImpl.setDescription(str);
        }
    }

    private final void careForUSAirways(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "US Airways")) {
            this.tracker.b("quirk_fix", "description_replace", "usairways", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "depart");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "destination");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "usairways", 1L);
                passImpl.setDescription(passFieldForKey.getLabel() + " -> " + passFieldForKey2.getLabel());
            }
        }
    }

    private final void careForVirginAustralia(PassImpl passImpl) {
        if (passImpl.getCreator() != null && Intrinsics.a(passImpl.getCreator(), "Virgin Australia")) {
            this.tracker.b("quirk_fix", "description_replace", "virgin_australia", 0L);
            PassField passFieldForKey = getPassFieldForKey(passImpl, "origin");
            PassField passFieldForKey2 = getPassFieldForKey(passImpl, "destination");
            if (passFieldForKey != null && passFieldForKey2 != null) {
                this.tracker.b("quirk_fix", "description_replace", "virgin_australia", 1L);
                passImpl.setDescription(passFieldForKey.getLabel() + " -> " + passFieldForKey2.getLabel());
            }
        }
    }

    private final void careForWestbahn(PassImpl passImpl) {
        String value;
        if (passImpl.getCalendarTimespan() == null) {
            String creator = passImpl.getCreator();
            if (creator == null) {
                creator = "";
            }
            String str = "WESTbahn";
            if (Intrinsics.a(creator, str)) {
                this.tracker.b("quirk_fix", "description_replace", "westbahn", 0L);
                PassField passFieldForKey = getPassFieldForKey(passImpl, "from");
                PassField passFieldForKey2 = getPassFieldForKey(passImpl, "to");
                if (passFieldForKey != null && (value = passFieldForKey.getValue()) != null) {
                    this.tracker.b("quirk_fix", "description_replace", "westbahn", 1L);
                    str = value;
                }
                if (passFieldForKey2 != null) {
                    this.tracker.b("quirk_fix", "description_replace", "westbahn", 2L);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("->");
                    String value2 = passFieldForKey2.getValue();
                    Intrinsics.c(value2);
                    sb.append(value2);
                    str = sb.toString();
                }
                passImpl.setDescription(str);
            }
        }
    }

    private final PassField getPassFieldForKey(PassImpl passImpl, String str) {
        Object obj;
        boolean z2;
        Iterator<T> it = passImpl.getFields().iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                PassField passField = (PassField) obj;
                if (passField.getKey() != null && Intrinsics.a(passField.getKey(), str)) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (PassField) obj;
    }

    private final PassField getPassFieldThatMatchesLabel(PassImpl passImpl, String str) {
        Object obj;
        boolean z2;
        Iterator<T> it = passImpl.getFields().iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                String label = ((PassField) obj).getLabel();
                if (label != null && new Regex(str).a(label)) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (PassField) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void tryToFindDate(PassImpl passImpl) {
        int m2;
        ZonedDateTime zonedDateTime;
        boolean z2;
        if (passImpl.getCalendarTimespan() == null) {
            List<PassField> fields = passImpl.getFields();
            ArrayList arrayList = new ArrayList();
            for (Object obj : fields) {
                if (Intrinsics.a("date", ((PassField) obj).getKey())) {
                    arrayList.add(obj);
                }
            }
            m2 = CollectionsKt__IterablesKt.m(arrayList, 10);
            ArrayList arrayList2 = new ArrayList(m2);
            Iterator it = arrayList.iterator();
            while (true) {
                zonedDateTime = null;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    zonedDateTime = ZonedDateTime.T(((PassField) it.next()).getValue());
                } catch (DateTimeException unused) {
                }
                arrayList2.add(zonedDateTime);
            }
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                if (((ZonedDateTime) next) != null) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    zonedDateTime = next;
                    break;
                }
            }
            ZonedDateTime zonedDateTime2 = zonedDateTime;
            if (zonedDateTime2 != null) {
                this.tracker.b("quirk_fix", "find_date", "find_date", 0L);
                passImpl.setCalendarTimespan(new PassImpl.TimeSpan(zonedDateTime2, null, null, 6, null));
            }
        }
    }

    public final void correctQuirks(PassImpl pass) {
        Intrinsics.f(pass, "pass");
        careForTUIFlight(pass);
        careForAirBerlin(pass);
        careForWestbahn(pass);
        careForAirCanada(pass);
        careForUSAirways(pass);
        careForVirginAustralia(pass);
        careForCathayPacific(pass);
        careForSWISS(pass);
        careForRenfe(pass);
        tryToFindDate(pass);
    }

    public final Tracker getTracker() {
        return this.tracker;
    }
}
