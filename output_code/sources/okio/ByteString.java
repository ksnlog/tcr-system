package okio;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.internal._ByteStringKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {

    /* renamed from: g  reason: collision with root package name */
    public static final Companion f2454g = new Companion(null);

    /* renamed from: h  reason: collision with root package name */
    public static final ByteString f2455h = new ByteString(new byte[0]);

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f2456d;

    /* renamed from: e  reason: collision with root package name */
    private transient int f2457e;

    /* renamed from: f  reason: collision with root package name */
    private transient String f2458f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ByteString a(String str) {
            boolean z2;
            Intrinsics.f(str, "<this>");
            if (str.length() % 2 == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                int length = str.length() / 2;
                byte[] bArr = new byte[length];
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i2 * 2;
                    bArr[i2] = (byte) ((_ByteStringKt.b(str.charAt(i3)) << 4) + _ByteStringKt.b(str.charAt(i3 + 1)));
                }
                return new ByteString(bArr);
            }
            throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
        }

        public final ByteString b(String str) {
            Intrinsics.f(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.a(str));
            byteString.r(str);
            return byteString;
        }

        public final ByteString c(byte... data) {
            Intrinsics.f(data, "data");
            byte[] copyOf = Arrays.copyOf(data, data.length);
            Intrinsics.e(copyOf, "copyOf(this, size)");
            return new ByteString(copyOf);
        }
    }

    public ByteString(byte[] data) {
        Intrinsics.f(data, "data");
        this.f2456d = data;
    }

    public static final ByteString c(String str) {
        return f2454g.a(str);
    }

    public static final ByteString e(String str) {
        return f2454g.b(str);
    }

    public static final ByteString n(byte... bArr) {
        return f2454g.c(bArr);
    }

    public String a() {
        return _Base64Kt.b(g(), null, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032 A[ORIG_RETURN, RETURN] */
    @Override // java.lang.Comparable
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.f(r10, r0)
            int r0 = r9.u()
            int r1 = r10.u()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = 0
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2b
            byte r7 = r9.f(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.f(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L28
            int r4 = r4 + 1
            goto L13
        L28:
            if (r7 >= r8) goto L32
            goto L30
        L2b:
            if (r0 != r1) goto L2e
            goto L33
        L2e:
            if (r0 >= r1) goto L32
        L30:
            r3 = -1
            goto L33
        L32:
            r3 = 1
        L33:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public ByteString d(String algorithm) {
        Intrinsics.f(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(this.f2456d, 0, u());
        byte[] digestBytes = messageDigest.digest();
        Intrinsics.e(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.u() == g().length && byteString.p(0, g(), 0, g().length)) {
                return true;
            }
        }
        return false;
    }

    public final byte f(int i2) {
        return m(i2);
    }

    public final byte[] g() {
        return this.f2456d;
    }

    public final int h() {
        return this.f2457e;
    }

    public int hashCode() {
        int h2 = h();
        if (h2 == 0) {
            int hashCode = Arrays.hashCode(g());
            q(hashCode);
            return hashCode;
        }
        return h2;
    }

    public int i() {
        return g().length;
    }

    public final String j() {
        return this.f2458f;
    }

    public String k() {
        byte[] g2;
        String d2;
        char[] cArr = new char[g().length * 2];
        int i2 = 0;
        for (byte b2 : g()) {
            int i3 = i2 + 1;
            cArr[i2] = _ByteStringKt.f()[(b2 >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = _ByteStringKt.f()[b2 & 15];
        }
        d2 = StringsKt__StringsJVMKt.d(cArr);
        return d2;
    }

    public byte[] l() {
        return g();
    }

    public byte m(int i2) {
        return g()[i2];
    }

    public boolean o(int i2, ByteString other, int i3, int i4) {
        Intrinsics.f(other, "other");
        return other.p(i3, g(), i2, i4);
    }

    public boolean p(int i2, byte[] other, int i3, int i4) {
        Intrinsics.f(other, "other");
        if (i2 >= 0 && i2 <= g().length - i4 && i3 >= 0 && i3 <= other.length - i4 && _UtilKt.a(g(), i2, other, i3, i4)) {
            return true;
        }
        return false;
    }

    public final void q(int i2) {
        this.f2457e = i2;
    }

    public final void r(String str) {
        this.f2458f = str;
    }

    public final ByteString s() {
        return d("SHA-1");
    }

    public final ByteString t() {
        return d("SHA-256");
    }

    public String toString() {
        boolean z2;
        String l2;
        String l3;
        String l4;
        boolean z3;
        ByteString byteString;
        byte[] h2;
        String str;
        boolean z4 = true;
        if (g().length == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            str = "[size=0]";
        } else {
            int a2 = _ByteStringKt.a(g(), 64);
            if (a2 == -1) {
                if (g().length <= 64) {
                    str = "[hex=" + k() + ']';
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[size=");
                    sb.append(g().length);
                    sb.append(" hex=");
                    int c2 = _UtilKt.c(this, 64);
                    if (c2 <= g().length) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        if (c2 + 0 < 0) {
                            z4 = false;
                        }
                        if (z4) {
                            if (c2 == g().length) {
                                byteString = this;
                            } else {
                                h2 = ArraysKt___ArraysJvmKt.h(g(), 0, c2);
                                byteString = new ByteString(h2);
                            }
                            sb.append(byteString.k());
                            sb.append("…]");
                            return sb.toString();
                        }
                        throw new IllegalArgumentException("endIndex < beginIndex".toString());
                    }
                    throw new IllegalArgumentException(("endIndex > length(" + g().length + ')').toString());
                }
            } else {
                String y2 = y();
                String substring = y2.substring(0, a2);
                Intrinsics.e(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                l2 = StringsKt__StringsJVMKt.l(substring, "\\", "\\\\", false, 4, null);
                l3 = StringsKt__StringsJVMKt.l(l2, "\n", "\\n", false, 4, null);
                l4 = StringsKt__StringsJVMKt.l(l3, "\r", "\\r", false, 4, null);
                if (a2 < y2.length()) {
                    return "[size=" + g().length + " text=" + l4 + "…]";
                }
                return "[text=" + l4 + ']';
            }
        }
        return str;
    }

    public final int u() {
        return i();
    }

    public final boolean v(ByteString prefix) {
        Intrinsics.f(prefix, "prefix");
        return o(0, prefix, 0, prefix.u());
    }

    public ByteString w() {
        for (int i2 = 0; i2 < g().length; i2++) {
            byte b2 = g()[i2];
            if (b2 >= 65 && b2 <= 90) {
                byte[] g2 = g();
                byte[] copyOf = Arrays.copyOf(g2, g2.length);
                Intrinsics.e(copyOf, "copyOf(this, size)");
                copyOf[i2] = (byte) (b2 + 32);
                for (int i3 = i2 + 1; i3 < copyOf.length; i3++) {
                    byte b3 = copyOf[i3];
                    if (b3 >= 65 && b3 <= 90) {
                        copyOf[i3] = (byte) (b3 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return this;
    }

    public byte[] x() {
        byte[] g2 = g();
        byte[] copyOf = Arrays.copyOf(g2, g2.length);
        Intrinsics.e(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    public String y() {
        String j2 = j();
        if (j2 == null) {
            String b2 = _JvmPlatformKt.b(l());
            r(b2);
            return b2;
        }
        return j2;
    }

    public void z(Buffer buffer, int i2, int i3) {
        Intrinsics.f(buffer, "buffer");
        _ByteStringKt.d(this, buffer, i2, i3);
    }
}
