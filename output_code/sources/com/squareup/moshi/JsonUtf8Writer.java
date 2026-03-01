package com.squareup.moshi;

import java.io.IOException;
import javax.annotation.Nullable;
import okio.BufferedSink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class JsonUtf8Writer extends JsonWriter {

    /* renamed from: p  reason: collision with root package name */
    private static final String[] f541p = new String[128];

    /* renamed from: m  reason: collision with root package name */
    private final BufferedSink f542m;

    /* renamed from: n  reason: collision with root package name */
    private String f543n = ":";

    /* renamed from: o  reason: collision with root package name */
    private String f544o;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f541p[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f541p;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonUtf8Writer(BufferedSink bufferedSink) {
        if (bufferedSink != null) {
            this.f542m = bufferedSink;
            E(6);
            return;
        }
        throw new NullPointerException("sink == null");
    }

    private void V() {
        int B = B();
        if (B == 5) {
            this.f542m.writeByte(44);
        } else if (B != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        Y();
        G(4);
    }

    private void W() {
        int B = B();
        int i2 = 2;
        if (B != 1) {
            if (B != 2) {
                if (B != 4) {
                    if (B != 9) {
                        i2 = 7;
                        if (B != 6) {
                            if (B == 7) {
                                if (!this.f557i) {
                                    throw new IllegalStateException("JSON must have only one top-level value.");
                                }
                            } else {
                                throw new IllegalStateException("Nesting problem.");
                            }
                        }
                    } else {
                        throw new IllegalStateException("Sink from valueSink() was not closed");
                    }
                } else {
                    this.f542m.H(this.f543n);
                    i2 = 5;
                }
                G(i2);
            }
            this.f542m.writeByte(44);
        }
        Y();
        G(i2);
    }

    private JsonWriter X(int i2, int i3, char c2) {
        int B = B();
        if (B != i3 && B != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f544o == null) {
            int i4 = this.f552d;
            int i5 = this.f560l;
            if (i4 == (i5 ^ (-1))) {
                this.f560l = i5 ^ (-1);
                return this;
            }
            int i6 = i4 - 1;
            this.f552d = i6;
            this.f554f[i6] = null;
            int[] iArr = this.f555g;
            int i7 = i6 - 1;
            iArr[i7] = iArr[i7] + 1;
            if (B == i3) {
                Y();
            }
            this.f542m.writeByte(c2);
            return this;
        }
        throw new IllegalStateException("Dangling name: " + this.f544o);
    }

    private void Y() {
        if (this.f556h == null) {
            return;
        }
        this.f542m.writeByte(10);
        int i2 = this.f552d;
        for (int i3 = 1; i3 < i2; i3++) {
            this.f542m.H(this.f556h);
        }
    }

    private JsonWriter Z(int i2, int i3, char c2) {
        int i4 = this.f552d;
        int i5 = this.f560l;
        if (i4 == i5) {
            int[] iArr = this.f553e;
            if (iArr[i4 - 1] == i2 || iArr[i4 - 1] == i3) {
                this.f560l = i5 ^ (-1);
                return this;
            }
        }
        W();
        e();
        E(i2);
        this.f555g[this.f552d - 1] = 0;
        this.f542m.writeByte(c2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a0(okio.BufferedSink r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = com.squareup.moshi.JsonUtf8Writer.f541p
            r1 = 34
            r7.writeByte(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = 0
        Ld:
            if (r3 >= r2) goto L36
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r6) goto L1c
            r5 = r0[r5]
            if (r5 != 0) goto L29
            goto L33
        L1c:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L23
            java.lang.String r5 = "\\u2028"
            goto L29
        L23:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L33
            java.lang.String r5 = "\\u2029"
        L29:
            if (r4 >= r3) goto L2e
            r7.h(r8, r4, r3)
        L2e:
            r7.H(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.h(r8, r4, r2)
        L3b:
            r7.writeByte(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonUtf8Writer.a0(okio.BufferedSink, java.lang.String):void");
    }

    private void b0() {
        if (this.f544o != null) {
            V();
            a0(this.f542m, this.f544o);
            this.f544o = null;
        }
    }

    @Override // com.squareup.moshi.JsonWriter
    public void I(String str) {
        String str2;
        super.I(str);
        if (!str.isEmpty()) {
            str2 = ": ";
        } else {
            str2 = ":";
        }
        this.f543n = str2;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter Q(double d2) {
        if (!this.f557i && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        } else if (this.f559k) {
            this.f559k = false;
            return w(Double.toString(d2));
        } else {
            b0();
            W();
            this.f542m.H(Double.toString(d2));
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter R(long j2) {
        if (this.f559k) {
            this.f559k = false;
            return w(Long.toString(j2));
        }
        b0();
        W();
        this.f542m.H(Long.toString(j2));
        int[] iArr = this.f555g;
        int i2 = this.f552d - 1;
        iArr[i2] = iArr[i2] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter S(@Nullable Number number) {
        if (number == null) {
            return x();
        }
        String obj = number.toString();
        if (!this.f557i && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        } else if (this.f559k) {
            this.f559k = false;
            return w(obj);
        } else {
            b0();
            W();
            this.f542m.H(obj);
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter T(String str) {
        if (str == null) {
            return x();
        }
        if (this.f559k) {
            this.f559k = false;
            return w(str);
        }
        b0();
        W();
        a0(this.f542m, str);
        int[] iArr = this.f555g;
        int i2 = this.f552d - 1;
        iArr[i2] = iArr[i2] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter U(boolean z2) {
        String str;
        if (!this.f559k) {
            b0();
            W();
            BufferedSink bufferedSink = this.f542m;
            if (z2) {
                str = "true";
            } else {
                str = "false";
            }
            bufferedSink.H(str);
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + o());
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter b() {
        if (!this.f559k) {
            b0();
            return Z(1, 2, '[');
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + o());
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter c() {
        if (!this.f559k) {
            b0();
            return Z(3, 5, '{');
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + o());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f542m.close();
        int i2 = this.f552d;
        if (i2 <= 1 && (i2 != 1 || this.f553e[i2 - 1] == 7)) {
            this.f552d = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    @Override // java.io.Flushable
    public void flush() {
        if (this.f552d != 0) {
            this.f542m.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter k() {
        return X(1, 2, ']');
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter m() {
        this.f559k = false;
        return X(3, 5, '}');
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter w(String str) {
        if (str != null) {
            if (this.f552d != 0) {
                int B = B();
                if ((B == 3 || B == 5) && this.f544o == null && !this.f559k) {
                    this.f544o = str;
                    this.f554f[this.f552d - 1] = str;
                    return this;
                }
                throw new IllegalStateException("Nesting problem.");
            }
            throw new IllegalStateException("JsonWriter is closed.");
        }
        throw new NullPointerException("name == null");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter x() {
        if (!this.f559k) {
            if (this.f544o != null) {
                if (this.f558j) {
                    b0();
                } else {
                    this.f544o = null;
                    return this;
                }
            }
            W();
            this.f542m.H("null");
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + o());
    }
}
