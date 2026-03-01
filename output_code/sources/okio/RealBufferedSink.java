package okio;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class RealBufferedSink implements BufferedSink {

    /* renamed from: d  reason: collision with root package name */
    public final Sink f2479d;

    /* renamed from: e  reason: collision with root package name */
    public final Buffer f2480e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f2481f;

    public RealBufferedSink(Sink sink) {
        Intrinsics.f(sink, "sink");
        this.f2479d = sink;
        this.f2480e = new Buffer();
    }

    @Override // okio.BufferedSink
    public BufferedSink H(String string) {
        Intrinsics.f(string, "string");
        if (!this.f2481f) {
            this.f2480e.H(string);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public Buffer a() {
        return this.f2480e;
    }

    public BufferedSink b() {
        if (!this.f2481f) {
            long e2 = this.f2480e.e();
            if (e2 > 0) {
                this.f2479d.f(this.f2480e, e2);
            }
            return this;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.f2481f) {
            try {
                if (this.f2480e.size() > 0) {
                    Sink sink = this.f2479d;
                    Buffer buffer = this.f2480e;
                    sink.f(buffer, buffer.size());
                }
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                this.f2479d.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                }
            }
            this.f2481f = true;
            if (th != null) {
                throw th;
            }
        }
    }

    @Override // okio.Sink
    public Timeout d() {
        return this.f2479d.d();
    }

    @Override // okio.Sink
    public void f(Buffer source, long j2) {
        Intrinsics.f(source, "source");
        if (!this.f2481f) {
            this.f2480e.f(source, j2);
            b();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (!this.f2481f) {
            if (this.f2480e.size() > 0) {
                Sink sink = this.f2479d;
                Buffer buffer = this.f2480e;
                sink.f(buffer, buffer.size());
            }
            this.f2479d.flush();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink h(String string, int i2, int i3) {
        Intrinsics.f(string, "string");
        if (!this.f2481f) {
            this.f2480e.h(string, i2, i3);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink i(long j2) {
        if (!this.f2481f) {
            this.f2480e.i(j2);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2481f;
    }

    public String toString() {
        return "buffer(" + this.f2479d + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) {
        Intrinsics.f(source, "source");
        if (!this.f2481f) {
            int write = this.f2480e.write(source);
            b();
            return write;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int i2) {
        if (!this.f2481f) {
            this.f2480e.writeByte(i2);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int i2) {
        if (!this.f2481f) {
            this.f2480e.writeInt(i2);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int i2) {
        if (!this.f2481f) {
            this.f2480e.writeShort(i2);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] source) {
        Intrinsics.f(source, "source");
        if (!this.f2481f) {
            this.f2480e.write(source);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] source, int i2, int i3) {
        Intrinsics.f(source, "source");
        if (!this.f2481f) {
            this.f2480e.write(source, i2, i3);
            return b();
        }
        throw new IllegalStateException("closed".toString());
    }
}
