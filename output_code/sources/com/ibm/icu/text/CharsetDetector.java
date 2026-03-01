package com.ibm.icu.text;

import com.ibm.icu.text.CharsetRecog_2022;
import com.ibm.icu.text.CharsetRecog_Unicode;
import com.ibm.icu.text.CharsetRecog_mbcs;
import com.ibm.icu.text.CharsetRecog_sbcs;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CharsetDetector {

    /* renamed from: j  reason: collision with root package name */
    private static final List<CSRecognizerInfo> f54j;

    /* renamed from: b  reason: collision with root package name */
    int f56b;

    /* renamed from: e  reason: collision with root package name */
    byte[] f59e;

    /* renamed from: f  reason: collision with root package name */
    int f60f;

    /* renamed from: g  reason: collision with root package name */
    InputStream f61g;

    /* renamed from: i  reason: collision with root package name */
    private boolean[] f63i;

    /* renamed from: a  reason: collision with root package name */
    byte[] f55a = new byte[8000];

    /* renamed from: c  reason: collision with root package name */
    short[] f57c = new short[256];

    /* renamed from: d  reason: collision with root package name */
    boolean f58d = false;

    /* renamed from: h  reason: collision with root package name */
    private boolean f62h = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class CSRecognizerInfo {

        /* renamed from: a  reason: collision with root package name */
        CharsetRecognizer f64a;

        /* renamed from: b  reason: collision with root package name */
        boolean f65b;

        CSRecognizerInfo(CharsetRecognizer charsetRecognizer, boolean z2) {
            this.f64a = charsetRecognizer;
            this.f65b = z2;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_UTF8(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_16_BE(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_16_LE(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_32_BE(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_32_LE(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_sjis(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022JP(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022CN(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022KR(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_gb_18030(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_euc.CharsetRecog_euc_jp(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_euc.CharsetRecog_euc_kr(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_big5(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_1(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_2(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_5_ru(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_6_ar(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_7_el(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_8_I_he(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_8_he(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_windows_1251(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_windows_1256(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_KOI8_R(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_9_tr(), true));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM424_he_rtl(), false));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM424_he_ltr(), false));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM420_ar_rtl(), false));
        arrayList.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM420_ar_ltr(), false));
        f54j = Collections.unmodifiableList(arrayList);
    }

    private void a() {
        int i2;
        int i3;
        if (this.f62h) {
            int i4 = 0;
            i2 = 0;
            i3 = 0;
            boolean z2 = false;
            for (int i5 = 0; i5 < this.f60f; i5++) {
                byte[] bArr = this.f55a;
                if (i4 >= bArr.length) {
                    break;
                }
                byte b2 = this.f59e[i5];
                if (b2 == 60) {
                    if (z2) {
                        i3++;
                    }
                    i2++;
                    z2 = true;
                }
                if (!z2) {
                    bArr[i4] = b2;
                    i4++;
                }
                if (b2 == 62) {
                    z2 = false;
                }
            }
            this.f56b = i4;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (i2 < 5 || i2 / 5 < i3 || (this.f56b < 100 && this.f60f > 600)) {
            int i6 = this.f60f;
            if (i6 > 8000) {
                i6 = 8000;
            }
            int i7 = 0;
            while (i7 < i6) {
                this.f55a[i7] = this.f59e[i7];
                i7++;
            }
            this.f56b = i7;
        }
        Arrays.fill(this.f57c, (short) 0);
        for (int i8 = 0; i8 < this.f56b; i8++) {
            int i9 = this.f55a[i8] & 255;
            short[] sArr = this.f57c;
            sArr[i9] = (short) (sArr[i9] + 1);
        }
        this.f58d = false;
        for (int i10 = 128; i10 <= 159; i10++) {
            if (this.f57c[i10] != 0) {
                this.f58d = true;
                return;
            }
        }
    }

    public CharsetMatch b() {
        CharsetMatch[] c2 = c();
        if (c2 != null && c2.length != 0) {
            return c2[0];
        }
        return null;
    }

    public CharsetMatch[] c() {
        boolean z2;
        CharsetMatch c2;
        ArrayList arrayList = new ArrayList();
        a();
        int i2 = 0;
        while (true) {
            List<CSRecognizerInfo> list = f54j;
            if (i2 < list.size()) {
                CSRecognizerInfo cSRecognizerInfo = list.get(i2);
                boolean[] zArr = this.f63i;
                if (zArr != null) {
                    z2 = zArr[i2];
                } else {
                    z2 = cSRecognizerInfo.f65b;
                }
                if (z2 && (c2 = cSRecognizerInfo.f64a.c(this)) != null) {
                    arrayList.add(c2);
                }
                i2++;
            } else {
                Collections.sort(arrayList);
                Collections.reverse(arrayList);
                return (CharsetMatch[]) arrayList.toArray(new CharsetMatch[arrayList.size()]);
            }
        }
    }

    public CharsetDetector d(byte[] bArr) {
        this.f59e = bArr;
        this.f60f = bArr.length;
        return this;
    }
}
