package org.ligi.passandroid.model.pass;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import com.squareup.moshi.JsonClass;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.functions.BarcodeKt;
import timber.log.Timber;
@JsonClass(generateAdapter = true)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BarCode implements KoinComponent {
    public static final Companion Companion = new Companion(null);
    private String alternativeText;
    private final PassBarCodeFormat format;
    private final String message;
    private final Lazy tracker$delegate;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PassBarCodeFormat getFormatFromString(String format) {
            boolean p2;
            boolean p3;
            boolean p4;
            boolean p5;
            Intrinsics.f(format, "format");
            p2 = StringsKt__StringsKt.p(format, "417", false, 2, null);
            if (p2) {
                return PassBarCodeFormat.PDF_417;
            }
            Locale ENGLISH = Locale.ENGLISH;
            Intrinsics.e(ENGLISH, "ENGLISH");
            String upperCase = format.toUpperCase(ENGLISH);
            Intrinsics.e(upperCase, "this as java.lang.String).toUpperCase(locale)");
            p3 = StringsKt__StringsKt.p(upperCase, "AZTEC", false, 2, null);
            if (p3) {
                return PassBarCodeFormat.AZTEC;
            }
            Intrinsics.e(ENGLISH, "ENGLISH");
            String upperCase2 = format.toUpperCase(ENGLISH);
            Intrinsics.e(upperCase2, "this as java.lang.String).toUpperCase(locale)");
            p4 = StringsKt__StringsKt.p(upperCase2, "128", false, 2, null);
            if (p4) {
                return PassBarCodeFormat.CODE_128;
            }
            Intrinsics.e(ENGLISH, "ENGLISH");
            String upperCase3 = format.toUpperCase(ENGLISH);
            Intrinsics.e(upperCase3, "this as java.lang.String).toUpperCase(locale)");
            p5 = StringsKt__StringsKt.p(upperCase3, "39", false, 2, null);
            if (p5) {
                return PassBarCodeFormat.CODE_39;
            }
            return PassBarCodeFormat.QR_CODE;
        }
    }

    public BarCode(PassBarCodeFormat passBarCodeFormat, String str) {
        Lazy a2;
        this.format = passBarCodeFormat;
        this.message = str;
        a2 = LazyKt__LazyJVMKt.a(KoinPlatformTools.f2602a.b(), new BarCode$special$$inlined$inject$default$1(this, null, null));
        this.tracker$delegate = a2;
    }

    public final String getAlternativeText() {
        return this.alternativeText;
    }

    public final BitmapDrawable getBitmap(Resources resources) {
        Intrinsics.f(resources, "resources");
        String str = this.message;
        if (str == null) {
            getTracker().c("No Barcode in pass - strange", false);
            return null;
        }
        PassBarCodeFormat passBarCodeFormat = this.format;
        if (passBarCodeFormat == null) {
            Timber.f3479a.k("Barcode format is null - fallback to QR", new Object[0]);
            getTracker().c("Barcode format is null - fallback to QR", false);
            return BarcodeKt.b(resources, this.message, PassBarCodeFormat.QR_CODE);
        }
        return BarcodeKt.b(resources, str, passBarCodeFormat);
    }

    public final PassBarCodeFormat getFormat() {
        return this.format;
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }

    public final String getMessage() {
        return this.message;
    }

    public final Tracker getTracker() {
        return (Tracker) this.tracker$delegate.getValue();
    }

    public final void setAlternativeText(String str) {
        this.alternativeText = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ BarCode(org.ligi.passandroid.model.pass.PassBarCodeFormat r1, java.lang.String r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L21
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "randomUUID().toString()"
            kotlin.jvm.internal.Intrinsics.e(r2, r3)
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r4 = "ROOT"
            kotlin.jvm.internal.Intrinsics.e(r3, r4)
            java.lang.String r2 = r2.toUpperCase(r3)
            java.lang.String r3 = "this as java.lang.String).toUpperCase(locale)"
            kotlin.jvm.internal.Intrinsics.e(r2, r3)
        L21:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.passandroid.model.pass.BarCode.<init>(org.ligi.passandroid.model.pass.PassBarCodeFormat, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
