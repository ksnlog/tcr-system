package org.ligi.snackengage.snacks;

import org.ligi.snackengage.conditions.AfterNumberOfOpportunities;
import org.ligi.snackengage.conditions.NeverAgainWhenClickedOnce;
import org.ligi.snackengage.conditions.connectivity.IsConnectedViaWiFiOrUnknown;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class DefaultRateSnack extends RateSnack {
    public DefaultRateSnack() {
        k(new NeverAgainWhenClickedOnce(), new AfterNumberOfOpportunities(5), new IsConnectedViaWiFiOrUnknown());
    }
}
