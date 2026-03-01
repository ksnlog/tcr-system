package org.ligi.snackengage;

import android.content.Context;
import android.view.View;
import org.ligi.snackengage.stats.SnackStats;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SnackContext {

    /* renamed from: a  reason: collision with root package name */
    private final View f2962a;

    /* renamed from: b  reason: collision with root package name */
    private final SnackStats f2963b;

    public SnackContext(View view) {
        this(view, new SnackStats(view.getContext()));
    }

    public Context a() {
        return this.f2962a.getContext();
    }

    public View b() {
        return this.f2962a;
    }

    public SnackStats c() {
        return this.f2963b;
    }

    public SnackContext(View view, SnackStats snackStats) {
        this.f2962a = view;
        this.f2963b = snackStats;
    }
}
