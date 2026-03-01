package org.koin.core.component;

import org.koin.core.Koin;
import org.koin.mp.KoinPlatformTools;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface KoinComponent {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class DefaultImpls {
        public static Koin a(KoinComponent koinComponent) {
            return KoinPlatformTools.f2602a.a().get();
        }
    }

    Koin getKoin();
}
