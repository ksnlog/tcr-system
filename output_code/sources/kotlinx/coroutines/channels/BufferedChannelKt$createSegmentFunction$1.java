package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public /* synthetic */ class BufferedChannelKt$createSegmentFunction$1 extends FunctionReferenceImpl implements Function2<Long, ChannelSegment<E>, ChannelSegment<E>> {

    /* renamed from: m  reason: collision with root package name */
    public static final BufferedChannelKt$createSegmentFunction$1 f1086m = new BufferedChannelKt$createSegmentFunction$1();

    BufferedChannelKt$createSegmentFunction$1() {
        super(2, BufferedChannelKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Long l2, Object obj) {
        return j(l2.longValue(), (ChannelSegment) obj);
    }

    public final ChannelSegment<E> j(long j2, ChannelSegment<E> channelSegment) {
        ChannelSegment<E> x2;
        x2 = BufferedChannelKt.x(j2, channelSegment);
        return x2;
    }
}
