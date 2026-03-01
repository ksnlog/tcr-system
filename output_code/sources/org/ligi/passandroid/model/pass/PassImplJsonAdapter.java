package org.ligi.passandroid.model.pass;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.ligi.passandroid.model.comparator.DirectionAwarePassByTimeComparator;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImplJsonAdapter extends JsonAdapter<PassImpl> {
    private final JsonAdapter<Integer> intAtHexColorAdapter;
    private final JsonAdapter<List<PassLocation>> listOfPassLocationAdapter;
    private final JsonAdapter<List<PassImpl.TimeSpan>> listOfTimeSpanAdapter;
    private final JsonAdapter<List<PassField>> mutableListOfPassFieldAdapter;
    private final JsonAdapter<BarCode> nullableBarCodeAdapter;
    private final JsonAdapter<String> nullableStringAdapter;
    private final JsonAdapter<PassImpl.TimeSpan> nullableTimeSpanAdapter;
    private final JsonReader.Options options;
    private final JsonAdapter<PassType> passTypeAdapter;
    private final JsonAdapter<String> stringAdapter;

    public PassImplJsonAdapter(Moshi moshi) {
        Set<? extends Annotation> b2;
        Set<? extends Annotation> a2;
        Set<? extends Annotation> b3;
        Set<? extends Annotation> b4;
        Set<? extends Annotation> b5;
        Set<? extends Annotation> b6;
        Set<? extends Annotation> b7;
        Set<? extends Annotation> b8;
        Set<? extends Annotation> b9;
        Intrinsics.f(moshi, "moshi");
        JsonReader.Options a3 = JsonReader.Options.a("id", "accentColor", "app", "authToken", "barCode", "calendarTimespan", "creator", "description", "fields", "locations", "passIdent", "serial", "type", "validTimespans", "webServiceURL");
        Intrinsics.e(a3, "of(\"id\", \"accentColor\", …espans\", \"webServiceURL\")");
        this.options = a3;
        b2 = SetsKt__SetsKt.b();
        JsonAdapter<String> f2 = moshi.f(String.class, b2, "id");
        Intrinsics.e(f2, "moshi.adapter(String::cl…, emptySet(),\n      \"id\")");
        this.stringAdapter = f2;
        Class cls = Integer.TYPE;
        a2 = SetsKt__SetsJVMKt.a(new PassImpl.HexColor() { // from class: org.ligi.passandroid.model.pass.PassImplJsonAdapter$annotationImpl$org_ligi_passandroid_model_pass_PassImpl_HexColor$0
            @Override // java.lang.annotation.Annotation
            public final /* synthetic */ Class annotationType() {
                return PassImpl.HexColor.class;
            }

            @Override // java.lang.annotation.Annotation
            public final boolean equals(Object obj) {
                if (!(obj instanceof PassImpl.HexColor)) {
                    return false;
                }
                PassImpl.HexColor hexColor = (PassImpl.HexColor) obj;
                return true;
            }

            @Override // java.lang.annotation.Annotation
            public final int hashCode() {
                return 0;
            }

            @Override // java.lang.annotation.Annotation
            public final String toString() {
                return "@org.ligi.passandroid.model.pass.PassImpl.HexColor()";
            }
        });
        JsonAdapter<Integer> f3 = moshi.f(cls, a2, "accentColor");
        Intrinsics.e(f3, "moshi.adapter(Int::class…xColor()), \"accentColor\")");
        this.intAtHexColorAdapter = f3;
        b3 = SetsKt__SetsKt.b();
        JsonAdapter<String> f4 = moshi.f(String.class, b3, "app");
        Intrinsics.e(f4, "moshi.adapter(String::cl…\n      emptySet(), \"app\")");
        this.nullableStringAdapter = f4;
        b4 = SetsKt__SetsKt.b();
        JsonAdapter<BarCode> f5 = moshi.f(BarCode.class, b4, "barCode");
        Intrinsics.e(f5, "moshi.adapter(BarCode::c…   emptySet(), \"barCode\")");
        this.nullableBarCodeAdapter = f5;
        b5 = SetsKt__SetsKt.b();
        JsonAdapter<PassImpl.TimeSpan> f6 = moshi.f(PassImpl.TimeSpan.class, b5, "calendarTimespan");
        Intrinsics.e(f6, "moshi.adapter(PassImpl.T…et(), \"calendarTimespan\")");
        this.nullableTimeSpanAdapter = f6;
        ParameterizedType j2 = Types.j(List.class, PassField.class);
        b6 = SetsKt__SetsKt.b();
        JsonAdapter<List<PassField>> f7 = moshi.f(j2, b6, "fields");
        Intrinsics.e(f7, "moshi.adapter(Types.newP…    emptySet(), \"fields\")");
        this.mutableListOfPassFieldAdapter = f7;
        ParameterizedType j3 = Types.j(List.class, PassLocation.class);
        b7 = SetsKt__SetsKt.b();
        JsonAdapter<List<PassLocation>> f8 = moshi.f(j3, b7, "locations");
        Intrinsics.e(f8, "moshi.adapter(Types.newP… emptySet(), \"locations\")");
        this.listOfPassLocationAdapter = f8;
        b8 = SetsKt__SetsKt.b();
        JsonAdapter<PassType> f9 = moshi.f(PassType.class, b8, "type");
        Intrinsics.e(f9, "moshi.adapter(PassType::…      emptySet(), \"type\")");
        this.passTypeAdapter = f9;
        ParameterizedType j4 = Types.j(List.class, PassImpl.TimeSpan.class);
        b9 = SetsKt__SetsKt.b();
        JsonAdapter<List<PassImpl.TimeSpan>> f10 = moshi.f(j4, b9, "validTimespans");
        Intrinsics.e(f10, "moshi.adapter(Types.newP…ySet(), \"validTimespans\")");
        this.listOfTimeSpanAdapter = f10;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(30);
        sb.append("GeneratedJsonAdapter(");
        sb.append("PassImpl");
        sb.append(')');
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public PassImpl fromJson(JsonReader reader) {
        Intrinsics.f(reader, "reader");
        reader.c();
        String str = null;
        Integer num = null;
        String str2 = null;
        String str3 = null;
        BarCode barCode = null;
        PassImpl.TimeSpan timeSpan = null;
        String str4 = null;
        String str5 = null;
        List<PassField> list = null;
        List<PassLocation> list2 = null;
        String str6 = null;
        String str7 = null;
        PassType passType = null;
        List<PassImpl.TimeSpan> list3 = null;
        String str8 = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        while (true) {
            List<PassImpl.TimeSpan> list4 = list3;
            if (reader.o()) {
                PassType passType2 = passType;
                switch (reader.O(this.options)) {
                    case DirectionAwarePassByTimeComparator.DIRECTION_DESC /* -1 */:
                        reader.T();
                        reader.U();
                        passType = passType2;
                        break;
                    case 0:
                        str = this.stringAdapter.fromJson(reader);
                        if (str == null) {
                            JsonDataException w2 = Util.w("id", "id", reader);
                            Intrinsics.e(w2, "unexpectedNull(\"id\", \"id\", reader)");
                            throw w2;
                        }
                        passType = passType2;
                        break;
                    case 1:
                        num = this.intAtHexColorAdapter.fromJson(reader);
                        if (num == null) {
                            JsonDataException w3 = Util.w("accentColor", "accentColor", reader);
                            Intrinsics.e(w3, "unexpectedNull(\"accentCo…\", \"accentColor\", reader)");
                            throw w3;
                        }
                        passType = passType2;
                        break;
                    case 2:
                        str2 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z2 = true;
                        continue;
                    case 3:
                        str3 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z3 = true;
                        continue;
                    case 4:
                        barCode = this.nullableBarCodeAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z4 = true;
                        continue;
                    case 5:
                        timeSpan = this.nullableTimeSpanAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z5 = true;
                        continue;
                    case R$styleable.f1342r /* 6 */:
                        str4 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z6 = true;
                        continue;
                    case R$styleable.f1343s /* 7 */:
                        str5 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z7 = true;
                        continue;
                    case R$styleable.f1328d /* 8 */:
                        list = this.mutableListOfPassFieldAdapter.fromJson(reader);
                        if (list == null) {
                            JsonDataException w4 = Util.w("fields", "fields", reader);
                            Intrinsics.e(w4, "unexpectedNull(\"fields\", \"fields\", reader)");
                            throw w4;
                        }
                        passType = passType2;
                        break;
                    case R$styleable.f1329e /* 9 */:
                        list2 = this.listOfPassLocationAdapter.fromJson(reader);
                        if (list2 == null) {
                            JsonDataException w5 = Util.w("locations", "locations", reader);
                            Intrinsics.e(w5, "unexpectedNull(\"locations\", \"locations\", reader)");
                            throw w5;
                        }
                        passType = passType2;
                        break;
                    case R$styleable.f1330f /* 10 */:
                        str6 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z8 = true;
                        continue;
                    case R$styleable.f1331g /* 11 */:
                        str7 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z9 = true;
                        continue;
                    case R$styleable.f1332h /* 12 */:
                        passType = this.passTypeAdapter.fromJson(reader);
                        if (passType == null) {
                            JsonDataException w6 = Util.w("type", "type", reader);
                            Intrinsics.e(w6, "unexpectedNull(\"type\", \"type\",\n            reader)");
                            throw w6;
                        }
                        break;
                    case R$styleable.f1333i /* 13 */:
                        list3 = this.listOfTimeSpanAdapter.fromJson(reader);
                        if (list3 == null) {
                            JsonDataException w7 = Util.w("validTimespans", "validTimespans", reader);
                            Intrinsics.e(w7, "unexpectedNull(\"validTim…\"validTimespans\", reader)");
                            throw w7;
                        }
                        passType = passType2;
                        continue;
                    case R$styleable.f1334j /* 14 */:
                        str8 = this.nullableStringAdapter.fromJson(reader);
                        passType = passType2;
                        list3 = list4;
                        z10 = true;
                        continue;
                    default:
                        passType = passType2;
                        break;
                }
                list3 = list4;
            } else {
                PassType passType3 = passType;
                reader.k();
                if (str != null) {
                    PassImpl passImpl = new PassImpl(str);
                    passImpl.setAccentColor(num != null ? num.intValue() : passImpl.getAccentColor());
                    if (z2) {
                        passImpl.setApp(str2);
                    }
                    if (z3) {
                        passImpl.setAuthToken(str3);
                    }
                    if (z4) {
                        passImpl.setBarCode(barCode);
                    }
                    if (z5) {
                        passImpl.setCalendarTimespan(timeSpan);
                    }
                    if (z6) {
                        passImpl.setCreator(str4);
                    }
                    if (z7) {
                        passImpl.setDescription(str5);
                    }
                    if (list == null) {
                        list = passImpl.getFields();
                    }
                    passImpl.setFields(list);
                    if (list2 == null) {
                        list2 = passImpl.getLocations();
                    }
                    passImpl.setLocations(list2);
                    if (z8) {
                        passImpl.setPassIdent(str6);
                    }
                    if (z9) {
                        passImpl.setSerial(str7);
                    }
                    passImpl.setType(passType3 == null ? passImpl.getType() : passType3);
                    passImpl.setValidTimespans(list4 == null ? passImpl.getValidTimespans() : list4);
                    if (z10) {
                        passImpl.setWebServiceURL(str8);
                    }
                    return passImpl;
                }
                JsonDataException o2 = Util.o("id", "id", reader);
                Intrinsics.e(o2, "missingProperty(\"id\", \"id\", reader)");
                throw o2;
            }
        }
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, PassImpl passImpl) {
        Intrinsics.f(writer, "writer");
        if (passImpl != null) {
            writer.c();
            writer.w("id");
            this.stringAdapter.toJson(writer, (JsonWriter) passImpl.getId());
            writer.w("accentColor");
            this.intAtHexColorAdapter.toJson(writer, (JsonWriter) Integer.valueOf(passImpl.getAccentColor()));
            writer.w("app");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getApp());
            writer.w("authToken");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getAuthToken());
            writer.w("barCode");
            this.nullableBarCodeAdapter.toJson(writer, (JsonWriter) passImpl.getBarCode());
            writer.w("calendarTimespan");
            this.nullableTimeSpanAdapter.toJson(writer, (JsonWriter) passImpl.getCalendarTimespan());
            writer.w("creator");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getCreator());
            writer.w("description");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getDescription());
            writer.w("fields");
            this.mutableListOfPassFieldAdapter.toJson(writer, (JsonWriter) passImpl.getFields());
            writer.w("locations");
            this.listOfPassLocationAdapter.toJson(writer, (JsonWriter) passImpl.getLocations());
            writer.w("passIdent");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getPassIdent());
            writer.w("serial");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getSerial());
            writer.w("type");
            this.passTypeAdapter.toJson(writer, (JsonWriter) passImpl.getType());
            writer.w("validTimespans");
            this.listOfTimeSpanAdapter.toJson(writer, (JsonWriter) passImpl.getValidTimespans());
            writer.w("webServiceURL");
            this.nullableStringAdapter.toJson(writer, (JsonWriter) passImpl.getWebServiceURL());
            writer.m();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
