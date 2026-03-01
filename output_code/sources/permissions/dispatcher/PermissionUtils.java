package permissions.dispatcher;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PermissionUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final SimpleArrayMap<String, Integer> f3452a;

    static {
        SimpleArrayMap<String, Integer> simpleArrayMap = new SimpleArrayMap<>(13);
        f3452a = simpleArrayMap;
        simpleArrayMap.put("com.android.voicemail.permission.ADD_VOICEMAIL", 14);
        simpleArrayMap.put("android.permission.READ_CALL_LOG", 16);
        simpleArrayMap.put("android.permission.READ_EXTERNAL_STORAGE", 16);
        simpleArrayMap.put("android.permission.WRITE_CALL_LOG", 16);
        simpleArrayMap.put("android.permission.BODY_SENSORS", 20);
        simpleArrayMap.put("android.permission.SYSTEM_ALERT_WINDOW", 23);
        simpleArrayMap.put("android.permission.WRITE_SETTINGS", 23);
        simpleArrayMap.put("android.permission.READ_PHONE_NUMBERS", 26);
        simpleArrayMap.put("android.permission.ANSWER_PHONE_CALLS", 26);
        simpleArrayMap.put("android.permission.ACCEPT_HANDOVER", 28);
        simpleArrayMap.put("android.permission.ACTIVITY_RECOGNITION", 29);
        simpleArrayMap.put("android.permission.ACCESS_MEDIA_LOCATION", 29);
        simpleArrayMap.put("android.permission.ACCESS_BACKGROUND_LOCATION", 29);
    }

    private static boolean a(Context context, String str) {
        try {
            return PermissionChecker.b(context, str) == 0;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public static boolean b(Context context, String... strArr) {
        for (String str : strArr) {
            if (c(str) && !a(context, str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean c(String str) {
        Integer num = (Integer) f3452a.get(str);
        if (num != null && Build.VERSION.SDK_INT < num.intValue()) {
            return false;
        }
        return true;
    }

    public static boolean d(Activity activity, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.q(activity, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(Fragment fragment, String... strArr) {
        for (String str : strArr) {
            if (fragment.L1(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean f(int... iArr) {
        if (iArr.length == 0) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 != 0) {
                return false;
            }
        }
        return true;
    }
}
