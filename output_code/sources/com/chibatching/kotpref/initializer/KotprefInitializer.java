package com.chibatching.kotpref.initializer;

import android.content.Context;
import androidx.startup.Initializer;
import com.chibatching.kotpref.Kotpref;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class KotprefInitializer implements Initializer<Kotpref> {
    public List<Class<? extends Initializer<?>>> a() {
        List<Class<? extends Initializer<?>>> f2;
        f2 = CollectionsKt__CollectionsKt.f();
        return f2;
    }

    /* renamed from: c */
    public Kotpref b(Context context) {
        Intrinsics.f(context, "context");
        Kotpref kotpref = Kotpref.f9a;
        kotpref.a(context);
        return kotpref;
    }
}
