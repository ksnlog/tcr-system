package kotlin.text;

import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Charsets {

    /* renamed from: a  reason: collision with root package name */
    public static final Charsets f908a = new Charsets();

    /* renamed from: b  reason: collision with root package name */
    public static final Charset f909b;

    /* renamed from: c  reason: collision with root package name */
    public static final Charset f910c;

    /* renamed from: d  reason: collision with root package name */
    public static final Charset f911d;

    /* renamed from: e  reason: collision with root package name */
    public static final Charset f912e;

    /* renamed from: f  reason: collision with root package name */
    public static final Charset f913f;

    /* renamed from: g  reason: collision with root package name */
    public static final Charset f914g;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.e(forName, "forName(\"UTF-8\")");
        f909b = forName;
        Charset forName2 = Charset.forName("UTF-16");
        Intrinsics.e(forName2, "forName(\"UTF-16\")");
        f910c = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        Intrinsics.e(forName3, "forName(\"UTF-16BE\")");
        f911d = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        Intrinsics.e(forName4, "forName(\"UTF-16LE\")");
        f912e = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        Intrinsics.e(forName5, "forName(\"US-ASCII\")");
        f913f = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        Intrinsics.e(forName6, "forName(\"ISO-8859-1\")");
        f914g = forName6;
    }

    private Charsets() {
    }
}
