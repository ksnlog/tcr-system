package _COROUTINE;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class CoroutineDebuggingKt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1a = "_COROUTINE";

    /* JADX INFO: Access modifiers changed from: private */
    public static final StackTraceElement b(Throwable th, String str) {
        StackTraceElement stackTraceElement = th.getStackTrace()[0];
        return new StackTraceElement(f1a + '.' + str, "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
    }
}
