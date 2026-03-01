package org.koin.core.qualifier;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StringQualifier implements Qualifier {

    /* renamed from: a  reason: collision with root package name */
    private final String f2574a;

    public StringQualifier(String value) {
        Intrinsics.f(value, "value");
        this.f2574a = value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StringQualifier) && Intrinsics.a(getValue(), ((StringQualifier) obj).getValue());
    }

    @Override // org.koin.core.qualifier.Qualifier
    public String getValue() {
        return this.f2574a;
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public String toString() {
        return getValue();
    }
}
