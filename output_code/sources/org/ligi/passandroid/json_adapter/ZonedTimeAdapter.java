package org.ligi.passandroid.json_adapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ZonedTimeAdapter {
    @FromJson
    public final ZonedDateTime fromJson$PassAndroid_3_7_3_noMapsNoAnalyticsForFDroidRelease(String zonedDateTime) {
        Intrinsics.f(zonedDateTime, "zonedDateTime");
        return ZonedDateTime.T(zonedDateTime);
    }

    @ToJson
    public final String toJson$PassAndroid_3_7_3_noMapsNoAnalyticsForFDroidRelease(ZonedDateTime zonedDateTime) {
        Intrinsics.f(zonedDateTime, "zonedDateTime");
        return zonedDateTime.o(DateTimeFormatter.f3221o);
    }
}
