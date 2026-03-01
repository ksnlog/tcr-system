package org.ligi.passandroid.reader;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AppleStylePassReaderFunctionsKt {
    public static final JSONObject a(JSONObject jSONObject) {
        Intrinsics.f(jSONObject, "<this>");
        if (jSONObject.has("barcode")) {
            return jSONObject.getJSONObject("barcode");
        }
        if (jSONObject.has("barcodes")) {
            JSONArray jSONArray = jSONObject.getJSONArray("barcodes");
            if (jSONObject.length() > 0) {
                return jSONArray.getJSONObject(0);
            }
            return null;
        }
        return null;
    }
}
