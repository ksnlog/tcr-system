package net.lingala.zip4j.util;

import java.io.File;
import java.nio.charset.Charset;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InternalZipConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1717a = File.separator;

    /* renamed from: b  reason: collision with root package name */
    public static final Charset f1718b;

    /* renamed from: c  reason: collision with root package name */
    public static final Charset f1719c;

    static {
        Charset forName = Charset.forName("UTF-8");
        f1718b = forName;
        f1719c = forName;
    }
}
