package kotlinx.coroutines;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher T();

    /* JADX INFO: Access modifiers changed from: protected */
    public final String U() {
        MainCoroutineDispatcher mainCoroutineDispatcher;
        MainCoroutineDispatcher c2 = Dispatchers.c();
        if (this == c2) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher = c2.T();
        } catch (UnsupportedOperationException unused) {
            mainCoroutineDispatcher = null;
        }
        if (this != mainCoroutineDispatcher) {
            return null;
        }
        return "Dispatchers.Main.immediate";
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String U = U();
        if (U == null) {
            return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this);
        }
        return U;
    }
}
