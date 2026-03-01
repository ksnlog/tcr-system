package org.ligi.passandroid.model.pass;

import com.squareup.moshi.JsonAdapter;
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
public final class BarCodeJsonAdapter extends JsonAdapter<BarCode> {
    private volatile Constructor<BarCode> constructorRef;
    private final JsonAdapter<PassBarCodeFormat> nullablePassBarCodeFormatAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;

    public BarCodeJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Set<? extends Annotation> b3;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a2 = JsonReader.Options.a("format", "message", "alternativeText");
        Intrinsics.e(a2, "of(\"format\", \"message\",\n      \"alternativeText\")");
        this.options = a2;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<PassBarCodeFormat> f2 = moshi.f(PassBarCodeFormat.class, b2, "format");
        Intrinsics.e(f2, "moshi.adapter(PassBarCod…va, emptySet(), \"format\")");
        this.nullablePassBarCodeFormatAdapter = f2;
        b3 = SetsKt__SetsKt.b();
        JsonAdapter<String> f3 = moshi.f(String.class, b3, "message");
        Intrinsics.e(f3, "moshi.adapter(String::cl…   emptySet(), \"message\")");
        this.nullableStringAdapter = f3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(29);
        sb.append("GeneratedJsonAdapter(");
        sb.append("BarCode");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public BarCode fromJson(JsonReader reader) {
        BarCode barCode;
        Intrinsics.f(reader, "reader");
        reader.c();
        PassBarCodeFormat passBarCodeFormat = null;
        String str = null;
        String str2 = null;
        int i2 = -1;
        boolean z2 = false;
        while (reader.o()) {
            int O = reader.O(this.options);
            if (O == -1) {
                reader.T();
                reader.U();
            } else if (O == 0) {
                passBarCodeFormat = this.nullablePassBarCodeFormatAdapter.fromJson(reader);
            } else if (O == 1) {
                str = this.nullableStringAdapter.fromJson(reader);
                i2 &= -3;
            } else if (O == 2) {
                str2 = this.nullableStringAdapter.fromJson(reader);
                z2 = true;
            }
        }
        reader.k();
        if (i2 == -3) {
            barCode = new BarCode(passBarCodeFormat, str);
        } else {
            Constructor<BarCode> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = BarCode.class.getDeclaredConstructor(PassBarCodeFormat.class, String.class, Integer.TYPE, Util.f636c);
                this.constructorRef = constructor;
                Intrinsics.e(constructor, "BarCode::class.java.getD…his.constructorRef = it }");
            }
            BarCode newInstance = constructor.newInstance(passBarCodeFormat, str, Integer.valueOf(i2), null);
            Intrinsics.e(newInstance, "localConstructor.newInst…torMarker */ null\n      )");
            barCode = newInstance;
        }
        if (z2) {
            barCode.setAlternativeText(str2);
        }
        return barCode;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, BarCode barCode) {
        Intrinsics.f(writer, "writer");
        if (barCode != null) {
            writer.c();
            writer.w("format");
            this.nullablePassBarCodeFormatAdapter.toJson(writer, (JsonWriter) barCode.getFormat());
            writer.w("message");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) barCode.getMessage());
            writer.w("alternativeText");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) barCode.getAlternativeText());
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
