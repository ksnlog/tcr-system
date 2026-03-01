package kotlin.text;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    public static final boolean d(char c2, char c3, boolean z2) {
        if (c2 == c3) {
            return true;
        }
        if (!z2) {
            return false;
        }
        char upperCase = Character.toUpperCase(c2);
        char upperCase2 = Character.toUpperCase(c3);
        if (upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) {
            return true;
        }
        return false;
    }
}
