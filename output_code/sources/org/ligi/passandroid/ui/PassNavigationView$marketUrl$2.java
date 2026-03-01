package org.ligi.passandroid.ui;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassNavigationView$marketUrl$2 extends Lambda implements Function0<String> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Context f2843d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassNavigationView$marketUrl$2(Context context) {
        super(0);
        this.f2843d = context;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a */
    public final String mo2invoke() {
        Context context = this.f2843d;
        return context.getString(2131755151, context.getPackageName());
    }
}
