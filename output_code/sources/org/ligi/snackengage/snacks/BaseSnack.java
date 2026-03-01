package org.ligi.snackengage.snacks;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.ligi.snackengage.SnackContext;
import org.ligi.snackengage.conditions.SnackCondition;
import org.ligi.snackengage.snacks.BaseSnack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class BaseSnack implements Snack {

    /* renamed from: a  reason: collision with root package name */
    protected SnackContext f2971a;

    /* renamed from: d  reason: collision with root package name */
    private String f2974d;

    /* renamed from: e  reason: collision with root package name */
    private String f2975e;

    /* renamed from: b  reason: collision with root package name */
    protected final List<SnackCondition> f2972b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private int f2973c = -2;

    /* renamed from: f  reason: collision with root package name */
    private Integer f2976f = null;

    /* renamed from: g  reason: collision with root package name */
    private Integer f2977g = null;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(View view) {
        e();
    }

    @Override // org.ligi.snackengage.snacks.Snack
    public String a() {
        return g();
    }

    @Override // org.ligi.snackengage.snacks.Snack
    public boolean b(SnackContext snackContext) {
        this.f2971a = snackContext;
        for (SnackCondition snackCondition : this.f2972b) {
            if (!snackCondition.a(snackContext, this)) {
                return false;
            }
        }
        snackContext.c().g(this);
        String str = this.f2974d;
        if (str == null) {
            str = f();
        }
        this.f2974d = str;
        String str2 = this.f2975e;
        if (str2 == null) {
            str2 = i();
        }
        this.f2975e = str2;
        d(snackContext).X();
        return true;
    }

    protected Snackbar d(SnackContext snackContext) {
        Snackbar n0 = Snackbar.n0(snackContext.b(), this.f2975e, this.f2973c);
        Integer num = this.f2976f;
        if (num != null) {
            n0.r0(num.intValue());
        }
        if (this.f2977g != null) {
            n0.H().setBackgroundColor(this.f2977g.intValue());
        }
        return n0.q0(this.f2974d, new View.OnClickListener() { // from class: i0.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseSnack.this.j(view);
            }
        });
    }

    public void e() {
        this.f2971a.c().f(this);
    }

    public abstract String f();

    public abstract String g();

    /* JADX INFO: Access modifiers changed from: protected */
    public String h(int i2) {
        return this.f2971a.a().getString(i2);
    }

    public abstract String i();

    public BaseSnack k(SnackCondition... snackConditionArr) {
        Collections.addAll(this.f2972b, snackConditionArr);
        return this;
    }

    public BaseSnack l(int i2) {
        this.f2973c = i2;
        return this;
    }
}
