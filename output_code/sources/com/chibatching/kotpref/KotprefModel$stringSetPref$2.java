package com.chibatching.kotpref;

import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class KotprefModel$stringSetPref$2 extends Lambda implements Function0<Set<? extends String>> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Set f13d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotprefModel$stringSetPref$2(Set set) {
        super(0);
        this.f13d = set;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final Set<String> mo2invoke() {
        return this.f13d;
    }
}
