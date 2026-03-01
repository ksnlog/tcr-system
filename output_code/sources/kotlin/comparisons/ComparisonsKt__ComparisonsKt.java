package kotlin.comparisons;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ComparisonsKt__ComparisonsKt {
    public static <T extends Comparable<?>> int a(T t2, T t3) {
        if (t2 == t3) {
            return 0;
        }
        if (t2 == null) {
            return -1;
        }
        if (t3 != null) {
            return t2.compareTo(t3);
        }
        return 1;
    }
}
