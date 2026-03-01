package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable c() {
        return Reflection.d(this);
    }

    @Override // kotlin.reflect.KProperty1
    public KProperty1.Getter e() {
        ((KMutableProperty1) j()).e();
        return null;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object f(Object obj) {
        return get(obj);
    }
}
