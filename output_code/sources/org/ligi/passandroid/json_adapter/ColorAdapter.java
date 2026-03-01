package org.ligi.passandroid.json_adapter;

import android.graphics.Color;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ColorAdapter {
    @PassImpl.HexColor
    @FromJson
    public final int fromJson$PassAndroid_3_7_3_noMapsNoAnalyticsForFDroidRelease(String rgb) {
        Intrinsics.f(rgb, "rgb");
        return Color.parseColor(rgb);
    }

    @ToJson
    public final String toJson$PassAndroid_3_7_3_noMapsNoAnalyticsForFDroidRelease(@PassImpl.HexColor int i2) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.f887a;
        String format = String.format("#%06x", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
        Intrinsics.e(format, "format(format, *args)");
        return format;
    }
}
