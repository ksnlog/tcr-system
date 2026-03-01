package org.ligi.snackengage.conditions;

import org.ligi.snackengage.SnackContext;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class AfterNumberOfOpportunities implements SnackCondition {

    /* renamed from: a  reason: collision with root package name */
    private final int f2968a;

    public AfterNumberOfOpportunities(int i2) {
        this.f2968a = i2;
    }

    @Override // org.ligi.snackengage.conditions.SnackCondition
    public boolean a(SnackContext snackContext, Snack snack) {
        return snackContext.c().b(snack) > ((long) this.f2968a);
    }
}
