package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class ArrayJsonAdapter extends JsonAdapter<Object> {

    /* renamed from: c  reason: collision with root package name */
    public static final JsonAdapter.Factory f480c = new JsonAdapter.Factory() { // from class: com.squareup.moshi.ArrayJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Type a2 = Types.a(type);
            if (a2 == null || !set.isEmpty()) {
                return null;
            }
            return new ArrayJsonAdapter(Types.g(a2), moshi.d(a2)).nullSafe();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f481a;

    /* renamed from: b  reason: collision with root package name */
    private final JsonAdapter<Object> f482b;

    ArrayJsonAdapter(Class<?> cls, JsonAdapter<Object> jsonAdapter) {
        this.f481a = cls;
        this.f482b = jsonAdapter;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader) {
        ArrayList arrayList = new ArrayList();
        jsonReader.b();
        while (jsonReader.o()) {
            arrayList.add(this.f482b.fromJson(jsonReader));
        }
        jsonReader.e();
        Object newInstance = Array.newInstance(this.f481a, arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Array.set(newInstance, i2, arrayList.get(i2));
        }
        return newInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, Object obj) {
        jsonWriter.b();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f482b.toJson(jsonWriter, (JsonWriter) Array.get(obj, i2));
        }
        jsonWriter.k();
    }

    public String toString() {
        return this.f482b + ".array()";
    }
}
