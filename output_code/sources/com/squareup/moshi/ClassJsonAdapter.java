package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ClassJsonAdapter<T> extends JsonAdapter<T> {

    /* renamed from: d  reason: collision with root package name */
    public static final JsonAdapter.Factory f493d = new JsonAdapter.Factory() { // from class: com.squareup.moshi.ClassJsonAdapter.1
        private void b(Moshi moshi, Type type, Map<String, FieldBinding<?>> map) {
            Field[] declaredFields;
            Json json;
            FieldBinding<?> fieldBinding;
            FieldBinding<?> put;
            Class<?> g2 = Types.g(type);
            boolean j2 = Util.j(g2);
            for (Field field : g2.getDeclaredFields()) {
                if (c(j2, field.getModifiers()) && ((json = (Json) field.getAnnotation(Json.class)) == null || !json.ignore())) {
                    Type q2 = Util.q(type, g2, field.getGenericType());
                    Set<? extends Annotation> k2 = Util.k(field);
                    String name = field.getName();
                    JsonAdapter<T> f2 = moshi.f(q2, k2, name);
                    field.setAccessible(true);
                    String m2 = Util.m(name, json);
                    if (map.put(m2, new FieldBinding<>(m2, field, f2)) != null) {
                        throw new IllegalArgumentException("Conflicting fields:\n    " + put.f498b + "\n    " + fieldBinding.f498b);
                    }
                }
            }
        }

        private boolean c(boolean z2, int i2) {
            if (Modifier.isStatic(i2) || Modifier.isTransient(i2)) {
                return false;
            }
            if (!Modifier.isPublic(i2) && !Modifier.isProtected(i2) && z2) {
                return false;
            }
            return true;
        }

        private void d(Type type, Class<?> cls) {
            Class<?> g2 = Types.g(type);
            if (!cls.isAssignableFrom(g2)) {
                return;
            }
            throw new IllegalArgumentException("No JsonAdapter for " + type + ", you should probably use " + cls.getSimpleName() + " instead of " + g2.getSimpleName() + " (Moshi only supports the collection interfaces by default) or else register a custom JsonAdapter.");
        }

        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
                return null;
            }
            Class<?> g2 = Types.g(type);
            if (g2.isInterface() || g2.isEnum() || !set.isEmpty()) {
                return null;
            }
            if (Util.j(g2)) {
                d(type, List.class);
                d(type, Set.class);
                d(type, Map.class);
                d(type, Collection.class);
                String str = "Platform " + g2;
                if (type instanceof ParameterizedType) {
                    str = str + " in " + type;
                }
                throw new IllegalArgumentException(str + " requires explicit JsonAdapter to be registered");
            } else if (!g2.isAnonymousClass()) {
                if (!g2.isLocalClass()) {
                    if (g2.getEnclosingClass() != null && !Modifier.isStatic(g2.getModifiers())) {
                        throw new IllegalArgumentException("Cannot serialize non-static nested class " + g2.getName());
                    } else if (!Modifier.isAbstract(g2.getModifiers())) {
                        if (!Util.i(g2)) {
                            ClassFactory a2 = ClassFactory.a(g2);
                            TreeMap treeMap = new TreeMap();
                            while (type != Object.class) {
                                b(moshi, type, treeMap);
                                type = Types.f(type);
                            }
                            return (JsonAdapter<T>) new ClassJsonAdapter(a2, treeMap).nullSafe();
                        }
                        throw new IllegalArgumentException("Cannot serialize Kotlin type " + g2.getName() + ". Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. Please use KotlinJsonAdapterFactory from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact.");
                    } else {
                        throw new IllegalArgumentException("Cannot serialize abstract class " + g2.getName());
                    }
                }
                throw new IllegalArgumentException("Cannot serialize local class " + g2.getName());
            } else {
                throw new IllegalArgumentException("Cannot serialize anonymous class " + g2.getName());
            }
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final ClassFactory<T> f494a;

    /* renamed from: b  reason: collision with root package name */
    private final FieldBinding<?>[] f495b;

    /* renamed from: c  reason: collision with root package name */
    private final JsonReader.Options f496c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class FieldBinding<T> {

        /* renamed from: a  reason: collision with root package name */
        final String f497a;

        /* renamed from: b  reason: collision with root package name */
        final Field f498b;

        /* renamed from: c  reason: collision with root package name */
        final JsonAdapter<T> f499c;

        FieldBinding(String str, Field field, JsonAdapter<T> jsonAdapter) {
            this.f497a = str;
            this.f498b = field;
            this.f499c = jsonAdapter;
        }

        void a(JsonReader jsonReader, Object obj) {
            this.f498b.set(obj, this.f499c.fromJson(jsonReader));
        }

        /* JADX WARN: Multi-variable type inference failed */
        void b(JsonWriter jsonWriter, Object obj) {
            this.f499c.toJson(jsonWriter, (JsonWriter) this.f498b.get(obj));
        }
    }

    ClassJsonAdapter(ClassFactory<T> classFactory, Map<String, FieldBinding<?>> map) {
        this.f494a = classFactory;
        this.f495b = (FieldBinding[]) map.values().toArray(new FieldBinding[map.size()]);
        this.f496c = JsonReader.Options.a((String[]) map.keySet().toArray(new String[map.size()]));
    }

    @Override // com.squareup.moshi.JsonAdapter
    public T fromJson(JsonReader jsonReader) {
        try {
            T b2 = this.f494a.b();
            try {
                jsonReader.c();
                while (jsonReader.o()) {
                    int O = jsonReader.O(this.f496c);
                    if (O == -1) {
                        jsonReader.T();
                        jsonReader.U();
                    } else {
                        this.f495b[O].a(jsonReader, b2);
                    }
                }
                jsonReader.k();
                return b2;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw Util.t(e3);
        }
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, T t2) {
        FieldBinding<?>[] fieldBindingArr;
        try {
            jsonWriter.c();
            for (FieldBinding<?> fieldBinding : this.f495b) {
                jsonWriter.w(fieldBinding.f497a);
                fieldBinding.b(jsonWriter, t2);
            }
            jsonWriter.m();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public String toString() {
        return "JsonAdapter(" + this.f494a + ")";
    }
}
