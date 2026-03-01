package org.ligi.passandroid.model.pass;

import android.content.res.Resources;
import com.squareup.moshi.JsonClass;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@JsonClass(generateAdapter = true)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassField {
    public static final Companion Companion = new Companion(null);
    private boolean hide;
    private String hint;
    private String key;
    private String label;
    private String value;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ PassField create$default(Companion companion, int i2, int i3, Resources resources, boolean z2, String str, int i4, Object obj) {
            boolean z3 = (i4 & 8) != 0 ? false : z2;
            if ((i4 & 16) != 0) {
                str = null;
            }
            return companion.create(i2, i3, resources, z3, str);
        }

        public final PassField create(int i2, int i3, Resources res, boolean z2, String str) {
            Intrinsics.f(res, "res");
            return new PassField(null, res.getString(i2), res.getString(i3), z2, str);
        }
    }

    public PassField(String str, String str2, String str3, boolean z2, String str4) {
        this.key = str;
        this.label = str2;
        this.value = str3;
        this.hide = z2;
        this.hint = str4;
    }

    public final boolean getHide() {
        return this.hide;
    }

    public final String getHint() {
        return this.hint;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setHide(boolean z2) {
        this.hide = z2;
    }

    public final void setHint(String str) {
        this.hint = str;
    }

    public final void setKey(String str) {
        this.key = str;
    }

    public final void setLabel(String str) {
        this.label = str;
    }

    public final void setValue(String str) {
        this.value = str;
    }

    public final String toHtmlSnippet() {
        StringBuilder sb = new StringBuilder();
        if (this.label != null) {
            sb.append("<b>" + this.label + "</b> ");
        }
        String str = this.value;
        if (str != null) {
            sb.append(str);
        }
        if (this.label != null || this.value != null) {
            sb.append("<br/>");
        }
        return String.valueOf(sb);
    }

    public /* synthetic */ PassField(String str, String str2, String str3, boolean z2, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, z2, (i2 & 16) != 0 ? null : str4);
    }
}
