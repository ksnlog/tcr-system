package org.ligi.passandroid.scan.events;

import kotlinx.coroutines.channels.ConflatedBroadcastChannel;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassScanEventChannelProvider {

    /* renamed from: a  reason: collision with root package name */
    private final ConflatedBroadcastChannel<PassScanEvent> f2729a = new ConflatedBroadcastChannel<>();

    public final ConflatedBroadcastChannel<PassScanEvent> a() {
        return this.f2729a;
    }
}
