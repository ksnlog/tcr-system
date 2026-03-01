package org.koin.core.logger;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class Logger {

    /* renamed from: a  reason: collision with root package name */
    private Level f2565a;

    public Logger(Level level) {
        Intrinsics.f(level, "level");
        this.f2565a = level;
    }

    public abstract void a(Level level, String str);

    public final boolean b(Level lvl) {
        Intrinsics.f(lvl, "lvl");
        if (this.f2565a.compareTo(lvl) <= 0) {
            return true;
        }
        return false;
    }
}
