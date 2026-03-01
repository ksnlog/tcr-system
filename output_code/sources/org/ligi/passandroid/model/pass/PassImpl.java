package org.ligi.passandroid.model.pass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.JsonQualifier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.ligi.passandroid.model.PassStore;
import org.threeten.bp.ZonedDateTime;
@JsonClass(generateAdapter = true)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImpl implements Pass {
    public static final Companion Companion = new Companion(null);
    public static final String FILETYPE_IMAGES = ".png";
    @HexColor
    private int accentColor;
    private String app;
    private String authToken;
    private BarCode barCode;
    private TimeSpan calendarTimespan;
    private String creator;
    private String description;
    private List<PassField> fields;
    private final String id;
    private List<PassLocation> locations;
    private String passIdent;
    private String serial;
    private PassType type;
    private List<TimeSpan> validTimespans;
    private String webServiceURL;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JsonQualifier
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public @interface HexColor {
    }

    @JsonClass(generateAdapter = true)
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class TimeRepeat {
        private final int count;
        private final int offset;

        public TimeRepeat(int i2, int i3) {
            this.offset = i2;
            this.count = i3;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getOffset() {
            return this.offset;
        }
    }

    public PassImpl(String id) {
        Intrinsics.f(id, "id");
        this.id = id;
        this.type = PassType.EVENT;
        this.validTimespans = new ArrayList();
        this.fields = new ArrayList();
        this.locations = new ArrayList();
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public int getAccentColor() {
        return this.accentColor;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getApp() {
        return this.app;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getAuthToken() {
        return this.authToken;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public BarCode getBarCode() {
        return this.barCode;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public Bitmap getBitmap(PassStore passStore, String passBitmap) {
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(passBitmap, "passBitmap");
        try {
            File pathForID = passStore.getPathForID(getId());
            return BitmapFactory.decodeStream(new FileInputStream(new File(pathForID, passBitmap + FILETYPE_IMAGES)));
        } catch (FileNotFoundException | OutOfMemoryError unused) {
            return null;
        }
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public TimeSpan getCalendarTimespan() {
        return this.calendarTimespan;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getCreator() {
        return this.creator;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getDescription() {
        String str = this.description;
        return str == null ? "" : str;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public List<PassField> getFields() {
        return this.fields;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getId() {
        return this.id;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public List<PassLocation> getLocations() {
        return this.locations;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getPassIdent() {
        return this.passIdent;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getSerial() {
        return this.serial;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getSource(PassStore passStore) {
        BufferedReader bufferedReader;
        Intrinsics.f(passStore, "passStore");
        File file = new File(passStore.getPathForID(getId()), "source.txt");
        if (file.exists()) {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charsets.f909b);
            if (inputStreamReader instanceof BufferedReader) {
                bufferedReader = (BufferedReader) inputStreamReader;
            } else {
                bufferedReader = new BufferedReader(inputStreamReader, 8192);
            }
            return TextStreamsKt.f(bufferedReader);
        }
        return null;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public PassType getType() {
        return this.type;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public List<TimeSpan> getValidTimespans() {
        return this.validTimespans;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public String getWebServiceURL() {
        return this.webServiceURL;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public void setAccentColor(int i2) {
        this.accentColor = i2;
    }

    public void setApp(String str) {
        this.app = str;
    }

    public void setAuthToken(String str) {
        this.authToken = str;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public void setBarCode(BarCode barCode) {
        this.barCode = barCode;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public void setCalendarTimespan(TimeSpan timeSpan) {
        this.calendarTimespan = timeSpan;
    }

    public void setCreator(String str) {
        this.creator = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFields(List<PassField> list) {
        Intrinsics.f(list, "<set-?>");
        this.fields = list;
    }

    public void setLocations(List<PassLocation> list) {
        Intrinsics.f(list, "<set-?>");
        this.locations = list;
    }

    public void setPassIdent(String str) {
        this.passIdent = str;
    }

    public void setSerial(String str) {
        this.serial = str;
    }

    @Override // org.ligi.passandroid.model.pass.Pass
    public void setType(PassType passType) {
        Intrinsics.f(passType, "<set-?>");
        this.type = passType;
    }

    public void setValidTimespans(List<TimeSpan> list) {
        Intrinsics.f(list, "<set-?>");
        this.validTimespans = list;
    }

    public void setWebServiceURL(String str) {
        this.webServiceURL = str;
    }

    public String toString() {
        return "ID=" + getId();
    }

    @JsonClass(generateAdapter = true)
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class TimeSpan {
        private final ZonedDateTime from;
        private final TimeRepeat repeat;
        private final ZonedDateTime to;

        public TimeSpan() {
            this(null, null, null, 7, null);
        }

        public TimeSpan(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, TimeRepeat timeRepeat) {
            this.from = zonedDateTime;
            this.to = zonedDateTime2;
            this.repeat = timeRepeat;
        }

        public final ZonedDateTime getFrom() {
            return this.from;
        }

        public final TimeRepeat getRepeat() {
            return this.repeat;
        }

        public final ZonedDateTime getTo() {
            return this.to;
        }

        public /* synthetic */ TimeSpan(ZonedDateTime zonedDateTime, ZonedDateTime zonedDateTime2, TimeRepeat timeRepeat, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : zonedDateTime, (i2 & 2) != 0 ? null : zonedDateTime2, (i2 & 4) != 0 ? null : timeRepeat);
        }
    }
}
