package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class GlobalScope implements CoroutineScope {

    /* renamed from: d  reason: collision with root package name */
    public static final GlobalScope f979d = new GlobalScope();

    private GlobalScope() {
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext f() {
        return EmptyCoroutineContext.f802d;
    }
}
