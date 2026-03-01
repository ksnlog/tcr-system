package org.threeten.bp.format;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum SignStyle {
    NORMAL,
    ALWAYS,
    NEVER,
    NOT_NEGATIVE,
    EXCEEDS_PAD;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(boolean z2, boolean z3, boolean z4) {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return !z2 || !z3;
        } else if (ordinal == 1 || ordinal == 4) {
            return true;
        } else {
            return !z3 && !z4;
        }
    }
}
