package org.threeten.bp.format;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.bp.temporal.TemporalField;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class DateTimeTextProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicReference<DateTimeTextProvider> f3312a = new AtomicReference<>();

    public abstract String a(TemporalField temporalField, long j2, TextStyle textStyle, Locale locale);

    public abstract Iterator<Map.Entry<String, Long>> b(TemporalField temporalField, TextStyle textStyle, Locale locale);
}
