package org.koin.core.context;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface KoinContext {
    KoinApplication a(Function1<? super KoinApplication, Unit> function1);

    Koin get();
}
