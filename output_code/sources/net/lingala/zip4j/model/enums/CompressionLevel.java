package net.lingala.zip4j.model.enums;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum CompressionLevel {
    NO_COMPRESSION(0),
    FASTEST(1),
    FASTER(2),
    FAST(3),
    MEDIUM_FAST(4),
    NORMAL(5),
    HIGHER(6),
    MAXIMUM(7),
    PRE_ULTRA(8),
    ULTRA(9);
    

    /* renamed from: d  reason: collision with root package name */
    private final int f1649d;

    CompressionLevel(int i2) {
        this.f1649d = i2;
    }

    public int a() {
        return this.f1649d;
    }
}
