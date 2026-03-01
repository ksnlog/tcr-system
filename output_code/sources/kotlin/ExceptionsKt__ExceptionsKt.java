package kotlin;

import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ExceptionsKt__ExceptionsKt {
    public static void a(Throwable th, Throwable exception) {
        Intrinsics.f(th, "<this>");
        Intrinsics.f(exception, "exception");
        if (th != exception) {
            PlatformImplementationsKt.f824a.a(th, exception);
        }
    }
}
