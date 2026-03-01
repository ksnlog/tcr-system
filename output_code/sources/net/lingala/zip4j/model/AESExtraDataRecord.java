package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AESExtraDataRecord extends ZipHeader {

    /* renamed from: b  reason: collision with root package name */
    private int f1520b;

    /* renamed from: c  reason: collision with root package name */
    private AesVersion f1521c;

    /* renamed from: d  reason: collision with root package name */
    private String f1522d;

    /* renamed from: e  reason: collision with root package name */
    private AesKeyStrength f1523e;

    /* renamed from: f  reason: collision with root package name */
    private CompressionMethod f1524f;

    public AESExtraDataRecord() {
        b(HeaderSignature.AES_EXTRA_DATA_RECORD);
        this.f1520b = 7;
        this.f1521c = AesVersion.TWO;
        this.f1522d = "AE";
        this.f1523e = AesKeyStrength.KEY_STRENGTH_256;
        this.f1524f = CompressionMethod.DEFLATE;
    }

    public AesKeyStrength c() {
        return this.f1523e;
    }

    public AesVersion d() {
        return this.f1521c;
    }

    public CompressionMethod e() {
        return this.f1524f;
    }

    public int f() {
        return this.f1520b;
    }

    public String g() {
        return this.f1522d;
    }

    public void h(AesKeyStrength aesKeyStrength) {
        this.f1523e = aesKeyStrength;
    }

    public void i(AesVersion aesVersion) {
        this.f1521c = aesVersion;
    }

    public void j(CompressionMethod compressionMethod) {
        this.f1524f = compressionMethod;
    }

    public void k(int i2) {
        this.f1520b = i2;
    }

    public void l(String str) {
        this.f1522d = str;
    }
}
