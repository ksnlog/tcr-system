package com.nineoldandroids.util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    public IntProperty(String str) {
        super(Integer.class, str);
    }

    @Override // com.nineoldandroids.util.Property
    /* renamed from: d */
    public final void c(T t2, Integer num) {
        c(t2, Integer.valueOf(num.intValue()));
    }
}
