package okio;

import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class Okio__OkioKt {
    public static final BufferedSink a(Sink sink) {
        Intrinsics.f(sink, "<this>");
        return new RealBufferedSink(sink);
    }

    public static final BufferedSource b(Source source) {
        Intrinsics.f(source, "<this>");
        return new RealBufferedSource(source);
    }
}
