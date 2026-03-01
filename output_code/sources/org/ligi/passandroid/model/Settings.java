package org.ligi.passandroid.model;

import java.io.File;
import org.ligi.passandroid.model.comparator.PassSortOrder;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Settings {
    boolean doTraceDroidEmailSend();

    int getNightMode();

    File getPassesDir();

    PassSortOrder getSortOrder();

    File getStateDir();

    boolean isAutomaticLightEnabled();

    boolean isCondensedModeEnabled();
}
