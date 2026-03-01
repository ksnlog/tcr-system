package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    public PropertyReference0(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable c() {
        return Reflection.e(this);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public Object mo2invoke() {
        return get();
    }
}
