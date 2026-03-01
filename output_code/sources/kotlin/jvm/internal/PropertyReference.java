package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class PropertyReference extends CallableReference implements KProperty {

    /* renamed from: k  reason: collision with root package name */
    private final boolean f883k;

    public PropertyReference() {
        this.f883k = false;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable a() {
        return this.f883k ? this : super.a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            if (g().equals(propertyReference.g()) && getName().equals(propertyReference.getName()) && i().equals(propertyReference.i()) && Intrinsics.a(d(), propertyReference.d())) {
                return true;
            }
            return false;
        } else if (!(obj instanceof KProperty)) {
            return false;
        } else {
            return obj.equals(a());
        }
    }

    public int hashCode() {
        return (((g().hashCode() * 31) + getName().hashCode()) * 31) + i().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KProperty j() {
        if (!this.f883k) {
            return (KProperty) super.h();
        }
        throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties");
    }

    public String toString() {
        KCallable a2 = a();
        if (a2 != this) {
            return a2.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertyReference(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        boolean z2 = false;
        this.f883k = (i2 & 2) == 2 ? true : z2;
    }
}
