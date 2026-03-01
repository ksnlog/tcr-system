package org.ligi.passandroid;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.ligi.passandroid.model.AndroidSettings;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class App$settings$2 extends Lambda implements Function0<AndroidSettings> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ App f2618d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public App$settings$2(App app) {
        super(0);
        this.f2618d = app;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final AndroidSettings mo2invoke() {
        return new AndroidSettings(this.f2618d);
    }
}
