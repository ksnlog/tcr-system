package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Moshi {

    /* renamed from: e  reason: collision with root package name */
    static final List<JsonAdapter.Factory> f595e;

    /* renamed from: a  reason: collision with root package name */
    private final List<JsonAdapter.Factory> f596a;

    /* renamed from: b  reason: collision with root package name */
    private final int f597b;

    /* renamed from: c  reason: collision with root package name */
    private final ThreadLocal<LookupChain> f598c = new ThreadLocal<>();

    /* renamed from: d  reason: collision with root package name */
    private final Map<Object, JsonAdapter<?>> f599d = new LinkedHashMap();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        final List<JsonAdapter.Factory> f600a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        int f601b = 0;

        public Builder a(JsonAdapter.Factory factory) {
            if (factory != null) {
                List<JsonAdapter.Factory> list = this.f600a;
                int i2 = this.f601b;
                this.f601b = i2 + 1;
                list.add(i2, factory);
                return this;
            }
            throw new IllegalArgumentException("factory == null");
        }

        public Builder b(Object obj) {
            if (obj != null) {
                return a(AdapterMethodsFactory.d(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }

        @CheckReturnValue
        public Moshi c() {
            return new Moshi(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Lookup<T> extends JsonAdapter<T> {

        /* renamed from: a  reason: collision with root package name */
        final Type f602a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        final String f603b;

        /* renamed from: c  reason: collision with root package name */
        final Object f604c;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        JsonAdapter<T> f605d;

        Lookup(Type type, @Nullable String str, Object obj) {
            this.f602a = type;
            this.f603b = str;
            this.f604c = obj;
        }

        @Override // com.squareup.moshi.JsonAdapter
        public T fromJson(JsonReader jsonReader) {
            JsonAdapter<T> jsonAdapter = this.f605d;
            if (jsonAdapter != null) {
                return jsonAdapter.fromJson(jsonReader);
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, T t2) {
            JsonAdapter<T> jsonAdapter = this.f605d;
            if (jsonAdapter != null) {
                jsonAdapter.toJson(jsonWriter, (JsonWriter) t2);
                return;
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        public String toString() {
            JsonAdapter<T> jsonAdapter = this.f605d;
            return jsonAdapter != null ? jsonAdapter.toString() : super.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public final class LookupChain {

        /* renamed from: a  reason: collision with root package name */
        final List<Lookup<?>> f606a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        final Deque<Lookup<?>> f607b = new ArrayDeque();

        /* renamed from: c  reason: collision with root package name */
        boolean f608c;

        LookupChain() {
        }

        <T> void a(JsonAdapter<T> jsonAdapter) {
            this.f607b.getLast().f605d = jsonAdapter;
        }

        IllegalArgumentException b(IllegalArgumentException illegalArgumentException) {
            if (this.f608c) {
                return illegalArgumentException;
            }
            this.f608c = true;
            if (this.f607b.size() == 1 && this.f607b.getFirst().f603b == null) {
                return illegalArgumentException;
            }
            StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
            Iterator<Lookup<?>> descendingIterator = this.f607b.descendingIterator();
            while (descendingIterator.hasNext()) {
                Lookup<?> next = descendingIterator.next();
                sb.append("\nfor ");
                sb.append(next.f602a);
                if (next.f603b != null) {
                    sb.append(' ');
                    sb.append(next.f603b);
                }
            }
            return new IllegalArgumentException(sb.toString(), illegalArgumentException);
        }

        void c(boolean z2) {
            this.f607b.removeLast();
            if (!this.f607b.isEmpty()) {
                return;
            }
            Moshi.this.f598c.remove();
            if (z2) {
                synchronized (Moshi.this.f599d) {
                    int size = this.f606a.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Lookup<?> lookup = this.f606a.get(i2);
                        JsonAdapter<T> jsonAdapter = (JsonAdapter) Moshi.this.f599d.put(lookup.f604c, lookup.f605d);
                        if (jsonAdapter != 0) {
                            lookup.f605d = jsonAdapter;
                            Moshi.this.f599d.put(lookup.f604c, jsonAdapter);
                        }
                    }
                }
            }
        }

        <T> JsonAdapter<T> d(Type type, @Nullable String str, Object obj) {
            int size = this.f606a.size();
            for (int i2 = 0; i2 < size; i2++) {
                Lookup<?> lookup = this.f606a.get(i2);
                if (lookup.f604c.equals(obj)) {
                    this.f607b.add(lookup);
                    JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) lookup.f605d;
                    if (jsonAdapter != null) {
                        return jsonAdapter;
                    }
                    return lookup;
                }
            }
            Lookup<?> lookup2 = new Lookup<>(type, str, obj);
            this.f606a.add(lookup2);
            this.f607b.add(lookup2);
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(5);
        f595e = arrayList;
        arrayList.add(StandardJsonAdapters.f611a);
        arrayList.add(CollectionJsonAdapter.f500b);
        arrayList.add(MapJsonAdapter.f592c);
        arrayList.add(ArrayJsonAdapter.f480c);
        arrayList.add(RecordJsonAdapter.f610a);
        arrayList.add(ClassJsonAdapter.f493d);
    }

    Moshi(Builder builder) {
        int size = builder.f600a.size();
        List<JsonAdapter.Factory> list = f595e;
        ArrayList arrayList = new ArrayList(size + list.size());
        arrayList.addAll(builder.f600a);
        arrayList.addAll(list);
        this.f596a = Collections.unmodifiableList(arrayList);
        this.f597b = builder.f601b;
    }

    private Object g(Type type, Set<? extends Annotation> set) {
        if (set.isEmpty()) {
            return type;
        }
        return Arrays.asList(type, set);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> c(Class<T> cls) {
        return e(cls, Util.f634a);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> d(Type type) {
        return e(type, Util.f634a);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> e(Type type, Set<? extends Annotation> set) {
        return f(type, set, null);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> f(Type type, Set<? extends Annotation> set, @Nullable String str) {
        if (type != null) {
            if (set != null) {
                Type p2 = Util.p(Util.a(type));
                Object g2 = g(p2, set);
                synchronized (this.f599d) {
                    JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) this.f599d.get(g2);
                    if (jsonAdapter != null) {
                        return jsonAdapter;
                    }
                    LookupChain lookupChain = this.f598c.get();
                    if (lookupChain == null) {
                        lookupChain = new LookupChain();
                        this.f598c.set(lookupChain);
                    }
                    JsonAdapter<T> d2 = lookupChain.d(p2, str, g2);
                    try {
                        if (d2 != null) {
                            return d2;
                        }
                        try {
                            int size = this.f596a.size();
                            for (int i2 = 0; i2 < size; i2++) {
                                JsonAdapter<T> jsonAdapter2 = (JsonAdapter<T>) this.f596a.get(i2).a(p2, set, this);
                                if (jsonAdapter2 != null) {
                                    lookupChain.a(jsonAdapter2);
                                    lookupChain.c(true);
                                    return jsonAdapter2;
                                }
                            }
                            throw new IllegalArgumentException("No JsonAdapter for " + Util.u(p2, set));
                        } catch (IllegalArgumentException e2) {
                            throw lookupChain.b(e2);
                        }
                    } finally {
                        lookupChain.c(false);
                    }
                }
            }
            throw new NullPointerException("annotations == null");
        }
        throw new NullPointerException("type == null");
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> h(JsonAdapter.Factory factory, Type type, Set<? extends Annotation> set) {
        if (set != null) {
            Type p2 = Util.p(Util.a(type));
            int indexOf = this.f596a.indexOf(factory);
            if (indexOf != -1) {
                int size = this.f596a.size();
                for (int i2 = indexOf + 1; i2 < size; i2++) {
                    JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) this.f596a.get(i2).a(p2, set, this);
                    if (jsonAdapter != null) {
                        return jsonAdapter;
                    }
                }
                throw new IllegalArgumentException("No next JsonAdapter for " + Util.u(p2, set));
            }
            throw new IllegalArgumentException("Unable to skip past unknown factory " + factory);
        }
        throw new NullPointerException("annotations == null");
    }
}
