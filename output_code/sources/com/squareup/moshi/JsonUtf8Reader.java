package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.io.EOFException;
import java.math.BigDecimal;
import javax.annotation.Nullable;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JsonUtf8Reader extends JsonReader {

    /* renamed from: p  reason: collision with root package name */
    private static final ByteString f530p = ByteString.e("'\\");

    /* renamed from: q  reason: collision with root package name */
    private static final ByteString f531q = ByteString.e("\"\\");

    /* renamed from: r  reason: collision with root package name */
    private static final ByteString f532r = ByteString.e("{}[]:, \n\t\r\f/\\;#=");

    /* renamed from: s  reason: collision with root package name */
    private static final ByteString f533s = ByteString.e("\n\r");

    /* renamed from: t  reason: collision with root package name */
    private static final ByteString f534t = ByteString.e("*/");

    /* renamed from: j  reason: collision with root package name */
    private final BufferedSource f535j;

    /* renamed from: k  reason: collision with root package name */
    private final Buffer f536k;

    /* renamed from: l  reason: collision with root package name */
    private int f537l = 0;

    /* renamed from: m  reason: collision with root package name */
    private long f538m;

    /* renamed from: n  reason: collision with root package name */
    private int f539n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    private String f540o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonUtf8Reader(BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            this.f535j = bufferedSource;
            this.f536k = bufferedSource.t();
            J(6);
            return;
        }
        throw new NullPointerException("source == null");
    }

    private void X() {
        if (this.f515h) {
            return;
        }
        throw V("Use JsonReader.setLenient(true) to accept malformed JSON");
    }

    private int Y() {
        int[] iArr = this.f512e;
        int i2 = this.f511d;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int d02 = d0(true);
            this.f536k.readByte();
            if (d02 != 44) {
                if (d02 != 59) {
                    if (d02 == 93) {
                        this.f537l = 4;
                        return 4;
                    }
                    throw V("Unterminated array");
                }
                X();
            }
        } else if (i3 != 3 && i3 != 5) {
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int d03 = d0(true);
                this.f536k.readByte();
                if (d03 != 58) {
                    if (d03 == 61) {
                        X();
                        if (this.f535j.l(1L) && this.f536k.o(0L) == 62) {
                            this.f536k.readByte();
                        }
                    } else {
                        throw V("Expected ':'");
                    }
                }
            } else if (i3 == 6) {
                iArr[i2 - 1] = 7;
            } else if (i3 == 7) {
                if (d0(false) == -1) {
                    this.f537l = 18;
                    return 18;
                }
                X();
            } else if (i3 != 9) {
                if (i3 == 8) {
                    throw new IllegalStateException("JsonReader is closed");
                }
            } else {
                throw null;
            }
        } else {
            iArr[i2 - 1] = 4;
            if (i3 == 5) {
                int d04 = d0(true);
                this.f536k.readByte();
                if (d04 != 44) {
                    if (d04 != 59) {
                        if (d04 == 125) {
                            this.f537l = 2;
                            return 2;
                        }
                        throw V("Unterminated object");
                    }
                    X();
                }
            }
            int d05 = d0(true);
            if (d05 != 34) {
                if (d05 != 39) {
                    if (d05 != 125) {
                        X();
                        if (b0((char) d05)) {
                            this.f537l = 14;
                            return 14;
                        }
                        throw V("Expected name");
                    } else if (i3 != 5) {
                        this.f536k.readByte();
                        this.f537l = 2;
                        return 2;
                    } else {
                        throw V("Expected name");
                    }
                }
                this.f536k.readByte();
                X();
                this.f537l = 12;
                return 12;
            }
            this.f536k.readByte();
            this.f537l = 13;
            return 13;
        }
        int d06 = d0(true);
        if (d06 != 34) {
            if (d06 != 39) {
                if (d06 != 44 && d06 != 59) {
                    if (d06 != 91) {
                        if (d06 != 93) {
                            if (d06 != 123) {
                                int g02 = g0();
                                if (g02 != 0) {
                                    return g02;
                                }
                                int h02 = h0();
                                if (h02 != 0) {
                                    return h02;
                                }
                                if (b0(this.f536k.o(0L))) {
                                    X();
                                    this.f537l = 10;
                                    return 10;
                                }
                                throw V("Expected value");
                            }
                            this.f536k.readByte();
                            this.f537l = 1;
                            return 1;
                        } else if (i3 == 1) {
                            this.f536k.readByte();
                            this.f537l = 4;
                            return 4;
                        }
                    } else {
                        this.f536k.readByte();
                        this.f537l = 3;
                        return 3;
                    }
                }
                if (i3 != 1 && i3 != 2) {
                    throw V("Unexpected value");
                }
                X();
                this.f537l = 7;
                return 7;
            }
            X();
            this.f536k.readByte();
            this.f537l = 8;
            return 8;
        }
        this.f536k.readByte();
        this.f537l = 9;
        return 9;
    }

    private int Z(String str, JsonReader.Options options) {
        int length = options.f517a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.f517a[i2])) {
                this.f537l = 0;
                this.f513f[this.f511d - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private int a0(String str, JsonReader.Options options) {
        int length = options.f517a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.f517a[i2])) {
                this.f537l = 0;
                int[] iArr = this.f514g;
                int i3 = this.f511d - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
        }
        return -1;
    }

    private boolean b0(int i2) {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        X();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r6.f536k.skip(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1 != 47) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        if (r6.f535j.l(2) != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        X();
        r3 = r6.f536k.o(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r3 == 42) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
        if (r3 == 47) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
        r6.f536k.readByte();
        r6.f536k.readByte();
        l0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
        r6.f536k.readByte();
        r6.f536k.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
        if (k0() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
        throw V("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
        if (r1 != 35) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
        X();
        l0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int d0(boolean r7) {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = 0
        L2:
            okio.BufferedSource r2 = r6.f535j
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.l(r4)
            if (r2 == 0) goto L82
            okio.Buffer r2 = r6.f536k
            long r4 = (long) r1
            byte r1 = r2.o(r4)
            r2 = 10
            if (r1 == r2) goto L80
            r2 = 32
            if (r1 == r2) goto L80
            r2 = 13
            if (r1 == r2) goto L80
            r2 = 9
            if (r1 != r2) goto L25
            goto L80
        L25:
            okio.Buffer r2 = r6.f536k
            int r3 = r3 + (-1)
            long r3 = (long) r3
            r2.skip(r3)
            r2 = 47
            if (r1 != r2) goto L74
            okio.BufferedSource r3 = r6.f535j
            r4 = 2
            boolean r3 = r3.l(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.X()
            okio.Buffer r3 = r6.f536k
            r4 = 1
            byte r3 = r3.o(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            okio.Buffer r1 = r6.f536k
            r1.readByte()
            okio.Buffer r1 = r6.f536k
            r1.readByte()
            r6.l0()
            goto L1
        L5c:
            okio.Buffer r1 = r6.f536k
            r1.readByte()
            okio.Buffer r1 = r6.f536k
            r1.readByte()
            boolean r1 = r6.k0()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.squareup.moshi.JsonEncodingException r7 = r6.V(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7f
            r6.X()
            r6.l0()
            goto L1
        L7f:
            return r1
        L80:
            r1 = r3
            goto L2
        L82:
            if (r7 != 0) goto L86
            r7 = -1
            return r7
        L86:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            goto L8f
        L8e:
            throw r7
        L8f:
            goto L8e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonUtf8Reader.d0(boolean):int");
    }

    private String e0(ByteString byteString) {
        StringBuilder sb = null;
        while (true) {
            long y2 = this.f535j.y(byteString);
            if (y2 != -1) {
                if (this.f536k.o(y2) == 92) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.f536k.g(y2));
                    this.f536k.readByte();
                    sb.append(i0());
                } else if (sb == null) {
                    String g2 = this.f536k.g(y2);
                    this.f536k.readByte();
                    return g2;
                } else {
                    sb.append(this.f536k.g(y2));
                    this.f536k.readByte();
                    return sb.toString();
                }
            } else {
                throw V("Unterminated string");
            }
        }
    }

    private String f0() {
        long y2 = this.f535j.y(f532r);
        if (y2 != -1) {
            return this.f536k.g(y2);
        }
        return this.f536k.I();
    }

    private int g0() {
        String str;
        String str2;
        int i2;
        byte o2 = this.f536k.o(0L);
        if (o2 != 116 && o2 != 84) {
            if (o2 != 102 && o2 != 70) {
                if (o2 != 110 && o2 != 78) {
                    return 0;
                }
                str = "null";
                str2 = "NULL";
                i2 = 7;
            } else {
                str = "false";
                str2 = "FALSE";
                i2 = 6;
            }
        } else {
            str = "true";
            str2 = "TRUE";
            i2 = 5;
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.f535j.l(i4)) {
                return 0;
            }
            byte o3 = this.f536k.o(i3);
            if (o3 != str.charAt(i3) && o3 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.f535j.l(length + 1) && b0(this.f536k.o(length))) {
            return 0;
        }
        this.f536k.skip(length);
        this.f537l = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0083, code lost:
        if (b0(r11) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0085, code lost:
        if (r6 != 2) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0087, code lost:
        if (r7 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008d, code lost:
        if (r8 != Long.MIN_VALUE) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r10 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r8 != 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        if (r10 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0097, code lost:
        if (r10 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009b, code lost:
        r16.f538m = r8;
        r16.f536k.skip(r5);
        r16.f537l = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a7, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a8, code lost:
        if (r6 == 2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00ab, code lost:
        if (r6 == 4) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ae, code lost:
        if (r6 != 7) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b1, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b2, code lost:
        r16.f539n = r5;
        r16.f537l = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b8, code lost:
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b9, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int h0() {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonUtf8Reader.h0():int");
    }

    private char i0() {
        int i2;
        int i3;
        if (this.f535j.l(1L)) {
            byte readByte = this.f536k.readByte();
            if (readByte != 10 && readByte != 34 && readByte != 39 && readByte != 47 && readByte != 92) {
                if (readByte != 98) {
                    if (readByte != 102) {
                        if (readByte == 110) {
                            return '\n';
                        }
                        if (readByte != 114) {
                            if (readByte != 116) {
                                if (readByte != 117) {
                                    if (this.f515h) {
                                        return (char) readByte;
                                    }
                                    throw V("Invalid escape sequence: \\" + ((char) readByte));
                                } else if (this.f535j.l(4L)) {
                                    char c2 = 0;
                                    for (int i4 = 0; i4 < 4; i4++) {
                                        byte o2 = this.f536k.o(i4);
                                        char c3 = (char) (c2 << 4);
                                        if (o2 >= 48 && o2 <= 57) {
                                            i3 = o2 - 48;
                                        } else {
                                            if (o2 >= 97 && o2 <= 102) {
                                                i2 = o2 - 97;
                                            } else if (o2 < 65 || o2 > 70) {
                                                throw V("\\u" + this.f536k.g(4L));
                                            } else {
                                                i2 = o2 - 65;
                                            }
                                            i3 = i2 + 10;
                                        }
                                        c2 = (char) (c3 + i3);
                                    }
                                    this.f536k.skip(4L);
                                    return c2;
                                } else {
                                    throw new EOFException("Unterminated escape sequence at path " + n());
                                }
                            }
                            return '\t';
                        }
                        return '\r';
                    }
                    return '\f';
                }
                return '\b';
            }
            return (char) readByte;
        }
        throw V("Unterminated escape sequence");
    }

    private void j0(ByteString byteString) {
        while (true) {
            long y2 = this.f535j.y(byteString);
            if (y2 != -1) {
                if (this.f536k.o(y2) == 92) {
                    this.f536k.skip(y2 + 1);
                    i0();
                } else {
                    this.f536k.skip(y2 + 1);
                    return;
                }
            } else {
                throw V("Unterminated string");
            }
        }
    }

    private boolean k0() {
        boolean z2;
        long size;
        BufferedSource bufferedSource = this.f535j;
        ByteString byteString = f534t;
        long s2 = bufferedSource.s(byteString);
        if (s2 != -1) {
            z2 = true;
        } else {
            z2 = false;
        }
        Buffer buffer = this.f536k;
        if (z2) {
            size = s2 + byteString.u();
        } else {
            size = buffer.size();
        }
        buffer.skip(size);
        return z2;
    }

    private void l0() {
        long size;
        long y2 = this.f535j.y(f533s);
        Buffer buffer = this.f536k;
        if (y2 != -1) {
            size = y2 + 1;
        } else {
            size = buffer.size();
        }
        buffer.skip(size);
    }

    private void m0() {
        long y2 = this.f535j.y(f532r);
        Buffer buffer = this.f536k;
        if (y2 == -1) {
            y2 = buffer.size();
        }
        buffer.skip(y2);
    }

    @Override // com.squareup.moshi.JsonReader
    @Nullable
    public <T> T B() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 7) {
            this.f537l = 0;
            int[] iArr = this.f514g;
            int i3 = this.f511d - 1;
            iArr[i3] = iArr[i3] + 1;
            return null;
        }
        throw new JsonDataException("Expected null but was " + G() + " at path " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public String D() {
        String g2;
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 10) {
            g2 = f0();
        } else if (i2 == 9) {
            g2 = e0(f531q);
        } else if (i2 == 8) {
            g2 = e0(f530p);
        } else if (i2 == 11) {
            g2 = this.f540o;
            this.f540o = null;
        } else if (i2 == 16) {
            g2 = Long.toString(this.f538m);
        } else if (i2 == 17) {
            g2 = this.f536k.g(this.f539n);
        } else {
            throw new JsonDataException("Expected a string but was " + G() + " at path " + n());
        }
        this.f537l = 0;
        int[] iArr = this.f514g;
        int i3 = this.f511d - 1;
        iArr[i3] = iArr[i3] + 1;
        return g2;
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token G() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        switch (i2) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case R$styleable.f1342r /* 6 */:
                return JsonReader.Token.BOOLEAN;
            case R$styleable.f1343s /* 7 */:
                return JsonReader.Token.NULL;
            case R$styleable.f1328d /* 8 */:
            case R$styleable.f1329e /* 9 */:
            case R$styleable.f1330f /* 10 */:
            case R$styleable.f1331g /* 11 */:
                return JsonReader.Token.STRING;
            case R$styleable.f1332h /* 12 */:
            case R$styleable.f1333i /* 13 */:
            case R$styleable.f1334j /* 14 */:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void I() {
        if (o()) {
            this.f540o = c0();
            this.f537l = 11;
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int O(JsonReader.Options options) {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 < 12 || i2 > 15) {
            return -1;
        }
        if (i2 == 15) {
            return Z(this.f540o, options);
        }
        int P = this.f535j.P(options.f518b);
        if (P != -1) {
            this.f537l = 0;
            this.f513f[this.f511d - 1] = options.f517a[P];
            return P;
        }
        String str = this.f513f[this.f511d - 1];
        String c02 = c0();
        int Z = Z(c02, options);
        if (Z == -1) {
            this.f537l = 15;
            this.f540o = c02;
            this.f513f[this.f511d - 1] = str;
        }
        return Z;
    }

    @Override // com.squareup.moshi.JsonReader
    public int Q(JsonReader.Options options) {
        int[] iArr;
        int i2;
        int i3 = this.f537l;
        if (i3 == 0) {
            i3 = Y();
        }
        if (i3 < 8 || i3 > 11) {
            return -1;
        }
        if (i3 == 11) {
            return a0(this.f540o, options);
        }
        int P = this.f535j.P(options.f518b);
        if (P != -1) {
            this.f537l = 0;
            int[] iArr2 = this.f514g;
            int i4 = this.f511d - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return P;
        }
        String D = D();
        int a02 = a0(D, options);
        if (a02 == -1) {
            this.f537l = 11;
            this.f540o = D;
            this.f514g[this.f511d - 1] = iArr[i2] - 1;
        }
        return a02;
    }

    @Override // com.squareup.moshi.JsonReader
    public void T() {
        if (!this.f516i) {
            int i2 = this.f537l;
            if (i2 == 0) {
                i2 = Y();
            }
            if (i2 == 14) {
                m0();
            } else if (i2 == 13) {
                j0(f531q);
            } else if (i2 == 12) {
                j0(f530p);
            } else if (i2 != 15) {
                throw new JsonDataException("Expected a name but was " + G() + " at path " + n());
            }
            this.f537l = 0;
            this.f513f[this.f511d - 1] = "null";
            return;
        }
        JsonReader.Token G = G();
        c0();
        throw new JsonDataException("Cannot skip unexpected " + G + " at " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public void U() {
        if (!this.f516i) {
            int i2 = 0;
            do {
                int i3 = this.f537l;
                if (i3 == 0) {
                    i3 = Y();
                }
                if (i3 == 3) {
                    J(1);
                } else if (i3 == 1) {
                    J(3);
                } else {
                    if (i3 == 4) {
                        i2--;
                        if (i2 >= 0) {
                            this.f511d--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + G() + " at path " + n());
                        }
                    } else if (i3 == 2) {
                        i2--;
                        if (i2 >= 0) {
                            this.f511d--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + G() + " at path " + n());
                        }
                    } else if (i3 != 14 && i3 != 10) {
                        if (i3 != 9 && i3 != 13) {
                            if (i3 != 8 && i3 != 12) {
                                if (i3 == 17) {
                                    this.f536k.skip(this.f539n);
                                } else if (i3 == 18) {
                                    throw new JsonDataException("Expected a value but was " + G() + " at path " + n());
                                }
                            } else {
                                j0(f530p);
                            }
                        } else {
                            j0(f531q);
                        }
                    } else {
                        m0();
                    }
                    this.f537l = 0;
                }
                i2++;
                this.f537l = 0;
            } while (i2 != 0);
            int[] iArr = this.f514g;
            int i4 = this.f511d;
            int i5 = i4 - 1;
            iArr[i5] = iArr[i5] + 1;
            this.f513f[i4 - 1] = "null";
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + G() + " at " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public void b() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 3) {
            J(1);
            this.f514g[this.f511d - 1] = 0;
            this.f537l = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + G() + " at path " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public void c() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 1) {
            J(3);
            this.f537l = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + G() + " at path " + n());
    }

    public String c0() {
        String str;
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 14) {
            str = f0();
        } else if (i2 == 13) {
            str = e0(f531q);
        } else if (i2 == 12) {
            str = e0(f530p);
        } else if (i2 == 15) {
            str = this.f540o;
            this.f540o = null;
        } else {
            throw new JsonDataException("Expected a name but was " + G() + " at path " + n());
        }
        this.f537l = 0;
        this.f513f[this.f511d - 1] = str;
        return str;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f537l = 0;
        this.f512e[0] = 8;
        this.f511d = 1;
        this.f536k.b();
        this.f535j.close();
    }

    @Override // com.squareup.moshi.JsonReader
    public void e() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 4) {
            int i3 = this.f511d - 1;
            this.f511d = i3;
            int[] iArr = this.f514g;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.f537l = 0;
            return;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + G() + " at path " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public void k() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 2) {
            int i3 = this.f511d - 1;
            this.f511d = i3;
            this.f513f[i3] = null;
            int[] iArr = this.f514g;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.f537l = 0;
            return;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + G() + " at path " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean o() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 != 2 && i2 != 4 && i2 != 18) {
            return true;
        }
        return false;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean r() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 5) {
            this.f537l = 0;
            int[] iArr = this.f514g;
            int i3 = this.f511d - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.f537l = 0;
            int[] iArr2 = this.f514g;
            int i4 = this.f511d - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + G() + " at path " + n());
        }
    }

    public String toString() {
        return "JsonReader(" + this.f535j + ")";
    }

    @Override // com.squareup.moshi.JsonReader
    public double w() {
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 16) {
            this.f537l = 0;
            int[] iArr = this.f514g;
            int i3 = this.f511d - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.f538m;
        }
        if (i2 == 17) {
            this.f540o = this.f536k.g(this.f539n);
        } else if (i2 == 9) {
            this.f540o = e0(f531q);
        } else if (i2 == 8) {
            this.f540o = e0(f530p);
        } else if (i2 == 10) {
            this.f540o = f0();
        } else if (i2 != 11) {
            throw new JsonDataException("Expected a double but was " + G() + " at path " + n());
        }
        this.f537l = 11;
        try {
            double parseDouble = Double.parseDouble(this.f540o);
            if (!this.f515h && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + n());
            }
            this.f540o = null;
            this.f537l = 0;
            int[] iArr2 = this.f514g;
            int i4 = this.f511d - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.f540o + " at path " + n());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int x() {
        String e02;
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 16) {
            long j2 = this.f538m;
            int i3 = (int) j2;
            if (j2 == i3) {
                this.f537l = 0;
                int[] iArr = this.f514g;
                int i4 = this.f511d - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new JsonDataException("Expected an int but was " + this.f538m + " at path " + n());
        }
        if (i2 == 17) {
            this.f540o = this.f536k.g(this.f539n);
        } else if (i2 != 9 && i2 != 8) {
            if (i2 != 11) {
                throw new JsonDataException("Expected an int but was " + G() + " at path " + n());
            }
        } else {
            if (i2 == 9) {
                e02 = e0(f531q);
            } else {
                e02 = e0(f530p);
            }
            this.f540o = e02;
            try {
                int parseInt = Integer.parseInt(e02);
                this.f537l = 0;
                int[] iArr2 = this.f514g;
                int i5 = this.f511d - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.f537l = 11;
        try {
            double parseDouble = Double.parseDouble(this.f540o);
            int i6 = (int) parseDouble;
            if (i6 == parseDouble) {
                this.f540o = null;
                this.f537l = 0;
                int[] iArr3 = this.f514g;
                int i7 = this.f511d - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.f540o + " at path " + n());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.f540o + " at path " + n());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public long z() {
        String e02;
        int i2 = this.f537l;
        if (i2 == 0) {
            i2 = Y();
        }
        if (i2 == 16) {
            this.f537l = 0;
            int[] iArr = this.f514g;
            int i3 = this.f511d - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.f538m;
        }
        if (i2 == 17) {
            this.f540o = this.f536k.g(this.f539n);
        } else if (i2 != 9 && i2 != 8) {
            if (i2 != 11) {
                throw new JsonDataException("Expected a long but was " + G() + " at path " + n());
            }
        } else {
            if (i2 == 9) {
                e02 = e0(f531q);
            } else {
                e02 = e0(f530p);
            }
            this.f540o = e02;
            try {
                long parseLong = Long.parseLong(e02);
                this.f537l = 0;
                int[] iArr2 = this.f514g;
                int i4 = this.f511d - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.f537l = 11;
        try {
            long longValueExact = new BigDecimal(this.f540o).longValueExact();
            this.f540o = null;
            this.f537l = 0;
            int[] iArr3 = this.f514g;
            int i5 = this.f511d - 1;
            iArr3[i5] = iArr3[i5] + 1;
            return longValueExact;
        } catch (ArithmeticException | NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + this.f540o + " at path " + n());
        }
    }
}
