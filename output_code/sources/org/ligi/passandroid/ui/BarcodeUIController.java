package org.ligi.passandroid.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.WindowManagerExtensionsKt;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.PassBarCodeFormat;
import org.ligi.passandroid.ui.BarcodeUIController;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BarcodeUIController {

    /* renamed from: a  reason: collision with root package name */
    private final View f2731a;

    /* renamed from: b  reason: collision with root package name */
    private final BarCode f2732b;

    /* renamed from: c  reason: collision with root package name */
    private final PassViewHelper f2733c;

    /* renamed from: d  reason: collision with root package name */
    private int f2734d;

    /* renamed from: e  reason: collision with root package name */
    private final AppCompatImageView f2735e;

    /* renamed from: f  reason: collision with root package name */
    private final AppCompatImageView f2736f;

    /* renamed from: g  reason: collision with root package name */
    private final ImageView f2737g;

    /* renamed from: h  reason: collision with root package name */
    private final TextView f2738h;

    public BarcodeUIController(View rootView, BarCode barCode, Activity activity, PassViewHelper passViewHelper) {
        Intrinsics.f(rootView, "rootView");
        Intrinsics.f(activity, "activity");
        Intrinsics.f(passViewHelper, "passViewHelper");
        this.f2731a = rootView;
        this.f2732b = barCode;
        this.f2733c = passViewHelper;
        AppCompatImageView findViewById = rootView.findViewById(2131296813);
        this.f2735e = findViewById;
        AppCompatImageView findViewById2 = rootView.findViewById(2131296814);
        this.f2736f = findViewById2;
        ImageView barcodeImage = (ImageView) rootView.findViewById(2131296352);
        this.f2737g = barcodeImage;
        TextView textView = (TextView) rootView.findViewById(2131296351);
        this.f2738h = textView;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: e0.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeUIController.c(BarcodeUIController.this, view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: e0.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeUIController.d(BarcodeUIController.this, view);
            }
        });
        if (barCode != null) {
            WindowManager windowManager = activity.getWindowManager();
            Intrinsics.e(windowManager, "activity.windowManager");
            int b2 = WindowManagerExtensionsKt.b(windowManager);
            Resources resources = activity.getResources();
            Intrinsics.e(resources, "activity.resources");
            BitmapDrawable bitmap = barCode.getBitmap(resources);
            if (bitmap != null) {
                barcodeImage.setImageDrawable(bitmap);
                barcodeImage.setVisibility(0);
            } else {
                barcodeImage.setVisibility(8);
            }
            if (barCode.getAlternativeText() != null) {
                textView.setText(barCode.getAlternativeText());
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            f(b2 / 2);
            return;
        }
        Intrinsics.e(barcodeImage, "barcodeImage");
        passViewHelper.e(barcodeImage, null);
        findViewById.setVisibility(8);
        findViewById2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(BarcodeUIController this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        this$0.f(this$0.f2734d + this$0.f2733c.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(BarcodeUIController this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        this$0.f(this$0.f2734d - this$0.f2733c.b());
    }

    private final void f(int i2) {
        int i3;
        int i4;
        AppCompatImageView appCompatImageView = this.f2736f;
        if (i2 < this.f2733c.b() * 2) {
            i3 = 4;
        } else {
            i3 = 0;
        }
        appCompatImageView.setVisibility(i3);
        if (i2 > this.f2733c.d() - (this.f2733c.b() * 2)) {
            this.f2735e.setVisibility(4);
        } else {
            this.f2735e.setVisibility(0);
        }
        this.f2734d = i2;
        BarCode barCode = this.f2732b;
        Intrinsics.c(barCode);
        PassBarCodeFormat format = barCode.getFormat();
        Intrinsics.c(format);
        boolean isQuadratic = format.isQuadratic();
        ImageView imageView = this.f2737g;
        if (isQuadratic) {
            i4 = i2;
        } else {
            i4 = -2;
        }
        imageView.setLayoutParams(new FrameLayout.LayoutParams(i2, i4));
    }

    public final ImageView e() {
        View findViewById = this.f2731a.findViewById(2131296352);
        Intrinsics.e(findViewById, "rootView.findViewById(R.id.barcode_img)");
        return (ImageView) findViewById;
    }
}
