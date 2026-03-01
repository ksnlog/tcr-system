package com.chibatching.kotpref.pref;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractStringSetPref implements PreferenceProperty {

    /* renamed from: a  reason: collision with root package name */
    private KProperty<?> f24a;

    public abstract String a();

    @Override // com.chibatching.kotpref.pref.PreferenceProperty
    public String c() {
        String a2 = a();
        if (a2 != null) {
            return a2;
        }
        KProperty<?> kProperty = this.f24a;
        if (kProperty == null) {
            Intrinsics.p("property");
        }
        return kProperty.getName();
    }
}
