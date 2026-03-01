package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class _JvmPlatformKt {
    public static final byte[] a(String str) {
        Intrinsics.f(str, "<this>");
        byte[] bytes = str.getBytes(Charsets.f909b);
        Intrinsics.e(bytes, "this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static final String b(byte[] bArr) {
        Intrinsics.f(bArr, "<this>");
        return new String(bArr, Charsets.f909b);
    }
}
