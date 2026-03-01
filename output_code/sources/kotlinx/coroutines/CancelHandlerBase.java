package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class CancelHandlerBase implements Function1<Throwable, Unit> {
    public abstract void d(Throwable th);
}
