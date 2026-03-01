package org.ligi.passandroid.model.pass;

import android.graphics.Bitmap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Pass {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public @interface PassBitmap {
    }

    int getAccentColor();

    String getApp();

    String getAuthToken();

    BarCode getBarCode();

    Bitmap getBitmap(PassStore passStore, String str);

    PassImpl.TimeSpan getCalendarTimespan();

    String getCreator();

    String getDescription();

    List<PassField> getFields();

    String getId();

    List<PassLocation> getLocations();

    String getPassIdent();

    String getSerial();

    String getSource(PassStore passStore);

    PassType getType();

    List<PassImpl.TimeSpan> getValidTimespans();

    String getWebServiceURL();

    void setAccentColor(int i2);

    void setBarCode(BarCode barCode);

    void setCalendarTimespan(PassImpl.TimeSpan timeSpan);

    void setType(PassType passType);
}
