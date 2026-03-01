package kotlin.io;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class TextStreamsKt$readLines$1 extends Lambda implements Function1<String, Unit> {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ ArrayList<String> f860d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextStreamsKt$readLines$1(ArrayList<String> arrayList) {
        super(1);
        this.f860d = arrayList;
    }

    public final void a(String it) {
        Intrinsics.f(it, "it");
        this.f860d.add(it);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit f(String str) {
        a(str);
        return Unit.f763a;
    }
}
