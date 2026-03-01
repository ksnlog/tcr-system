package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FastServiceLoaderKt {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f1113a;

    static {
        Object a2;
        try {
            Result.Companion companion = Result.f752d;
            a2 = Result.a(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.f752d;
            a2 = Result.a(ResultKt.a(th));
        }
        f1113a = Result.d(a2);
    }

    public static final boolean a() {
        return f1113a;
    }
}
