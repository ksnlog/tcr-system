package net.i2p.android.ext.floatingactionbutton;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import java.util.ArrayList;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class TouchDelegateGroup extends TouchDelegate {

    /* renamed from: d  reason: collision with root package name */
    private static final Rect f1344d = new Rect();

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<TouchDelegate> f1345a;

    /* renamed from: b  reason: collision with root package name */
    private TouchDelegate f1346b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1347c;

    public TouchDelegateGroup(View view) {
        super(f1344d, view);
        this.f1345a = new ArrayList<>();
    }

    public void a(TouchDelegate touchDelegate) {
        this.f1345a.add(touchDelegate);
    }

    public void b() {
        this.f1345a.clear();
        this.f1346b = null;
    }

    public void c(boolean z2) {
        this.f1347c = z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0014, code lost:
        if (r0 != 3) goto L12;
     */
    @Override // android.view.TouchDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            boolean r0 = r6.f1347c
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getAction()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L20
            if (r0 == r3) goto L1a
            r4 = 2
            if (r0 == r4) goto L17
            r4 = 3
            if (r0 == r4) goto L1a
            goto L3d
        L17:
            android.view.TouchDelegate r2 = r6.f1346b
            goto L3d
        L1a:
            android.view.TouchDelegate r0 = r6.f1346b
            r6.f1346b = r2
            r2 = r0
            goto L3d
        L20:
            r0 = 0
        L21:
            java.util.ArrayList<android.view.TouchDelegate> r4 = r6.f1345a
            int r4 = r4.size()
            if (r0 >= r4) goto L3d
            java.util.ArrayList<android.view.TouchDelegate> r4 = r6.f1345a
            java.lang.Object r4 = r4.get(r0)
            android.view.TouchDelegate r4 = (android.view.TouchDelegate) r4
            boolean r5 = r4.onTouchEvent(r7)
            if (r5 == 0) goto L3a
            r6.f1346b = r4
            return r3
        L3a:
            int r0 = r0 + 1
            goto L21
        L3d:
            if (r2 == 0) goto L46
            boolean r7 = r2.onTouchEvent(r7)
            if (r7 == 0) goto L46
            r1 = 1
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.i2p.android.ext.floatingactionbutton.TouchDelegateGroup.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
