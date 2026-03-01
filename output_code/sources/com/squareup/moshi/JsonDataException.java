package com.squareup.moshi;

import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JsonDataException extends RuntimeException {
    public JsonDataException(@Nullable String str) {
        super(str);
    }

    public JsonDataException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
