package net.steamcrafted.loadtoast;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class LoadToast {

    /* renamed from: a  reason: collision with root package name */
    private LoadToastView f1723a;

    /* renamed from: b  reason: collision with root package name */
    private ViewGroup f1724b;

    /* renamed from: c  reason: collision with root package name */
    private int f1725c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1726d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1727e = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1728f = false;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1729g = false;

    public LoadToast(Context context) {
        this.f1723a = new LoadToastView(context);
        ViewGroup viewGroup = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(16908290);
        this.f1724b = viewGroup;
        viewGroup.addView(this.f1723a, new ViewGroup.LayoutParams(-2, -2));
        ViewHelper.a(this.f1723a, 0.0f);
        this.f1724b.postDelayed(new Runnable() { // from class: net.steamcrafted.loadtoast.LoadToast.1
            @Override // java.lang.Runnable
            public void run() {
                ViewHelper.b(LoadToast.this.f1723a, (LoadToast.this.f1724b.getWidth() - LoadToast.this.f1723a.getWidth()) / 2);
                ViewHelper.c(LoadToast.this.f1723a, (-LoadToast.this.f1723a.getHeight()) + LoadToast.this.f1725c);
                LoadToast.this.f1728f = true;
                if (!LoadToast.this.f1727e && LoadToast.this.f1726d) {
                    LoadToast.this.l();
                }
            }
        }, 1L);
        this.f1724b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: net.steamcrafted.loadtoast.LoadToast.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LoadToast.this.h();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.f1729g && this.f1724b.indexOfChild(this.f1723a) != this.f1724b.getChildCount() - 1) {
            ((ViewGroup) this.f1723a.getParent()).removeView(this.f1723a);
            this.f1724b.requestLayout();
            this.f1724b.addView(this.f1723a, new ViewGroup.LayoutParams(-2, -2));
        }
    }

    private void m() {
        ViewPropertyAnimator.b(this.f1723a).e(1000L).a(0.0f).g((-this.f1723a.getHeight()) + this.f1725c).d(new AccelerateInterpolator()).c(300L).f();
        this.f1729g = false;
    }

    public void i() {
        if (!this.f1728f) {
            this.f1727e = true;
            return;
        }
        this.f1723a.e();
        m();
    }

    public LoadToast j(int i2) {
        this.f1723a.setBackgroundColor(i2);
        return this;
    }

    public LoadToast k(String str) {
        this.f1723a.setText(str);
        return this;
    }

    public LoadToast l() {
        if (!this.f1728f) {
            this.f1726d = true;
            return this;
        }
        this.f1723a.k();
        ViewHelper.b(this.f1723a, (this.f1724b.getWidth() - this.f1723a.getWidth()) / 2);
        ViewHelper.a(this.f1723a, 0.0f);
        LoadToastView loadToastView = this.f1723a;
        ViewHelper.c(loadToastView, (-loadToastView.getHeight()) + this.f1725c);
        ViewPropertyAnimator.b(this.f1723a).a(1.0f).g(this.f1725c + 25).d(new DecelerateInterpolator()).c(300L).e(0L).f();
        this.f1729g = true;
        h();
        return this;
    }

    public void n() {
        if (!this.f1728f) {
            this.f1727e = true;
            return;
        }
        this.f1723a.l();
        m();
    }
}
