package org.threeten.bp.jdk8;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Jdk8Methods {
    public static int a(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 > i3 ? 1 : 0;
    }

    public static int b(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 > j3 ? 1 : 0;
    }

    public static boolean c(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj2 != null) {
            return obj.equals(obj2);
        } else {
            return false;
        }
    }

    public static int d(int i2, int i3) {
        return i2 >= 0 ? i2 / i3 : ((i2 + 1) / i3) - 1;
    }

    public static long e(long j2, long j3) {
        return j2 >= 0 ? j2 / j3 : ((j2 + 1) / j3) - 1;
    }

    public static int f(int i2, int i3) {
        return ((i2 % i3) + i3) % i3;
    }

    public static int g(long j2, int i2) {
        long j3 = i2;
        return (int) (((j2 % j3) + j3) % j3);
    }

    public static long h(long j2, long j3) {
        return ((j2 % j3) + j3) % j3;
    }

    public static <T> T i(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str + " must not be null");
    }

    public static int j(int i2, int i3) {
        int i4 = i2 + i3;
        if ((i2 ^ i4) >= 0 || (i2 ^ i3) < 0) {
            return i4;
        }
        throw new ArithmeticException("Addition overflows an int: " + i2 + " + " + i3);
    }

    public static long k(long j2, long j3) {
        long j4 = j2 + j3;
        if ((j2 ^ j4) >= 0 || (j2 ^ j3) < 0) {
            return j4;
        }
        throw new ArithmeticException("Addition overflows a long: " + j2 + " + " + j3);
    }

    public static long l(long j2, int i2) {
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1) {
                    long j3 = i2;
                    long j4 = j2 * j3;
                    if (j4 / j3 == j2) {
                        return j4;
                    }
                    throw new ArithmeticException("Multiplication overflows a long: " + j2 + " * " + i2);
                }
                return j2;
            }
            return 0L;
        } else if (j2 != Long.MIN_VALUE) {
            return -j2;
        } else {
            throw new ArithmeticException("Multiplication overflows a long: " + j2 + " * " + i2);
        }
    }

    public static long m(long j2, long j3) {
        if (j3 == 1) {
            return j2;
        }
        if (j2 == 1) {
            return j3;
        }
        if (j2 == 0 || j3 == 0) {
            return 0L;
        }
        long j4 = j2 * j3;
        if (j4 / j3 == j2 && ((j2 != Long.MIN_VALUE || j3 != -1) && (j3 != Long.MIN_VALUE || j2 != -1))) {
            return j4;
        }
        throw new ArithmeticException("Multiplication overflows a long: " + j2 + " * " + j3);
    }

    public static int n(int i2, int i3) {
        int i4 = i2 - i3;
        if ((i2 ^ i4) >= 0 || (i2 ^ i3) >= 0) {
            return i4;
        }
        throw new ArithmeticException("Subtraction overflows an int: " + i2 + " - " + i3);
    }

    public static long o(long j2, long j3) {
        long j4 = j2 - j3;
        if ((j2 ^ j4) >= 0 || (j2 ^ j3) >= 0) {
            return j4;
        }
        throw new ArithmeticException("Subtraction overflows a long: " + j2 + " - " + j3);
    }

    public static int p(long j2) {
        if (j2 > 2147483647L || j2 < -2147483648L) {
            throw new ArithmeticException("Calculation overflows an int: " + j2);
        }
        return (int) j2;
    }
}
