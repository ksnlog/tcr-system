package org.ligi.passandroid.ui;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.ActivityExtensionsKt;
import org.ligi.passandroid.databinding.FullscreenImageBinding;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.PassBarCodeFormat;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FullscreenBarcodeActivity extends PassViewActivityBase {
    private FullscreenImageBinding K;

    /* JADX WARN: Multi-variable type inference failed */
    private final void x0() {
        BarCode barCode = s0().getBarCode();
        Intrinsics.c(barCode);
        PassBarCodeFormat format = barCode.getFormat();
        Intrinsics.c(format);
        if (format.isQuadratic()) {
            int requestedOrientation = getRequestedOrientation();
            if (requestedOrientation != 1 && requestedOrientation != 7 && requestedOrientation != 9 && requestedOrientation != 12) {
                ActivityExtensionsKt.d(this, 1);
                return;
            }
            return;
        }
        int requestedOrientation2 = getRequestedOrientation();
        if (requestedOrientation2 != 0 && requestedOrientation2 != 6 && requestedOrientation2 != 8 && requestedOrientation2 != 11) {
            ActivityExtensionsKt.d(this, 2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FullscreenImageBinding c2 = FullscreenImageBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        this.K = c2;
        if (c2 == null) {
            Intrinsics.p("binding");
            c2 = null;
        }
        setContentView(c2.b());
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            return;
        }
        getWindow().addFlags(524288);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.ligi.passandroid.ui.PassViewActivityBase, org.ligi.passandroid.ui.PassAndroidActivity
    protected void onResume() {
        super.onResume();
        if (s0().getBarCode() == null) {
            Timber.f3479a.k("FullscreenBarcodeActivity in bad state", new Object[0]);
            finish();
            return;
        }
        x0();
        FullscreenImageBinding fullscreenImageBinding = this.K;
        FullscreenImageBinding fullscreenImageBinding2 = null;
        if (fullscreenImageBinding == null) {
            Intrinsics.p("binding");
            fullscreenImageBinding = null;
        }
        ImageView imageView = fullscreenImageBinding.f2655c;
        BarCode barCode = s0().getBarCode();
        Intrinsics.c(barCode);
        Resources resources = getResources();
        Intrinsics.e(resources, "resources");
        imageView.setImageDrawable(barCode.getBitmap(resources));
        BarCode barCode2 = s0().getBarCode();
        Intrinsics.c(barCode2);
        if (barCode2.getAlternativeText() != null) {
            FullscreenImageBinding fullscreenImageBinding3 = this.K;
            if (fullscreenImageBinding3 == null) {
                Intrinsics.p("binding");
                fullscreenImageBinding3 = null;
            }
            fullscreenImageBinding3.f2654b.setVisibility(0);
            FullscreenImageBinding fullscreenImageBinding4 = this.K;
            if (fullscreenImageBinding4 == null) {
                Intrinsics.p("binding");
            } else {
                fullscreenImageBinding2 = fullscreenImageBinding4;
            }
            TextView textView = fullscreenImageBinding2.f2654b;
            BarCode barCode3 = s0().getBarCode();
            Intrinsics.c(barCode3);
            textView.setText(barCode3.getAlternativeText());
            return;
        }
        FullscreenImageBinding fullscreenImageBinding5 = this.K;
        if (fullscreenImageBinding5 == null) {
            Intrinsics.p("binding");
        } else {
            fullscreenImageBinding2 = fullscreenImageBinding5;
        }
        fullscreenImageBinding2.f2654b.setVisibility(8);
    }
}
