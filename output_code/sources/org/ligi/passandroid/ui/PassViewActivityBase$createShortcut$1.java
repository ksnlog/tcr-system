package org.ligi.passandroid.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PassViewActivityBase$createShortcut$1 extends Lambda implements Function0<Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PassViewActivityBase f2856d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassViewActivityBase$createShortcut$1(PassViewActivityBase passViewActivityBase) {
        super(0);
        this.f2856d = passViewActivityBase;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0020, code lost:
        if (r0 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r7 = this;
            org.ligi.passandroid.ui.PassViewActivityBase r0 = r7.f2856d
            org.ligi.passandroid.model.pass.Pass r0 = r0.s0()
            org.ligi.passandroid.ui.PassViewActivityBase r1 = r7.f2856d
            org.ligi.passandroid.model.PassStore r1 = r1.n0()
            java.lang.String r2 = "icon"
            android.graphics.Bitmap r0 = r0.getBitmap(r1, r2)
            r1 = 1
            if (r0 == 0) goto L22
            r2 = 128(0x80, float:1.794E-43)
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r2, r1)
            java.lang.String r2 = "createScaledBitmap(this, width, height, filter)"
            kotlin.jvm.internal.Intrinsics.e(r0, r2)
            if (r0 != 0) goto L2f
        L22:
            org.ligi.passandroid.ui.PassViewActivityBase r0 = r7.f2856d
            android.content.res.Resources r0 = r0.getResources()
            r2 = 2131230896(0x7f0800b0, float:1.8077858E38)
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r2)
        L2f:
            org.ligi.passandroid.ui.PassViewActivityBase r2 = r7.f2856d
            org.ligi.passandroid.model.pass.Pass r2 = r2.s0()
            java.lang.String r2 = r2.getDescription()
            if (r2 == 0) goto L43
            int r3 = r2.length()
            if (r3 != 0) goto L42
            goto L43
        L42:
            r1 = 0
        L43:
            if (r1 == 0) goto L47
            java.lang.String r2 = "pass"
        L47:
            android.content.Intent r1 = new android.content.Intent
            org.ligi.passandroid.ui.PassViewActivityBase r3 = r7.f2856d
            java.lang.Class<org.ligi.passandroid.ui.PassViewActivity> r4 = org.ligi.passandroid.ui.PassViewActivity.class
            r1.<init>(r3, r4)
            java.lang.String r3 = "android.intent.action.MAIN"
            android.content.Intent r1 = r1.setAction(r3)
            org.ligi.passandroid.ui.PassViewActivityBase r3 = r7.f2856d
            org.ligi.passandroid.model.pass.Pass r3 = r3.s0()
            java.lang.String r3 = r3.getId()
            java.lang.String r4 = "uuid"
            android.content.Intent r1 = r1.putExtra(r4, r3)
            java.lang.String r3 = "Intent(this, PassViewAct…KEY_UUID, currentPass.id)"
            kotlin.jvm.internal.Intrinsics.e(r1, r3)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r3 = new androidx.core.content.pm.ShortcutInfoCompat$Builder
            org.ligi.passandroid.ui.PassViewActivityBase r4 = r7.f2856d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "shortcut"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r3.<init>(r4, r5)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r1 = r3.c(r1)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r1 = r1.e(r2)
            androidx.core.graphics.drawable.IconCompat r0 = androidx.core.graphics.drawable.IconCompat.g(r0)
            androidx.core.content.pm.ShortcutInfoCompat$Builder r0 = r1.b(r0)
            androidx.core.content.pm.ShortcutInfoCompat r0 = r0.a()
            java.lang.String r1 = "Builder(this, \"shortcut$…\n                .build()"
            kotlin.jvm.internal.Intrinsics.e(r0, r1)
            org.ligi.passandroid.ui.PassViewActivityBase r1 = r7.f2856d
            r2 = 0
            androidx.core.content.pm.ShortcutManagerCompat.b(r1, r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.ui.PassViewActivityBase$createShortcut$1.a():void");
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo2invoke() {
        a();
        return Unit.f763a;
    }
}
