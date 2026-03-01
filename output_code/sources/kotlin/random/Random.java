package kotlin.random;

import java.io.Serializable;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Random {

    /* renamed from: d  reason: collision with root package name */
    public static final Default f889d = new Default(null);

    /* renamed from: e  reason: collision with root package name */
    private static final Random f890e = PlatformImplementationsKt.f824a.b();

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Default extends Random implements Serializable {
        private Default() {
        }

        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // kotlin.random.Random
        public int b() {
            return Random.f890e.b();
        }
    }

    public abstract int b();
}
