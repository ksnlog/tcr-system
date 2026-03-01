package org.ligi.snackengage.conditions;

import org.ligi.snackengage.SnackContext;
import org.ligi.snackengage.snacks.Snack;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class NeverAgainWhenClickedOnce implements SnackCondition {
    @Override // org.ligi.snackengage.conditions.SnackCondition
    public boolean a(SnackContext snackContext, Snack snack) {
        return !snackContext.c().i(snack);
    }
}
