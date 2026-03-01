package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {

    /* renamed from: b  reason: collision with root package name */
    public static final Key f944b = Key.f945d;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Key implements CoroutineContext.Key<CoroutineExceptionHandler> {

        /* renamed from: d  reason: collision with root package name */
        static final /* synthetic */ Key f945d = new Key();

        private Key() {
        }
    }

    void G(CoroutineContext coroutineContext, Throwable th);
}
