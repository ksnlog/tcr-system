package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class Http2Writer implements Closeable {

    /* renamed from: j  reason: collision with root package name */
    private static final Logger f2375j = Logger.getLogger(Http2.class.getName());

    /* renamed from: d  reason: collision with root package name */
    private final BufferedSink f2376d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f2377e;

    /* renamed from: f  reason: collision with root package name */
    private final Buffer f2378f;

    /* renamed from: g  reason: collision with root package name */
    private int f2379g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2380h;

    /* renamed from: i  reason: collision with root package name */
    final Hpack.Writer f2381i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Writer(BufferedSink bufferedSink, boolean z2) {
        this.f2376d = bufferedSink;
        this.f2377e = z2;
        Buffer buffer = new Buffer();
        this.f2378f = buffer;
        this.f2381i = new Hpack.Writer(buffer);
        this.f2379g = 16384;
    }

    private void E(int i2, long j2) {
        byte b2;
        while (j2 > 0) {
            int min = (int) Math.min(this.f2379g, j2);
            long j3 = min;
            j2 -= j3;
            if (j2 == 0) {
                b2 = 4;
            } else {
                b2 = 0;
            }
            m(i2, min, (byte) 9, b2);
            this.f2376d.f(this.f2378f, j3);
        }
    }

    private static void G(BufferedSink bufferedSink, int i2) {
        bufferedSink.writeByte((i2 >>> 16) & 255);
        bufferedSink.writeByte((i2 >>> 8) & 255);
        bufferedSink.writeByte(i2 & 255);
    }

    public synchronized void B(boolean z2, int i2, int i3, List<Header> list) {
        if (!this.f2380h) {
            o(z2, i2, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void D(int i2, long j2) {
        if (!this.f2380h) {
            if (j2 != 0 && j2 <= 2147483647L) {
                m(i2, 4, (byte) 8, (byte) 0);
                this.f2376d.writeInt((int) j2);
                this.f2376d.flush();
            } else {
                throw Http2.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j2));
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void b(Settings settings) {
        if (!this.f2380h) {
            this.f2379g = settings.f(this.f2379g);
            if (settings.c() != -1) {
                this.f2381i.e(settings.c());
            }
            m(0, 0, (byte) 4, (byte) 1);
            this.f2376d.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void c() {
        if (!this.f2380h) {
            if (!this.f2377e) {
                return;
            }
            Logger logger = f2375j;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.q(">> CONNECTION %s", Http2.f2261a.k()));
            }
            this.f2376d.write(Http2.f2261a.x());
            this.f2376d.flush();
            return;
        }
        throw new IOException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f2380h = true;
        this.f2376d.close();
    }

    public synchronized void e(boolean z2, int i2, Buffer buffer, int i3) {
        byte b2;
        if (!this.f2380h) {
            if (z2) {
                b2 = (byte) 1;
            } else {
                b2 = 0;
            }
            k(i2, b2, buffer, i3);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void flush() {
        if (!this.f2380h) {
            this.f2376d.flush();
        } else {
            throw new IOException("closed");
        }
    }

    void k(int i2, byte b2, Buffer buffer, int i3) {
        m(i2, i3, (byte) 0, b2);
        if (i3 > 0) {
            this.f2376d.f(buffer, i3);
        }
    }

    public void m(int i2, int i3, byte b2, byte b3) {
        Logger logger = f2375j;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.b(false, i2, i3, b2, b3));
        }
        int i4 = this.f2379g;
        if (i3 <= i4) {
            if ((Integer.MIN_VALUE & i2) == 0) {
                G(this.f2376d, i3);
                this.f2376d.writeByte(b2 & 255);
                this.f2376d.writeByte(b3 & 255);
                this.f2376d.writeInt(i2 & Integer.MAX_VALUE);
                return;
            }
            throw Http2.c("reserved bit set: %s", Integer.valueOf(i2));
        }
        throw Http2.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i4), Integer.valueOf(i3));
    }

    public synchronized void n(int i2, ErrorCode errorCode, byte[] bArr) {
        if (!this.f2380h) {
            if (errorCode.f2231d != -1) {
                m(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.f2376d.writeInt(i2);
                this.f2376d.writeInt(errorCode.f2231d);
                if (bArr.length > 0) {
                    this.f2376d.write(bArr);
                }
                this.f2376d.flush();
            } else {
                throw Http2.c("errorCode.httpCode == -1", new Object[0]);
            }
        } else {
            throw new IOException("closed");
        }
    }

    void o(boolean z2, int i2, List<Header> list) {
        byte b2;
        if (!this.f2380h) {
            this.f2381i.g(list);
            long size = this.f2378f.size();
            int min = (int) Math.min(this.f2379g, size);
            long j2 = min;
            if (size == j2) {
                b2 = 4;
            } else {
                b2 = 0;
            }
            if (z2) {
                b2 = (byte) (b2 | 1);
            }
            m(i2, min, (byte) 1, b2);
            this.f2376d.f(this.f2378f, j2);
            if (size > j2) {
                E(i2, size - j2);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }

    public int p() {
        return this.f2379g;
    }

    public synchronized void r(boolean z2, int i2, int i3) {
        byte b2;
        if (!this.f2380h) {
            if (z2) {
                b2 = 1;
            } else {
                b2 = 0;
            }
            m(0, 8, (byte) 6, b2);
            this.f2376d.writeInt(i2);
            this.f2376d.writeInt(i3);
            this.f2376d.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void w(int i2, int i3, List<Header> list) {
        byte b2;
        if (!this.f2380h) {
            this.f2381i.g(list);
            long size = this.f2378f.size();
            int min = (int) Math.min(this.f2379g - 4, size);
            long j2 = min;
            if (size == j2) {
                b2 = 4;
            } else {
                b2 = 0;
            }
            m(i2, min + 4, (byte) 5, b2);
            this.f2376d.writeInt(i3 & Integer.MAX_VALUE);
            this.f2376d.f(this.f2378f, j2);
            if (size > j2) {
                E(i2, size - j2);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void x(int i2, ErrorCode errorCode) {
        if (!this.f2380h) {
            if (errorCode.f2231d != -1) {
                m(i2, 4, (byte) 3, (byte) 0);
                this.f2376d.writeInt(errorCode.f2231d);
                this.f2376d.flush();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void z(Settings settings) {
        int i2;
        if (!this.f2380h) {
            m(0, settings.j() * 6, (byte) 4, (byte) 0);
            for (int i3 = 0; i3 < 10; i3++) {
                if (settings.g(i3)) {
                    if (i3 == 4) {
                        i2 = 3;
                    } else if (i3 == 7) {
                        i2 = 4;
                    } else {
                        i2 = i3;
                    }
                    this.f2376d.writeShort(i2);
                    this.f2376d.writeInt(settings.b(i3));
                }
            }
            this.f2376d.flush();
        } else {
            throw new IOException("closed");
        }
    }
}
