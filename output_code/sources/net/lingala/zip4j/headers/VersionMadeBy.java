package net.lingala.zip4j.headers;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum VersionMadeBy {
    SPECIFICATION_VERSION((byte) 51),
    WINDOWS((byte) 0),
    UNIX((byte) 3);
    

    /* renamed from: d  reason: collision with root package name */
    private final byte f1433d;

    VersionMadeBy(byte b2) {
        this.f1433d = b2;
    }

    public byte a() {
        return this.f1433d;
    }
}
