package org.ligi.tracedroid;

import java.io.File;
import java.io.FilenameFilter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class TraceDroid$stackTraceFiles$filter$1 implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final TraceDroid$stackTraceFiles$filter$1 f2983a = new TraceDroid$stackTraceFiles$filter$1();

    TraceDroid$stackTraceFiles$filter$1() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String name) {
        boolean g2;
        Intrinsics.e(name, "name");
        g2 = StringsKt__StringsJVMKt.g(name, TraceDroid.f2981c.f().d(), false, 2, null);
        return g2;
    }
}
