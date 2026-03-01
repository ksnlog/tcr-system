package net.lingala.zip4j.headers;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum VersionNeededToExtract {
    DEFAULT(10),
    DEFLATE_COMPRESSED(20),
    ZIP_64_FORMAT(45),
    AES_ENCRYPTED(51);
    

    /* renamed from: d  reason: collision with root package name */
    private final int f1439d;

    VersionNeededToExtract(int i2) {
        this.f1439d = i2;
    }

    public int a() {
        return this.f1439d;
    }
}
