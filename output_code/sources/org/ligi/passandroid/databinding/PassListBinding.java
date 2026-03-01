package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;
import net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu;
import org.ligi.passandroid.ui.PassNavigationView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassListBinding {

    /* renamed from: a  reason: collision with root package name */
    private final DrawerLayout f2656a;

    /* renamed from: b  reason: collision with root package name */
    public final AppBarLayout f2657b;

    /* renamed from: c  reason: collision with root package name */
    public final DrawerLayout f2658c;

    /* renamed from: d  reason: collision with root package name */
    public final TextView f2659d;

    /* renamed from: e  reason: collision with root package name */
    public final FloatingActionButton f2660e;

    /* renamed from: f  reason: collision with root package name */
    public final FloatingActionButton f2661f;

    /* renamed from: g  reason: collision with root package name */
    public final FloatingActionButton f2662g;

    /* renamed from: h  reason: collision with root package name */
    public final FloatingActionButton f2663h;

    /* renamed from: i  reason: collision with root package name */
    public final FloatingActionsMenu f2664i;

    /* renamed from: j  reason: collision with root package name */
    public final PassNavigationView f2665j;

    /* renamed from: k  reason: collision with root package name */
    public final TabLayout f2666k;

    /* renamed from: l  reason: collision with root package name */
    public final Toolbar f2667l;

    /* renamed from: m  reason: collision with root package name */
    public final ViewPager f2668m;

    private PassListBinding(DrawerLayout drawerLayout, AppBarLayout appBarLayout, DrawerLayout drawerLayout2, TextView textView, FloatingActionButton floatingActionButton, FloatingActionButton floatingActionButton2, FloatingActionButton floatingActionButton3, FloatingActionButton floatingActionButton4, FloatingActionsMenu floatingActionsMenu, PassNavigationView passNavigationView, TabLayout tabLayout, Toolbar toolbar, ViewPager viewPager) {
        this.f2656a = drawerLayout;
        this.f2657b = appBarLayout;
        this.f2658c = drawerLayout2;
        this.f2659d = textView;
        this.f2660e = floatingActionButton;
        this.f2661f = floatingActionButton2;
        this.f2662g = floatingActionButton3;
        this.f2663h = floatingActionButton4;
        this.f2664i = floatingActionsMenu;
        this.f2665j = passNavigationView;
        this.f2666k = tabLayout;
        this.f2667l = toolbar;
        this.f2668m = viewPager;
    }

    public static PassListBinding a(View view) {
        int i2 = 2131296340;
        AppBarLayout a2 = ViewBindings.a(view, 2131296340);
        if (a2 != null) {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            i2 = 2131296432;
            TextView textView = (TextView) ViewBindings.a(view, 2131296432);
            if (textView != null) {
                i2 = 2131296441;
                FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.a(view, 2131296441);
                if (floatingActionButton != null) {
                    i2 = 2131296442;
                    FloatingActionButton floatingActionButton2 = (FloatingActionButton) ViewBindings.a(view, 2131296442);
                    if (floatingActionButton2 != null) {
                        i2 = 2131296443;
                        FloatingActionButton floatingActionButton3 = (FloatingActionButton) ViewBindings.a(view, 2131296443);
                        if (floatingActionButton3 != null) {
                            i2 = 2131296444;
                            FloatingActionButton floatingActionButton4 = (FloatingActionButton) ViewBindings.a(view, 2131296444);
                            if (floatingActionButton4 != null) {
                                i2 = 2131296448;
                                FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) ViewBindings.a(view, 2131296448);
                                if (floatingActionsMenu != null) {
                                    i2 = 2131296595;
                                    PassNavigationView passNavigationView = (PassNavigationView) ViewBindings.a(view, 2131296595);
                                    if (passNavigationView != null) {
                                        i2 = 2131296740;
                                        TabLayout a3 = ViewBindings.a(view, 2131296740);
                                        if (a3 != null) {
                                            i2 = 2131296778;
                                            Toolbar a4 = ViewBindings.a(view, 2131296778);
                                            if (a4 != null) {
                                                i2 = 2131296801;
                                                ViewPager a5 = ViewBindings.a(view, 2131296801);
                                                if (a5 != null) {
                                                    return new PassListBinding(drawerLayout, a2, drawerLayout, textView, floatingActionButton, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionsMenu, passNavigationView, a3, a4, a5);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static PassListBinding c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static PassListBinding d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492996, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public DrawerLayout b() {
        return this.f2656a;
    }
}
