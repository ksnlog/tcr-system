package org.ligi.passandroid.ui.quirk_fix;

import android.net.Uri;
import java.net.URLEncoder;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class URLRewriteController {

    /* renamed from: a  reason: collision with root package name */
    private final Tracker f2957a;

    public URLRewriteController(Tracker tracker) {
        Intrinsics.f(tracker, "tracker");
        this.f2957a = tracker;
    }

    private final String a(Uri uri) {
        return uri + "?appDetection=false";
    }

    private final String b(Uri uri) {
        return uri + ".pkpass";
    }

    private final String c(Uri uri) {
        String queryParameter = uri.getQueryParameter("v");
        this.f2957a.b("quirk_fix", "redirect_attempt", "cathay", null);
        if (queryParameter == null) {
            return null;
        }
        this.f2957a.b("quirk_fix", "redirect", "cathay", null);
        return "https://www.cathaypacific.com/icheckin2/PassbookServlet?v=" + URLEncoder.encode(queryParameter, "UTF-8");
    }

    private final String d(Uri uri) {
        boolean g2;
        List f2;
        boolean z2;
        String valueOf = String.valueOf(uri);
        g2 = StringsKt__StringsJVMKt.g(valueOf, "/", false, 2, null);
        if (g2) {
            valueOf = StringsKt___StringsKt.S(valueOf, 1);
        }
        List<String> c2 = new Regex("/").c(valueOf, 0);
        if (!c2.isEmpty()) {
            ListIterator<String> listIterator = c2.listIterator(c2.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() == 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    f2 = CollectionsKt___CollectionsKt.F(c2, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        f2 = CollectionsKt__CollectionsKt.f();
        String[] strArr = (String[]) f2.toArray(new String[0]);
        if (strArr.length < 6) {
            return null;
        }
        return "http://prod.wap.ncrwebhost.mobi/mobiqa/wap/" + strArr[strArr.length - 2] + '/' + strArr[strArr.length - 1] + "/passbook";
    }

    private final String f(Uri uri) {
        boolean p2;
        String queryParameter;
        p2 = StringsKt__StringsKt.p(String.valueOf(uri), "CheckInApiIntegration", false, 2, null);
        if (p2) {
            queryParameter = uri.getQueryParameter("key");
            this.f2957a.b("quirk_fix", "redirect_attempt", "virgin_australia2", null);
        } else {
            this.f2957a.b("quirk_fix", "redirect_attempt", "virgin_australia1", null);
            queryParameter = uri.getQueryParameter("c");
        }
        if (queryParameter == null) {
            return null;
        }
        this.f2957a.b("quirk_fix", "redirect", "virgin_australia", null);
        return "https://mobile.virginaustralia.com/boarding/pass.pkpass?key=" + URLEncoder.encode(queryParameter, "UTF-8");
    }

    public final String e(Uri uri) {
        boolean g2;
        String scheme;
        Intrinsics.f(uri, "uri");
        if (uri.getScheme() != null && uri.getAuthority() != null && Intrinsics.a(uri.getAuthority(), "import") && (scheme = uri.getScheme()) != null) {
            int hashCode = scheme.hashCode();
            if (hashCode != -995382700) {
                if (hashCode == -982561474 && scheme.equals("passandroid")) {
                    String substring = String.valueOf(uri).substring(21);
                    Intrinsics.e(substring, "this as java.lang.String).substring(startIndex)");
                    return substring;
                }
            } else if (scheme.equals("pass2u")) {
                String substring2 = String.valueOf(uri).substring(16);
                Intrinsics.e(substring2, "this as java.lang.String).substring(startIndex)");
                return substring2;
            }
        }
        String host = uri.getHost();
        if (host != null) {
            g2 = StringsKt__StringsJVMKt.g(host, ".virginaustralia.com", false, 2, null);
            if (g2) {
                return f(uri);
            }
        }
        if (host == null) {
            return null;
        }
        switch (host.hashCode()) {
            case -564952560:
                if (!host.equals("mci.aircanada.com")) {
                    return null;
                }
                return b(uri);
            case -457845419:
                if (!host.equals("m.aircanada.ca")) {
                    return null;
                }
                return a(uri);
            case -445566247:
                if (!host.equals("prod.wap.ncrwebhost.mobi")) {
                    return null;
                }
                return d(uri);
            case -359929083:
                if (!host.equals("mbp.swiss.com")) {
                    return null;
                }
                return d(uri);
            case -163402341:
                if (!host.equals("services.aircanada.com")) {
                    return null;
                }
                return a(uri);
            case 201182907:
                if (!host.equals("www.cathaypacific.com")) {
                    return null;
                }
                return c(uri);
            case 1940613825:
                if (!host.equals("pass-cloud.appspot.com")) {
                    return null;
                }
                return uri.getQueryParameter("url");
            default:
                return null;
        }
    }
}
