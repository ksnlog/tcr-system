package org.ligi.passandroid.model.pass;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassFieldJsonAdapter extends JsonAdapter<PassField> {
    private final JsonAdapter<Boolean> booleanAdapter;
    private volatile Constructor<PassField> constructorRef;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;

    public PassFieldJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Set<? extends Annotation> b3;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a2 = JsonReader.Options.a("key", "label", "value", "hide", "hint");
        Intrinsics.e(a2, "of(\"key\", \"label\", \"value\", \"hide\",\n      \"hint\")");
        this.options = a2;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<String> f2 = moshi.f(String.class, b2, "key");
        Intrinsics.e(f2, "moshi.adapter(String::cl…\n      emptySet(), \"key\")");
        this.nullableStringAdapter = f2;
        Class cls = Boolean.TYPE;
        b3 = SetsKt__SetsKt.b();
        JsonAdapter<Boolean> f3 = moshi.f(cls, b3, "hide");
        Intrinsics.e(f3, "moshi.adapter(Boolean::c…emptySet(),\n      \"hide\")");
        this.booleanAdapter = f3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(31);
        sb.append("GeneratedJsonAdapter(");
        sb.append("PassField");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public PassField fromJson(JsonReader reader) {
        Intrinsics.f(reader, "reader");
        reader.c();
        int i2 = -1;
        Boolean bool = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (reader.o()) {
            int O = reader.O(this.options);
            if (O == -1) {
                reader.T();
                reader.U();
            } else if (O == 0) {
                str = this.nullableStringAdapter.fromJson(reader);
            } else if (O == 1) {
                str2 = this.nullableStringAdapter.fromJson(reader);
            } else if (O == 2) {
                str3 = this.nullableStringAdapter.fromJson(reader);
            } else if (O == 3) {
                bool = this.booleanAdapter.fromJson(reader);
                if (bool == null) {
                    JsonDataException w2 = Util.w("hide", "hide", reader);
                    Intrinsics.e(w2, "unexpectedNull(\"hide\", \"hide\",\n            reader)");
                    throw w2;
                }
            } else if (O == 4) {
                str4 = this.nullableStringAdapter.fromJson(reader);
                i2 &= -17;
            }
        }
        reader.k();
        if (i2 == -17) {
            if (bool != null) {
                return new PassField(str, str2, str3, bool.booleanValue(), str4);
            }
            JsonDataException o2 = Util.o("hide", "hide", reader);
            Intrinsics.e(o2, "missingProperty(\"hide\", \"hide\", reader)");
            throw o2;
        }
        Constructor<PassField> constructor = this.constructorRef;
        if (constructor == null) {
            constructor = PassField.class.getDeclaredConstructor(String.class, String.class, String.class, Boolean.TYPE, String.class, Integer.TYPE, Util.f636c);
            this.constructorRef = constructor;
            Intrinsics.e(constructor, "PassField::class.java.ge…his.constructorRef = it }");
        }
        Object[] objArr = new Object[7];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = str3;
        if (bool == null) {
            JsonDataException o3 = Util.o("hide", "hide", reader);
            Intrinsics.e(o3, "missingProperty(\"hide\", \"hide\", reader)");
            throw o3;
        }
        objArr[3] = Boolean.valueOf(bool.booleanValue());
        objArr[4] = str4;
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = null;
        PassField newInstance = constructor.newInstance(objArr);
        Intrinsics.e(newInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return newInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, PassField passField) {
        Intrinsics.f(writer, "writer");
        if (passField != null) {
            writer.c();
            writer.w("key");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passField.getKey());
            writer.w("label");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passField.getLabel());
            writer.w("value");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passField.getValue());
            writer.w("hide");
            this.booleanAdapter.toJson(writer, (JsonWriter) Boolean.valueOf(passField.getHide()));
            writer.w("hint");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passField.getHint());
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
