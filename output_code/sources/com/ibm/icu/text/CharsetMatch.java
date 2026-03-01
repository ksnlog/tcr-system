package com.ibm.icu.text;

import java.io.InputStream;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CharsetMatch implements Comparable<CharsetMatch> {

    /* renamed from: d  reason: collision with root package name */
    private int f66d;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f67e;

    /* renamed from: f  reason: collision with root package name */
    private int f68f;

    /* renamed from: g  reason: collision with root package name */
    private InputStream f69g;

    /* renamed from: h  reason: collision with root package name */
    private String f70h;

    /* renamed from: i  reason: collision with root package name */
    private String f71i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharsetMatch(CharsetDetector charsetDetector, CharsetRecognizer charsetRecognizer, int i2) {
        this.f67e = null;
        this.f66d = i2;
        InputStream inputStream = charsetDetector.f61g;
        if (inputStream == null) {
            this.f67e = charsetDetector.f59e;
            this.f68f = charsetDetector.f60f;
        }
        this.f69g = inputStream;
        this.f70h = charsetRecognizer.b();
        this.f71i = charsetRecognizer.a();
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(CharsetMatch charsetMatch) {
        int i2 = this.f66d;
        int i3 = charsetMatch.f66d;
        if (i2 > i3) {
            return 1;
        }
        return i2 < i3 ? -1 : 0;
    }

    public String b() {
        return this.f70h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharsetMatch(CharsetDetector charsetDetector, CharsetRecognizer charsetRecognizer, int i2, String str, String str2) {
        this.f67e = null;
        this.f66d = i2;
        InputStream inputStream = charsetDetector.f61g;
        if (inputStream == null) {
            this.f67e = charsetDetector.f59e;
            this.f68f = charsetDetector.f60f;
        }
        this.f69g = inputStream;
        this.f70h = str;
        this.f71i = str2;
    }
}
