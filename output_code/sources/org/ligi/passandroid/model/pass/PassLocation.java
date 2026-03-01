package org.ligi.passandroid.model.pass;

import com.squareup.moshi.JsonClass;
@JsonClass(generateAdapter = true)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassLocation {
    private double lat;
    private double lon;
    private String name;

    public final String getCommaSeparated() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lat);
        sb.append(',');
        sb.append(this.lon);
        return sb.toString();
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLon() {
        return this.lon;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getNameWithFallback(org.ligi.passandroid.model.pass.Pass r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pass"
            kotlin.jvm.internal.Intrinsics.f(r2, r0)
            java.lang.String r0 = r1.name
            if (r0 == 0) goto L12
            boolean r0 = kotlin.text.StringsKt.i(r0)
            if (r0 == 0) goto L10
            goto L12
        L10:
            r0 = 0
            goto L13
        L12:
            r0 = 1
        L13:
            if (r0 == 0) goto L1a
            java.lang.String r2 = r2.getDescription()
            goto L1c
        L1a:
            java.lang.String r2 = r1.name
        L1c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.model.pass.PassLocation.getNameWithFallback(org.ligi.passandroid.model.pass.Pass):java.lang.String");
    }

    public final void setLat(double d2) {
        this.lat = d2;
    }

    public final void setLon(double d2) {
        this.lon = d2;
    }

    public final void setName(String str) {
        this.name = str;
    }
}
