package org.ligi.passandroid.reader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ligi.kaxt.StringExtensionsKt;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.functions.CategoryHelperKt;
import org.ligi.passandroid.functions.SafeJSONReaderKt;
import org.ligi.passandroid.model.ApplePassbookQuirkCorrector;
import org.ligi.passandroid.model.AppleStylePassTranslation;
import org.ligi.passandroid.model.PassBitmapDefinitions;
import org.ligi.passandroid.model.PassDefinitions;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassField;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.model.pass.PassLocation;
import org.ligi.passandroid.model.pass.PassType;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.ZonedDateTime;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class AppleStylePassReader {

    /* renamed from: a  reason: collision with root package name */
    public static final AppleStylePassReader f2677a = new AppleStylePassReader();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface JsonStringReadCallback {
        void a(String str);
    }

    private AppleStylePassReader() {
    }

    private final void a(ArrayList<PassField> arrayList, JSONObject jSONObject, String str, AppleStylePassTranslation appleStylePassTranslation, boolean z2) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    JSONObject jsonObject = jSONArray.getJSONObject(i2);
                    Intrinsics.e(jsonObject, "jsonObject");
                    try {
                        arrayList.add(new PassField(f(jsonObject, "key", appleStylePassTranslation), f(jsonObject, "label", appleStylePassTranslation), f(jsonObject, "value", appleStylePassTranslation), z2, str));
                    } catch (JSONException e2) {
                        e = e2;
                        Timber.Forest forest = Timber.f3479a;
                        forest.k("could not process PassField from JSON for " + str + " cause: " + e, new Object[0]);
                    }
                } catch (JSONException e3) {
                    e = e3;
                }
            }
        } catch (JSONException e4) {
            Timber.Forest forest2 = Timber.f3479a;
            forest2.k("could not process PassFields " + str + " from JSON: " + e4, new Object[0]);
        }
    }

    static /* synthetic */ void b(AppleStylePassReader appleStylePassReader, ArrayList arrayList, JSONObject jSONObject, String str, AppleStylePassTranslation appleStylePassTranslation, boolean z2, int i2, Object obj) {
        appleStylePassReader.a(arrayList, jSONObject, str, appleStylePassTranslation, (i2 & 16) != 0 ? false : z2);
    }

    private final void c(File file, String str, String str2) {
        Bitmap d2 = d(file, str, str2);
        if (d2 != null) {
            try {
                Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
                d2.compress(compressFormat, 100, new FileOutputStream(new File(file, str2 + PassImpl.FILETYPE_IMAGES)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private final Bitmap d(File file, String str, String str2) {
        Bitmap bitmap;
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new File(str, str2 + "@2x.png"));
            arrayList.add(new File(str, str2 + PassImpl.FILETYPE_IMAGES));
        }
        arrayList.add(new File(file, str2 + "@2x.png"));
        arrayList.add(new File(file, str2 + "@2x.png"));
        Iterator it = arrayList.iterator();
        do {
            bitmap = null;
            if (!it.hasNext()) {
                break;
            }
            File file2 = (File) it.next();
            try {
                bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                continue;
            } catch (OutOfMemoryError unused) {
                System.gc();
                try {
                    bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                    continue;
                } catch (OutOfMemoryError unused2) {
                    continue;
                }
            }
        } while (bitmap == null);
        return bitmap;
    }

    private final String e(File file, String str, Tracker tracker) {
        File file2 = new File(file, str + ".lproj");
        if (file2.exists() && file2.isDirectory()) {
            tracker.b("measure_event", "pass", str + "_native_lproj", null);
            return file2.getPath();
        }
        File file3 = new File(file, "en.lproj");
        if (!file3.exists() || !file3.isDirectory()) {
            return null;
        }
        tracker.b("measure_event", "pass", "en_lproj", null);
        return file3.getPath();
    }

    private final String f(JSONObject jSONObject, String str, AppleStylePassTranslation appleStylePassTranslation) {
        if (jSONObject.has(str)) {
            try {
                return appleStylePassTranslation.translate(jSONObject.getString(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private final void h(JSONObject jSONObject, String str, JsonStringReadCallback jsonStringReadCallback) {
        if (jSONObject.has(str)) {
            try {
                String string = jSONObject.getString(str);
                Intrinsics.e(string, "json.getString(key)");
                jsonStringReadCallback.a(string);
            } catch (JSONException unused) {
            }
        }
    }

    private final String i(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public final Pass g(File passFile, String language, Context context, Tracker tracker) {
        JSONObject jSONObject;
        List<PassImpl.TimeSpan> b2;
        Intrinsics.f(passFile, "passFile");
        Intrinsics.f(language, "language");
        Intrinsics.f(context, "context");
        Intrinsics.f(tracker, "tracker");
        final AppleStylePassTranslation appleStylePassTranslation = new AppleStylePassTranslation();
        String name = passFile.getName();
        Intrinsics.e(name, "passFile.name");
        final PassImpl passImpl = new PassImpl(name);
        String e2 = e(passFile, language, tracker);
        if (e2 != null) {
            appleStylePassTranslation.loadFromFile(new File(e2, "pass.strings"));
        }
        c(passFile, e2, PassBitmapDefinitions.BITMAP_ICON);
        c(passFile, e2, PassBitmapDefinitions.BITMAP_LOGO);
        c(passFile, e2, PassBitmapDefinitions.BITMAP_STRIP);
        c(passFile, e2, PassBitmapDefinitions.BITMAP_THUMBNAIL);
        c(passFile, e2, PassBitmapDefinitions.BITMAP_FOOTER);
        File file = new File(passFile, "pass.json");
        try {
            jSONObject = SafeJSONReaderKt.a(AppleStylePassTranslation.readFileAsStringGuessEncoding(file));
        } catch (Exception e3) {
            Timber.Forest forest = Timber.f3479a;
            forest.f("PassParse Exception: " + e3, new Object[0]);
            jSONObject = null;
        }
        if (jSONObject == null) {
            for (Charset charset : Charset.availableCharsets().values()) {
                try {
                    Intrinsics.e(charset, "charset");
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
                    jSONObject = SafeJSONReaderKt.a(TextStreamsKt.f(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192)));
                } catch (Exception unused) {
                }
                if (jSONObject != null) {
                    break;
                }
            }
        }
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null) {
            Timber.f3479a.k("could not load pass.json from passcode ", new Object[0]);
            tracker.b("problem_event", "pass", "without_pass_json", null);
            return null;
        }
        try {
            JSONObject a2 = AppleStylePassReaderFunctionsKt.a(jSONObject2);
            if (a2 != null) {
                String barcodeFormatString = a2.getString("format");
                tracker.b("measure_event", "barcode_format", barcodeFormatString, 0L);
                BarCode.Companion companion = BarCode.Companion;
                Intrinsics.e(barcodeFormatString, "barcodeFormatString");
                passImpl.setBarCode(new BarCode(companion.getFormatFromString(barcodeFormatString), a2.getString("message")));
                if (a2.has("altText")) {
                    BarCode barCode = passImpl.getBarCode();
                    Intrinsics.c(barCode);
                    barCode.setAlternativeText(a2.getString("altText"));
                }
            }
        } catch (Exception unused2) {
        }
        if (jSONObject2.has("relevantDate")) {
            try {
                passImpl.setCalendarTimespan(new PassImpl.TimeSpan(ZonedDateTime.T(jSONObject2.getString("relevantDate")), null, null, 6, null));
            } catch (JSONException e4) {
                tracker.a("problem parsing relevant date", e4, false);
            } catch (DateTimeException e5) {
                tracker.a("problem parsing relevant date", e5, false);
            }
        }
        if (jSONObject2.has("expirationDate")) {
            try {
                b2 = CollectionsKt__CollectionsJVMKt.b(new PassImpl.TimeSpan(null, ZonedDateTime.T(jSONObject2.getString("expirationDate")), null, 5, null));
                passImpl.setValidTimespans(b2);
            } catch (JSONException e6) {
                tracker.a("problem parsing expiration date", e6, false);
            } catch (DateTimeException e7) {
                tracker.a("problem parsing expiration date", e7, false);
            }
        }
        passImpl.setSerial(i(jSONObject2, "serialNumber"));
        passImpl.setAuthToken(i(jSONObject2, "authenticationToken"));
        passImpl.setWebServiceURL(i(jSONObject2, "webServiceURL"));
        passImpl.setPassIdent(i(jSONObject2, "passTypeIdentifier"));
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject2.getJSONArray("locations");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                PassLocation passLocation = new PassLocation();
                passLocation.setLat(jSONObject3.getDouble("latitude"));
                passLocation.setLon(jSONObject3.getDouble("longitude"));
                if (jSONObject3.has("relevantText")) {
                    passLocation.setName(appleStylePassTranslation.translate(jSONObject3.getString("relevantText")));
                }
                arrayList.add(passLocation);
            }
        } catch (JSONException unused3) {
        }
        passImpl.setLocations(arrayList);
        h(jSONObject2, "backgroundColor", new JsonStringReadCallback() { // from class: org.ligi.passandroid.reader.AppleStylePassReader$read$1
            @Override // org.ligi.passandroid.reader.AppleStylePassReader.JsonStringReadCallback
            public void a(String string) {
                Intrinsics.f(string, "string");
                PassImpl.this.setAccentColor(StringExtensionsKt.a(string, -16777216));
            }
        });
        h(jSONObject2, "description", new JsonStringReadCallback() { // from class: org.ligi.passandroid.reader.AppleStylePassReader$read$2
            @Override // org.ligi.passandroid.reader.AppleStylePassReader.JsonStringReadCallback
            public void a(String string) {
                Intrinsics.f(string, "string");
                PassImpl.this.setDescription(appleStylePassTranslation.translate(string));
            }
        });
        for (Map.Entry<PassType, String> entry : PassDefinitions.INSTANCE.getTYPE_TO_NAME().entrySet()) {
            if (jSONObject2.has(entry.getValue())) {
                passImpl.setType(entry.getKey());
            }
        }
        try {
            JSONObject jSONObject4 = jSONObject2.getJSONObject(PassDefinitions.INSTANCE.getTYPE_TO_NAME().get(passImpl.getType()));
            if (jSONObject4 != null) {
                ArrayList<PassField> arrayList2 = new ArrayList<>();
                b(this, arrayList2, jSONObject4, "primaryFields", appleStylePassTranslation, false, 16, null);
                b(this, arrayList2, jSONObject4, "headerFields", appleStylePassTranslation, false, 16, null);
                b(this, arrayList2, jSONObject4, "secondaryFields", appleStylePassTranslation, false, 16, null);
                b(this, arrayList2, jSONObject4, "auxiliaryFields", appleStylePassTranslation, false, 16, null);
                a(arrayList2, jSONObject4, "backFields", appleStylePassTranslation, true);
                arrayList2.add(new PassField("", context.getString(2131755316), context.getString(CategoryHelperKt.c(passImpl.getType())), false, null, 16, null));
                passImpl.setFields(arrayList2);
            }
        } catch (JSONException unused4) {
        }
        try {
            passImpl.setCreator(jSONObject2.getString("organizationName"));
            tracker.b("measure_event", "organisation_parse", passImpl.getCreator(), 1L);
        } catch (JSONException unused5) {
        }
        new ApplePassbookQuirkCorrector(tracker).correctQuirks(passImpl);
        return passImpl;
    }
}
