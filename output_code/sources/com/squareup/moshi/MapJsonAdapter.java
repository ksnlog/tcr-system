package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class MapJsonAdapter<K, V> extends JsonAdapter<Map<K, V>> {

    /* renamed from: c  reason: collision with root package name */
    public static final JsonAdapter.Factory f592c = new JsonAdapter.Factory() { // from class: com.squareup.moshi.MapJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Class<?> g2;
            if (!set.isEmpty() || (g2 = Types.g(type)) != Map.class) {
                return null;
            }
            Type[] i2 = Types.i(type, g2);
            return new MapJsonAdapter(moshi, i2[0], i2[1]).nullSafe();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final JsonAdapter<K> f593a;

    /* renamed from: b  reason: collision with root package name */
    private final JsonAdapter<V> f594b;

    MapJsonAdapter(Moshi moshi, Type type, Type type2) {
        this.f593a = moshi.d(type);
        this.f594b = moshi.d(type2);
    }

    @Override // com.squareup.moshi.JsonAdapter
    /* renamed from: a */
    public Map<K, V> fromJson(JsonReader jsonReader) {
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        jsonReader.c();
        while (jsonReader.o()) {
            jsonReader.I();
            K fromJson = this.f593a.fromJson(jsonReader);
            V fromJson2 = this.f594b.fromJson(jsonReader);
            Object put = linkedHashTreeMap.put(fromJson, fromJson2);
            if (put != null) {
                throw new JsonDataException("Map key '" + fromJson + "' has multiple values at path " + jsonReader.n() + ": " + put + " and " + fromJson2);
            }
        }
        jsonReader.k();
        return linkedHashTreeMap;
    }

    @Override // com.squareup.moshi.JsonAdapter
    /* renamed from: b */
    public void toJson(JsonWriter jsonWriter, Map<K, V> map) {
        jsonWriter.c();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                jsonWriter.D();
                this.f593a.toJson(jsonWriter, (JsonWriter) entry.getKey());
                this.f594b.toJson(jsonWriter, (JsonWriter) entry.getValue());
            } else {
                throw new JsonDataException("Map key is null at " + jsonWriter.o());
            }
        }
        jsonWriter.m();
    }

    public String toString() {
        return "JsonAdapter(" + this.f593a + "=" + this.f594b + ")";
    }
}
