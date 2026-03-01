package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class NonNullJsonAdapter<T> extends JsonAdapter<T> {

    /* renamed from: a  reason: collision with root package name */
    private final JsonAdapter<T> f632a;

    public NonNullJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.f632a = jsonAdapter;
    }

    @Override // com.squareup.moshi.JsonAdapter
    @Nullable
    public T fromJson(JsonReader jsonReader) {
        if (jsonReader.G() != JsonReader.Token.NULL) {
            return this.f632a.fromJson(jsonReader);
        }
        throw new JsonDataException("Unexpected null at " + jsonReader.n());
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
        if (t2 != null) {
            this.f632a.toJson(jsonWriter, (JsonWriter) t2);
            return;
        }
        throw new JsonDataException("Unexpected null at " + jsonWriter.o());
    }

    public String toString() {
        return this.f632a + ".nonNull()";
    }
}
