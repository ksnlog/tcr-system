package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum AesVersion {
    ONE(1),
    TWO(2);
    

    /* renamed from: d  reason: collision with root package name */
    private int f1637d;

    AesVersion(int i2) {
        this.f1637d = i2;
    }

    public static AesVersion a(int i2) {
        AesVersion[] values;
        for (AesVersion aesVersion : values()) {
            if (aesVersion.f1637d == i2) {
                return aesVersion;
            }
        }
        throw new ZipException("Unsupported Aes version");
    }

    public int b() {
        return this.f1637d;
    }
}
