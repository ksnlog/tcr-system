package org.threeten.bp.format;

import org.threeten.bp.DateTimeException;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class DateTimeParseException extends DateTimeException {

    /* renamed from: d  reason: collision with root package name */
    private final String f3302d;

    /* renamed from: e  reason: collision with root package name */
    private final int f3303e;

    public DateTimeParseException(String str, CharSequence charSequence, int i2) {
        super(str);
        this.f3302d = charSequence.toString();
        this.f3303e = i2;
    }

    public DateTimeParseException(String str, CharSequence charSequence, int i2, Throwable th) {
        super(str, th);
        this.f3302d = charSequence.toString();
        this.f3303e = i2;
    }
}
