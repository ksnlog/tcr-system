package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class CoroutineContextKt$foldCopies$folded$1 extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Ref$ObjectRef<CoroutineContext> f939d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f940e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineContextKt$foldCopies$folded$1(Ref$ObjectRef<CoroutineContext> ref$ObjectRef, boolean z2) {
        super(2);
        this.f939d = ref$ObjectRef;
        this.f940e = z2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, kotlin.coroutines.CoroutineContext] */
    @Override // kotlin.jvm.functions.Function2
    /* renamed from: a */
    public final CoroutineContext invoke(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        if (!(element instanceof CopyableThreadContextElement)) {
            return coroutineContext.n(element);
        }
        CoroutineContext.Element b2 = this.f939d.f884d.b(element.getKey());
        if (b2 == null) {
            CopyableThreadContextElement copyableThreadContextElement = (CopyableThreadContextElement) element;
            if (this.f940e) {
                copyableThreadContextElement = copyableThreadContextElement.o();
            }
            return coroutineContext.n(copyableThreadContextElement);
        }
        Ref$ObjectRef<CoroutineContext> ref$ObjectRef = this.f939d;
        ref$ObjectRef.f884d = ref$ObjectRef.f884d.I(element.getKey());
        return coroutineContext.n(((CopyableThreadContextElement) element).m(b2));
    }
}
