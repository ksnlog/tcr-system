package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Hpack {

    /* renamed from: a  reason: collision with root package name */
    static final Header[] f2241a;

    /* renamed from: b  reason: collision with root package name */
    static final Map<ByteString, Integer> f2242b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class Reader {

        /* renamed from: a  reason: collision with root package name */
        private final List<Header> f2243a;

        /* renamed from: b  reason: collision with root package name */
        private final BufferedSource f2244b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2245c;

        /* renamed from: d  reason: collision with root package name */
        private int f2246d;

        /* renamed from: e  reason: collision with root package name */
        Header[] f2247e;

        /* renamed from: f  reason: collision with root package name */
        int f2248f;

        /* renamed from: g  reason: collision with root package name */
        int f2249g;

        /* renamed from: h  reason: collision with root package name */
        int f2250h;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Reader(int i2, Source source) {
            this(i2, i2, source);
        }

        private void a() {
            int i2 = this.f2246d;
            int i3 = this.f2250h;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    d(i3 - i2);
                }
            }
        }

        private void b() {
            Arrays.fill(this.f2247e, (Object) null);
            this.f2248f = this.f2247e.length - 1;
            this.f2249g = 0;
            this.f2250h = 0;
        }

        private int c(int i2) {
            return this.f2248f + 1 + i2;
        }

        private int d(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f2247e.length;
                while (true) {
                    length--;
                    i3 = this.f2248f;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    int i5 = this.f2247e[length].f2240c;
                    i2 -= i5;
                    this.f2250h -= i5;
                    this.f2249g--;
                    i4++;
                }
                Header[] headerArr = this.f2247e;
                System.arraycopy(headerArr, i3 + 1, headerArr, i3 + 1 + i4, this.f2249g);
                this.f2248f += i4;
            }
            return i4;
        }

        private ByteString f(int i2) {
            if (h(i2)) {
                return Hpack.f2241a[i2].f2238a;
            }
            int c2 = c(i2 - Hpack.f2241a.length);
            if (c2 >= 0) {
                Header[] headerArr = this.f2247e;
                if (c2 < headerArr.length) {
                    return headerArr[c2].f2238a;
                }
            }
            throw new IOException("Header index too large " + (i2 + 1));
        }

        private void g(int i2, Header header) {
            this.f2243a.add(header);
            int i3 = header.f2240c;
            if (i2 != -1) {
                i3 -= this.f2247e[c(i2)].f2240c;
            }
            int i4 = this.f2246d;
            if (i3 > i4) {
                b();
                return;
            }
            int d2 = d((this.f2250h + i3) - i4);
            if (i2 == -1) {
                int i5 = this.f2249g + 1;
                Header[] headerArr = this.f2247e;
                if (i5 > headerArr.length) {
                    Header[] headerArr2 = new Header[headerArr.length * 2];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    this.f2248f = this.f2247e.length - 1;
                    this.f2247e = headerArr2;
                }
                int i6 = this.f2248f;
                this.f2248f = i6 - 1;
                this.f2247e[i6] = header;
                this.f2249g++;
            } else {
                this.f2247e[i2 + c(i2) + d2] = header;
            }
            this.f2250h += i3;
        }

        private boolean h(int i2) {
            return i2 >= 0 && i2 <= Hpack.f2241a.length - 1;
        }

        private int i() {
            return this.f2244b.readByte() & 255;
        }

        private void l(int i2) {
            if (h(i2)) {
                this.f2243a.add(Hpack.f2241a[i2]);
                return;
            }
            int c2 = c(i2 - Hpack.f2241a.length);
            if (c2 >= 0) {
                Header[] headerArr = this.f2247e;
                if (c2 < headerArr.length) {
                    this.f2243a.add(headerArr[c2]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i2 + 1));
        }

        private void n(int i2) {
            g(-1, new Header(f(i2), j()));
        }

        private void o() {
            g(-1, new Header(Hpack.a(j()), j()));
        }

        private void p(int i2) {
            this.f2243a.add(new Header(f(i2), j()));
        }

        private void q() {
            this.f2243a.add(new Header(Hpack.a(j()), j()));
        }

        public List<Header> e() {
            ArrayList arrayList = new ArrayList(this.f2243a);
            this.f2243a.clear();
            return arrayList;
        }

        ByteString j() {
            boolean z2;
            int i2 = i();
            if ((i2 & 128) == 128) {
                z2 = true;
            } else {
                z2 = false;
            }
            int m2 = m(i2, 127);
            if (z2) {
                return ByteString.n(Huffman.f().c(this.f2244b.v(m2)));
            }
            return this.f2244b.j(m2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void k() {
            while (!this.f2244b.u()) {
                int readByte = this.f2244b.readByte() & 255;
                if (readByte != 128) {
                    if ((readByte & 128) == 128) {
                        l(m(readByte, 127) - 1);
                    } else if (readByte == 64) {
                        o();
                    } else if ((readByte & 64) == 64) {
                        n(m(readByte, 63) - 1);
                    } else if ((readByte & 32) == 32) {
                        int m2 = m(readByte, 31);
                        this.f2246d = m2;
                        if (m2 >= 0 && m2 <= this.f2245c) {
                            a();
                        } else {
                            throw new IOException("Invalid dynamic table size update " + this.f2246d);
                        }
                    } else if (readByte != 16 && readByte != 0) {
                        p(m(readByte, 15) - 1);
                    } else {
                        q();
                    }
                } else {
                    throw new IOException("index == 0");
                }
            }
        }

        int m(int i2, int i3) {
            int i4 = i2 & i3;
            if (i4 < i3) {
                return i4;
            }
            int i5 = 0;
            while (true) {
                int i6 = i();
                if ((i6 & 128) == 0) {
                    return i3 + (i6 << i5);
                }
                i3 += (i6 & 127) << i5;
                i5 += 7;
            }
        }

        Reader(int i2, int i3, Source source) {
            this.f2243a = new ArrayList();
            Header[] headerArr = new Header[8];
            this.f2247e = headerArr;
            this.f2248f = headerArr.length - 1;
            this.f2249g = 0;
            this.f2250h = 0;
            this.f2245c = i2;
            this.f2246d = i3;
            this.f2244b = Okio.b(source);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class Writer {

        /* renamed from: a  reason: collision with root package name */
        private final Buffer f2251a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f2252b;

        /* renamed from: c  reason: collision with root package name */
        private int f2253c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f2254d;

        /* renamed from: e  reason: collision with root package name */
        int f2255e;

        /* renamed from: f  reason: collision with root package name */
        int f2256f;

        /* renamed from: g  reason: collision with root package name */
        Header[] f2257g;

        /* renamed from: h  reason: collision with root package name */
        int f2258h;

        /* renamed from: i  reason: collision with root package name */
        int f2259i;

        /* renamed from: j  reason: collision with root package name */
        int f2260j;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Writer(Buffer buffer) {
            this(4096, true, buffer);
        }

        private void a() {
            int i2 = this.f2256f;
            int i3 = this.f2260j;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    c(i3 - i2);
                }
            }
        }

        private void b() {
            Arrays.fill(this.f2257g, (Object) null);
            this.f2258h = this.f2257g.length - 1;
            this.f2259i = 0;
            this.f2260j = 0;
        }

        private int c(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f2257g.length;
                while (true) {
                    length--;
                    i3 = this.f2258h;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    int i5 = this.f2257g[length].f2240c;
                    i2 -= i5;
                    this.f2260j -= i5;
                    this.f2259i--;
                    i4++;
                }
                Header[] headerArr = this.f2257g;
                System.arraycopy(headerArr, i3 + 1, headerArr, i3 + 1 + i4, this.f2259i);
                Header[] headerArr2 = this.f2257g;
                int i6 = this.f2258h;
                Arrays.fill(headerArr2, i6 + 1, i6 + 1 + i4, (Object) null);
                this.f2258h += i4;
            }
            return i4;
        }

        private void d(Header header) {
            int i2 = header.f2240c;
            int i3 = this.f2256f;
            if (i2 > i3) {
                b();
                return;
            }
            c((this.f2260j + i2) - i3);
            int i4 = this.f2259i + 1;
            Header[] headerArr = this.f2257g;
            if (i4 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.f2258h = this.f2257g.length - 1;
                this.f2257g = headerArr2;
            }
            int i5 = this.f2258h;
            this.f2258h = i5 - 1;
            this.f2257g[i5] = header;
            this.f2259i++;
            this.f2260j += i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void e(int i2) {
            this.f2255e = i2;
            int min = Math.min(i2, 16384);
            int i3 = this.f2256f;
            if (i3 == min) {
                return;
            }
            if (min < i3) {
                this.f2253c = Math.min(this.f2253c, min);
            }
            this.f2254d = true;
            this.f2256f = min;
            a();
        }

        void f(ByteString byteString) {
            if (this.f2252b && Huffman.f().e(byteString) < byteString.u()) {
                Buffer buffer = new Buffer();
                Huffman.f().d(byteString, buffer);
                ByteString B = buffer.B();
                h(B.u(), 127, 128);
                this.f2251a.S(B);
                return;
            }
            h(byteString.u(), 127, 0);
            this.f2251a.S(byteString);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void g(List<Header> list) {
            int i2;
            int i3;
            if (this.f2254d) {
                int i4 = this.f2253c;
                if (i4 < this.f2256f) {
                    h(i4, 31, 32);
                }
                this.f2254d = false;
                this.f2253c = Integer.MAX_VALUE;
                h(this.f2256f, 31, 32);
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                Header header = list.get(i5);
                ByteString w2 = header.f2238a.w();
                ByteString byteString = header.f2239b;
                Integer num = Hpack.f2242b.get(w2);
                if (num != null) {
                    i2 = num.intValue() + 1;
                    if (i2 > 1 && i2 < 8) {
                        Header[] headerArr = Hpack.f2241a;
                        if (Util.p(headerArr[i2 - 1].f2239b, byteString)) {
                            i3 = i2;
                        } else if (Util.p(headerArr[i2].f2239b, byteString)) {
                            i3 = i2;
                            i2++;
                        }
                    }
                    i3 = i2;
                    i2 = -1;
                } else {
                    i2 = -1;
                    i3 = -1;
                }
                if (i2 == -1) {
                    int i6 = this.f2258h + 1;
                    int length = this.f2257g.length;
                    while (true) {
                        if (i6 >= length) {
                            break;
                        }
                        if (Util.p(this.f2257g[i6].f2238a, w2)) {
                            if (Util.p(this.f2257g[i6].f2239b, byteString)) {
                                i2 = Hpack.f2241a.length + (i6 - this.f2258h);
                                break;
                            } else if (i3 == -1) {
                                i3 = (i6 - this.f2258h) + Hpack.f2241a.length;
                            }
                        }
                        i6++;
                    }
                }
                if (i2 != -1) {
                    h(i2, 127, 128);
                } else if (i3 == -1) {
                    this.f2251a.writeByte(64);
                    f(w2);
                    f(byteString);
                    d(header);
                } else if (w2.v(Header.f2232d) && !Header.f2237i.equals(w2)) {
                    h(i3, 15, 0);
                    f(byteString);
                } else {
                    h(i3, 63, 64);
                    f(byteString);
                    d(header);
                }
            }
        }

        void h(int i2, int i3, int i4) {
            if (i2 < i3) {
                this.f2251a.writeByte(i2 | i4);
                return;
            }
            this.f2251a.writeByte(i4 | i3);
            int i5 = i2 - i3;
            while (i5 >= 128) {
                this.f2251a.writeByte(128 | (i5 & 127));
                i5 >>>= 7;
            }
            this.f2251a.writeByte(i5);
        }

        Writer(int i2, boolean z2, Buffer buffer) {
            this.f2253c = Integer.MAX_VALUE;
            Header[] headerArr = new Header[8];
            this.f2257g = headerArr;
            this.f2258h = headerArr.length - 1;
            this.f2259i = 0;
            this.f2260j = 0;
            this.f2255e = i2;
            this.f2256f = i2;
            this.f2252b = z2;
            this.f2251a = buffer;
        }
    }

    static {
        ByteString byteString = Header.f2234f;
        ByteString byteString2 = Header.f2235g;
        ByteString byteString3 = Header.f2236h;
        ByteString byteString4 = Header.f2233e;
        f2241a = new Header[]{new Header(Header.f2237i, ""), new Header(byteString, "GET"), new Header(byteString, "POST"), new Header(byteString2, "/"), new Header(byteString2, "/index.html"), new Header(byteString3, "http"), new Header(byteString3, "https"), new Header(byteString4, "200"), new Header(byteString4, "204"), new Header(byteString4, "206"), new Header(byteString4, "304"), new Header(byteString4, "400"), new Header(byteString4, "404"), new Header(byteString4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        f2242b = b();
    }

    static ByteString a(ByteString byteString) {
        int u2 = byteString.u();
        for (int i2 = 0; i2 < u2; i2++) {
            byte f2 = byteString.f(i2);
            if (f2 >= 65 && f2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.y());
            }
        }
        return byteString;
    }

    private static Map<ByteString, Integer> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f2241a.length);
        int i2 = 0;
        while (true) {
            Header[] headerArr = f2241a;
            if (i2 < headerArr.length) {
                if (!linkedHashMap.containsKey(headerArr[i2].f2238a)) {
                    linkedHashMap.put(headerArr[i2].f2238a, Integer.valueOf(i2));
                }
                i2++;
            } else {
                return Collections.unmodifiableMap(linkedHashMap);
            }
        }
    }
}
