package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Http2Reader implements Closeable {

    /* renamed from: h  reason: collision with root package name */
    static final Logger f2342h = Logger.getLogger(Http2.class.getName());

    /* renamed from: d  reason: collision with root package name */
    private final BufferedSource f2343d;

    /* renamed from: e  reason: collision with root package name */
    private final ContinuationSource f2344e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f2345f;

    /* renamed from: g  reason: collision with root package name */
    final Hpack.Reader f2346g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class ContinuationSource implements Source {

        /* renamed from: d  reason: collision with root package name */
        private final BufferedSource f2347d;

        /* renamed from: e  reason: collision with root package name */
        int f2348e;

        /* renamed from: f  reason: collision with root package name */
        byte f2349f;

        /* renamed from: g  reason: collision with root package name */
        int f2350g;

        /* renamed from: h  reason: collision with root package name */
        int f2351h;

        /* renamed from: i  reason: collision with root package name */
        short f2352i;

        ContinuationSource(BufferedSource bufferedSource) {
            this.f2347d = bufferedSource;
        }

        private void b() {
            int i2 = this.f2350g;
            int p2 = Http2Reader.p(this.f2347d);
            this.f2351h = p2;
            this.f2348e = p2;
            byte readByte = (byte) (this.f2347d.readByte() & 255);
            this.f2349f = (byte) (this.f2347d.readByte() & 255);
            Logger logger = Http2Reader.f2342h;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Http2.b(true, this.f2350g, this.f2348e, readByte, this.f2349f));
            }
            int readInt = this.f2347d.readInt() & Integer.MAX_VALUE;
            this.f2350g = readInt;
            if (readByte == 9) {
                if (readInt == i2) {
                    return;
                }
                throw Http2.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
            throw Http2.d("%s != TYPE_CONTINUATION", Byte.valueOf(readByte));
        }

        @Override // okio.Source
        public long A(Buffer buffer, long j2) {
            while (true) {
                int i2 = this.f2351h;
                if (i2 == 0) {
                    this.f2347d.skip(this.f2352i);
                    this.f2352i = (short) 0;
                    if ((this.f2349f & 4) != 0) {
                        return -1L;
                    }
                    b();
                } else {
                    long A = this.f2347d.A(buffer, Math.min(j2, i2));
                    if (A == -1) {
                        return -1L;
                    }
                    this.f2351h = (int) (this.f2351h - A);
                    return A;
                }
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // okio.Source
        public Timeout d() {
            return this.f2347d.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Handler {
        void a();

        void b(boolean z2, Settings settings);

        void c(boolean z2, int i2, BufferedSource bufferedSource, int i3);

        void d(boolean z2, int i2, int i3);

        void e(int i2, int i3, int i4, boolean z2);

        void f(int i2, ErrorCode errorCode);

        void g(boolean z2, int i2, int i3, List<Header> list);

        void h(int i2, long j2);

        void i(int i2, int i3, List<Header> list);

        void j(int i2, ErrorCode errorCode, ByteString byteString);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Reader(BufferedSource bufferedSource, boolean z2) {
        this.f2343d = bufferedSource;
        this.f2345f = z2;
        ContinuationSource continuationSource = new ContinuationSource(bufferedSource);
        this.f2344e = continuationSource;
        this.f2346g = new Hpack.Reader(4096, continuationSource);
    }

    private void B(Handler handler, int i2, byte b2, int i3) {
        if (i2 == 4) {
            if (i3 != 0) {
                int readInt = this.f2343d.readInt();
                ErrorCode a2 = ErrorCode.a(readInt);
                if (a2 != null) {
                    handler.f(i3, a2);
                    return;
                }
                throw Http2.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(readInt));
            }
            throw Http2.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        throw Http2.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i2));
    }

    private void D(Handler handler, int i2, byte b2, int i3) {
        if (i3 == 0) {
            if ((b2 & 1) != 0) {
                if (i2 == 0) {
                    handler.a();
                    return;
                }
                throw Http2.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            } else if (i2 % 6 == 0) {
                Settings settings = new Settings();
                for (int i4 = 0; i4 < i2; i4 += 6) {
                    int readShort = this.f2343d.readShort() & 65535;
                    int readInt = this.f2343d.readInt();
                    if (readShort != 2) {
                        if (readShort != 3) {
                            if (readShort != 4) {
                                if (readShort == 5 && (readInt < 16384 || readInt > 16777215)) {
                                    throw Http2.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(readInt));
                                }
                            } else if (readInt >= 0) {
                                readShort = 7;
                            } else {
                                throw Http2.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                            }
                        } else {
                            readShort = 4;
                        }
                    } else if (readInt != 0 && readInt != 1) {
                        throw Http2.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                    }
                    settings.i(readShort, readInt);
                }
                handler.b(false, settings);
                return;
            } else {
                throw Http2.d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i2));
            }
        }
        throw Http2.d("TYPE_SETTINGS streamId != 0", new Object[0]);
    }

    private void E(Handler handler, int i2, byte b2, int i3) {
        if (i2 == 4) {
            long readInt = this.f2343d.readInt() & 2147483647L;
            if (readInt != 0) {
                handler.h(i3, readInt);
                return;
            }
            throw Http2.d("windowSizeIncrement was 0", Long.valueOf(readInt));
        }
        throw Http2.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i2));
    }

    static int b(int i2, byte b2, short s2) {
        if ((b2 & 8) != 0) {
            i2--;
        }
        if (s2 <= i2) {
            return (short) (i2 - s2);
        }
        throw Http2.d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s2), Integer.valueOf(i2));
    }

    private void k(Handler handler, int i2, byte b2, int i3) {
        boolean z2;
        short s2 = 0;
        if (i3 != 0) {
            boolean z3 = true;
            if ((b2 & 1) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((b2 & 32) == 0) {
                z3 = false;
            }
            if (!z3) {
                if ((b2 & 8) != 0) {
                    s2 = (short) (this.f2343d.readByte() & 255);
                }
                handler.c(z2, i3, this.f2343d, b(i2, b2, s2));
                this.f2343d.skip(s2);
                return;
            }
            throw Http2.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        throw Http2.d("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
    }

    private void m(Handler handler, int i2, byte b2, int i3) {
        if (i2 >= 8) {
            if (i3 == 0) {
                int readInt = this.f2343d.readInt();
                int readInt2 = this.f2343d.readInt();
                int i4 = i2 - 8;
                ErrorCode a2 = ErrorCode.a(readInt2);
                if (a2 != null) {
                    ByteString byteString = ByteString.f2455h;
                    if (i4 > 0) {
                        byteString = this.f2343d.j(i4);
                    }
                    handler.j(readInt, a2, byteString);
                    return;
                }
                throw Http2.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(readInt2));
            }
            throw Http2.d("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        throw Http2.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i2));
    }

    private List<Header> n(int i2, short s2, byte b2, int i3) {
        ContinuationSource continuationSource = this.f2344e;
        continuationSource.f2351h = i2;
        continuationSource.f2348e = i2;
        continuationSource.f2352i = s2;
        continuationSource.f2349f = b2;
        continuationSource.f2350g = i3;
        this.f2346g.k();
        return this.f2346g.e();
    }

    private void o(Handler handler, int i2, byte b2, int i3) {
        boolean z2;
        short s2 = 0;
        if (i3 != 0) {
            if ((b2 & 1) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((b2 & 8) != 0) {
                s2 = (short) (this.f2343d.readByte() & 255);
            }
            if ((b2 & 32) != 0) {
                w(handler, i3);
                i2 -= 5;
            }
            handler.g(z2, i3, -1, n(b(i2, b2, s2), s2, b2, i3));
            return;
        }
        throw Http2.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
    }

    static int p(BufferedSource bufferedSource) {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    private void r(Handler handler, int i2, byte b2, int i3) {
        boolean z2 = false;
        if (i2 == 8) {
            if (i3 == 0) {
                int readInt = this.f2343d.readInt();
                int readInt2 = this.f2343d.readInt();
                if ((b2 & 1) != 0) {
                    z2 = true;
                }
                handler.d(z2, readInt, readInt2);
                return;
            }
            throw Http2.d("TYPE_PING streamId != 0", new Object[0]);
        }
        throw Http2.d("TYPE_PING length != 8: %s", Integer.valueOf(i2));
    }

    private void w(Handler handler, int i2) {
        boolean z2;
        int readInt = this.f2343d.readInt();
        if ((Integer.MIN_VALUE & readInt) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        handler.e(i2, readInt & Integer.MAX_VALUE, (this.f2343d.readByte() & 255) + 1, z2);
    }

    private void x(Handler handler, int i2, byte b2, int i3) {
        if (i2 == 5) {
            if (i3 != 0) {
                w(handler, i3);
                return;
            }
            throw Http2.d("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        throw Http2.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i2));
    }

    private void z(Handler handler, int i2, byte b2, int i3) {
        short s2 = 0;
        if (i3 != 0) {
            if ((b2 & 8) != 0) {
                s2 = (short) (this.f2343d.readByte() & 255);
            }
            handler.i(i3, this.f2343d.readInt() & Integer.MAX_VALUE, n(b(i2 - 4, b2, s2), s2, b2, i3));
            return;
        }
        throw Http2.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
    }

    public boolean c(boolean z2, Handler handler) {
        try {
            this.f2343d.F(9L);
            int p2 = p(this.f2343d);
            if (p2 >= 0 && p2 <= 16384) {
                byte readByte = (byte) (this.f2343d.readByte() & 255);
                if (z2 && readByte != 4) {
                    throw Http2.d("Expected a SETTINGS frame but was %s", Byte.valueOf(readByte));
                }
                byte readByte2 = (byte) (this.f2343d.readByte() & 255);
                int readInt = this.f2343d.readInt() & Integer.MAX_VALUE;
                Logger logger = f2342h;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Http2.b(true, readInt, p2, readByte, readByte2));
                }
                switch (readByte) {
                    case 0:
                        k(handler, p2, readByte2, readInt);
                        break;
                    case 1:
                        o(handler, p2, readByte2, readInt);
                        break;
                    case 2:
                        x(handler, p2, readByte2, readInt);
                        break;
                    case 3:
                        B(handler, p2, readByte2, readInt);
                        break;
                    case 4:
                        D(handler, p2, readByte2, readInt);
                        break;
                    case 5:
                        z(handler, p2, readByte2, readInt);
                        break;
                    case R$styleable.f1342r /* 6 */:
                        r(handler, p2, readByte2, readInt);
                        break;
                    case R$styleable.f1343s /* 7 */:
                        m(handler, p2, readByte2, readInt);
                        break;
                    case R$styleable.f1328d /* 8 */:
                        E(handler, p2, readByte2, readInt);
                        break;
                    default:
                        this.f2343d.skip(p2);
                        break;
                }
                return true;
            }
            throw Http2.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(p2));
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2343d.close();
    }

    public void e(Handler handler) {
        if (this.f2345f) {
            if (!c(true, handler)) {
                throw Http2.d("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        BufferedSource bufferedSource = this.f2343d;
        ByteString byteString = Http2.f2261a;
        ByteString j2 = bufferedSource.j(byteString.u());
        Logger logger = f2342h;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Util.q("<< CONNECTION %s", j2.k()));
        }
        if (byteString.equals(j2)) {
            return;
        }
        throw Http2.d("Expected a connection header but was %s", j2.y());
    }
}
