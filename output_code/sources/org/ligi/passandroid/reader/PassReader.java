package org.ligi.passandroid.reader;

import android.graphics.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONObject;
import org.ligi.passandroid.functions.SafeJSONReaderKt;
import org.ligi.passandroid.model.PassDefinitions;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.model.pass.PassType;
import org.threeten.bp.ZonedDateTime;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassReader {

    /* renamed from: a  reason: collision with root package name */
    public static final PassReader f2681a = new PassReader();

    private PassReader() {
    }

    public final Pass a(File path) {
        BufferedReader bufferedReader;
        Intrinsics.f(path, "path");
        String name = path.getName();
        Intrinsics.e(name, "path.name");
        PassImpl passImpl = new PassImpl(name);
        File file = new File(path, "data.json");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charsets.f909b);
            if (inputStreamReader instanceof BufferedReader) {
                bufferedReader = (BufferedReader) inputStreamReader;
            } else {
                bufferedReader = new BufferedReader(inputStreamReader, 8192);
            }
            JSONObject a2 = SafeJSONReaderKt.a(TextStreamsKt.f(bufferedReader));
            Intrinsics.c(a2);
            if (a2.has("what")) {
                passImpl.setDescription(a2.getJSONObject("what").getString("description"));
            }
            if (a2.has("meta")) {
                JSONObject jSONObject = a2.getJSONObject("meta");
                PassType passType = PassDefinitions.INSTANCE.getNAME_TO_TYPE().get(jSONObject.getString("type"));
                if (passType == null) {
                    passType = PassType.GENERIC;
                }
                passImpl.setType(passType);
                passImpl.setCreator(jSONObject.getString("organisation"));
                passImpl.setApp(jSONObject.getString("app"));
            }
            if (a2.has("ui")) {
                passImpl.setAccentColor(Color.parseColor(a2.getJSONObject("ui").getString("bgColor")));
            }
            if (a2.has("barcode")) {
                JSONObject jSONObject2 = a2.getJSONObject("barcode");
                String barcodeFormatString = jSONObject2.getString("type");
                BarCode.Companion companion = BarCode.Companion;
                Intrinsics.e(barcodeFormatString, "barcodeFormatString");
                BarCode barCode = new BarCode(companion.getFormatFromString(barcodeFormatString), jSONObject2.getString("message"));
                passImpl.setBarCode(barCode);
                if (jSONObject2.has("altText")) {
                    barCode.setAlternativeText(jSONObject2.getString("altText"));
                }
            }
            if (a2.has("when")) {
                String string = a2.getJSONObject("when").getString("dateTime");
                passImpl.setCalendarTimespan(new PassImpl.TimeSpan(null, null, null, 7, null));
                passImpl.setCalendarTimespan(new PassImpl.TimeSpan(ZonedDateTime.T(string), null, null, 6, null));
            }
        } catch (Exception e2) {
            Timber.Forest forest = Timber.f3479a;
            forest.f("PassParse Exception: " + e2, new Object[0]);
        }
        return passImpl;
    }
}
