package com.squareup.moshi;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {

    /* renamed from: l  reason: collision with root package name */
    private static final Comparator<Comparable> f561l = new Comparator<Comparable>() { // from class: com.squareup.moshi.LinkedHashTreeMap.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    final Comparator<? super K> f562d;

    /* renamed from: e  reason: collision with root package name */
    Node<K, V>[] f563e;

    /* renamed from: f  reason: collision with root package name */
    final Node<K, V> f564f;

    /* renamed from: g  reason: collision with root package name */
    int f565g;

    /* renamed from: h  reason: collision with root package name */
    int f566h;

    /* renamed from: i  reason: collision with root package name */
    int f567i;

    /* renamed from: j  reason: collision with root package name */
    private LinkedHashTreeMap<K, V>.EntrySet f568j;

    /* renamed from: k  reason: collision with root package name */
    private LinkedHashTreeMap<K, V>.KeySet f569k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AvlBuilder<K, V> {

        /* renamed from: a  reason: collision with root package name */
        private Node<K, V> f570a;

        /* renamed from: b  reason: collision with root package name */
        private int f571b;

        /* renamed from: c  reason: collision with root package name */
        private int f572c;

        /* renamed from: d  reason: collision with root package name */
        private int f573d;

        AvlBuilder() {
        }

        void a(Node<K, V> node) {
            node.f585f = null;
            node.f583d = null;
            node.f584e = null;
            node.f591l = 1;
            int i2 = this.f571b;
            if (i2 > 0) {
                int i3 = this.f573d;
                if ((i3 & 1) == 0) {
                    this.f573d = i3 + 1;
                    this.f571b = i2 - 1;
                    this.f572c++;
                }
            }
            node.f583d = this.f570a;
            this.f570a = node;
            int i4 = this.f573d + 1;
            this.f573d = i4;
            int i5 = this.f571b;
            if (i5 > 0 && (i4 & 1) == 0) {
                this.f573d = i4 + 1;
                this.f571b = i5 - 1;
                this.f572c++;
            }
            int i6 = 4;
            while (true) {
                int i7 = i6 - 1;
                if ((this.f573d & i7) == i7) {
                    int i8 = this.f572c;
                    if (i8 == 0) {
                        Node<K, V> node2 = this.f570a;
                        Node<K, V> node3 = node2.f583d;
                        Node<K, V> node4 = node3.f583d;
                        node3.f583d = node4.f583d;
                        this.f570a = node3;
                        node3.f584e = node4;
                        node3.f585f = node2;
                        node3.f591l = node2.f591l + 1;
                        node4.f583d = node3;
                        node2.f583d = node3;
                    } else if (i8 == 1) {
                        Node<K, V> node5 = this.f570a;
                        Node<K, V> node6 = node5.f583d;
                        this.f570a = node6;
                        node6.f585f = node5;
                        node6.f591l = node5.f591l + 1;
                        node5.f583d = node6;
                        this.f572c = 0;
                    } else if (i8 == 2) {
                        this.f572c = 0;
                    }
                    i6 *= 2;
                } else {
                    return;
                }
            }
        }

        void b(int i2) {
            this.f571b = ((Integer.highestOneBit(i2) * 2) - 1) - i2;
            this.f573d = 0;
            this.f572c = 0;
            this.f570a = null;
        }

        Node<K, V> c() {
            Node<K, V> node = this.f570a;
            if (node.f583d == null) {
                return node;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class AvlIterator<K, V> {

        /* renamed from: a  reason: collision with root package name */
        private Node<K, V> f574a;

        AvlIterator() {
        }

        public Node<K, V> a() {
            Node<K, V> node = this.f574a;
            if (node == null) {
                return null;
            }
            Node<K, V> node2 = node.f583d;
            node.f583d = null;
            Node<K, V> node3 = node.f585f;
            while (true) {
                Node<K, V> node4 = node2;
                node2 = node3;
                if (node2 != null) {
                    node2.f583d = node4;
                    node3 = node2.f584e;
                } else {
                    this.f574a = node4;
                    return node;
                }
            }
        }

        void b(Node<K, V> node) {
            Node<K, V> node2 = null;
            while (node != null) {
                node.f583d = node2;
                node2 = node;
                node = node.f584e;
            }
            this.f574a = node2;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.e((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() { // from class: com.squareup.moshi.LinkedHashTreeMap.EntrySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                /* renamed from: b */
                public Map.Entry<K, V> next() {
                    return a();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Node<K, V> e2;
            if (!(obj instanceof Map.Entry) || (e2 = LinkedHashTreeMap.this.e((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.h(e2, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f565g;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<K>() { // from class: com.squareup.moshi.LinkedHashTreeMap.KeySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return a().f588i;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.i(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f565g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public abstract class LinkedTreeMapIterator<T> implements Iterator<T> {

        /* renamed from: d  reason: collision with root package name */
        Node<K, V> f579d;

        /* renamed from: e  reason: collision with root package name */
        Node<K, V> f580e = null;

        /* renamed from: f  reason: collision with root package name */
        int f581f;

        LinkedTreeMapIterator() {
            this.f579d = LinkedHashTreeMap.this.f564f.f586g;
            this.f581f = LinkedHashTreeMap.this.f566h;
        }

        final Node<K, V> a() {
            Node<K, V> node = this.f579d;
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            if (node != linkedHashTreeMap.f564f) {
                if (linkedHashTreeMap.f566h == this.f581f) {
                    this.f579d = node.f586g;
                    this.f580e = node;
                    return node;
                }
                throw new ConcurrentModificationException();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f579d != LinkedHashTreeMap.this.f564f;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node<K, V> node = this.f580e;
            if (node != null) {
                LinkedHashTreeMap.this.h(node, true);
                this.f580e = null;
                this.f581f = LinkedHashTreeMap.this.f566h;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LinkedHashTreeMap() {
        this(null);
    }

    private void a() {
        Node<K, V>[] b2 = b(this.f563e);
        this.f563e = b2;
        this.f567i = (b2.length / 2) + (b2.length / 4);
    }

    static <K, V> Node<K, V>[] b(Node<K, V>[] nodeArr) {
        Node<K, V> node;
        int length = nodeArr.length;
        Node<K, V>[] nodeArr2 = new Node[length * 2];
        AvlIterator avlIterator = new AvlIterator();
        AvlBuilder avlBuilder = new AvlBuilder();
        AvlBuilder avlBuilder2 = new AvlBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            Node<K, V> node2 = nodeArr[i2];
            if (node2 != null) {
                avlIterator.b(node2);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    Node<K, V> a2 = avlIterator.a();
                    if (a2 == null) {
                        break;
                    } else if ((a2.f589j & length) == 0) {
                        i3++;
                    } else {
                        i4++;
                    }
                }
                avlBuilder.b(i3);
                avlBuilder2.b(i4);
                avlIterator.b(node2);
                while (true) {
                    Node<K, V> a3 = avlIterator.a();
                    if (a3 == null) {
                        break;
                    } else if ((a3.f589j & length) == 0) {
                        avlBuilder.a(a3);
                    } else {
                        avlBuilder2.a(a3);
                    }
                }
                Node<K, V> node3 = null;
                if (i3 > 0) {
                    node = avlBuilder.c();
                } else {
                    node = null;
                }
                nodeArr2[i2] = node;
                int i5 = i2 + length;
                if (i4 > 0) {
                    node3 = avlBuilder2.c();
                }
                nodeArr2[i5] = node3;
            }
        }
        return nodeArr2;
    }

    private boolean c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void g(Node<K, V> node, boolean z2) {
        int i2;
        int i3;
        int i4;
        int i5;
        while (node != null) {
            Node<K, V> node2 = node.f584e;
            Node<K, V> node3 = node.f585f;
            int i6 = 0;
            if (node2 != null) {
                i2 = node2.f591l;
            } else {
                i2 = 0;
            }
            if (node3 != null) {
                i3 = node3.f591l;
            } else {
                i3 = 0;
            }
            int i7 = i2 - i3;
            if (i7 == -2) {
                Node<K, V> node4 = node3.f584e;
                Node<K, V> node5 = node3.f585f;
                if (node5 != null) {
                    i5 = node5.f591l;
                } else {
                    i5 = 0;
                }
                if (node4 != null) {
                    i6 = node4.f591l;
                }
                int i8 = i6 - i5;
                if (i8 != -1 && (i8 != 0 || z2)) {
                    l(node3);
                }
                k(node);
                if (z2) {
                    return;
                }
            } else if (i7 == 2) {
                Node<K, V> node6 = node2.f584e;
                Node<K, V> node7 = node2.f585f;
                if (node7 != null) {
                    i4 = node7.f591l;
                } else {
                    i4 = 0;
                }
                if (node6 != null) {
                    i6 = node6.f591l;
                }
                int i9 = i6 - i4;
                if (i9 != 1 && (i9 != 0 || z2)) {
                    k(node2);
                }
                l(node);
                if (z2) {
                    return;
                }
            } else if (i7 == 0) {
                node.f591l = i2 + 1;
                if (z2) {
                    return;
                }
            } else {
                node.f591l = Math.max(i2, i3) + 1;
                if (!z2) {
                    return;
                }
            }
            node = node.f583d;
        }
    }

    private void j(Node<K, V> node, Node<K, V> node2) {
        Node<K, V> node3 = node.f583d;
        node.f583d = null;
        if (node2 != null) {
            node2.f583d = node3;
        }
        if (node3 != null) {
            if (node3.f584e == node) {
                node3.f584e = node2;
                return;
            } else {
                node3.f585f = node2;
                return;
            }
        }
        int i2 = node.f589j;
        Node<K, V>[] nodeArr = this.f563e;
        nodeArr[i2 & (nodeArr.length - 1)] = node2;
    }

    private void k(Node<K, V> node) {
        int i2;
        int i3;
        Node<K, V> node2 = node.f584e;
        Node<K, V> node3 = node.f585f;
        Node<K, V> node4 = node3.f584e;
        Node<K, V> node5 = node3.f585f;
        node.f585f = node4;
        if (node4 != null) {
            node4.f583d = node;
        }
        j(node, node3);
        node3.f584e = node;
        node.f583d = node3;
        int i4 = 0;
        if (node2 != null) {
            i2 = node2.f591l;
        } else {
            i2 = 0;
        }
        if (node4 != null) {
            i3 = node4.f591l;
        } else {
            i3 = 0;
        }
        int max = Math.max(i2, i3) + 1;
        node.f591l = max;
        if (node5 != null) {
            i4 = node5.f591l;
        }
        node3.f591l = Math.max(max, i4) + 1;
    }

    private void l(Node<K, V> node) {
        int i2;
        int i3;
        Node<K, V> node2 = node.f584e;
        Node<K, V> node3 = node.f585f;
        Node<K, V> node4 = node2.f584e;
        Node<K, V> node5 = node2.f585f;
        node.f584e = node5;
        if (node5 != null) {
            node5.f583d = node;
        }
        j(node, node2);
        node2.f585f = node;
        node.f583d = node2;
        int i4 = 0;
        if (node3 != null) {
            i2 = node3.f591l;
        } else {
            i2 = 0;
        }
        if (node5 != null) {
            i3 = node5.f591l;
        } else {
            i3 = 0;
        }
        int max = Math.max(i2, i3) + 1;
        node.f591l = max;
        if (node4 != null) {
            i4 = node4.f591l;
        }
        node2.f591l = Math.max(max, i4) + 1;
    }

    private static int m(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.f563e, (Object) null);
        this.f565g = 0;
        this.f566h++;
        Node<K, V> node = this.f564f;
        Node<K, V> node2 = node.f586g;
        while (node2 != node) {
            Node<K, V> node3 = node2.f586g;
            node2.f587h = null;
            node2.f586g = null;
            node2 = node3;
        }
        node.f587h = node;
        node.f586g = node;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return f(obj) != null;
    }

    Node<K, V> d(K k2, boolean z2) {
        Node<K, V> node;
        int i2;
        Node<K, V> node2;
        Comparable comparable;
        int compare;
        Node<K, V> node3;
        Comparator<? super K> comparator = this.f562d;
        Node<K, V>[] nodeArr = this.f563e;
        int m2 = m(k2.hashCode());
        int length = (nodeArr.length - 1) & m2;
        Node<K, V> node4 = nodeArr[length];
        if (node4 != null) {
            if (comparator == f561l) {
                comparable = (Comparable) k2;
            } else {
                comparable = null;
            }
            while (true) {
                if (comparable != null) {
                    compare = comparable.compareTo(node4.f588i);
                } else {
                    compare = comparator.compare(k2, (K) node4.f588i);
                }
                if (compare == 0) {
                    return node4;
                }
                if (compare < 0) {
                    node3 = node4.f584e;
                } else {
                    node3 = node4.f585f;
                }
                if (node3 == null) {
                    node = node4;
                    i2 = compare;
                    break;
                }
                node4 = node3;
            }
        } else {
            node = node4;
            i2 = 0;
        }
        if (!z2) {
            return null;
        }
        Node<K, V> node5 = this.f564f;
        if (node == null) {
            if (comparator == f561l && !(k2 instanceof Comparable)) {
                throw new ClassCastException(k2.getClass().getName() + " is not Comparable");
            }
            node2 = new Node<>(node, k2, m2, node5, node5.f587h);
            nodeArr[length] = node2;
        } else {
            node2 = new Node<>(node, k2, m2, node5, node5.f587h);
            if (i2 < 0) {
                node.f584e = node2;
            } else {
                node.f585f = node2;
            }
            g(node, true);
        }
        int i3 = this.f565g;
        this.f565g = i3 + 1;
        if (i3 > this.f567i) {
            a();
        }
        this.f566h++;
        return node2;
    }

    Node<K, V> e(Map.Entry<?, ?> entry) {
        boolean z2;
        Node<K, V> f2 = f(entry.getKey());
        if (f2 != null && c(f2.f590k, entry.getValue())) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return null;
        }
        return f2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.EntrySet entrySet = this.f568j;
        if (entrySet == null) {
            LinkedHashTreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
            this.f568j = entrySet2;
            return entrySet2;
        }
        return entrySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    Node<K, V> f(Object obj) {
        if (obj != 0) {
            try {
                return d(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Node<K, V> f2 = f(obj);
        if (f2 != null) {
            return f2.f590k;
        }
        return null;
    }

    void h(Node<K, V> node, boolean z2) {
        Node<K, V> a2;
        int i2;
        if (z2) {
            Node<K, V> node2 = node.f587h;
            node2.f586g = node.f586g;
            node.f586g.f587h = node2;
            node.f587h = null;
            node.f586g = null;
        }
        Node<K, V> node3 = node.f584e;
        Node<K, V> node4 = node.f585f;
        Node<K, V> node5 = node.f583d;
        int i3 = 0;
        if (node3 != null && node4 != null) {
            if (node3.f591l > node4.f591l) {
                a2 = node3.b();
            } else {
                a2 = node4.a();
            }
            h(a2, false);
            Node<K, V> node6 = node.f584e;
            if (node6 != null) {
                i2 = node6.f591l;
                a2.f584e = node6;
                node6.f583d = a2;
                node.f584e = null;
            } else {
                i2 = 0;
            }
            Node<K, V> node7 = node.f585f;
            if (node7 != null) {
                i3 = node7.f591l;
                a2.f585f = node7;
                node7.f583d = a2;
                node.f585f = null;
            }
            a2.f591l = Math.max(i2, i3) + 1;
            j(node, a2);
            return;
        }
        if (node3 != null) {
            j(node, node3);
            node.f584e = null;
        } else if (node4 != null) {
            j(node, node4);
            node.f585f = null;
        } else {
            j(node, null);
        }
        g(node5, false);
        this.f565g--;
        this.f566h++;
    }

    Node<K, V> i(Object obj) {
        Node<K, V> f2 = f(obj);
        if (f2 != null) {
            h(f2, true);
        }
        return f2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.KeySet keySet = this.f569k;
        if (keySet == null) {
            LinkedHashTreeMap<K, V>.KeySet keySet2 = new KeySet();
            this.f569k = keySet2;
            return keySet2;
        }
        return keySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v2) {
        if (k2 != null) {
            Node<K, V> d2 = d(k2, true);
            V v3 = d2.f590k;
            d2.f590k = v2;
            return v3;
        }
        throw new NullPointerException("key == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Node<K, V> i2 = i(obj);
        if (i2 != null) {
            return i2.f590k;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f565g;
    }

    LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.f565g = 0;
        this.f566h = 0;
        this.f562d = comparator == null ? f561l : comparator;
        this.f564f = new Node<>();
        Node<K, V>[] nodeArr = new Node[16];
        this.f563e = nodeArr;
        this.f567i = (nodeArr.length / 2) + (nodeArr.length / 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Node<K, V> implements Map.Entry<K, V> {

        /* renamed from: d  reason: collision with root package name */
        Node<K, V> f583d;

        /* renamed from: e  reason: collision with root package name */
        Node<K, V> f584e;

        /* renamed from: f  reason: collision with root package name */
        Node<K, V> f585f;

        /* renamed from: g  reason: collision with root package name */
        Node<K, V> f586g;

        /* renamed from: h  reason: collision with root package name */
        Node<K, V> f587h;

        /* renamed from: i  reason: collision with root package name */
        final K f588i;

        /* renamed from: j  reason: collision with root package name */
        final int f589j;

        /* renamed from: k  reason: collision with root package name */
        V f590k;

        /* renamed from: l  reason: collision with root package name */
        int f591l;

        Node() {
            this.f588i = null;
            this.f589j = -1;
            this.f587h = this;
            this.f586g = this;
        }

        public Node<K, V> a() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.f584e; node2 != null; node2 = node2.f584e) {
                node = node2;
            }
            return node;
        }

        public Node<K, V> b() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.f585f; node2 != null; node2 = node2.f585f) {
                node = node2;
            }
            return node;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k2 = this.f588i;
            if (k2 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k2.equals(entry.getKey())) {
                return false;
            }
            V v2 = this.f590k;
            if (v2 == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f588i;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f590k;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k2 = this.f588i;
            int i2 = 0;
            int hashCode = k2 == null ? 0 : k2.hashCode();
            V v2 = this.f590k;
            if (v2 != null) {
                i2 = v2.hashCode();
            }
            return hashCode ^ i2;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v2) {
            V v3 = this.f590k;
            this.f590k = v2;
            return v3;
        }

        public String toString() {
            return this.f588i + "=" + this.f590k;
        }

        Node(Node<K, V> node, K k2, int i2, Node<K, V> node2, Node<K, V> node3) {
            this.f583d = node;
            this.f588i = k2;
            this.f589j = i2;
            this.f591l = 1;
            this.f586g = node2;
            this.f587h = node3;
            node3.f586g = this;
            node2.f587h = this;
        }
    }
}
