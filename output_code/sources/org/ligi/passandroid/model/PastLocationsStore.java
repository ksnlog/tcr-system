package org.ligi.passandroid.model;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PastLocationsStore {
    public static final Companion Companion = new Companion(null);
    public static final String KEY_PAST_LOCATIONS = "past_locations";
    public static final int MAX_ELEMENTS = 5;
    private final SharedPreferences sharedPreferences;
    private final Tracker tracker;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PastLocationsStore(SharedPreferences sharedPreferences, Tracker tracker) {
        Intrinsics.f(sharedPreferences, "sharedPreferences");
        Intrinsics.f(tracker, "tracker");
        this.sharedPreferences = sharedPreferences;
        this.tracker = tracker;
    }

    private final void deleteOneElementFromSet(Set<String> set) {
        double random = Math.random();
        double d2 = 5;
        Double.isNaN(d2);
        int i2 = (int) (random * d2);
        int i3 = 0;
        for (String str : set) {
            int i4 = i3 + 1;
            if (i3 == i2) {
                set.remove(str);
                return;
            }
            i3 = i4;
        }
    }

    public final Set<String> getLocations() {
        Set<String> b2;
        Set<String> b3;
        SharedPreferences sharedPreferences = this.sharedPreferences;
        b2 = SetsKt__SetsKt.b();
        Set<String> stringSet = sharedPreferences.getStringSet(KEY_PAST_LOCATIONS, b2);
        if (stringSet == null) {
            b3 = SetsKt__SetsKt.b();
            return b3;
        }
        return stringSet;
    }

    public final void putLocation(String path) {
        Intrinsics.f(path, "path");
        Set<String> stringSet = this.sharedPreferences.getStringSet(KEY_PAST_LOCATIONS, new HashSet());
        Intrinsics.c(stringSet);
        if (stringSet.size() >= 5) {
            deleteOneElementFromSet(stringSet);
        }
        if (!stringSet.contains(path)) {
            stringSet.add(path);
        }
        this.tracker.b("scan", "put location", "count", Long.valueOf(stringSet.size()));
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        Intrinsics.e(editor, "editor");
        editor.putStringSet(KEY_PAST_LOCATIONS, stringSet);
        editor.apply();
    }
}
