package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class YieldContext extends AbstractCoroutineContextElement {

    /* renamed from: f  reason: collision with root package name */
    public static final Key f1017f = new Key(null);

    /* renamed from: e  reason: collision with root package name */
    public boolean f1018e;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Key implements CoroutineContext.Key<YieldContext> {
        private Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public YieldContext() {
        super(f1017f);
    }
}
