package com.nineoldandroids.animation;

import com.nineoldandroids.animation.Animator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AnimatorSet extends Animator {

    /* renamed from: e  reason: collision with root package name */
    private ArrayList<Animator> f236e = new ArrayList<>();

    /* renamed from: f  reason: collision with root package name */
    private HashMap<Animator, Node> f237f = new HashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<Node> f238g = new ArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private ArrayList<Node> f239h = new ArrayList<>();

    /* renamed from: i  reason: collision with root package name */
    private boolean f240i = true;

    /* renamed from: j  reason: collision with root package name */
    private AnimatorSetListener f241j = null;

    /* renamed from: k  reason: collision with root package name */
    boolean f242k = false;

    /* renamed from: l  reason: collision with root package name */
    private boolean f243l = false;

    /* renamed from: m  reason: collision with root package name */
    private long f244m = 0;

    /* renamed from: n  reason: collision with root package name */
    private ValueAnimator f245n = null;

    /* renamed from: o  reason: collision with root package name */
    private long f246o = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class AnimatorSetListener implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        private AnimatorSet f250a;

        AnimatorSetListener(AnimatorSet animatorSet) {
            this.f250a = animatorSet;
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void a(Animator animator) {
            animator.e(this);
            AnimatorSet.this.f236e.remove(animator);
            boolean z2 = true;
            ((Node) this.f250a.f237f.get(animator)).f264i = true;
            if (!AnimatorSet.this.f242k) {
                ArrayList arrayList = this.f250a.f239h;
                int size = arrayList.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    } else if (!((Node) arrayList.get(i2)).f264i) {
                        z2 = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    ArrayList<Animator.AnimatorListener> arrayList2 = AnimatorSet.this.f235d;
                    if (arrayList2 != null) {
                        ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                        int size2 = arrayList3.size();
                        for (int i3 = 0; i3 < size2; i3++) {
                            ((Animator.AnimatorListener) arrayList3.get(i3)).a(this.f250a);
                        }
                    }
                    this.f250a.f243l = false;
                }
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void b(Animator animator) {
            ArrayList<Animator.AnimatorListener> arrayList;
            AnimatorSet animatorSet = AnimatorSet.this;
            if (!animatorSet.f242k && animatorSet.f236e.size() == 0 && (arrayList = AnimatorSet.this.f235d) != null) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    AnimatorSet.this.f235d.get(i2).b(this.f250a);
                }
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void c(Animator animator) {
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void d(Animator animator) {
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Node f252a;

        Builder(Animator animator) {
            Node node = (Node) AnimatorSet.this.f237f.get(animator);
            this.f252a = node;
            if (node == null) {
                this.f252a = new Node(animator);
                AnimatorSet.this.f237f.put(animator, this.f252a);
                AnimatorSet.this.f238g.add(this.f252a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Dependency {

        /* renamed from: a  reason: collision with root package name */
        public Node f254a;

        /* renamed from: b  reason: collision with root package name */
        public int f255b;

        public Dependency(Node node, int i2) {
            this.f254a = node;
            this.f255b = i2;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class DependencyListener implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        private AnimatorSet f256a;

        /* renamed from: b  reason: collision with root package name */
        private Node f257b;

        /* renamed from: c  reason: collision with root package name */
        private int f258c;

        public DependencyListener(AnimatorSet animatorSet, Node node, int i2) {
            this.f256a = animatorSet;
            this.f257b = node;
            this.f258c = i2;
        }

        private void e(Animator animator) {
            Dependency dependency;
            if (this.f256a.f242k) {
                return;
            }
            int size = this.f257b.f261f.size();
            int i2 = 0;
            while (true) {
                if (i2 < size) {
                    dependency = this.f257b.f261f.get(i2);
                    if (dependency.f255b == this.f258c && dependency.f254a.f259d == animator) {
                        animator.e(this);
                        break;
                    }
                    i2++;
                } else {
                    dependency = null;
                    break;
                }
            }
            this.f257b.f261f.remove(dependency);
            if (this.f257b.f261f.size() == 0) {
                this.f257b.f259d.g();
                this.f256a.f236e.add(this.f257b.f259d);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void a(Animator animator) {
            if (this.f258c == 1) {
                e(animator);
            }
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void b(Animator animator) {
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void c(Animator animator) {
        }

        @Override // com.nineoldandroids.animation.Animator.AnimatorListener
        public void d(Animator animator) {
            if (this.f258c == 0) {
                e(animator);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Node implements Cloneable {

        /* renamed from: d  reason: collision with root package name */
        public Animator f259d;

        /* renamed from: e  reason: collision with root package name */
        public ArrayList<Dependency> f260e = null;

        /* renamed from: f  reason: collision with root package name */
        public ArrayList<Dependency> f261f = null;

        /* renamed from: g  reason: collision with root package name */
        public ArrayList<Node> f262g = null;

        /* renamed from: h  reason: collision with root package name */
        public ArrayList<Node> f263h = null;

        /* renamed from: i  reason: collision with root package name */
        public boolean f264i = false;

        public Node(Animator animator) {
            this.f259d = animator;
        }

        public void a(Dependency dependency) {
            if (this.f260e == null) {
                this.f260e = new ArrayList<>();
                this.f262g = new ArrayList<>();
            }
            this.f260e.add(dependency);
            if (!this.f262g.contains(dependency.f254a)) {
                this.f262g.add(dependency.f254a);
            }
            Node node = dependency.f254a;
            if (node.f263h == null) {
                node.f263h = new ArrayList<>();
            }
            node.f263h.add(this);
        }

        /* renamed from: b */
        public Node clone() {
            try {
                Node node = (Node) super.clone();
                node.f259d = this.f259d.clone();
                return node;
            } catch (CloneNotSupportedException unused) {
                throw new AssertionError();
            }
        }
    }

    private void r() {
        if (this.f240i) {
            this.f239h.clear();
            ArrayList arrayList = new ArrayList();
            int size = this.f238g.size();
            for (int i2 = 0; i2 < size; i2++) {
                Node node = this.f238g.get(i2);
                ArrayList<Dependency> arrayList2 = node.f260e;
                if (arrayList2 == null || arrayList2.size() == 0) {
                    arrayList.add(node);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            while (arrayList.size() > 0) {
                int size2 = arrayList.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    Node node2 = (Node) arrayList.get(i3);
                    this.f239h.add(node2);
                    ArrayList<Node> arrayList4 = node2.f263h;
                    if (arrayList4 != null) {
                        int size3 = arrayList4.size();
                        for (int i4 = 0; i4 < size3; i4++) {
                            Node node3 = node2.f263h.get(i4);
                            node3.f262g.remove(node2);
                            if (node3.f262g.size() == 0) {
                                arrayList3.add(node3);
                            }
                        }
                    }
                }
                arrayList.clear();
                arrayList.addAll(arrayList3);
                arrayList3.clear();
            }
            this.f240i = false;
            if (this.f239h.size() != this.f238g.size()) {
                throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
            }
            return;
        }
        int size4 = this.f238g.size();
        for (int i5 = 0; i5 < size4; i5++) {
            Node node4 = this.f238g.get(i5);
            ArrayList<Dependency> arrayList5 = node4.f260e;
            if (arrayList5 != null && arrayList5.size() > 0) {
                int size5 = node4.f260e.size();
                for (int i6 = 0; i6 < size5; i6++) {
                    Dependency dependency = node4.f260e.get(i6);
                    if (node4.f262g == null) {
                        node4.f262g = new ArrayList<>();
                    }
                    if (!node4.f262g.contains(dependency.f254a)) {
                        node4.f262g.add(dependency.f254a);
                    }
                }
            }
            node4.f264i = false;
        }
    }

    @Override // com.nineoldandroids.animation.Animator
    public void b() {
        ArrayList arrayList;
        this.f242k = true;
        if (o()) {
            ArrayList<Animator.AnimatorListener> arrayList2 = this.f235d;
            if (arrayList2 != null) {
                arrayList = (ArrayList) arrayList2.clone();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorListener) it.next()).b(this);
                }
            } else {
                arrayList = null;
            }
            ValueAnimator valueAnimator = this.f245n;
            if (valueAnimator != null && valueAnimator.C()) {
                this.f245n.b();
            } else if (this.f239h.size() > 0) {
                Iterator<Node> it2 = this.f239h.iterator();
                while (it2.hasNext()) {
                    it2.next().f259d.b();
                }
            }
            if (arrayList != null) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((Animator.AnimatorListener) it3.next()).a(this);
                }
            }
            this.f243l = false;
        }
    }

    @Override // com.nineoldandroids.animation.Animator
    public void g() {
        this.f242k = false;
        this.f243l = true;
        r();
        int size = this.f239h.size();
        for (int i2 = 0; i2 < size; i2++) {
            Node node = this.f239h.get(i2);
            ArrayList<Animator.AnimatorListener> d2 = node.f259d.d();
            if (d2 != null && d2.size() > 0) {
                Iterator it = new ArrayList(d2).iterator();
                while (it.hasNext()) {
                    Animator.AnimatorListener animatorListener = (Animator.AnimatorListener) it.next();
                    if ((animatorListener instanceof DependencyListener) || (animatorListener instanceof AnimatorSetListener)) {
                        node.f259d.e(animatorListener);
                    }
                }
            }
        }
        final ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < size; i3++) {
            Node node2 = this.f239h.get(i3);
            if (this.f241j == null) {
                this.f241j = new AnimatorSetListener(this);
            }
            ArrayList<Dependency> arrayList2 = node2.f260e;
            if (arrayList2 != null && arrayList2.size() != 0) {
                int size2 = node2.f260e.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Dependency dependency = node2.f260e.get(i4);
                    dependency.f254a.f259d.a(new DependencyListener(this, node2, dependency.f255b));
                }
                node2.f261f = (ArrayList) node2.f260e.clone();
            } else {
                arrayList.add(node2);
            }
            node2.f259d.a(this.f241j);
        }
        if (this.f244m <= 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Node node3 = (Node) it2.next();
                node3.f259d.g();
                this.f236e.add(node3.f259d);
            }
        } else {
            ValueAnimator D = ValueAnimator.D(0.0f, 1.0f);
            this.f245n = D;
            D.f(this.f244m);
            this.f245n.a(new AnimatorListenerAdapter() { // from class: com.nineoldandroids.animation.AnimatorSet.1

                /* renamed from: a  reason: collision with root package name */
                boolean f247a = false;

                @Override // com.nineoldandroids.animation.Animator.AnimatorListener
                public void a(Animator animator) {
                    if (!this.f247a) {
                        int size3 = arrayList.size();
                        for (int i5 = 0; i5 < size3; i5++) {
                            Node node4 = (Node) arrayList.get(i5);
                            node4.f259d.g();
                            AnimatorSet.this.f236e.add(node4.f259d);
                        }
                    }
                }

                @Override // com.nineoldandroids.animation.AnimatorListenerAdapter, com.nineoldandroids.animation.Animator.AnimatorListener
                public void b(Animator animator) {
                    this.f247a = true;
                }
            });
            this.f245n.g();
        }
        ArrayList<Animator.AnimatorListener> arrayList3 = this.f235d;
        if (arrayList3 != null) {
            ArrayList arrayList4 = (ArrayList) arrayList3.clone();
            int size3 = arrayList4.size();
            for (int i5 = 0; i5 < size3; i5++) {
                ((Animator.AnimatorListener) arrayList4.get(i5)).d(this);
            }
        }
        if (this.f238g.size() == 0 && this.f244m == 0) {
            this.f243l = false;
            ArrayList<Animator.AnimatorListener> arrayList5 = this.f235d;
            if (arrayList5 != null) {
                ArrayList arrayList6 = (ArrayList) arrayList5.clone();
                int size4 = arrayList6.size();
                for (int i6 = 0; i6 < size4; i6++) {
                    ((Animator.AnimatorListener) arrayList6.get(i6)).a(this);
                }
            }
        }
    }

    @Override // com.nineoldandroids.animation.Animator
    /* renamed from: n */
    public AnimatorSet clone() {
        AnimatorSet animatorSet = (AnimatorSet) super.clone();
        animatorSet.f240i = true;
        animatorSet.f242k = false;
        animatorSet.f243l = false;
        animatorSet.f236e = new ArrayList<>();
        animatorSet.f237f = new HashMap<>();
        animatorSet.f238g = new ArrayList<>();
        animatorSet.f239h = new ArrayList<>();
        HashMap hashMap = new HashMap();
        Iterator<Node> it = this.f238g.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            Node clone = next.clone();
            hashMap.put(next, clone);
            animatorSet.f238g.add(clone);
            animatorSet.f237f.put(clone.f259d, clone);
            ArrayList arrayList = null;
            clone.f260e = null;
            clone.f261f = null;
            clone.f263h = null;
            clone.f262g = null;
            ArrayList<Animator.AnimatorListener> d2 = clone.f259d.d();
            if (d2 != null) {
                Iterator<Animator.AnimatorListener> it2 = d2.iterator();
                while (it2.hasNext()) {
                    Animator.AnimatorListener next2 = it2.next();
                    if (next2 instanceof AnimatorSetListener) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(next2);
                    }
                }
                if (arrayList != null) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        d2.remove((Animator.AnimatorListener) it3.next());
                    }
                }
            }
        }
        Iterator<Node> it4 = this.f238g.iterator();
        while (it4.hasNext()) {
            Node next3 = it4.next();
            Node node = (Node) hashMap.get(next3);
            ArrayList<Dependency> arrayList2 = next3.f260e;
            if (arrayList2 != null) {
                Iterator<Dependency> it5 = arrayList2.iterator();
                while (it5.hasNext()) {
                    Dependency next4 = it5.next();
                    node.a(new Dependency((Node) hashMap.get(next4.f254a), next4.f255b));
                }
            }
        }
        return animatorSet;
    }

    public boolean o() {
        return this.f243l;
    }

    public Builder p(Animator animator) {
        if (animator != null) {
            this.f240i = true;
            return new Builder(animator);
        }
        return null;
    }

    @Override // com.nineoldandroids.animation.Animator
    /* renamed from: q */
    public AnimatorSet f(long j2) {
        if (j2 >= 0) {
            Iterator<Node> it = this.f238g.iterator();
            while (it.hasNext()) {
                it.next().f259d.f(j2);
            }
            this.f246o = j2;
            return this;
        }
        throw new IllegalArgumentException("duration must be a value of zero or greater");
    }
}
