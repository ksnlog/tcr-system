package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CollectionJsonAdapter<C extends Collection<T>, T> extends JsonAdapter<C> {

    /* renamed from: b  reason: collision with root package name */
    public static final JsonAdapter.Factory f500b = new JsonAdapter.Factory() { // from class: com.squareup.moshi.CollectionJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Class<?> g2 = Types.g(type);
            if (!set.isEmpty()) {
                return null;
            }
            if (g2 != List.class && g2 != Collection.class) {
                if (g2 != Set.class) {
                    return null;
                }
                return CollectionJsonAdapter.d(type, moshi).nullSafe();
            }
            return CollectionJsonAdapter.b(type, moshi).nullSafe();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final JsonAdapter<T> f501a;

    static <T> JsonAdapter<Collection<T>> b(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Collection<T>, T>(moshi.d(Types.c(type, Collection.class))) { // from class: com.squareup.moshi.CollectionJsonAdapter.2
            @Override // com.squareup.moshi.CollectionJsonAdapter
            Collection<T> c() {
                return new ArrayList();
            }

            @Override // com.squareup.moshi.JsonAdapter
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) {
                return super.a(jsonReader);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.squareup.moshi.JsonAdapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) {
                super.e(jsonWriter, (Collection) obj);
            }
        };
    }

    static <T> JsonAdapter<Set<T>> d(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Set<T>, T>(moshi.d(Types.c(type, Collection.class))) { // from class: com.squareup.moshi.CollectionJsonAdapter.3
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.squareup.moshi.CollectionJsonAdapter
            /* renamed from: f */
            public Set<T> c() {
                return new LinkedHashSet();
            }

            @Override // com.squareup.moshi.JsonAdapter
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) {
                return super.a(jsonReader);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.squareup.moshi.JsonAdapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) {
                super.e(jsonWriter, (Collection) obj);
            }
        };
    }

    public C a(JsonReader jsonReader) {
        C c2 = c();
        jsonReader.b();
        while (jsonReader.o()) {
            c2.add(this.f501a.fromJson(jsonReader));
        }
        jsonReader.e();
        return c2;
    }

    abstract C c();

    /* JADX WARN: Multi-variable type inference failed */
    public void e(JsonWriter jsonWriter, C c2) {
        jsonWriter.b();
        for (Object obj : c2) {
            this.f501a.toJson(jsonWriter, (JsonWriter) obj);
        }
        jsonWriter.k();
    }

    public String toString() {
        return this.f501a + ".collection()";
    }

    private CollectionJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.f501a = jsonAdapter;
    }
}
