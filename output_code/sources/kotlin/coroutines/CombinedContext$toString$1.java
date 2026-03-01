package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class CombinedContext$toString$1 extends Lambda implements Function2<String, CoroutineContext.Element, String> {

    /* renamed from: d  reason: collision with root package name */
    public static final CombinedContext$toString$1 f798d = new CombinedContext$toString$1();

    CombinedContext$toString$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: a */
    public final String invoke(String acc, CoroutineContext.Element element) {
        boolean z2;
        Intrinsics.f(acc, "acc");
        Intrinsics.f(element, "element");
        if (acc.length() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return element.toString();
        }
        return acc + ", " + element;
    }
}
