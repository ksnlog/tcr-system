package com.squareup.moshi;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class JsonScope {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(int i2, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3];
            if (i4 != 1 && i4 != 2) {
                if (i4 == 3 || i4 == 4 || i4 == 5) {
                    sb.append('.');
                    String str = strArr[i3];
                    if (str != null) {
                        sb.append(str);
                    }
                }
            } else {
                sb.append('[');
                sb.append(iArr2[i3]);
                sb.append(']');
            }
        }
        return sb.toString();
    }
}
