package net.lingala.zip4j.util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class BitUtils {
    public static boolean a(byte b2, int i2) {
        return ((1 << i2) & ((long) b2)) != 0;
    }

    public static byte b(byte b2, int i2) {
        return (byte) (b2 | (1 << i2));
    }

    public static byte c(byte b2, int i2) {
        return (byte) (b2 & ((1 << i2) ^ (-1)));
    }
}
