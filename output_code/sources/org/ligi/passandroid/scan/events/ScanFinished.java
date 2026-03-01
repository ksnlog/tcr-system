package org.ligi.passandroid.scan.events;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.Pass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ScanFinished extends PassScanEvent {

    /* renamed from: a  reason: collision with root package name */
    private final List<Pass> f2730a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScanFinished(List<? extends Pass> foundPasses) {
        super(null);
        Intrinsics.f(foundPasses, "foundPasses");
        this.f2730a = foundPasses;
    }

    public final List<Pass> a() {
        return this.f2730a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ScanFinished) && Intrinsics.a(this.f2730a, ((ScanFinished) obj).f2730a);
    }

    public int hashCode() {
        return this.f2730a.hashCode();
    }

    public String toString() {
        return "ScanFinished(foundPasses=" + this.f2730a + ')';
    }
}
