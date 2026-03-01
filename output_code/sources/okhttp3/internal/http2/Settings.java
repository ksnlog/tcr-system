package okhttp3.internal.http2;

import java.util.Arrays;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Settings {

    /* renamed from: a  reason: collision with root package name */
    private int f2390a;

    /* renamed from: b  reason: collision with root package name */
    private final int[] f2391b = new int[10];

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.f2390a = 0;
        Arrays.fill(this.f2391b, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(int i2) {
        return this.f2391b[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        if ((this.f2390a & 2) != 0) {
            return this.f2391b[1];
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        if ((this.f2390a & 128) != 0) {
            return this.f2391b[7];
        }
        return 65535;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(int i2) {
        return (this.f2390a & 16) != 0 ? this.f2391b[4] : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(int i2) {
        return (this.f2390a & 32) != 0 ? this.f2391b[5] : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(int i2) {
        return ((1 << i2) & this.f2390a) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Settings settings) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (settings.g(i2)) {
                i(i2, settings.b(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Settings i(int i2, int i3) {
        if (i2 >= 0) {
            int[] iArr = this.f2391b;
            if (i2 < iArr.length) {
                this.f2390a = (1 << i2) | this.f2390a;
                iArr[i2] = i3;
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return Integer.bitCount(this.f2390a);
    }
}
