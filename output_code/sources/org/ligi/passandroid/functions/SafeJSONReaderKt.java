package org.ligi.passandroid.functions;

import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.text.Regex;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SafeJSONReaderKt {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, String> f2672a;

    static {
        Map<String, String> e2;
        e2 = MapsKt__MapsKt.e(TuplesKt.a("", ""), TuplesKt.a(",[\n\r\t ]*\\}", "}"), TuplesKt.a(",[\n\r\t ]*\\]", "]"), TuplesKt.a(":[ ]*,[\n\r\t ]*\"", ":\"\","), TuplesKt.a(",[\n\r\t ]*,", ","));
        f2672a = e2;
    }

    public static final JSONObject a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = str;
        for (Map.Entry<String, String> entry : f2672a.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                str2 = new Regex(key).b(str2, value);
                return new JSONObject(new Regex(key).b(str, value));
            } catch (JSONException unused) {
            }
        }
        return new JSONObject(str2);
    }
}
