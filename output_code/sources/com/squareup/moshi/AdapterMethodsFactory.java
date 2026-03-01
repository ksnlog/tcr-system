package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AdapterMethodsFactory implements JsonAdapter.Factory {

    /* renamed from: a  reason: collision with root package name */
    private final List<AdapterMethod> f454a;

    /* renamed from: b  reason: collision with root package name */
    private final List<AdapterMethod> f455b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static abstract class AdapterMethod {

        /* renamed from: a  reason: collision with root package name */
        final Type f473a;

        /* renamed from: b  reason: collision with root package name */
        final Set<? extends Annotation> f474b;

        /* renamed from: c  reason: collision with root package name */
        final Object f475c;

        /* renamed from: d  reason: collision with root package name */
        final Method f476d;

        /* renamed from: e  reason: collision with root package name */
        final int f477e;

        /* renamed from: f  reason: collision with root package name */
        final JsonAdapter<?>[] f478f;

        /* renamed from: g  reason: collision with root package name */
        final boolean f479g;

        AdapterMethod(Type type, Set<? extends Annotation> set, Object obj, Method method, int i2, int i3, boolean z2) {
            this.f473a = Util.a(type);
            this.f474b = set;
            this.f475c = obj;
            this.f476d = method;
            this.f477e = i3;
            this.f478f = new JsonAdapter[i2 - i3];
            this.f479g = z2;
        }

        public void a(Moshi moshi, JsonAdapter.Factory factory) {
            JsonAdapter<?> e2;
            if (this.f478f.length > 0) {
                Type[] genericParameterTypes = this.f476d.getGenericParameterTypes();
                Annotation[][] parameterAnnotations = this.f476d.getParameterAnnotations();
                int length = genericParameterTypes.length;
                for (int i2 = this.f477e; i2 < length; i2++) {
                    Type type = ((ParameterizedType) genericParameterTypes[i2]).getActualTypeArguments()[0];
                    Set<? extends Annotation> l2 = Util.l(parameterAnnotations[i2]);
                    JsonAdapter<?>[] jsonAdapterArr = this.f478f;
                    int i3 = i2 - this.f477e;
                    if (Types.d(this.f473a, type) && this.f474b.equals(l2)) {
                        e2 = moshi.h(factory, type, l2);
                    } else {
                        e2 = moshi.e(type, l2);
                    }
                    jsonAdapterArr[i3] = e2;
                }
            }
        }

        @Nullable
        public Object b(Moshi moshi, JsonReader jsonReader) {
            throw new AssertionError();
        }

        @Nullable
        protected Object c(@Nullable Object obj) {
            JsonAdapter<?>[] jsonAdapterArr = this.f478f;
            Object[] objArr = new Object[jsonAdapterArr.length + 1];
            objArr[0] = obj;
            System.arraycopy(jsonAdapterArr, 0, objArr, 1, jsonAdapterArr.length);
            try {
                return this.f476d.invoke(this.f475c, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        protected Object d(@Nullable Object obj, @Nullable Object obj2) {
            JsonAdapter<?>[] jsonAdapterArr = this.f478f;
            Object[] objArr = new Object[jsonAdapterArr.length + 2];
            objArr[0] = obj;
            objArr[1] = obj2;
            System.arraycopy(jsonAdapterArr, 0, objArr, 2, jsonAdapterArr.length);
            try {
                return this.f476d.invoke(this.f475c, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        public void e(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj) {
            throw new AssertionError();
        }
    }

    AdapterMethodsFactory(List<AdapterMethod> list, List<AdapterMethod> list2) {
        this.f454a = list;
        this.f455b = list2;
    }

    static AdapterMethod b(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Set<? extends Annotation> k2 = Util.k(method);
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 1 && genericParameterTypes[0] == JsonReader.class && genericReturnType != Void.TYPE && e(1, genericParameterTypes)) {
            return new AdapterMethod(genericReturnType, k2, obj, method, genericParameterTypes.length, 1, true) { // from class: com.squareup.moshi.AdapterMethodsFactory.4
                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public Object b(Moshi moshi, JsonReader jsonReader) {
                    return c(jsonReader);
                }
            };
        }
        if (genericParameterTypes.length == 1 && genericReturnType != Void.TYPE) {
            final Set<? extends Annotation> l2 = Util.l(parameterAnnotations[0]);
            return new AdapterMethod(genericReturnType, k2, obj, method, genericParameterTypes.length, 1, Util.f(parameterAnnotations[0])) { // from class: com.squareup.moshi.AdapterMethodsFactory.5

                /* renamed from: h  reason: collision with root package name */
                JsonAdapter<Object> f468h;

                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public void a(Moshi moshi, JsonAdapter.Factory factory) {
                    JsonAdapter<Object> e2;
                    super.a(moshi, factory);
                    if (Types.d(genericParameterTypes[0], genericReturnType) && l2.equals(k2)) {
                        e2 = moshi.h(factory, genericParameterTypes[0], l2);
                    } else {
                        e2 = moshi.e(genericParameterTypes[0], l2);
                    }
                    this.f468h = e2;
                }

                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public Object b(Moshi moshi, JsonReader jsonReader) {
                    return c(this.f468h.fromJson(jsonReader));
                }
            };
        }
        throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@FromJson method signatures may have one of the following structures:\n    <any access modifier> R fromJson(JsonReader jsonReader) throws <any>;\n    <any access modifier> R fromJson(JsonReader jsonReader, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R fromJson(T value) throws <any>;\n");
    }

    @Nullable
    private static AdapterMethod c(List<AdapterMethod> list, Type type, Set<? extends Annotation> set) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            AdapterMethod adapterMethod = list.get(i2);
            if (Types.d(adapterMethod.f473a, type) && adapterMethod.f474b.equals(set)) {
                return adapterMethod;
            }
        }
        return null;
    }

    public static AdapterMethodsFactory d(Object obj) {
        Method[] declaredMethods;
        AdapterMethod c2;
        AdapterMethod c3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ToJson.class)) {
                    AdapterMethod f2 = f(obj, method);
                    if (c(arrayList, f2.f473a, f2.f474b) == null) {
                        arrayList.add(f2);
                    } else {
                        throw new IllegalArgumentException("Conflicting @ToJson methods:\n    " + c3.f476d + "\n    " + f2.f476d);
                    }
                }
                if (method.isAnnotationPresent(FromJson.class)) {
                    AdapterMethod b2 = b(obj, method);
                    if (c(arrayList2, b2.f473a, b2.f474b) == null) {
                        arrayList2.add(b2);
                    } else {
                        throw new IllegalArgumentException("Conflicting @FromJson methods:\n    " + c2.f476d + "\n    " + b2.f476d);
                    }
                }
            }
        }
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one @ToJson or @FromJson method on " + obj.getClass().getName());
        }
        return new AdapterMethodsFactory(arrayList, arrayList2);
    }

    private static boolean e(int i2, Type[] typeArr) {
        int length = typeArr.length;
        while (i2 < length) {
            Type type = typeArr[i2];
            if (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != JsonAdapter.class) {
                return false;
            }
            i2++;
        }
        return true;
    }

    static AdapterMethod f(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 2 && genericParameterTypes[0] == JsonWriter.class && genericReturnType == Void.TYPE && e(2, genericParameterTypes)) {
            return new AdapterMethod(genericParameterTypes[1], Util.l(parameterAnnotations[1]), obj, method, genericParameterTypes.length, 2, true) { // from class: com.squareup.moshi.AdapterMethodsFactory.2
                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public void e(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj2) {
                    d(jsonWriter, obj2);
                }
            };
        } else if (genericParameterTypes.length == 1 && genericReturnType != Void.TYPE) {
            final Set<? extends Annotation> k2 = Util.k(method);
            final Set<? extends Annotation> l2 = Util.l(parameterAnnotations[0]);
            return new AdapterMethod(genericParameterTypes[0], l2, obj, method, genericParameterTypes.length, 1, Util.f(parameterAnnotations[0])) { // from class: com.squareup.moshi.AdapterMethodsFactory.3

                /* renamed from: h  reason: collision with root package name */
                private JsonAdapter<Object> f463h;

                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public void a(Moshi moshi, JsonAdapter.Factory factory) {
                    JsonAdapter<Object> e2;
                    super.a(moshi, factory);
                    if (Types.d(genericParameterTypes[0], genericReturnType) && l2.equals(k2)) {
                        e2 = moshi.h(factory, genericReturnType, k2);
                    } else {
                        e2 = moshi.e(genericReturnType, k2);
                    }
                    this.f463h = e2;
                }

                @Override // com.squareup.moshi.AdapterMethodsFactory.AdapterMethod
                public void e(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj2) {
                    this.f463h.toJson(jsonWriter, (JsonWriter) c(obj2));
                }
            };
        } else {
            throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@ToJson method signatures may have one of the following structures:\n    <any access modifier> void toJson(JsonWriter writer, T value) throws <any>;\n    <any access modifier> void toJson(JsonWriter writer, T value, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R toJson(T value) throws <any>;\n");
        }
    }

    @Override // com.squareup.moshi.JsonAdapter.Factory
    @Nullable
    public JsonAdapter<?> a(final Type type, final Set<? extends Annotation> set, final Moshi moshi) {
        String str;
        final AdapterMethod c2 = c(this.f454a, type, set);
        final AdapterMethod c3 = c(this.f455b, type, set);
        JsonAdapter jsonAdapter = null;
        if (c2 == null && c3 == null) {
            return null;
        }
        if (c2 == null || c3 == null) {
            try {
                jsonAdapter = moshi.h(this, type, set);
            } catch (IllegalArgumentException e2) {
                if (c2 == null) {
                    str = "@ToJson";
                } else {
                    str = "@FromJson";
                }
                throw new IllegalArgumentException("No " + str + " adapter for " + Util.u(type, set), e2);
            }
        }
        final JsonAdapter jsonAdapter2 = jsonAdapter;
        if (c2 != null) {
            c2.a(moshi, this);
        }
        if (c3 != null) {
            c3.a(moshi, this);
        }
        return new JsonAdapter<Object>() { // from class: com.squareup.moshi.AdapterMethodsFactory.1
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader) {
                AdapterMethod adapterMethod = c3;
                if (adapterMethod == null) {
                    return jsonAdapter2.fromJson(jsonReader);
                }
                if (!adapterMethod.f479g && jsonReader.G() == JsonReader.Token.NULL) {
                    jsonReader.B();
                    return null;
                }
                try {
                    return c3.b(moshi, jsonReader);
                } catch (InvocationTargetException e3) {
                    Throwable cause = e3.getCause();
                    if (cause instanceof IOException) {
                        throw ((IOException) cause);
                    }
                    throw new JsonDataException(cause + " at " + jsonReader.n(), cause);
                }
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable Object obj) {
                AdapterMethod adapterMethod = c2;
                if (adapterMethod == null) {
                    jsonAdapter2.toJson(jsonWriter, (JsonWriter) obj);
                } else if (!adapterMethod.f479g && obj == null) {
                    jsonWriter.x();
                } else {
                    try {
                        adapterMethod.e(moshi, jsonWriter, obj);
                    } catch (InvocationTargetException e3) {
                        Throwable cause = e3.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        throw new JsonDataException(cause + " at " + jsonWriter.o(), cause);
                    }
                }
            }

            public String toString() {
                return "JsonAdapter" + set + "(" + type + ")";
            }
        };
    }
}
