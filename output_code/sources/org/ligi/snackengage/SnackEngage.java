package org.ligi.snackengage;

import android.view.View;
import java.util.List;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SnackEngage {

    /* renamed from: a  reason: collision with root package name */
    private final List<Snack> f2964a;

    /* renamed from: b  reason: collision with root package name */
    private final SnackContext f2965b;

    public SnackEngage(List<Snack> list, View view) {
        this(list, new SnackContext(view));
    }

    public static SnackEngageBuilder b(View view) {
        return new SnackEngageBuilder(view);
    }

    public boolean a() {
        for (Snack snack : this.f2964a) {
            this.f2965b.c().e(snack);
            if (snack.b(this.f2965b)) {
                return true;
            }
        }
        return false;
    }

    public SnackEngage(List<Snack> list, SnackContext snackContext) {
        this.f2964a = list;
        this.f2965b = snackContext;
    }
}
