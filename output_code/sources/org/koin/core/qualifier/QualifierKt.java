package org.koin.core.qualifier;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class QualifierKt {
    public static final StringQualifier a(String name) {
        Intrinsics.f(name, "name");
        return new StringQualifier(name);
    }
}
