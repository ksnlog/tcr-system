package kotlin.collections;

import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    public static final <T> List<T> c(T[] tArr) {
        Intrinsics.f(tArr, "<this>");
        List<T> a2 = ArraysUtilJVM.a(tArr);
        Intrinsics.e(a2, "asList(this)");
        return a2;
    }

    public static byte[] d(byte[] bArr, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.f(bArr, "<this>");
        Intrinsics.f(destination, "destination");
        System.arraycopy(bArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static final <T> T[] e(T[] tArr, T[] destination, int i2, int i3, int i4) {
        Intrinsics.f(tArr, "<this>");
        Intrinsics.f(destination, "destination");
        System.arraycopy(tArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static /* synthetic */ byte[] f(byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5, Object obj) {
        byte[] d2;
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = bArr.length;
        }
        d2 = d(bArr, bArr2, i2, i3, i4);
        return d2;
    }

    public static /* synthetic */ Object[] g(Object[] objArr, Object[] objArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = objArr.length;
        }
        return e(objArr, objArr2, i2, i3, i4);
    }

    public static byte[] h(byte[] bArr, int i2, int i3) {
        Intrinsics.f(bArr, "<this>");
        ArraysKt__ArraysJVMKt.b(i3, bArr.length);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i2, i3);
        Intrinsics.e(copyOfRange, "copyOfRange(this, fromIndex, toIndex)");
        return copyOfRange;
    }

    public static final <T> void i(T[] tArr, T t2, int i2, int i3) {
        Intrinsics.f(tArr, "<this>");
        Arrays.fill(tArr, i2, i3, t2);
    }
}
