package org.ligi.passandroid.ui.edit;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ligi.passandroid.ui.edit.BarCodeIntentIntegrator;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class BarCodeIntentIntegrator {

    /* renamed from: i  reason: collision with root package name */
    public static final Collection<String> f2909i = i("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");

    /* renamed from: j  reason: collision with root package name */
    public static final Collection<String> f2910j = i("UPC_A", "UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");

    /* renamed from: k  reason: collision with root package name */
    public static final Collection<String> f2911k = Collections.singleton("QR_CODE");

    /* renamed from: l  reason: collision with root package name */
    public static final Collection<String> f2912l = Collections.singleton("DATA_MATRIX");

    /* renamed from: m  reason: collision with root package name */
    public static final Collection<String> f2913m = null;

    /* renamed from: n  reason: collision with root package name */
    public static final List<String> f2914n = Collections.singletonList("com.google.zxing.client.android");

    /* renamed from: o  reason: collision with root package name */
    public static final List<String> f2915o = i("de.markusfisch.android.binaryeye", "com.srowen.bs.android", "com.srowen.bs.android.simple", "com.google.zxing.client.android", "com.teacapps.barcodescanner", "com.example.barcodescanner", "net.qrbot");

    /* renamed from: a  reason: collision with root package name */
    private final Activity f2916a;

    /* renamed from: b  reason: collision with root package name */
    private final Fragment f2917b;

    /* renamed from: c  reason: collision with root package name */
    private String f2918c;

    /* renamed from: d  reason: collision with root package name */
    private String f2919d;

    /* renamed from: e  reason: collision with root package name */
    private String f2920e;

    /* renamed from: f  reason: collision with root package name */
    private String f2921f;

    /* renamed from: g  reason: collision with root package name */
    private List<String> f2922g;

    /* renamed from: h  reason: collision with root package name */
    private final Map<String, Object> f2923h = new HashMap(3);

    public BarCodeIntentIntegrator(Fragment fragment) {
        this.f2916a = fragment.m();
        this.f2917b = fragment;
        e();
    }

    private void b(Intent intent) {
        for (Map.Entry<String, Object> entry : this.f2923h.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                intent.putExtra(key, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(key, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(key, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(key, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(key, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(key, (Bundle) value);
            } else {
                intent.putExtra(key, value.toString());
            }
        }
    }

    private static boolean c(Iterable<ResolveInfo> iterable, String str) {
        for (ResolveInfo resolveInfo : iterable) {
            if (str.equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    private String d(Intent intent) {
        List<ResolveInfo> queryIntentActivities = this.f2916a.getPackageManager().queryIntentActivities(intent, 65536);
        if (queryIntentActivities != null) {
            for (String str : this.f2922g) {
                if (c(queryIntentActivities, str)) {
                    return str;
                }
            }
            return null;
        }
        return null;
    }

    private void e() {
        this.f2918c = "Install Barcode Scanner?";
        this.f2919d = "This application requires Barcode Scanner. Would you like to install it?";
        this.f2920e = "Yes";
        this.f2921f = "No";
        this.f2922g = f2915o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(DialogInterface dialogInterface, int i2) {
        String str = "com.google.zxing.client.android";
        if (!this.f2922g.contains(str)) {
            str = this.f2922g.get(0);
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
        try {
            Fragment fragment = this.f2917b;
            if (fragment == null) {
                this.f2916a.startActivity(intent);
            } else {
                fragment.M1(intent);
            }
        } catch (ActivityNotFoundException unused) {
            Timber.g("Google Play is not installed; cannot install " + str, new Object[0]);
        }
    }

    private static List<String> i(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    private AlertDialog j() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f2916a);
        builder.w(this.f2918c);
        builder.k(this.f2919d);
        builder.s(this.f2920e, new DialogInterface.OnClickListener() { // from class: f0.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                BarCodeIntentIntegrator.this.h(dialogInterface, i2);
            }
        });
        builder.n(this.f2921f, (DialogInterface.OnClickListener) null);
        builder.d(true);
        return builder.z();
    }

    private void k(Intent intent, int i2) {
        Fragment fragment = this.f2917b;
        if (fragment == null) {
            this.f2916a.startActivityForResult(intent, i2);
        } else {
            fragment.O1(intent, i2);
        }
    }

    public final AlertDialog f(Collection<String> collection) {
        return g(collection, -1);
    }

    public final AlertDialog g(Collection<String> collection, int i2) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.addCategory("android.intent.category.DEFAULT");
        if (collection != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : collection) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(str);
            }
            intent.putExtra("SCAN_FORMATS", sb.toString());
        }
        if (i2 >= 0) {
            intent.putExtra("SCAN_CAMERA_ID", i2);
        }
        String d2 = d(intent);
        if (d2 == null) {
            return j();
        }
        intent.setPackage(d2);
        intent.addFlags(67108864);
        intent.addFlags(524288);
        b(intent);
        k(intent, 49374);
        return null;
    }
}
