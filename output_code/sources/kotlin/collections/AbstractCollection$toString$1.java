package kotlin.collections;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class AbstractCollection$toString$1 extends Lambda implements Function1<E, CharSequence> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ AbstractCollection<E> f766d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractCollection$toString$1(AbstractCollection<? extends E> abstractCollection) {
        super(1);
        this.f766d = abstractCollection;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: a */
    public final CharSequence f(E e2) {
        return e2 == this.f766d ? "(this Collection)" : String.valueOf(e2);
    }
}
