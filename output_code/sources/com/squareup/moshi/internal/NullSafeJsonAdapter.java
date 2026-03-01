package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NullSafeJsonAdapter<T> extends JsonAdapter<T> {

    /* renamed from: a  reason: collision with root package name */
    private final JsonAdapter<T> f633a;

    public NullSafeJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.f633a = jsonAdapter;
    }

    @Override // com.squareup.moshi.JsonAdapter
    @Nullable
    public T fromJson(JsonReader jsonReader) {
        if (jsonReader.G() == JsonReader.Token.NULL) {
            return (T) jsonReader.B();
        }
        return this.f633a.fromJson(jsonReader);
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
        if (t2 == null) {
            jsonWriter.x();
        } else {
            this.f633a.toJson(jsonWriter, (JsonWriter) t2);
        }
    }

    public String toString() {
        return this.f633a + ".nullSafe()";
    }
}
