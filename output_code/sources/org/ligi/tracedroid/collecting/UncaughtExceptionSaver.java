package org.ligi.tracedroid.collecting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.tracedroid.logging.BufferedLogTree;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UncaughtExceptionSaver implements Thread.UncaughtExceptionHandler {

    /* renamed from: d  reason: collision with root package name */
    public static final Companion f2991d = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final TraceDroidMetaInfo f2992a;

    /* renamed from: b  reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f2993b;

    /* renamed from: c  reason: collision with root package name */
    private final BufferedLogTree f2994c;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String a(Throwable t2) {
            Intrinsics.f(t2, "t");
            try {
                StringWriter stringWriter = new StringWriter();
                t2.printStackTrace(new PrintWriter(stringWriter));
                String stringWriter2 = stringWriter.toString();
                Intrinsics.e(stringWriter2, "sw.toString()");
                return stringWriter2;
            } catch (Exception unused) {
                return "bad exception stack";
            }
        }
    }

    public UncaughtExceptionSaver(TraceDroidMetaInfo traceDroidMetaInfo, Thread.UncaughtExceptionHandler oldHandler, BufferedLogTree bufferedLogTree) {
        Intrinsics.f(traceDroidMetaInfo, "traceDroidMetaInfo");
        Intrinsics.f(oldHandler, "oldHandler");
        Intrinsics.f(bufferedLogTree, "bufferedLogTree");
        this.f2992a = traceDroidMetaInfo;
        this.f2993b = oldHandler;
        this.f2994c = bufferedLogTree;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable throwable) {
        String B;
        Intrinsics.f(thread, "thread");
        Intrinsics.f(throwable, "throwable");
        try {
            String str = this.f2992a.e() + "/" + this.f2992a.c() + "-" + System.currentTimeMillis() + this.f2992a.d();
            Timber.d("Writing unhandled exception to: " + str, new Object[0]);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write("Android Version: " + this.f2992a.a() + '\n');
            bufferedWriter.write("Phone Model: " + this.f2992a.f() + "\\n");
            bufferedWriter.write("TraceDroid Version: " + this.f2992a.g() + "\\n");
            bufferedWriter.write("Stacktrace: " + f2991d.a(throwable) + "\\n");
            StringBuilder sb = new StringBuilder();
            sb.append("Log:  ");
            B = CollectionsKt___CollectionsKt.B(this.f2994c.m(), "\n", null, null, 0, null, null, 62, null);
            sb.append(B);
            bufferedWriter.write(sb.toString());
            bufferedWriter.flush();
            Unit unit = Unit.f763a;
            CloseableKt.a(bufferedWriter, null);
        } catch (Exception e2) {
            Timber.e(e2, "Error saving exception stacktrace", new Object[0]);
        }
        Timber.d(f2991d.a(throwable), new Object[0]);
        this.f2993b.uncaughtException(thread, throwable);
    }
}
