package com.chibatching.kotpref;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KotprefModel$kotprefPreference$2 extends Lambda implements Function0<KotprefPreferences> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ KotprefModel f11d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotprefModel$kotprefPreference$2(KotprefModel kotprefModel) {
        super(0);
        this.f11d = kotprefModel;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final KotprefPreferences mo2invoke() {
        PreferencesProvider preferencesProvider;
        preferencesProvider = this.f11d.preferencesProvider;
        return new KotprefPreferences(preferencesProvider.a(this.f11d.getContext(), this.f11d.getKotprefName(), this.f11d.getKotprefMode()));
    }
}
