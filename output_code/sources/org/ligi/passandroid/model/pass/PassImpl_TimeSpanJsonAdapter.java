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
import org.ligi.passandroid.model.pass.PassImpl;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImpl_TimeSpanJsonAdapter extends JsonAdapter<PassImpl.TimeSpan> {
    private volatile Constructor<PassImpl.TimeSpan> constructorRef;
    private final JsonAdapter<PassImpl.TimeRepeat> nullableTimeRepeatAdapter;
    private final JsonAdapter<ZonedDateTime> nullableZonedDateTimeAdapter;
    private final JsonReader.Options options;

    public PassImpl_TimeSpanJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Set<? extends Annotation> b3;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a2 = JsonReader.Options.a("from", "to", "repeat");
        Intrinsics.e(a2, "of(\"from\", \"to\", \"repeat\")");
        this.options = a2;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<ZonedDateTime> f2 = moshi.f(ZonedDateTime.class, b2, "from");
        Intrinsics.e(f2, "moshi.adapter(ZonedDateT…java, emptySet(), \"from\")");
        this.nullableZonedDateTimeAdapter = f2;
        b3 = SetsKt__SetsKt.b();
        JsonAdapter<PassImpl.TimeRepeat> f3 = moshi.f(PassImpl.TimeRepeat.class, b3, "repeat");
        Intrinsics.e(f3, "moshi.adapter(PassImpl.T…va, emptySet(), \"repeat\")");
        this.nullableTimeRepeatAdapter = f3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(39);
        sb.append("GeneratedJsonAdapter(");
        sb.append("PassImpl.TimeSpan");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public PassImpl.TimeSpan fromJson(JsonReader reader) {
        Intrinsics.f(reader, "reader");
        reader.c();
        ZonedDateTime zonedDateTime = null;
        ZonedDateTime zonedDateTime2 = null;
        PassImpl.TimeRepeat timeRepeat = null;
        int i2 = -1;
        while (reader.o()) {
            int O = reader.O(this.options);
            if (O == -1) {
                reader.T();
                reader.U();
            } else if (O == 0) {
                zonedDateTime = this.nullableZonedDateTimeAdapter.fromJson(reader);
                i2 &= -2;
            } else if (O == 1) {
                zonedDateTime2 = this.nullableZonedDateTimeAdapter.fromJson(reader);
                i2 &= -3;
            } else if (O == 2) {
                timeRepeat = this.nullableTimeRepeatAdapter.fromJson(reader);
                i2 &= -5;
            }
        }
        reader.k();
        if (i2 == -8) {
            return new PassImpl.TimeSpan(zonedDateTime, zonedDateTime2, timeRepeat);
        }
        Constructor<PassImpl.TimeSpan> constructor = this.constructorRef;
        if (constructor == null) {
            constructor = PassImpl.TimeSpan.class.getDeclaredConstructor(ZonedDateTime.class, ZonedDateTime.class, PassImpl.TimeRepeat.class, Integer.TYPE, Util.f636c);
            this.constructorRef = constructor;
            Intrinsics.e(constructor, "PassImpl.TimeSpan::class…his.constructorRef = it }");
        }
        PassImpl.TimeSpan newInstance = constructor.newInstance(zonedDateTime, zonedDateTime2, timeRepeat, Integer.valueOf(i2), null);
        Intrinsics.e(newInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return newInstance;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, PassImpl.TimeSpan timeSpan) {
        Intrinsics.f(writer, "writer");
        if (timeSpan != null) {
            writer.c();
            writer.w("from");
            this.nullableZonedDateTimeAdapter.toJson(writer, (JsonWriter) timeSpan.getFrom());
            writer.w("to");
            this.nullableZonedDateTimeAdapter.toJson(writer, (JsonWriter) timeSpan.getTo());
            writer.w("repeat");
            this.nullableTimeRepeatAdapter.toJson(writer, (JsonWriter) timeSpan.getRepeat());
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
