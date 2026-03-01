package net.lingala.zip4j.model.enums;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum AesKeyStrength {
    KEY_STRENGTH_128(1, 8, 16, 16),
    KEY_STRENGTH_192(2, 12, 24, 24),
    KEY_STRENGTH_256(3, 16, 32, 32);
    

    /* renamed from: d  reason: collision with root package name */
    private int f1630d;

    /* renamed from: e  reason: collision with root package name */
    private int f1631e;

    /* renamed from: f  reason: collision with root package name */
    private int f1632f;

    /* renamed from: g  reason: collision with root package name */
    private int f1633g;

    AesKeyStrength(int i2, int i3, int i4, int i5) {
        this.f1630d = i2;
        this.f1631e = i3;
        this.f1632f = i4;
        this.f1633g = i5;
    }

    public static AesKeyStrength a(int i2) {
        AesKeyStrength[] values;
        for (AesKeyStrength aesKeyStrength : values()) {
            if (aesKeyStrength.d() == i2) {
                return aesKeyStrength;
            }
        }
        return null;
    }

    public int b() {
        return this.f1633g;
    }

    public int c() {
        return this.f1632f;
    }

    public int d() {
        return this.f1630d;
    }

    public int e() {
        return this.f1631e;
    }
}
