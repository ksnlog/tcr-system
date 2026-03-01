package org.ligi.passandroid.model;

import com.chibatching.kotpref.ContextProvider;
import com.chibatching.kotpref.KotprefModel;
import com.chibatching.kotpref.PreferencesProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class State extends KotprefModel {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public static final State INSTANCE;
    private static final ReadWriteProperty lastSelectedPassUUID$delegate;
    private static final ReadWriteProperty lastSelectedTab$delegate;

    static {
        KProperty<?>[] kPropertyArr = {Reflection.d(new MutablePropertyReference1Impl(State.class, "lastSelectedTab", "getLastSelectedTab()I", 0)), Reflection.d(new MutablePropertyReference1Impl(State.class, "lastSelectedPassUUID", "getLastSelectedPassUUID()Ljava/lang/String;", 0))};
        $$delegatedProperties = kPropertyArr;
        State state = new State();
        INSTANCE = state;
        lastSelectedTab$delegate = KotprefModel.intPref$default((KotprefModel) state, 0, (String) null, false, 7, (Object) null).g(state, kPropertyArr[0]);
        lastSelectedPassUUID$delegate = KotprefModel.stringPref$default((KotprefModel) state, (String) null, (String) null, false, 7, (Object) null).g(state, kPropertyArr[1]);
    }

    private State() {
        super((ContextProvider) null, (PreferencesProvider) null, 3, (DefaultConstructorMarker) null);
    }

    public final String getLastSelectedPassUUID() {
        return (String) lastSelectedPassUUID$delegate.b(this, $$delegatedProperties[1]);
    }

    public final int getLastSelectedTab() {
        return ((Number) lastSelectedTab$delegate.b(this, $$delegatedProperties[0])).intValue();
    }

    public final void setLastSelectedPassUUID(String str) {
        Intrinsics.f(str, "<set-?>");
        lastSelectedPassUUID$delegate.a(this, $$delegatedProperties[1], str);
    }

    public final void setLastSelectedTab(int i2) {
        lastSelectedTab$delegate.a(this, $$delegatedProperties[0], Integer.valueOf(i2));
    }
}
