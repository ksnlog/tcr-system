package com.chibatching.kotpref.pref;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.chibatching.kotpref.KotprefModel;
import com.chibatching.kotpref.KotprefPreferences;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class AbstractPref<T> implements ReadWriteProperty<KotprefModel, T>, PreferenceProperty {

    /* renamed from: a  reason: collision with root package name */
    private long f21a;

    /* renamed from: b  reason: collision with root package name */
    private Object f22b;

    /* renamed from: c  reason: collision with root package name */
    private KProperty<?> f23c;

    @Override // com.chibatching.kotpref.pref.PreferenceProperty
    public String c() {
        String e2 = e();
        if (e2 != null) {
            return e2;
        }
        KProperty<?> kProperty = this.f23c;
        if (kProperty == null) {
            Intrinsics.p("property");
        }
        return kProperty.getName();
    }

    public abstract T d(KProperty<?> kProperty, SharedPreferences sharedPreferences);

    public abstract String e();

    @Override // kotlin.properties.ReadWriteProperty
    /* renamed from: f */
    public T b(KotprefModel thisRef, KProperty<?> property) {
        Intrinsics.f(thisRef, "thisRef");
        Intrinsics.f(property, "property");
        if (!thisRef.getKotprefInTransaction$kotpref_release()) {
            return d(property, thisRef.getKotprefPreference$kotpref_release());
        }
        if (this.f21a < thisRef.getKotprefTransactionStartTime$kotpref_release()) {
            this.f22b = d(property, thisRef.getKotprefPreference$kotpref_release());
            this.f21a = SystemClock.uptimeMillis();
        }
        return (T) this.f22b;
    }

    public final ReadWriteProperty<KotprefModel, T> g(KotprefModel thisRef, KProperty<?> property) {
        Intrinsics.f(thisRef, "thisRef");
        Intrinsics.f(property, "property");
        this.f23c = property;
        thisRef.getKotprefProperties$kotpref_release().put(property.getName(), this);
        return this;
    }

    public abstract void h(KProperty<?> kProperty, T t2, SharedPreferences.Editor editor);

    public abstract void i(KProperty<?> kProperty, T t2, SharedPreferences sharedPreferences);

    @Override // kotlin.properties.ReadWriteProperty
    /* renamed from: j */
    public void a(KotprefModel thisRef, KProperty<?> property, T t2) {
        Intrinsics.f(thisRef, "thisRef");
        Intrinsics.f(property, "property");
        if (thisRef.getKotprefInTransaction$kotpref_release()) {
            this.f22b = t2;
            this.f21a = SystemClock.uptimeMillis();
            KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = thisRef.getKotprefEditor$kotpref_release();
            Intrinsics.c(kotprefEditor$kotpref_release);
            h(property, t2, kotprefEditor$kotpref_release);
            return;
        }
        i(property, t2, thisRef.getKotprefPreference$kotpref_release());
    }
}
