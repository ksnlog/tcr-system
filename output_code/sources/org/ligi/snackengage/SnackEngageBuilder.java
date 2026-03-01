package org.ligi.snackengage;

import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class SnackEngageBuilder {

    /* renamed from: a  reason: collision with root package name */
    private final View f2966a;

    /* renamed from: b  reason: collision with root package name */
    private final List<Snack> f2967b = new ArrayList();

    public SnackEngageBuilder(View view) {
        this.f2966a = view;
    }

    public SnackEngage a() {
        return new SnackEngage(this.f2967b, this.f2966a);
    }

    public SnackEngageBuilder b(Snack snack) {
        this.f2967b.add(snack);
        return this;
    }
}
