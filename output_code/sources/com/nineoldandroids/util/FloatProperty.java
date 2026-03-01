package com.nineoldandroids.util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class FloatProperty<T> extends Property<T, Float> {
    public FloatProperty(String str) {
        super(Float.class, str);
    }

    @Override // com.nineoldandroids.util.Property
    /* renamed from: d */
    public final void c(T t2, Float f2) {
        e(t2, f2.floatValue());
    }

    public abstract void e(T t2, float f2);
}
