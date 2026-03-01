package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class PreHoneycombCompat {

    /* renamed from: a  reason: collision with root package name */
    static Property<View, Float> f280a = new FloatProperty<View>("alpha") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.1
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).b());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).s(f2);
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static Property<View, Float> f281b = new FloatProperty<View>("pivotX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.2
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).c());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).t(f2);
        }
    };

    /* renamed from: c  reason: collision with root package name */
    static Property<View, Float> f282c = new FloatProperty<View>("pivotY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.3
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).d());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).u(f2);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    static Property<View, Float> f283d = new FloatProperty<View>("translationX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.4
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).m());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).A(f2);
        }
    };

    /* renamed from: e  reason: collision with root package name */
    static Property<View, Float> f284e = new FloatProperty<View>("translationY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.5
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).n());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).B(f2);
        }
    };

    /* renamed from: f  reason: collision with root package name */
    static Property<View, Float> f285f = new FloatProperty<View>("rotation") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.6
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).e());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).v(f2);
        }
    };

    /* renamed from: g  reason: collision with root package name */
    static Property<View, Float> f286g = new FloatProperty<View>("rotationX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.7
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).f());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).w(f2);
        }
    };

    /* renamed from: h  reason: collision with root package name */
    static Property<View, Float> f287h = new FloatProperty<View>("rotationY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.8
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).g());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).x(f2);
        }
    };

    /* renamed from: i  reason: collision with root package name */
    static Property<View, Float> f288i = new FloatProperty<View>("scaleX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.9
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).h());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).y(f2);
        }
    };

    /* renamed from: j  reason: collision with root package name */
    static Property<View, Float> f289j = new FloatProperty<View>("scaleY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.10
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).i());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).z(f2);
        }
    };

    /* renamed from: k  reason: collision with root package name */
    static Property<View, Integer> f290k = new IntProperty<View>("scrollX") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.11
        @Override // com.nineoldandroids.util.Property
        /* renamed from: e */
        public Integer a(View view) {
            return Integer.valueOf(AnimatorProxy.F(view).j());
        }
    };

    /* renamed from: l  reason: collision with root package name */
    static Property<View, Integer> f291l = new IntProperty<View>("scrollY") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.12
        @Override // com.nineoldandroids.util.Property
        /* renamed from: e */
        public Integer a(View view) {
            return Integer.valueOf(AnimatorProxy.F(view).l());
        }
    };

    /* renamed from: m  reason: collision with root package name */
    static Property<View, Float> f292m = new FloatProperty<View>("x") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.13
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).o());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).C(f2);
        }
    };

    /* renamed from: n  reason: collision with root package name */
    static Property<View, Float> f293n = new FloatProperty<View>("y") { // from class: com.nineoldandroids.animation.PreHoneycombCompat.14
        @Override // com.nineoldandroids.util.Property
        /* renamed from: f */
        public Float a(View view) {
            return Float.valueOf(AnimatorProxy.F(view).p());
        }

        @Override // com.nineoldandroids.util.FloatProperty
        /* renamed from: g */
        public void e(View view, float f2) {
            AnimatorProxy.F(view).D(f2);
        }
    };
}
