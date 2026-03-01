package com.nineoldandroids.animation;

import java.util.ArrayList;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Animator implements Cloneable {

    /* renamed from: d  reason: collision with root package name */
    ArrayList<AnimatorListener> f235d = null;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface AnimatorListener {
        void a(Animator animator);

        void b(Animator animator);

        void c(Animator animator);

        void d(Animator animator);
    }

    public void a(AnimatorListener animatorListener) {
        if (this.f235d == null) {
            this.f235d = new ArrayList<>();
        }
        this.f235d.add(animatorListener);
    }

    public void b() {
    }

    @Override // 
    /* renamed from: c */
    public Animator clone() {
        try {
            Animator animator = (Animator) super.clone();
            ArrayList<AnimatorListener> arrayList = this.f235d;
            if (arrayList != null) {
                animator.f235d = new ArrayList<>();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    animator.f235d.add(arrayList.get(i2));
                }
            }
            return animator;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public ArrayList<AnimatorListener> d() {
        return this.f235d;
    }

    public void e(AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.f235d;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
        if (this.f235d.size() == 0) {
            this.f235d = null;
        }
    }

    public abstract Animator f(long j2);

    public void g() {
    }
}
