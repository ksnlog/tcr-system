package w;

import java.util.concurrent.atomic.AtomicReferenceArray;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class a {
    public static /* synthetic */ boolean a(AtomicReferenceArray atomicReferenceArray, int i2, Object obj, Object obj2) {
        while (!atomicReferenceArray.compareAndSet(i2, obj, obj2)) {
            if (atomicReferenceArray.get(i2) != obj) {
                return false;
            }
        }
        return true;
    }
}
