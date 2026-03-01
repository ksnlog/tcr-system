package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ConflatedBufferedChannel<E> extends BufferedChannel<E> {

    /* renamed from: m  reason: collision with root package name */
    private final int f1094m;

    /* renamed from: n  reason: collision with root package name */
    private final BufferOverflow f1095n;

    public /* synthetic */ ConflatedBufferedChannel(int i2, BufferOverflow bufferOverflow, Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, bufferOverflow, (i3 & 4) != 0 ? null : function1);
    }

    static /* synthetic */ <E> Object w0(ConflatedBufferedChannel<E> conflatedBufferedChannel, E e2, Continuation<? super Boolean> continuation) {
        Object z0 = conflatedBufferedChannel.z0(e2, true);
        if (!(z0 instanceof ChannelResult.Failed)) {
            Unit unit = (Unit) z0;
            return Boxing.a(true);
        }
        return Boxing.a(false);
    }

    private final Object x0(E e2, boolean z2) {
        Function1<E, Unit> function1;
        UndeliveredElementException d2;
        Object m0 = super.m0(e2);
        if (!ChannelResult.g(m0) && !ChannelResult.f(m0)) {
            if (z2 && (function1 = this.f1056b) != null && (d2 = OnUndeliveredElementKt.d(function1, e2, null, 2, null)) != null) {
                throw d2;
            }
            return ChannelResult.f1087b.c(Unit.f763a);
        }
        return m0;
    }

    private final Object y0(E e2) {
        ChannelSegment channelSegment;
        Waiter waiter;
        Symbol symbol = BufferedChannelKt.f1070d;
        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.f1050h.get(this);
        while (true) {
            long andIncrement = BufferedChannel.f1046d.getAndIncrement(this);
            long j2 = andIncrement & 1152921504606846975L;
            boolean P = P(andIncrement);
            int i2 = BufferedChannelKt.f1068b;
            long j3 = j2 / i2;
            int i3 = (int) (j2 % i2);
            if (channelSegment2.f1150f != j3) {
                ChannelSegment z2 = z(j3, channelSegment2);
                if (z2 == null) {
                    if (P) {
                        return ChannelResult.f1087b.a(E());
                    }
                } else {
                    channelSegment = z2;
                }
            } else {
                channelSegment = channelSegment2;
            }
            int r0 = r0(channelSegment, i3, e2, j2, symbol, P);
            if (r0 != 0) {
                if (r0 != 1) {
                    if (r0 != 2) {
                        if (r0 != 3) {
                            if (r0 != 4) {
                                if (r0 == 5) {
                                    channelSegment.b();
                                }
                                channelSegment2 = channelSegment;
                            } else {
                                if (j2 < D()) {
                                    channelSegment.b();
                                }
                                return ChannelResult.f1087b.a(E());
                            }
                        } else {
                            throw new IllegalStateException("unexpected".toString());
                        }
                    } else if (P) {
                        channelSegment.p();
                        return ChannelResult.f1087b.a(E());
                    } else {
                        if (symbol instanceof Waiter) {
                            waiter = (Waiter) symbol;
                        } else {
                            waiter = null;
                        }
                        if (waiter != null) {
                            b0(waiter, channelSegment, i3);
                        }
                        v((channelSegment.f1150f * i2) + i3);
                        return ChannelResult.f1087b.c(Unit.f763a);
                    }
                } else {
                    return ChannelResult.f1087b.c(Unit.f763a);
                }
            } else {
                channelSegment.b();
                return ChannelResult.f1087b.c(Unit.f763a);
            }
        }
    }

    private final Object z0(E e2, boolean z2) {
        if (this.f1095n == BufferOverflow.DROP_LATEST) {
            return x0(e2, z2);
        }
        return y0(e2);
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    protected boolean Q() {
        return this.f1095n == BufferOverflow.DROP_OLDEST;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public Object g0(E e2, Continuation<? super Boolean> continuation) {
        return w0(this, e2, continuation);
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean j0() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public Object m0(E e2) {
        return z0(e2, false);
    }

    public ConflatedBufferedChannel(int i2, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        super(i2, function1);
        this.f1094m = i2;
        this.f1095n = bufferOverflow;
        boolean z2 = false;
        if (!(bufferOverflow != BufferOverflow.SUSPEND)) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + Reflection.b(BufferedChannel.class).b() + " instead").toString());
        }
        if (i2 >= 1 ? true : z2) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i2 + " was specified").toString());
    }
}
