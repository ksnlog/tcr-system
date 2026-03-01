package kotlin.io;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TextStreamsKt {
    public static final long a(Reader reader, Writer out, int i2) {
        Intrinsics.f(reader, "<this>");
        Intrinsics.f(out, "out");
        char[] cArr = new char[i2];
        int read = reader.read(cArr);
        long j2 = 0;
        while (read >= 0) {
            out.write(cArr, 0, read);
            j2 += read;
            read = reader.read(cArr);
        }
        return j2;
    }

    public static /* synthetic */ long b(Reader reader, Writer writer, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 8192;
        }
        return a(reader, writer, i2);
    }

    public static final void c(Reader reader, Function1<? super String, Unit> action) {
        BufferedReader bufferedReader;
        Intrinsics.f(reader, "<this>");
        Intrinsics.f(action, "action");
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader, 8192);
        }
        try {
            for (String str : d(bufferedReader)) {
                action.f(str);
            }
            Unit unit = Unit.f763a;
            CloseableKt.a(bufferedReader, null);
        } finally {
        }
    }

    public static final Sequence<String> d(BufferedReader bufferedReader) {
        Sequence<String> d2;
        Intrinsics.f(bufferedReader, "<this>");
        d2 = SequencesKt__SequencesKt.d(new LinesSequence(bufferedReader));
        return d2;
    }

    public static final List<String> e(Reader reader) {
        Intrinsics.f(reader, "<this>");
        ArrayList arrayList = new ArrayList();
        c(reader, new TextStreamsKt$readLines$1(arrayList));
        return arrayList;
    }

    public static final String f(Reader reader) {
        Intrinsics.f(reader, "<this>");
        StringWriter stringWriter = new StringWriter();
        b(reader, stringWriter, 0, 2, null);
        String stringWriter2 = stringWriter.toString();
        Intrinsics.e(stringWriter2, "buffer.toString()");
        return stringWriter2;
    }
}
