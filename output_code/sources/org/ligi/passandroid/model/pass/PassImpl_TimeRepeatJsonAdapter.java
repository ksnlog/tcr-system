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
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImpl_TimeRepeatJsonAdapter extends JsonAdapter<PassImpl.TimeRepeat> {
    private final JsonAdapter<Integer> intAdapter;
    private final JsonReader.Options options;

    public PassImpl_TimeRepeatJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a2 = JsonReader.Options.a("offset", "count");
        Intrinsics.e(a2, "of(\"offset\", \"count\")");
        this.options = a2;
        Class cls = Integer.TYPE;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<Integer> f2 = moshi.f(cls, b2, "offset");
        Intrinsics.e(f2, "moshi.adapter(Int::class…va, emptySet(), \"offset\")");
        this.intAdapter = f2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(41);
        sb.append("GeneratedJsonAdapter(");
        sb.append("PassImpl.TimeRepeat");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public PassImpl.TimeRepeat fromJson(JsonReader reader) {
        Intrinsics.f(reader, "reader");
        reader.c();
        Integer num = null;
        Integer num2 = null;
        while (reader.o()) {
            int O = reader.O(this.options);
            if (O == -1) {
                reader.T();
                reader.U();
            } else if (O == 0) {
                num = this.intAdapter.fromJson(reader);
                if (num == null) {
                    JsonDataException w2 = Util.w("offset", "offset", reader);
                    Intrinsics.e(w2, "unexpectedNull(\"offset\",…set\",\n            reader)");
                    throw w2;
                }
            } else if (O == 1 && (num2 = this.intAdapter.fromJson(reader)) == null) {
                JsonDataException w3 = Util.w("count", "count", reader);
                Intrinsics.e(w3, "unexpectedNull(\"count\", …unt\",\n            reader)");
                throw w3;
            }
        }
        reader.k();
        if (num != null) {
            int intValue = num.intValue();
            if (num2 != null) {
                return new PassImpl.TimeRepeat(intValue, num2.intValue());
            }
            JsonDataException o2 = Util.o("count", "count", reader);
            Intrinsics.e(o2, "missingProperty(\"count\", \"count\", reader)");
            throw o2;
        }
        JsonDataException o3 = Util.o("offset", "offset", reader);
        Intrinsics.e(o3, "missingProperty(\"offset\", \"offset\", reader)");
        throw o3;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, PassImpl.TimeRepeat timeRepeat) {
        Intrinsics.f(writer, "writer");
        if (timeRepeat != null) {
            writer.c();
            writer.w("offset");
            this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(timeRepeat.getOffset()));
            writer.w("count");
            this.intAdapter.toJson(writer, (JsonWriter) Integer.valueOf(timeRepeat.getCount()));
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
