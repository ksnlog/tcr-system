package org.ligi.passandroid.functions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.PassBarCodeFormat;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BarcodeKt {
    public static final Bitmap a(String data, PassBarCodeFormat type) {
        boolean z2;
        int e2;
        int i2;
        int i3;
        Intrinsics.f(data, "data");
        Intrinsics.f(type, "type");
        boolean z3 = true;
        if (data.length() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return null;
        }
        try {
            BitMatrix c2 = c(data, type);
            if (c2.e() != 1) {
                z3 = false;
            }
            int f2 = c2.f();
            if (z3) {
                e2 = f2 / 5;
            } else {
                e2 = c2.e();
            }
            Bitmap createBitmap = Bitmap.createBitmap(f2, e2, Bitmap.Config.RGB_565);
            for (int i4 = 0; i4 < e2; i4++) {
                for (int i5 = 0; i5 < f2; i5++) {
                    if (z3) {
                        i2 = 0;
                    } else {
                        i2 = i4;
                    }
                    if (c2.d(i5, i2)) {
                        i3 = 0;
                    } else {
                        i3 = 16777215;
                    }
                    createBitmap.setPixel(i5, i4, i3);
                }
            }
            return createBitmap;
        } catch (ArrayIndexOutOfBoundsException e3) {
            Timber.Forest forest = Timber.f3479a;
            forest.k("could not write image: " + e3, new Object[0]);
            return null;
        } catch (IllegalArgumentException e4) {
            Timber.Forest forest2 = Timber.f3479a;
            forest2.k("could not write image: " + e4, new Object[0]);
            return null;
        } catch (WriterException e5) {
            Timber.f3479a.l(e5, "could not write image", new Object[0]);
            return null;
        }
    }

    public static final BitmapDrawable b(Resources resources, String data, PassBarCodeFormat type) {
        Intrinsics.f(resources, "resources");
        Intrinsics.f(data, "data");
        Intrinsics.f(type, "type");
        Bitmap a2 = a(data, type);
        if (a2 == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, a2);
        bitmapDrawable.setFilterBitmap(false);
        bitmapDrawable.setAntiAlias(false);
        return bitmapDrawable;
    }

    public static final BitMatrix c(String data, PassBarCodeFormat type) {
        Intrinsics.f(data, "data");
        Intrinsics.f(type, "type");
        BitMatrix b2 = new MultiFormatWriter().b(data, type.zxingBarCodeFormat(), 0, 0);
        Intrinsics.c(b2);
        return b2;
    }
}
