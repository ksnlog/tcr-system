package org.ligi.passandroid.model.pass;

import com.google.zxing.BarcodeFormat;
import kotlin.NoWhenBranchMatchedException;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum PassBarCodeFormat {
    AZTEC,
    CODE_39,
    CODE_128,
    DATA_MATRIX,
    EAN_8,
    EAN_13,
    ITF,
    PDF_417,
    QR_CODE;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PassBarCodeFormat.values().length];
            try {
                iArr[PassBarCodeFormat.QR_CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PassBarCodeFormat.AZTEC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PassBarCodeFormat.CODE_39.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PassBarCodeFormat.CODE_128.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PassBarCodeFormat.DATA_MATRIX.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PassBarCodeFormat.EAN_8.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PassBarCodeFormat.EAN_13.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[PassBarCodeFormat.ITF.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[PassBarCodeFormat.PDF_417.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final boolean isQuadratic() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        return i2 == 1 || i2 == 2;
    }

    public final BarcodeFormat zxingBarCodeFormat() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return BarcodeFormat.o;
            case 2:
                return BarcodeFormat.d;
            case 3:
                return BarcodeFormat.f;
            case 4:
                return BarcodeFormat.h;
            case 5:
                return BarcodeFormat.i;
            case R$styleable.f1342r /* 6 */:
                return BarcodeFormat.j;
            case R$styleable.f1343s /* 7 */:
                return BarcodeFormat.k;
            case R$styleable.f1328d /* 8 */:
                return BarcodeFormat.l;
            case R$styleable.f1329e /* 9 */:
                return BarcodeFormat.n;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
