package org.ligi.passandroid.functions;

import android.content.Context;
import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.model.InputStreamWithSource;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InputStreamProviderKt {
    private static final InputStreamWithSource a(Context context, Uri uri) {
        String valueOf = String.valueOf(uri);
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        Intrinsics.c(openInputStream);
        return new InputStreamWithSource(valueOf, openInputStream);
    }

    private static final InputStreamWithSource b(Uri uri, Tracker tracker) {
        Map e2;
        boolean p2;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder h2 = new Request.Builder().h(new URL(String.valueOf(uri)));
        e2 = MapsKt__MapsKt.e(TuplesKt.a("air_canada", "//m.aircanada.ca/ebp/"), TuplesKt.a("air_canada2", "//services.aircanada.com/ebp/"), TuplesKt.a("air_canada3", "//mci.aircanada.com/mci/bp/"), TuplesKt.a("icelandair", "//checkin.si.amadeus.net"), TuplesKt.a("mbk", "//mbk.thy.com/"), TuplesKt.a("heathrow", "//passbook.heathrow.com/"), TuplesKt.a("eventbrite", "//www.eventbrite.com/passes/order"));
        for (Map.Entry entry : e2.entrySet()) {
            String str = (String) entry.getKey();
            p2 = StringsKt__StringsKt.p(String.valueOf(uri), (String) entry.getValue(), false, 2, null);
            if (p2) {
                tracker.b("quirk_fix", "ua_fake", str, null);
                h2.c("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
            }
        }
        ResponseBody b2 = okHttpClient.r(h2.b()).k().b();
        if (b2 == null) {
            return null;
        }
        String valueOf = String.valueOf(uri);
        InputStream b3 = b2.b();
        Intrinsics.e(b3, "body.byteStream()");
        return new InputStreamWithSource(valueOf, b3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
        if (r0.equals("https") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
        if (r0.equals("http") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
        return b(r5, r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final org.ligi.passandroid.model.InputStreamWithSource c(android.content.Context r4, android.net.Uri r5, org.ligi.passandroid.Tracker r6) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.f(r5, r0)
            java.lang.String r0 = "tracker"
            kotlin.jvm.internal.Intrinsics.f(r6, r0)
            java.lang.String r0 = r5.getScheme()
            r1 = 0
            java.lang.String r2 = "protocol"
            java.lang.String r3 = "to_inputstream"
            r6.b(r2, r3, r0, r1)
            java.lang.String r0 = r5.getScheme()
            if (r0 == 0) goto L5b
            int r1 = r0.hashCode()
            switch(r1) {
                case 3143036: goto L4d;
                case 3213448: goto L3f;
                case 99617003: goto L36;
                case 951530617: goto L29;
                default: goto L28;
            }
        L28:
            goto L5b
        L29:
            java.lang.String r1 = "content"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5b
            org.ligi.passandroid.model.InputStreamWithSource r4 = a(r4, r5)
            goto L78
        L36:
            java.lang.String r4 = "https"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L48
            goto L5b
        L3f:
            java.lang.String r4 = "http"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L48
            goto L5b
        L48:
            org.ligi.passandroid.model.InputStreamWithSource r4 = b(r5, r6)
            return r4
        L4d:
            java.lang.String r4 = "file"
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L56
            goto L5b
        L56:
            org.ligi.passandroid.model.InputStreamWithSource r4 = d(r5)
            goto L78
        L5b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "unknown scheme in ImportAsyncTask"
            r4.append(r0)
            java.lang.String r0 = r5.getScheme()
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r0 = 0
            r6.c(r4, r0)
            org.ligi.passandroid.model.InputStreamWithSource r4 = d(r5)
        L78:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.functions.InputStreamProviderKt.c(android.content.Context, android.net.Uri, org.ligi.passandroid.Tracker):org.ligi.passandroid.model.InputStreamWithSource");
    }

    private static final InputStreamWithSource d(Uri uri) {
        return new InputStreamWithSource(String.valueOf(uri), new BufferedInputStream(new URL(String.valueOf(uri)).openStream(), 4096));
    }
}
