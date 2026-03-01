package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StandardJsonAdapters {

    /* renamed from: a  reason: collision with root package name */
    public static final JsonAdapter.Factory f611a = new JsonAdapter.Factory() { // from class: com.squareup.moshi.StandardJsonAdapters.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.f612b;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.f613c;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.f614d;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.f615e;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.f616f;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.f617g;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.f618h;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.f619i;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.f612b.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.f613c.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.f614d.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.f615e.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.f616f.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.f617g.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.f618h.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.f619i.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.f620j.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(moshi).nullSafe();
            }
            Class<?> g2 = Types.g(type);
            JsonAdapter<?> d2 = Util.d(moshi, type, g2);
            if (d2 != null) {
                return d2;
            }
            if (!g2.isEnum()) {
                return null;
            }
            return new EnumJsonAdapter(g2).nullSafe();
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static final JsonAdapter<Boolean> f612b = new JsonAdapter<Boolean>() { // from class: com.squareup.moshi.StandardJsonAdapters.2
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Boolean fromJson(JsonReader jsonReader) {
            return Boolean.valueOf(jsonReader.r());
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Boolean bool) {
            jsonWriter.U(bool.booleanValue());
        }

        public String toString() {
            return "JsonAdapter(Boolean)";
        }
    };

    /* renamed from: c  reason: collision with root package name */
    static final JsonAdapter<Byte> f613c = new JsonAdapter<Byte>() { // from class: com.squareup.moshi.StandardJsonAdapters.3
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Byte fromJson(JsonReader jsonReader) {
            return Byte.valueOf((byte) StandardJsonAdapters.a(jsonReader, "a byte", -128, 255));
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Byte b2) {
            jsonWriter.R(b2.intValue() & 255);
        }

        public String toString() {
            return "JsonAdapter(Byte)";
        }
    };

    /* renamed from: d  reason: collision with root package name */
    static final JsonAdapter<Character> f614d = new JsonAdapter<Character>() { // from class: com.squareup.moshi.StandardJsonAdapters.4
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Character fromJson(JsonReader jsonReader) {
            String D = jsonReader.D();
            if (D.length() <= 1) {
                return Character.valueOf(D.charAt(0));
            }
            throw new JsonDataException(String.format("Expected %s but was %s at path %s", "a char", '\"' + D + '\"', jsonReader.n()));
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Character ch) {
            jsonWriter.T(ch.toString());
        }

        public String toString() {
            return "JsonAdapter(Character)";
        }
    };

    /* renamed from: e  reason: collision with root package name */
    static final JsonAdapter<Double> f615e = new JsonAdapter<Double>() { // from class: com.squareup.moshi.StandardJsonAdapters.5
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Double fromJson(JsonReader jsonReader) {
            return Double.valueOf(jsonReader.w());
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Double d2) {
            jsonWriter.Q(d2.doubleValue());
        }

        public String toString() {
            return "JsonAdapter(Double)";
        }
    };

    /* renamed from: f  reason: collision with root package name */
    static final JsonAdapter<Float> f616f = new JsonAdapter<Float>() { // from class: com.squareup.moshi.StandardJsonAdapters.6
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Float fromJson(JsonReader jsonReader) {
            float w2 = (float) jsonReader.w();
            if (!jsonReader.p() && Float.isInfinite(w2)) {
                throw new JsonDataException("JSON forbids NaN and infinities: " + w2 + " at path " + jsonReader.n());
            }
            return Float.valueOf(w2);
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Float f2) {
            f2.getClass();
            jsonWriter.S(f2);
        }

        public String toString() {
            return "JsonAdapter(Float)";
        }
    };

    /* renamed from: g  reason: collision with root package name */
    static final JsonAdapter<Integer> f617g = new JsonAdapter<Integer>() { // from class: com.squareup.moshi.StandardJsonAdapters.7
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Integer fromJson(JsonReader jsonReader) {
            return Integer.valueOf(jsonReader.x());
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Integer num) {
            jsonWriter.R(num.intValue());
        }

        public String toString() {
            return "JsonAdapter(Integer)";
        }
    };

    /* renamed from: h  reason: collision with root package name */
    static final JsonAdapter<Long> f618h = new JsonAdapter<Long>() { // from class: com.squareup.moshi.StandardJsonAdapters.8
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Long fromJson(JsonReader jsonReader) {
            return Long.valueOf(jsonReader.z());
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Long l2) {
            jsonWriter.R(l2.longValue());
        }

        public String toString() {
            return "JsonAdapter(Long)";
        }
    };

    /* renamed from: i  reason: collision with root package name */
    static final JsonAdapter<Short> f619i = new JsonAdapter<Short>() { // from class: com.squareup.moshi.StandardJsonAdapters.9
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public Short fromJson(JsonReader jsonReader) {
            return Short.valueOf((short) StandardJsonAdapters.a(jsonReader, "a short", -32768, 32767));
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, Short sh) {
            jsonWriter.R(sh.intValue());
        }

        public String toString() {
            return "JsonAdapter(Short)";
        }
    };

    /* renamed from: j  reason: collision with root package name */
    static final JsonAdapter<String> f620j = new JsonAdapter<String>() { // from class: com.squareup.moshi.StandardJsonAdapters.10
        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public String fromJson(JsonReader jsonReader) {
            return jsonReader.D();
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, String str) {
            jsonWriter.T(str);
        }

        public String toString() {
            return "JsonAdapter(String)";
        }
    };

    /* renamed from: com.squareup.moshi.StandardJsonAdapters$11  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static /* synthetic */ class AnonymousClass11 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f621a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            f621a = iArr;
            try {
                iArr[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f621a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f621a[JsonReader.Token.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f621a[JsonReader.Token.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f621a[JsonReader.Token.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f621a[JsonReader.Token.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Class<T> f622a;

        /* renamed from: b  reason: collision with root package name */
        private final String[] f623b;

        /* renamed from: c  reason: collision with root package name */
        private final T[] f624c;

        /* renamed from: d  reason: collision with root package name */
        private final JsonReader.Options f625d;

        EnumJsonAdapter(Class<T> cls) {
            this.f622a = cls;
            try {
                T[] enumConstants = cls.getEnumConstants();
                this.f624c = enumConstants;
                this.f623b = new String[enumConstants.length];
                int i2 = 0;
                while (true) {
                    T[] tArr = this.f624c;
                    if (i2 < tArr.length) {
                        String name = tArr[i2].name();
                        this.f623b[i2] = Util.n(name, cls.getField(name));
                        i2++;
                    } else {
                        this.f625d = JsonReader.Options.a(this.f623b);
                        return;
                    }
                }
            } catch (NoSuchFieldException unused) {
                throw new AssertionError("Missing field in " + cls.getName());
            }
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: a */
        public T fromJson(JsonReader jsonReader) {
            int Q = jsonReader.Q(this.f625d);
            if (Q != -1) {
                return this.f624c[Q];
            }
            String n2 = jsonReader.n();
            String D = jsonReader.D();
            throw new JsonDataException("Expected one of " + Arrays.asList(this.f623b) + " but was " + D + " at path " + n2);
        }

        @Override // com.squareup.moshi.JsonAdapter
        /* renamed from: b */
        public void toJson(JsonWriter jsonWriter, T t2) {
            jsonWriter.T(this.f623b[t2.ordinal()]);
        }

        public String toString() {
            return "JsonAdapter(" + this.f622a.getName() + ")";
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class ObjectJsonAdapter extends JsonAdapter<Object> {

        /* renamed from: a  reason: collision with root package name */
        private final Moshi f626a;

        /* renamed from: b  reason: collision with root package name */
        private final JsonAdapter<List> f627b;

        /* renamed from: c  reason: collision with root package name */
        private final JsonAdapter<Map> f628c;

        /* renamed from: d  reason: collision with root package name */
        private final JsonAdapter<String> f629d;

        /* renamed from: e  reason: collision with root package name */
        private final JsonAdapter<Double> f630e;

        /* renamed from: f  reason: collision with root package name */
        private final JsonAdapter<Boolean> f631f;

        ObjectJsonAdapter(Moshi moshi) {
            this.f626a = moshi;
            this.f627b = moshi.c(List.class);
            this.f628c = moshi.c(Map.class);
            this.f629d = moshi.c(String.class);
            this.f630e = moshi.c(Double.class);
            this.f631f = moshi.c(Boolean.class);
        }

        private Class<?> a(Class<?> cls) {
            if (Map.class.isAssignableFrom(cls)) {
                return Map.class;
            }
            if (Collection.class.isAssignableFrom(cls)) {
                return Collection.class;
            }
            return cls;
        }

        @Override // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader) {
            switch (AnonymousClass11.f621a[jsonReader.G().ordinal()]) {
                case 1:
                    return this.f627b.fromJson(jsonReader);
                case 2:
                    return this.f628c.fromJson(jsonReader);
                case 3:
                    return this.f629d.fromJson(jsonReader);
                case 4:
                    return this.f630e.fromJson(jsonReader);
                case 5:
                    return this.f631f.fromJson(jsonReader);
                case R$styleable.f1342r /* 6 */:
                    return jsonReader.B();
                default:
                    throw new IllegalStateException("Expected a value but was " + jsonReader.G() + " at path " + jsonReader.n());
            }
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Object obj) {
            Class<?> cls = obj.getClass();
            if (cls == Object.class) {
                jsonWriter.c();
                jsonWriter.m();
                return;
            }
            this.f626a.e(a(cls), Util.f634a).toJson(jsonWriter, (JsonWriter) obj);
        }

        public String toString() {
            return "JsonAdapter(Object)";
        }
    }

    static int a(JsonReader jsonReader, String str, int i2, int i3) {
        int x2 = jsonReader.x();
        if (x2 >= i2 && x2 <= i3) {
            return x2;
        }
        throw new JsonDataException(String.format("Expected %s but was %s at path %s", str, Integer.valueOf(x2), jsonReader.n()));
    }
}
