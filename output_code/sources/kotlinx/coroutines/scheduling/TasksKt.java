package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TasksKt {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1210a = SystemPropsKt.e("kotlinx.coroutines.scheduler.default.name", "DefaultDispatcher");

    /* renamed from: b  reason: collision with root package name */
    public static final long f1211b;

    /* renamed from: c  reason: collision with root package name */
    public static final int f1212c;

    /* renamed from: d  reason: collision with root package name */
    public static final int f1213d;

    /* renamed from: e  reason: collision with root package name */
    public static final long f1214e;

    /* renamed from: f  reason: collision with root package name */
    public static SchedulerTimeSource f1215f;

    /* renamed from: g  reason: collision with root package name */
    public static final TaskContext f1216g;

    /* renamed from: h  reason: collision with root package name */
    public static final TaskContext f1217h;

    static {
        long f2;
        int a2;
        int e2;
        int e3;
        long f3;
        f2 = SystemPropsKt__SystemProps_commonKt.f("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null);
        f1211b = f2;
        a2 = RangesKt___RangesKt.a(SystemPropsKt.a(), 2);
        e2 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.scheduler.core.pool.size", a2, 1, 0, 8, null);
        f1212c = e2;
        e3 = SystemPropsKt__SystemProps_commonKt.e("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);
        f1213d = e3;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        f3 = SystemPropsKt__SystemProps_commonKt.f("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null);
        f1214e = timeUnit.toNanos(f3);
        f1215f = NanoTimeSource.f1200a;
        f1216g = new TaskContextImpl(0);
        f1217h = new TaskContextImpl(1);
    }
}
