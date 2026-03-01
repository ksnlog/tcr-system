package kotlinx.coroutines.internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final /* synthetic */ class SystemPropsKt__SystemPropsKt {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1155a = Runtime.getRuntime().availableProcessors();

    public static final int a() {
        return f1155a;
    }

    public static final String b(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
