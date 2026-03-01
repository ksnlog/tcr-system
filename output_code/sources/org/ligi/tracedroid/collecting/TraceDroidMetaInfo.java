package org.ligi.tracedroid.collecting;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TraceDroidMetaInfo {

    /* renamed from: a  reason: collision with root package name */
    private final String f2984a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2985b;

    /* renamed from: c  reason: collision with root package name */
    private final String f2986c;

    /* renamed from: d  reason: collision with root package name */
    private final String f2987d;

    /* renamed from: e  reason: collision with root package name */
    private final String f2988e;

    /* renamed from: f  reason: collision with root package name */
    private final String f2989f;

    /* renamed from: g  reason: collision with root package name */
    private final String f2990g;

    public TraceDroidMetaInfo(String androidVersion, String phoneModel, String filesPath, String str, String str2, String fileSuffix, String traceDroidVersion) {
        Intrinsics.f(androidVersion, "androidVersion");
        Intrinsics.f(phoneModel, "phoneModel");
        Intrinsics.f(filesPath, "filesPath");
        Intrinsics.f(fileSuffix, "fileSuffix");
        Intrinsics.f(traceDroidVersion, "traceDroidVersion");
        this.f2984a = androidVersion;
        this.f2985b = phoneModel;
        this.f2986c = filesPath;
        this.f2987d = str;
        this.f2988e = str2;
        this.f2989f = fileSuffix;
        this.f2990g = traceDroidVersion;
    }

    public final String a() {
        return this.f2984a;
    }

    public final String b() {
        return this.f2988e;
    }

    public final String c() {
        return this.f2987d;
    }

    public final String d() {
        return this.f2989f;
    }

    public final String e() {
        return this.f2986c;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof TraceDroidMetaInfo)) {
                return false;
            }
            TraceDroidMetaInfo traceDroidMetaInfo = (TraceDroidMetaInfo) obj;
            return Intrinsics.a(this.f2984a, traceDroidMetaInfo.f2984a) && Intrinsics.a(this.f2985b, traceDroidMetaInfo.f2985b) && Intrinsics.a(this.f2986c, traceDroidMetaInfo.f2986c) && Intrinsics.a(this.f2987d, traceDroidMetaInfo.f2987d) && Intrinsics.a(this.f2988e, traceDroidMetaInfo.f2988e) && Intrinsics.a(this.f2989f, traceDroidMetaInfo.f2989f) && Intrinsics.a(this.f2990g, traceDroidMetaInfo.f2990g);
        }
        return true;
    }

    public final String f() {
        return this.f2985b;
    }

    public final String g() {
        return this.f2990g;
    }

    public int hashCode() {
        String str = this.f2984a;
        int i2 = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f2985b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f2986c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f2987d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f2988e;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.f2989f;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.f2990g;
        if (str7 != null) {
            i2 = str7.hashCode();
        }
        return hashCode6 + i2;
    }

    public String toString() {
        return "TraceDroidMetaInfo(androidVersion=" + this.f2984a + ", phoneModel=" + this.f2985b + ", filesPath=" + this.f2986c + ", appVersion=" + this.f2987d + ", appPackageName=" + this.f2988e + ", fileSuffix=" + this.f2989f + ", traceDroidVersion=" + this.f2990g + ")";
    }
}
