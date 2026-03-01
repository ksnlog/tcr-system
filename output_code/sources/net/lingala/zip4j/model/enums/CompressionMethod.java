package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum CompressionMethod {
    STORE(0),
    DEFLATE(8),
    AES_INTERNAL_ONLY(99);
    

    /* renamed from: d  reason: collision with root package name */
    private int f1654d;

    CompressionMethod(int i2) {
        this.f1654d = i2;
    }

    public static CompressionMethod b(int i2) {
        CompressionMethod[] values;
        for (CompressionMethod compressionMethod : values()) {
            if (compressionMethod.a() == i2) {
                return compressionMethod;
            }
        }
        throw new ZipException("Unknown compression method", ZipException.Type.UNKNOWN_COMPRESSION_METHOD);
    }

    public int a() {
        return this.f1654d;
    }
}
