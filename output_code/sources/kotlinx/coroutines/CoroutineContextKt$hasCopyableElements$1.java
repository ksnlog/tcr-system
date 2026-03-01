package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class CoroutineContextKt$hasCopyableElements$1 extends Lambda implements Function2<Boolean, CoroutineContext.Element, Boolean> {

    /* renamed from: d  reason: collision with root package name */
    public static final CoroutineContextKt$hasCopyableElements$1 f941d = new CoroutineContextKt$hasCopyableElements$1();

    CoroutineContextKt$hasCopyableElements$1() {
        super(2);
    }

    public final Boolean a(boolean z2, CoroutineContext.Element element) {
        return Boolean.valueOf(z2 || (element instanceof CopyableThreadContextElement));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool, CoroutineContext.Element element) {
        return a(bool.booleanValue(), element);
    }
}
