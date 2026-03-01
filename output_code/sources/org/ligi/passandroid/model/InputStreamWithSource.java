package org.ligi.passandroid.model;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class InputStreamWithSource {
    private final InputStream inputStream;
    private final String source;

    public InputStreamWithSource(String source, InputStream inputStream) {
        Intrinsics.f(source, "source");
        Intrinsics.f(inputStream, "inputStream");
        this.source = source;
        this.inputStream = inputStream;
    }

    public final InputStream getInputStream() {
        return this.inputStream;
    }

    public final String getSource() {
        return this.source;
    }
}
