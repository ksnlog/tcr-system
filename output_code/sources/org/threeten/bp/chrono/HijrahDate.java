package org.threeten.bp.chrono;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalTime;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class HijrahDate extends ChronoDateImpl<HijrahDate> {
    private static final Long[] A;
    private static final Integer[] B;
    private static final Integer[] C;
    private static final Integer[] D;
    private static final Integer[] E;
    private static final Integer[] F;
    private static final Integer[] G;
    private static final Integer[] H;
    private static final Integer[] I;

    /* renamed from: m  reason: collision with root package name */
    private static final int[] f3141m;

    /* renamed from: n  reason: collision with root package name */
    private static final int[] f3142n = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};

    /* renamed from: o  reason: collision with root package name */
    private static final int[] f3143o = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29};

    /* renamed from: p  reason: collision with root package name */
    private static final int[] f3144p = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 30};

    /* renamed from: q  reason: collision with root package name */
    private static final int[] f3145q = {0, 1, 0, 1, 0, 1, 1};

    /* renamed from: r  reason: collision with root package name */
    private static final int[] f3146r = {1, 9999, 11, 51, 5, 29, 354};

    /* renamed from: s  reason: collision with root package name */
    private static final int[] f3147s = {1, 9999, 11, 52, 6, 30, 355};

    /* renamed from: t  reason: collision with root package name */
    private static final int[] f3148t = {0, 354, 709, 1063, 1417, 1772, 2126, 2481, 2835, 3189, 3544, 3898, 4252, 4607, 4961, 5315, 5670, 6024, 6379, 6733, 7087, 7442, 7796, 8150, 8505, 8859, 9214, 9568, 9922, 10277};

    /* renamed from: u  reason: collision with root package name */
    private static final char f3149u;

    /* renamed from: v  reason: collision with root package name */
    private static final String f3150v;

    /* renamed from: w  reason: collision with root package name */
    private static final String f3151w;

    /* renamed from: x  reason: collision with root package name */
    private static final HashMap<Integer, Integer[]> f3152x;

    /* renamed from: y  reason: collision with root package name */
    private static final HashMap<Integer, Integer[]> f3153y;

    /* renamed from: z  reason: collision with root package name */
    private static final HashMap<Integer, Integer[]> f3154z;

    /* renamed from: e  reason: collision with root package name */
    private final transient HijrahEra f3155e;

    /* renamed from: f  reason: collision with root package name */
    private final transient int f3156f;

    /* renamed from: g  reason: collision with root package name */
    private final transient int f3157g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f3158h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f3159i;

    /* renamed from: j  reason: collision with root package name */
    private final transient DayOfWeek f3160j;

    /* renamed from: k  reason: collision with root package name */
    private final long f3161k;

    /* renamed from: l  reason: collision with root package name */
    private final transient boolean f3162l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.threeten.bp.chrono.HijrahDate$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3163a;

        static {
            int[] iArr = new int[ChronoField.values().length];
            f3163a = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3163a[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3163a[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3163a[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3163a[ChronoField.DAY_OF_WEEK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3163a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3163a[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3163a[ChronoField.EPOCH_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3163a[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3163a[ChronoField.MONTH_OF_YEAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3163a[ChronoField.YEAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3163a[ChronoField.ERA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
        int[] iArr = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};
        f3141m = iArr;
        char c2 = File.separatorChar;
        f3149u = c2;
        f3150v = File.pathSeparator;
        f3151w = "org" + c2 + "threeten" + c2 + "bp" + c2 + "chrono";
        f3152x = new HashMap<>();
        f3153y = new HashMap<>();
        f3154z = new HashMap<>();
        E = new Integer[iArr.length];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr2 = f3141m;
            if (i3 >= iArr2.length) {
                break;
            }
            E[i3] = Integer.valueOf(iArr2[i3]);
            i3++;
        }
        F = new Integer[f3142n.length];
        int i4 = 0;
        while (true) {
            int[] iArr3 = f3142n;
            if (i4 >= iArr3.length) {
                break;
            }
            F[i4] = Integer.valueOf(iArr3[i4]);
            i4++;
        }
        G = new Integer[f3143o.length];
        int i5 = 0;
        while (true) {
            int[] iArr4 = f3143o;
            if (i5 >= iArr4.length) {
                break;
            }
            G[i5] = Integer.valueOf(iArr4[i5]);
            i5++;
        }
        H = new Integer[f3144p.length];
        int i6 = 0;
        while (true) {
            int[] iArr5 = f3144p;
            if (i6 >= iArr5.length) {
                break;
            }
            H[i6] = Integer.valueOf(iArr5[i6]);
            i6++;
        }
        I = new Integer[f3148t.length];
        int i7 = 0;
        while (true) {
            int[] iArr6 = f3148t;
            if (i7 >= iArr6.length) {
                break;
            }
            I[i7] = Integer.valueOf(iArr6[i7]);
            i7++;
        }
        A = new Long[334];
        int i8 = 0;
        while (true) {
            Long[] lArr = A;
            if (i8 >= lArr.length) {
                break;
            }
            lArr[i8] = Long.valueOf(i8 * 10631);
            i8++;
        }
        B = new Integer[f3145q.length];
        int i9 = 0;
        while (true) {
            int[] iArr7 = f3145q;
            if (i9 >= iArr7.length) {
                break;
            }
            B[i9] = Integer.valueOf(iArr7[i9]);
            i9++;
        }
        C = new Integer[f3146r.length];
        int i10 = 0;
        while (true) {
            int[] iArr8 = f3146r;
            if (i10 >= iArr8.length) {
                break;
            }
            C[i10] = Integer.valueOf(iArr8[i10]);
            i10++;
        }
        D = new Integer[f3147s.length];
        while (true) {
            int[] iArr9 = f3147s;
            if (i2 < iArr9.length) {
                D[i2] = Integer.valueOf(iArr9[i2]);
                i2++;
            } else {
                try {
                    o0();
                    return;
                } catch (IOException | ParseException unused) {
                    return;
                }
            }
        }
    }

    private HijrahDate(long j2) {
        int[] T = T(j2);
        H(T[1]);
        G(T[2]);
        E(T[3]);
        F(T[4]);
        this.f3155e = HijrahEra.k(T[0]);
        int i2 = T[1];
        this.f3156f = i2;
        this.f3157g = T[2];
        this.f3158h = T[3];
        this.f3159i = T[4];
        this.f3160j = DayOfWeek.m(T[5]);
        this.f3161k = j2;
        this.f3162l = b0(i2);
    }

    private static void D(int i2, int i3, int i4, int i5, int i6) {
        if (i2 >= 1) {
            if (i4 < 1) {
                throw new IllegalArgumentException("endYear < 1");
            }
            if (i3 < 0 || i3 > 11) {
                throw new IllegalArgumentException("startMonth < 0 || startMonth > 11");
            }
            if (i5 < 0 || i5 > 11) {
                throw new IllegalArgumentException("endMonth < 0 || endMonth > 11");
            }
            if (i4 > 9999) {
                throw new IllegalArgumentException("endYear > 9999");
            }
            if (i4 < i2) {
                throw new IllegalArgumentException("startYear > endYear");
            }
            if (i4 == i2 && i5 < i3) {
                throw new IllegalArgumentException("startYear == endYear && endMonth < startMonth");
            }
            boolean b02 = b0(i2);
            Integer[] numArr = f3152x.get(Integer.valueOf(i2));
            if (numArr == null) {
                if (b02) {
                    numArr = new Integer[f3142n.length];
                    int i7 = 0;
                    while (true) {
                        int[] iArr = f3142n;
                        if (i7 >= iArr.length) {
                            break;
                        }
                        numArr[i7] = Integer.valueOf(iArr[i7]);
                        i7++;
                    }
                } else {
                    numArr = new Integer[f3141m.length];
                    int i8 = 0;
                    while (true) {
                        int[] iArr2 = f3141m;
                        if (i8 >= iArr2.length) {
                            break;
                        }
                        numArr[i8] = Integer.valueOf(iArr2[i8]);
                        i8++;
                    }
                }
            }
            Integer[] numArr2 = new Integer[numArr.length];
            for (int i9 = 0; i9 < 12; i9++) {
                if (i9 > i3) {
                    numArr2[i9] = Integer.valueOf(numArr[i9].intValue() - i6);
                } else {
                    numArr2[i9] = Integer.valueOf(numArr[i9].intValue());
                }
            }
            f3152x.put(Integer.valueOf(i2), numArr2);
            Integer[] numArr3 = f3153y.get(Integer.valueOf(i2));
            if (numArr3 == null) {
                if (b02) {
                    numArr3 = new Integer[f3144p.length];
                    int i10 = 0;
                    while (true) {
                        int[] iArr3 = f3144p;
                        if (i10 >= iArr3.length) {
                            break;
                        }
                        numArr3[i10] = Integer.valueOf(iArr3[i10]);
                        i10++;
                    }
                } else {
                    numArr3 = new Integer[f3143o.length];
                    int i11 = 0;
                    while (true) {
                        int[] iArr4 = f3143o;
                        if (i11 >= iArr4.length) {
                            break;
                        }
                        numArr3[i11] = Integer.valueOf(iArr4[i11]);
                        i11++;
                    }
                }
            }
            Integer[] numArr4 = new Integer[numArr3.length];
            for (int i12 = 0; i12 < 12; i12++) {
                if (i12 == i3) {
                    numArr4[i12] = Integer.valueOf(numArr3[i12].intValue() - i6);
                } else {
                    numArr4[i12] = Integer.valueOf(numArr3[i12].intValue());
                }
            }
            f3153y.put(Integer.valueOf(i2), numArr4);
            if (i2 != i4) {
                int i13 = i2 - 1;
                int i14 = i13 / 30;
                int i15 = i13 % 30;
                Integer[] numArr5 = f3154z.get(Integer.valueOf(i14));
                if (numArr5 == null) {
                    int length = f3148t.length;
                    Integer[] numArr6 = new Integer[length];
                    for (int i16 = 0; i16 < length; i16++) {
                        numArr6[i16] = Integer.valueOf(f3148t[i16]);
                    }
                    numArr5 = numArr6;
                }
                for (int i17 = i15 + 1; i17 < f3148t.length; i17++) {
                    numArr5[i17] = Integer.valueOf(numArr5[i17].intValue() - i6);
                }
                f3154z.put(Integer.valueOf(i14), numArr5);
                int i18 = i4 - 1;
                int i19 = i18 / 30;
                if (i14 != i19) {
                    int i20 = i14 + 1;
                    while (true) {
                        Long[] lArr = A;
                        if (i20 >= lArr.length) {
                            break;
                        }
                        lArr[i20] = Long.valueOf(lArr[i20].longValue() - i6);
                        i20++;
                    }
                    int i21 = i19 + 1;
                    while (true) {
                        Long[] lArr2 = A;
                        if (i21 >= lArr2.length) {
                            break;
                        }
                        lArr2[i21] = Long.valueOf(lArr2[i21].longValue() + i6);
                        i21++;
                        i19 = i19;
                    }
                }
                int i22 = i19;
                int i23 = i18 % 30;
                Integer[] numArr7 = f3154z.get(Integer.valueOf(i22));
                if (numArr7 == null) {
                    int length2 = f3148t.length;
                    Integer[] numArr8 = new Integer[length2];
                    for (int i24 = 0; i24 < length2; i24++) {
                        numArr8[i24] = Integer.valueOf(f3148t[i24]);
                    }
                    numArr7 = numArr8;
                }
                for (int i25 = i23 + 1; i25 < f3148t.length; i25++) {
                    numArr7[i25] = Integer.valueOf(numArr7[i25].intValue() + i6);
                }
                f3154z.put(Integer.valueOf(i22), numArr7);
            }
            boolean b03 = b0(i4);
            Integer[] numArr9 = f3152x.get(Integer.valueOf(i4));
            if (numArr9 == null) {
                if (b03) {
                    numArr9 = new Integer[f3142n.length];
                    int i26 = 0;
                    while (true) {
                        int[] iArr5 = f3142n;
                        if (i26 >= iArr5.length) {
                            break;
                        }
                        numArr9[i26] = Integer.valueOf(iArr5[i26]);
                        i26++;
                    }
                } else {
                    numArr9 = new Integer[f3141m.length];
                    int i27 = 0;
                    while (true) {
                        int[] iArr6 = f3141m;
                        if (i27 >= iArr6.length) {
                            break;
                        }
                        numArr9[i27] = Integer.valueOf(iArr6[i27]);
                        i27++;
                    }
                }
            }
            Integer[] numArr10 = new Integer[numArr9.length];
            for (int i28 = 0; i28 < 12; i28++) {
                if (i28 > i5) {
                    numArr10[i28] = Integer.valueOf(numArr9[i28].intValue() + i6);
                } else {
                    numArr10[i28] = Integer.valueOf(numArr9[i28].intValue());
                }
            }
            f3152x.put(Integer.valueOf(i4), numArr10);
            Integer[] numArr11 = f3153y.get(Integer.valueOf(i4));
            if (numArr11 == null) {
                if (b03) {
                    numArr11 = new Integer[f3144p.length];
                    int i29 = 0;
                    while (true) {
                        int[] iArr7 = f3144p;
                        if (i29 >= iArr7.length) {
                            break;
                        }
                        numArr11[i29] = Integer.valueOf(iArr7[i29]);
                        i29++;
                    }
                } else {
                    numArr11 = new Integer[f3143o.length];
                    int i30 = 0;
                    while (true) {
                        int[] iArr8 = f3143o;
                        if (i30 >= iArr8.length) {
                            break;
                        }
                        numArr11[i30] = Integer.valueOf(iArr8[i30]);
                        i30++;
                    }
                }
            }
            Integer[] numArr12 = new Integer[numArr11.length];
            for (int i31 = 0; i31 < 12; i31++) {
                if (i31 == i5) {
                    numArr12[i31] = Integer.valueOf(numArr11[i31].intValue() + i6);
                } else {
                    numArr12[i31] = Integer.valueOf(numArr11[i31].intValue());
                }
            }
            HashMap<Integer, Integer[]> hashMap = f3153y;
            hashMap.put(Integer.valueOf(i4), numArr12);
            Integer[] numArr13 = hashMap.get(Integer.valueOf(i2));
            Integer[] numArr14 = hashMap.get(Integer.valueOf(i4));
            HashMap<Integer, Integer[]> hashMap2 = f3152x;
            int intValue = numArr13[i3].intValue();
            int intValue2 = numArr14[i5].intValue();
            int intValue3 = hashMap2.get(Integer.valueOf(i2))[11].intValue() + numArr13[11].intValue();
            int intValue4 = hashMap2.get(Integer.valueOf(i4))[11].intValue() + numArr14[11].intValue();
            Integer[] numArr15 = D;
            int intValue5 = numArr15[5].intValue();
            Integer[] numArr16 = C;
            int intValue6 = numArr16[5].intValue();
            if (intValue5 < intValue) {
                intValue5 = intValue;
            }
            if (intValue5 < intValue2) {
                intValue5 = intValue2;
            }
            numArr15[5] = Integer.valueOf(intValue5);
            if (intValue6 <= intValue) {
                intValue = intValue6;
            }
            if (intValue <= intValue2) {
                intValue2 = intValue;
            }
            numArr16[5] = Integer.valueOf(intValue2);
            int intValue7 = numArr15[6].intValue();
            int intValue8 = numArr16[6].intValue();
            if (intValue7 < intValue3) {
                intValue7 = intValue3;
            }
            if (intValue7 < intValue4) {
                intValue7 = intValue4;
            }
            numArr15[6] = Integer.valueOf(intValue7);
            if (intValue8 <= intValue3) {
                intValue3 = intValue8;
            }
            if (intValue3 <= intValue4) {
                intValue4 = intValue3;
            }
            numArr16[6] = Integer.valueOf(intValue4);
            return;
        }
        throw new IllegalArgumentException("startYear < 1");
    }

    private static void E(int i2) {
        if (i2 >= 1 && i2 <= U()) {
            return;
        }
        throw new DateTimeException("Invalid day of month of Hijrah date, day " + i2 + " greater than " + U() + " or less than 1");
    }

    private static void F(int i2) {
        if (i2 >= 1 && i2 <= V()) {
            return;
        }
        throw new DateTimeException("Invalid day of year of Hijrah date");
    }

    private static void G(int i2) {
        if (i2 < 1 || i2 > 12) {
            throw new DateTimeException("Invalid month of Hijrah date");
        }
    }

    private static void H(int i2) {
        if (i2 < 1 || i2 > 9999) {
            throw new DateTimeException("Invalid year of Hijrah Era");
        }
    }

    private static Integer[] I(int i2) {
        Integer[] numArr;
        try {
            numArr = f3154z.get(Integer.valueOf(i2));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            return I;
        }
        return numArr;
    }

    private static Integer[] J(int i2) {
        Integer[] numArr;
        try {
            numArr = f3152x.get(Integer.valueOf(i2));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            if (b0(i2)) {
                return F;
            }
            return E;
        }
        return numArr;
    }

    private static Integer[] K(int i2) {
        Integer[] numArr;
        try {
            numArr = f3153y.get(Integer.valueOf(i2));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            if (b0(i2)) {
                return H;
            }
            return G;
        }
        return numArr;
    }

    private static InputStream M() {
        ZipFile zipFile;
        String property = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigFile");
        if (property == null) {
            property = "hijrah_deviation.cfg";
        }
        String property2 = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigDir");
        if (property2 != null) {
            if (property2.length() != 0 || !property2.endsWith(System.getProperty("file.separator"))) {
                property2 = property2 + System.getProperty("file.separator");
            }
            File file = new File(property2 + f3149u + property);
            if (!file.exists()) {
                return null;
            }
            try {
                return new FileInputStream(file);
            } catch (IOException e2) {
                throw e2;
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.class.path"), f3150v);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            File file2 = new File(nextToken);
            if (file2.exists()) {
                if (file2.isDirectory()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(nextToken);
                    char c2 = f3149u;
                    sb.append(c2);
                    String str = f3151w;
                    sb.append(str);
                    if (new File(sb.toString(), property).exists()) {
                        try {
                            return new FileInputStream(nextToken + c2 + str + c2 + property);
                        } catch (IOException e3) {
                            throw e3;
                        }
                    }
                } else {
                    try {
                        zipFile = new ZipFile(file2);
                    } catch (IOException unused) {
                        zipFile = null;
                    }
                    if (zipFile != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(f3151w);
                        char c3 = f3149u;
                        sb2.append(c3);
                        sb2.append(property);
                        String sb3 = sb2.toString();
                        ZipEntry entry = zipFile.getEntry(sb3);
                        if (entry == null) {
                            if (c3 == '/') {
                                sb3 = sb3.replace('/', '\\');
                            } else if (c3 == '\\') {
                                sb3 = sb3.replace('\\', '/');
                            }
                            entry = zipFile.getEntry(sb3);
                        }
                        if (entry != null) {
                            try {
                                return zipFile.getInputStream(entry);
                            } catch (IOException e4) {
                                throw e4;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static int N(long j2) {
        Long[] lArr = A;
        for (int i2 = 0; i2 < lArr.length; i2++) {
            try {
                if (j2 < lArr[i2].longValue()) {
                    return i2 - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                return ((int) j2) / 10631;
            }
        }
        return ((int) j2) / 10631;
    }

    private static int O(long j2, int i2) {
        Long l2;
        try {
            l2 = A[i2];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l2 = null;
        }
        if (l2 == null) {
            l2 = Long.valueOf(i2 * 10631);
        }
        return (int) (j2 - l2.longValue());
    }

    private static int P(int i2, int i3, int i4) {
        int intValue;
        Integer[] J = J(i4);
        if (i2 >= 0) {
            if (i3 > 0) {
                intValue = J[i3].intValue();
            } else {
                return i2;
            }
        } else {
            if (b0(i4)) {
                i2 += 355;
            } else {
                i2 += 354;
            }
            if (i3 > 0) {
                intValue = J[i3].intValue();
            } else {
                return i2;
            }
        }
        return i2 - intValue;
    }

    private static int Q(int i2, int i3, int i4) {
        Integer[] I2 = I(i2);
        if (i3 > 0) {
            return i3 - I2[i4].intValue();
        }
        return I2[i4].intValue() + i3;
    }

    private static long S(int i2, int i3, int i4) {
        return u0(i2) + W(i3 - 1, i2) + i4;
    }

    private static int[] T(long j2) {
        int i2;
        int Y;
        int P;
        int value;
        int i3;
        int i4;
        int i5;
        long j3 = j2 - (-492148);
        if (j3 >= 0) {
            int N = N(j3);
            int O = O(j3, N);
            int Z = Z(N, O);
            i3 = Q(N, O, Z);
            i4 = (N * 30) + Z + 1;
            Y = Y(i3, i4);
            P = P(i3, Y, i4) + 1;
            value = HijrahEra.AH.getValue();
        } else {
            int i6 = (int) j3;
            int i7 = i6 / 10631;
            int i8 = i6 % 10631;
            if (i8 == 0) {
                i7++;
                i8 = -10631;
            }
            int Z2 = Z(i7, i8);
            int Q = Q(i7, i8, Z2);
            int i9 = 1 - ((i7 * 30) - Z2);
            if (b0(i9)) {
                i2 = Q + 355;
            } else {
                i2 = Q + 354;
            }
            Y = Y(i2, i9);
            P = P(i2, Y, i9) + 1;
            value = HijrahEra.BEFORE_AH.getValue();
            i3 = i2;
            i4 = i9;
        }
        int i10 = (int) ((j3 + 5) % 7);
        if (i10 <= 0) {
            i5 = 7;
        } else {
            i5 = 0;
        }
        return new int[]{value, i4, Y + 1, P, i3 + 1, i10 + i5};
    }

    static int U() {
        return D[5].intValue();
    }

    static int V() {
        return D[6].intValue();
    }

    private static int W(int i2, int i3) {
        return J(i3)[i2].intValue();
    }

    static int X(int i2, int i3) {
        return K(i3)[i2].intValue();
    }

    private static int Y(int i2, int i3) {
        int i4;
        Integer[] J = J(i3);
        int i5 = 0;
        if (i2 >= 0) {
            while (i5 < J.length) {
                if (i2 < J[i5].intValue()) {
                    return i5 - 1;
                }
                i5++;
            }
            return 11;
        }
        if (b0(i3)) {
            i4 = i2 + 355;
        } else {
            i4 = i2 + 354;
        }
        while (i5 < J.length) {
            if (i4 < J[i5].intValue()) {
                return i5 - 1;
            }
            i5++;
        }
        return 11;
    }

    private static int Z(int i2, long j2) {
        Integer[] I2 = I(i2);
        int i3 = 0;
        if (j2 == 0) {
            return 0;
        }
        if (j2 > 0) {
            while (i3 < I2.length) {
                if (j2 < I2[i3].intValue()) {
                    return i3 - 1;
                }
                i3++;
            }
            return 29;
        }
        long j3 = -j2;
        while (i3 < I2.length) {
            if (j3 <= I2[i3].intValue()) {
                return i3 - 1;
            }
            i3++;
        }
        return 29;
    }

    static int a0(int i2) {
        Integer[] numArr;
        int i3 = i2 - 1;
        int i4 = i3 / 30;
        try {
            numArr = f3154z.get(Integer.valueOf(i4));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr != null) {
            int i5 = i3 % 30;
            if (i5 == 29) {
                Long[] lArr = A;
                return (lArr[i4 + 1].intValue() - lArr[i4].intValue()) - numArr[i5].intValue();
            }
            return numArr[i5 + 1].intValue() - numArr[i5].intValue();
        } else if (b0(i2)) {
            return 355;
        } else {
            return 354;
        }
    }

    static boolean b0(long j2) {
        if (j2 <= 0) {
            j2 = -j2;
        }
        return ((j2 * 11) + 14) % 30 < 11;
    }

    public static HijrahDate f0(int i2, int i3, int i4) {
        if (i2 >= 1) {
            return g0(HijrahEra.AH, i2, i3, i4);
        }
        return g0(HijrahEra.BEFORE_AH, 1 - i2, i3, i4);
    }

    static HijrahDate g0(HijrahEra hijrahEra, int i2, int i3, int i4) {
        Jdk8Methods.i(hijrahEra, "era");
        H(i2);
        G(i3);
        E(i4);
        return new HijrahDate(S(hijrahEra.m(i2), i3, i4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HijrahDate h0(long j2) {
        return new HijrahDate(j2);
    }

    private static void i0(String str, int i2) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(58);
            if (indexOf != -1) {
                try {
                    int parseInt = Integer.parseInt(nextToken.substring(indexOf + 1, nextToken.length()));
                    int indexOf2 = nextToken.indexOf(45);
                    if (indexOf2 != -1) {
                        String substring = nextToken.substring(0, indexOf2);
                        String substring2 = nextToken.substring(indexOf2 + 1, indexOf);
                        int indexOf3 = substring.indexOf(47);
                        int indexOf4 = substring2.indexOf(47);
                        if (indexOf3 != -1) {
                            String substring3 = substring.substring(0, indexOf3);
                            String substring4 = substring.substring(indexOf3 + 1, substring.length());
                            try {
                                int parseInt2 = Integer.parseInt(substring3);
                                try {
                                    int parseInt3 = Integer.parseInt(substring4);
                                    if (indexOf4 != -1) {
                                        String substring5 = substring2.substring(0, indexOf4);
                                        String substring6 = substring2.substring(indexOf4 + 1, substring2.length());
                                        try {
                                            int parseInt4 = Integer.parseInt(substring5);
                                            try {
                                                int parseInt5 = Integer.parseInt(substring6);
                                                if (parseInt2 != -1 && parseInt3 != -1 && parseInt4 != -1 && parseInt5 != -1) {
                                                    D(parseInt2, parseInt3, parseInt4, parseInt5, parseInt);
                                                } else {
                                                    throw new ParseException("Unknown error at line " + i2 + ".", i2);
                                                }
                                            } catch (NumberFormatException unused) {
                                                throw new ParseException("End month is not properly set at line " + i2 + ".", i2);
                                            }
                                        } catch (NumberFormatException unused2) {
                                            throw new ParseException("End year is not properly set at line " + i2 + ".", i2);
                                        }
                                    } else {
                                        throw new ParseException("End year/month has incorrect format at line " + i2 + ".", i2);
                                    }
                                } catch (NumberFormatException unused3) {
                                    throw new ParseException("Start month is not properly set at line " + i2 + ".", i2);
                                }
                            } catch (NumberFormatException unused4) {
                                throw new ParseException("Start year is not properly set at line " + i2 + ".", i2);
                            }
                        } else {
                            throw new ParseException("Start year/month has incorrect format at line " + i2 + ".", i2);
                        }
                    } else {
                        throw new ParseException("Start and end year/month has incorrect format at line " + i2 + ".", i2);
                    }
                } catch (NumberFormatException unused5) {
                    throw new ParseException("Offset is not properly set at line " + i2 + ".", i2);
                }
            } else {
                throw new ParseException("Offset has incorrect format at line " + i2 + ".", i2);
            }
        }
    }

    private static void o0() {
        InputStream M = M();
        if (M != null) {
            BufferedReader bufferedReader = null;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(M));
                int i2 = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            i2++;
                            i0(readLine.trim(), i2);
                        } else {
                            bufferedReader2.close();
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChronoLocalDate p0(DataInput dataInput) {
        return HijrahChronology.f3137h.s(dataInput.readInt(), dataInput.readByte(), dataInput.readByte());
    }

    private static HijrahDate q0(int i2, int i3, int i4) {
        int W = W(i3 - 1, i2);
        if (i4 > W) {
            i4 = W;
        }
        return f0(i2, i3, i4);
    }

    private Object readResolve() {
        return new HijrahDate(this.f3161k);
    }

    private static long u0(int i2) {
        Long l2;
        int i3 = i2 - 1;
        int i4 = i3 / 30;
        int i5 = i3 % 30;
        int intValue = I(i4)[Math.abs(i5)].intValue();
        if (i5 < 0) {
            intValue = -intValue;
        }
        try {
            l2 = A[i4];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l2 = null;
        }
        if (l2 == null) {
            l2 = Long.valueOf(i4 * 10631);
        }
        return ((l2.longValue() + intValue) - 492148) - 1;
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: L */
    public HijrahChronology p() {
        return HijrahChronology.f3137h;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: R */
    public HijrahEra q() {
        return this.f3155e;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange a(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (d(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i2 = AnonymousClass1.f3163a[chronoField.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                return p().v(chronoField);
                            }
                            return ValueRange.i(1L, 1000L);
                        }
                        return ValueRange.i(1L, 5L);
                    }
                    return ValueRange.i(1L, d0());
                }
                return ValueRange.i(1L, c0());
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.d(this);
    }

    public int c0() {
        return X(this.f3157g - 1, this.f3156f);
    }

    public int d0() {
        return a0(this.f3156f);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: e0 */
    public HijrahDate t(long j2, TemporalUnit temporalUnit) {
        return (HijrahDate) super.g(j2, temporalUnit);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long h(TemporalField temporalField) {
        int i2;
        int i3;
        if (temporalField instanceof ChronoField) {
            switch (AnonymousClass1.f3163a[((ChronoField) temporalField).ordinal()]) {
                case 1:
                    i2 = this.f3158h;
                    break;
                case 2:
                    i2 = this.f3159i;
                    break;
                case 3:
                    i3 = (this.f3158h - 1) / 7;
                    i2 = i3 + 1;
                    break;
                case 4:
                    i2 = this.f3156f;
                    break;
                case 5:
                    i2 = this.f3160j.getValue();
                    break;
                case R$styleable.f1342r /* 6 */:
                    i3 = (this.f3158h - 1) % 7;
                    i2 = i3 + 1;
                    break;
                case R$styleable.f1343s /* 7 */:
                    i3 = (this.f3159i - 1) % 7;
                    i2 = i3 + 1;
                    break;
                case R$styleable.f1328d /* 8 */:
                    return w();
                case R$styleable.f1329e /* 9 */:
                    i3 = (this.f3159i - 1) / 7;
                    i2 = i3 + 1;
                    break;
                case R$styleable.f1330f /* 10 */:
                    i2 = this.f3157g;
                    break;
                case R$styleable.f1331g /* 11 */:
                    i2 = this.f3156f;
                    break;
                case R$styleable.f1332h /* 12 */:
                    i2 = this.f3155e.getValue();
                    break;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
            return i2;
        }
        return temporalField.c(this);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: j0 */
    public HijrahDate z(long j2, TemporalUnit temporalUnit) {
        return (HijrahDate) super.u(j2, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: k0 */
    public HijrahDate v(TemporalAmount temporalAmount) {
        return (HijrahDate) super.v(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long l(Temporal temporal, TemporalUnit temporalUnit) {
        return super.l(temporal, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: l0 */
    public HijrahDate A(long j2) {
        return new HijrahDate(this.f3161k + j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: m0 */
    public HijrahDate B(long j2) {
        if (j2 == 0) {
            return this;
        }
        int i2 = (this.f3157g - 1) + ((int) j2);
        int i3 = i2 / 12;
        int i4 = i2 % 12;
        while (i4 < 0) {
            i4 += 12;
            i3 = Jdk8Methods.n(i3, 1);
        }
        return g0(this.f3155e, Jdk8Methods.j(this.f3156f, i3), i4 + 1, this.f3158h);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<HijrahDate> n(LocalTime localTime) {
        return super.n(localTime);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    /* renamed from: n0 */
    public HijrahDate C(long j2) {
        if (j2 == 0) {
            return this;
        }
        return g0(this.f3155e, Jdk8Methods.j(this.f3156f, (int) j2), this.f3157g, this.f3158h);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: r0 */
    public HijrahDate x(TemporalAdjuster temporalAdjuster) {
        return (HijrahDate) super.c(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    /* renamed from: s0 */
    public HijrahDate y(TemporalField temporalField, long j2) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.h(j2);
            int i2 = (int) j2;
            switch (AnonymousClass1.f3163a[chronoField.ordinal()]) {
                case 1:
                    return q0(this.f3156f, this.f3157g, i2);
                case 2:
                    int i3 = i2 - 1;
                    return q0(this.f3156f, (i3 / 30) + 1, (i3 % 30) + 1);
                case 3:
                    return A((j2 - h(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7);
                case 4:
                    if (this.f3156f < 1) {
                        i2 = 1 - i2;
                    }
                    return q0(i2, this.f3157g, this.f3158h);
                case 5:
                    return A(j2 - this.f3160j.getValue());
                case R$styleable.f1342r /* 6 */:
                    return A(j2 - h(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case R$styleable.f1343s /* 7 */:
                    return A(j2 - h(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case R$styleable.f1328d /* 8 */:
                    return new HijrahDate(i2);
                case R$styleable.f1329e /* 9 */:
                    return A((j2 - h(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7);
                case R$styleable.f1330f /* 10 */:
                    return q0(this.f3156f, i2, this.f3158h);
                case R$styleable.f1331g /* 11 */:
                    return q0(i2, this.f3157g, this.f3158h);
                case R$styleable.f1332h /* 12 */:
                    return q0(1 - this.f3156f, this.f3157g, this.f3158h);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
        }
        return (HijrahDate) temporalField.b(this, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t0(DataOutput dataOutput) {
        dataOutput.writeInt(f(ChronoField.YEAR));
        dataOutput.writeByte(f(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(f(ChronoField.DAY_OF_MONTH));
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long w() {
        return S(this.f3156f, this.f3157g, this.f3158h);
    }
}
