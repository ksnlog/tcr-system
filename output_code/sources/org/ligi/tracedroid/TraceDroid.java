package org.ligi.tracedroid;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Thread;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.tracedroid.collecting.TraceDroidMetaInfo;
import org.ligi.tracedroid.collecting.TraceDroidMetaInfoKt;
import org.ligi.tracedroid.collecting.UncaughtExceptionSaver;
import org.ligi.tracedroid.logging.BufferedLogTree;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TraceDroid {

    /* renamed from: a  reason: collision with root package name */
    public static TraceDroidMetaInfo f2979a;

    /* renamed from: b  reason: collision with root package name */
    private static final Lazy f2980b;

    /* renamed from: c  reason: collision with root package name */
    public static final TraceDroid f2981c = new TraceDroid();

    static {
        Lazy b2;
        b2 = LazyKt__LazyJVMKt.b(TraceDroid$bufferedLogTree$2.f2982d);
        f2980b = b2;
    }

    private TraceDroid() {
    }

    private final BufferedLogTree b() {
        return (BufferedLogTree) f2980b.getValue();
    }

    private final File c() {
        TraceDroidMetaInfo traceDroidMetaInfo = f2979a;
        if (traceDroidMetaInfo == null) {
            Intrinsics.p("traceDroidMetaInfo");
        }
        File file = new File(traceDroidMetaInfo.e());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public final void a() {
        for (File file : d()) {
            file.delete();
        }
    }

    public final File[] d() {
        File[] listFiles = c().listFiles(TraceDroid$stackTraceFiles$filter$1.f2983a);
        if (listFiles == null) {
            return new File[0];
        }
        return listFiles;
    }

    public final String e(int i2) {
        File[] d2;
        StringBuilder sb = new StringBuilder();
        sb.append("Searching Exceptions in: ");
        TraceDroidMetaInfo traceDroidMetaInfo = f2979a;
        if (traceDroidMetaInfo == null) {
            Intrinsics.p("traceDroidMetaInfo");
        }
        sb.append(traceDroidMetaInfo.e());
        Timber.d(sb.toString(), new Object[0]);
        StringBuilder sb2 = new StringBuilder();
        for (File file : d()) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                try {
                    sb2.append("file: ");
                    sb2.append(file.toString());
                    sb2.append(System.getProperty("line.separator"));
                    i2 = i3 - 1;
                    if (i3 > 0) {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                            for (String str : TextStreamsKt.e(bufferedReader)) {
                                sb2.append(str);
                                sb2.append(System.getProperty("line.separator"));
                            }
                            Unit unit = Unit.f763a;
                            CloseableKt.a(bufferedReader, null);
                        } catch (Exception e2) {
                            e = e2;
                            i3 = i2;
                            Timber.h(e, "problem loading stacktrace", new Object[0]);
                            i2 = i3;
                        }
                    } else {
                        sb2.append(" discarded by limit");
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            i2 = i3;
        }
        String sb3 = sb2.toString();
        Intrinsics.e(sb3, "stackTraceText.toString()");
        return sb3;
    }

    public final TraceDroidMetaInfo f() {
        TraceDroidMetaInfo traceDroidMetaInfo = f2979a;
        if (traceDroidMetaInfo == null) {
            Intrinsics.p("traceDroidMetaInfo");
        }
        return traceDroidMetaInfo;
    }

    public final void g(Context context) {
        Intrinsics.f(context, "context");
        f2979a = TraceDroidMetaInfoKt.a(context);
        Timber.f(b());
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            Timber.d("current handler class=" + defaultUncaughtExceptionHandler.getClass().getName(), new Object[0]);
            if (!(defaultUncaughtExceptionHandler instanceof UncaughtExceptionSaver)) {
                TraceDroidMetaInfo traceDroidMetaInfo = f2979a;
                if (traceDroidMetaInfo == null) {
                    Intrinsics.p("traceDroidMetaInfo");
                }
                Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionSaver(traceDroidMetaInfo, defaultUncaughtExceptionHandler, b()));
            }
        }
    }
}
