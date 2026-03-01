package org.koin.core.parameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ParametersHolder {

    /* renamed from: b  reason: collision with root package name */
    public static final Companion f2572b = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final List<Object> f2573a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ParametersHolder() {
        this(null, 1, null);
    }

    public ParametersHolder(List<Object> _values) {
        Intrinsics.f(_values, "_values");
        this.f2573a = _values;
    }

    public <T> T a(KClass<?> clazz) {
        T t2;
        Intrinsics.f(clazz, "clazz");
        Iterator<T> it = this.f2573a.iterator();
        do {
            t2 = null;
            if (!it.hasNext()) {
                break;
            }
            T next = it.next();
            if (clazz.a(next) && next != null) {
                t2 = next;
                continue;
            }
        } while (t2 == null);
        return t2;
    }

    public String toString() {
        List H;
        StringBuilder sb = new StringBuilder();
        sb.append("DefinitionParameters");
        H = CollectionsKt___CollectionsKt.H(this.f2573a);
        sb.append(H);
        return sb.toString();
    }

    public /* synthetic */ ParametersHolder(List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new ArrayList() : list);
    }
}
