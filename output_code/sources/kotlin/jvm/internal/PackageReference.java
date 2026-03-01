package kotlin.jvm.internal;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f881a;

    /* renamed from: b  reason: collision with root package name */
    private final String f882b;

    public PackageReference(Class<?> jClass, String moduleName) {
        Intrinsics.f(jClass, "jClass");
        Intrinsics.f(moduleName, "moduleName");
        this.f881a = jClass;
        this.f882b = moduleName;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> c() {
        return this.f881a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof PackageReference) && Intrinsics.a(c(), ((PackageReference) obj).c());
    }

    public int hashCode() {
        return c().hashCode();
    }

    public String toString() {
        return c().toString() + " (Kotlin reflection is not available)";
    }
}
