package okhttp3.internal.http2;

import okhttp3.internal.Util;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Header {

    /* renamed from: d  reason: collision with root package name */
    public static final ByteString f2232d = ByteString.e(":");

    /* renamed from: e  reason: collision with root package name */
    public static final ByteString f2233e = ByteString.e(":status");

    /* renamed from: f  reason: collision with root package name */
    public static final ByteString f2234f = ByteString.e(":method");

    /* renamed from: g  reason: collision with root package name */
    public static final ByteString f2235g = ByteString.e(":path");

    /* renamed from: h  reason: collision with root package name */
    public static final ByteString f2236h = ByteString.e(":scheme");

    /* renamed from: i  reason: collision with root package name */
    public static final ByteString f2237i = ByteString.e(":authority");

    /* renamed from: a  reason: collision with root package name */
    public final ByteString f2238a;

    /* renamed from: b  reason: collision with root package name */
    public final ByteString f2239b;

    /* renamed from: c  reason: collision with root package name */
    final int f2240c;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    interface Listener {
    }

    public Header(String str, String str2) {
        this(ByteString.e(str), ByteString.e(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (!this.f2238a.equals(header.f2238a) || !this.f2239b.equals(header.f2239b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + this.f2238a.hashCode()) * 31) + this.f2239b.hashCode();
    }

    public String toString() {
        return Util.q("%s: %s", this.f2238a.y(), this.f2239b.y());
    }

    public Header(ByteString byteString, String str) {
        this(byteString, ByteString.e(str));
    }

    public Header(ByteString byteString, ByteString byteString2) {
        this.f2238a = byteString;
        this.f2239b = byteString2;
        this.f2240c = byteString.u() + 32 + byteString2.u();
    }
}
