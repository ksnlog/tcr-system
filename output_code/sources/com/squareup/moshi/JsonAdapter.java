package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.NonNullJsonAdapter;
import com.squareup.moshi.internal.NullSafeJsonAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class JsonAdapter<T> {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Factory {
        @CheckReturnValue
        @Nullable
        JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi);
    }

    @CheckReturnValue
    public final JsonAdapter<T> failOnUnknown() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.3
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) {
                boolean m2 = jsonReader.m();
                jsonReader.R(true);
                try {
                    return (T) this.fromJson(jsonReader);
                } finally {
                    jsonReader.R(m2);
                }
            }

            @Override // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return this.isLenient();
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
                this.toJson(jsonWriter, (JsonWriter) t2);
            }

            public String toString() {
                return this + ".failOnUnknown()";
            }
        };
    }

    @CheckReturnValue
    @Nullable
    public abstract T fromJson(JsonReader jsonReader);

    @CheckReturnValue
    @Nullable
    public final T fromJson(BufferedSource bufferedSource) {
        return fromJson(JsonReader.E(bufferedSource));
    }

    @CheckReturnValue
    @Nullable
    public final T fromJsonValue(@Nullable Object obj) {
        try {
            return fromJson(new JsonValueReader(obj));
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @CheckReturnValue
    public JsonAdapter<T> indent(final String str) {
        if (str != null) {
            return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.4
                @Override // com.squareup.moshi.JsonAdapter
                @Nullable
                public T fromJson(JsonReader jsonReader) {
                    return (T) this.fromJson(jsonReader);
                }

                @Override // com.squareup.moshi.JsonAdapter
                boolean isLenient() {
                    return this.isLenient();
                }

                @Override // com.squareup.moshi.JsonAdapter
                public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
                    String n2 = jsonWriter.n();
                    jsonWriter.I(str);
                    try {
                        this.toJson(jsonWriter, (JsonWriter) t2);
                    } finally {
                        jsonWriter.I(n2);
                    }
                }

                public String toString() {
                    return this + ".indent(\"" + str + "\")";
                }
            };
        }
        throw new NullPointerException("indent == null");
    }

    boolean isLenient() {
        return false;
    }

    @CheckReturnValue
    public final JsonAdapter<T> lenient() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.2
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) {
                boolean p2 = jsonReader.p();
                jsonReader.S(true);
                try {
                    return (T) this.fromJson(jsonReader);
                } finally {
                    jsonReader.S(p2);
                }
            }

            @Override // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return true;
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
                boolean r2 = jsonWriter.r();
                jsonWriter.J(true);
                try {
                    this.toJson(jsonWriter, (JsonWriter) t2);
                } finally {
                    jsonWriter.J(r2);
                }
            }

            public String toString() {
                return this + ".lenient()";
            }
        };
    }

    @CheckReturnValue
    public final JsonAdapter<T> nonNull() {
        if (this instanceof NonNullJsonAdapter) {
            return this;
        }
        return new NonNullJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter<T> nullSafe() {
        if (this instanceof NullSafeJsonAdapter) {
            return this;
        }
        return new NullSafeJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter<T> serializeNulls() {
        return new JsonAdapter<T>() { // from class: com.squareup.moshi.JsonAdapter.1
            @Override // com.squareup.moshi.JsonAdapter
            @Nullable
            public T fromJson(JsonReader jsonReader) {
                return (T) this.fromJson(jsonReader);
            }

            @Override // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return this.isLenient();
            }

            @Override // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter, @Nullable T t2) {
                boolean p2 = jsonWriter.p();
                jsonWriter.O(true);
                try {
                    this.toJson(jsonWriter, (JsonWriter) t2);
                } finally {
                    jsonWriter.O(p2);
                }
            }

            public String toString() {
                return this + ".serializeNulls()";
            }
        };
    }

    public abstract void toJson(JsonWriter jsonWriter, @Nullable T t2);

    public final void toJson(BufferedSink bufferedSink, @Nullable T t2) {
        toJson(JsonWriter.z(bufferedSink), (JsonWriter) t2);
    }

    @CheckReturnValue
    @Nullable
    public final Object toJsonValue(@Nullable T t2) {
        JsonValueWriter jsonValueWriter = new JsonValueWriter();
        try {
            toJson((JsonWriter) jsonValueWriter, (JsonValueWriter) t2);
            return jsonValueWriter.W();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @CheckReturnValue
    @Nullable
    public final T fromJson(String str) {
        JsonReader E = JsonReader.E(new Buffer().H(str));
        T fromJson = fromJson(E);
        if (isLenient() || E.G() == JsonReader.Token.END_DOCUMENT) {
            return fromJson;
        }
        throw new JsonDataException("JSON document was not fully consumed.");
    }

    @CheckReturnValue
    public final String toJson(@Nullable T t2) {
        Buffer buffer = new Buffer();
        try {
            toJson((BufferedSink) buffer, (Buffer) t2);
            return buffer.I();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
