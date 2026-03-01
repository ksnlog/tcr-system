package org.ligi.passandroid.scan.events;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class DirectoryProcessed extends PassScanEvent {

    /* renamed from: a  reason: collision with root package name */
    private final String f2728a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DirectoryProcessed(String dir) {
        super(null);
        Intrinsics.f(dir, "dir");
        this.f2728a = dir;
    }

    public final String a() {
        return this.f2728a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DirectoryProcessed) && Intrinsics.a(this.f2728a, ((DirectoryProcessed) obj).f2728a);
    }

    public int hashCode() {
        return this.f2728a.hashCode();
    }

    public String toString() {
        return "DirectoryProcessed(dir=" + this.f2728a + ')';
    }
}
