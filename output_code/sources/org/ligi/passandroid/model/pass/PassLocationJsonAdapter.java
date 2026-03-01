package org.ligi.passandroid.model.pass;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassLocationJsonAdapter extends JsonAdapter<PassLocation> {
    private final JsonAdapter<Double> doubleAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonReader.Options options;

    public PassLocationJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Set<? extends Annotation> b3;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a2 = JsonReader.Options.a("lat", "lon", "name");
        Intrinsics.e(a2, "of(\"lat\", \"lon\", \"name\")");
        this.options = a2;
        Class cls = Double.TYPE;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<Double> f2 = moshi.f(cls, b2, "lat");
        Intrinsics.e(f2, "moshi.adapter(Double::cl… emptySet(),\n      \"lat\")");
        this.doubleAdapter = f2;
        b3 = SetsKt__SetsKt.b();
        JsonAdapter<String> f3 = moshi.f(String.class, b3, "name");
        Intrinsics.e(f3, "moshi.adapter(String::cl…      emptySet(), \"name\")");
        this.nullableStringAdapter = f3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(34);
        sb.append("GeneratedJsonAdapter(");
        sb.append("PassLocation");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public PassLocation fromJson(JsonReader reader) {
        Intrinsics.f(reader, "reader");
        reader.c();
        Double d2 = null;
        Double d3 = null;
        String str = null;
        boolean z2 = false;
        while (reader.o()) {
            int O = reader.O(this.options);
            if (O == -1) {
                reader.T();
                reader.U();
            } else if (O == 0) {
                d2 = this.doubleAdapter.fromJson(reader);
                if (d2 == null) {
                    JsonDataException w2 = Util.w("lat", "lat", reader);
                    Intrinsics.e(w2, "unexpectedNull(\"lat\", \"lat\", reader)");
                    throw w2;
                }
            } else if (O == 1) {
                d3 = this.doubleAdapter.fromJson(reader);
                if (d3 == null) {
                    JsonDataException w3 = Util.w("lon", "lon", reader);
                    Intrinsics.e(w3, "unexpectedNull(\"lon\", \"lon\", reader)");
                    throw w3;
                }
            } else if (O == 2) {
                str = this.nullableStringAdapter.fromJson(reader);
                z2 = true;
            }
        }
        reader.k();
        PassLocation passLocation = new PassLocation();
        passLocation.setLat(d2 != null ? d2.doubleValue() : passLocation.getLat());
        passLocation.setLon(d3 != null ? d3.doubleValue() : passLocation.getLon());
        if (z2) {
            passLocation.setName(str);
        }
        return passLocation;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, PassLocation passLocation) {
        Intrinsics.f(writer, "writer");
        if (passLocation != null) {
            writer.c();
            writer.w("lat");
            this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(passLocation.getLat()));
            writer.w("lon");
            this.doubleAdapter.toJson(writer, (JsonWriter) Double.valueOf(passLocation.getLon()));
            writer.w("name");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passLocation.getName());
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
