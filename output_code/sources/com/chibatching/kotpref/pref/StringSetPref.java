package com.chibatching.kotpref.pref;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import com.chibatching.kotpref.KotprefModel;
import com.chibatching.kotpref.KotprefPreferences;
import com.chibatching.kotpref.SharedPrefExtensionsKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
@TargetApi(R$styleable.f1331g)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StringSetPref extends AbstractStringSetPref {

    /* renamed from: b  reason: collision with root package name */
    private final Function0<Set<String>> f43b;

    /* renamed from: c  reason: collision with root package name */
    private final String f44c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f45d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class PrefMutableSet implements Set<String> {

        /* renamed from: d  reason: collision with root package name */
        private Set<String> f46d;

        /* renamed from: e  reason: collision with root package name */
        private final KotprefModel f47e;

        /* renamed from: f  reason: collision with root package name */
        private final Set<String> f48f;

        /* renamed from: g  reason: collision with root package name */
        private final String f49g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ StringSetPref f50h;

        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        private final class KotprefMutableIterator implements Iterator<String> {

            /* renamed from: d  reason: collision with root package name */
            private final Iterator<String> f51d;

            /* renamed from: e  reason: collision with root package name */
            private final boolean f52e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ PrefMutableSet f53f;

            public KotprefMutableIterator(PrefMutableSet prefMutableSet, Iterator<String> baseIterator, boolean z2) {
                Intrinsics.f(baseIterator, "baseIterator");
                this.f53f = prefMutableSet;
                this.f51d = baseIterator;
                this.f52e = z2;
            }

            @Override // java.util.Iterator
            /* renamed from: a */
            public String next() {
                String next = this.f51d.next();
                Intrinsics.e(next, "next(...)");
                return next;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f51d.hasNext();
            }

            @Override // java.util.Iterator
            @SuppressLint({"CommitPrefEdits"})
            public void remove() {
                this.f51d.remove();
                if (!this.f52e) {
                    SharedPreferences.Editor putStringSet = this.f53f.d().getKotprefPreference$kotpref_release().edit().putStringSet(this.f53f.c(), this.f53f.e());
                    Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
                    SharedPrefExtensionsKt.a(putStringSet, this.f53f.f50h.f45d);
                }
            }
        }

        private final Set<String> g() {
            Set<String> set = this.f46d;
            if (set == null) {
                set = CollectionsKt___CollectionsKt.K(this.f48f);
            }
            this.f46d = set;
            return set;
        }

        @Override // java.util.Set, java.util.Collection
        @SuppressLint({"CommitPrefEdits"})
        /* renamed from: a */
        public boolean add(String element) {
            Intrinsics.f(element, "element");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                boolean add = g2.add(element);
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return add;
            }
            boolean add2 = this.f48f.add(element);
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
            return add2;
        }

        @Override // java.util.Set, java.util.Collection
        @SuppressLint({"CommitPrefEdits"})
        public boolean addAll(Collection<? extends String> elements) {
            Intrinsics.f(elements, "elements");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                boolean addAll = g2.addAll(elements);
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return addAll;
            }
            boolean addAll2 = this.f48f.addAll(elements);
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
            return addAll2;
        }

        public boolean b(String element) {
            Intrinsics.f(element, "element");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                return g2.contains(element);
            }
            return this.f48f.contains(element);
        }

        public final String c() {
            return this.f49g;
        }

        @Override // java.util.Set, java.util.Collection
        @SuppressLint({"CommitPrefEdits"})
        public void clear() {
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                g2.clear();
                Unit unit = Unit.f763a;
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return;
            }
            this.f48f.clear();
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
        }

        @Override // java.util.Set, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return b((String) obj);
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            Intrinsics.f(elements, "elements");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                return g2.containsAll(elements);
            }
            return this.f48f.containsAll(elements);
        }

        public final KotprefModel d() {
            return this.f47e;
        }

        public final Set<String> e() {
            return this.f48f;
        }

        public int f() {
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                return g2.size();
            }
            return this.f48f.size();
        }

        @SuppressLint({"CommitPrefEdits"})
        public boolean h(String element) {
            Intrinsics.f(element, "element");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                boolean remove = g2.remove(element);
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return remove;
            }
            boolean remove2 = this.f48f.remove(element);
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
            return remove2;
        }

        public final void i() {
            synchronized (this) {
                Set<String> g2 = g();
                if (g2 != null) {
                    this.f48f.clear();
                    this.f48f.addAll(g2);
                    this.f46d = null;
                    Unit unit = Unit.f763a;
                }
            }
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return this.f48f.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<String> iterator() {
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                Set<String> g2 = g();
                Intrinsics.c(g2);
                return new KotprefMutableIterator(this, g2.iterator(), true);
            }
            return new KotprefMutableIterator(this, this.f48f.iterator(), false);
        }

        @Override // java.util.Set, java.util.Collection
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return h((String) obj);
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        @SuppressLint({"CommitPrefEdits"})
        public boolean removeAll(Collection<? extends Object> elements) {
            Intrinsics.f(elements, "elements");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                boolean removeAll = g2.removeAll(elements);
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return removeAll;
            }
            boolean removeAll2 = this.f48f.removeAll(elements);
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
            return removeAll2;
        }

        @Override // java.util.Set, java.util.Collection
        @SuppressLint({"CommitPrefEdits"})
        public boolean retainAll(Collection<? extends Object> elements) {
            Intrinsics.f(elements, "elements");
            if (this.f47e.getKotprefInTransaction$kotpref_release()) {
                Set<String> g2 = g();
                Intrinsics.c(g2);
                boolean retainAll = g2.retainAll(elements);
                KotprefPreferences.KotprefEditor kotprefEditor$kotpref_release = this.f47e.getKotprefEditor$kotpref_release();
                Intrinsics.c(kotprefEditor$kotpref_release);
                kotprefEditor$kotpref_release.b(this.f49g, this);
                return retainAll;
            }
            boolean retainAll2 = this.f48f.retainAll(elements);
            SharedPreferences.Editor putStringSet = this.f47e.getKotprefPreference$kotpref_release().edit().putStringSet(this.f49g, this.f48f);
            Intrinsics.e(putStringSet, "kotprefModel.kotprefPref…().putStringSet(key, set)");
            SharedPrefExtensionsKt.a(putStringSet, this.f50h.f45d);
            return retainAll2;
        }

        @Override // java.util.Set, java.util.Collection
        public final /* bridge */ int size() {
            return f();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return CollectionToArray.a(this);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) CollectionToArray.b(this, tArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StringSetPref(Function0<? extends Set<String>> function0, String str, boolean z2) {
        Intrinsics.f(function0, "default");
        this.f43b = function0;
        this.f44c = str;
        this.f45d = z2;
    }

    @Override // com.chibatching.kotpref.pref.AbstractStringSetPref
    public String a() {
        return this.f44c;
    }
}
