package org.ligi.passandroid.scan;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassBitmapDefinitions;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.ui.PassViewActivity;
import org.ligi.passandroid.ui.UnzipPassController;
import org.threeten.bp.ZonedDateTime;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class SearchSuccessCallback implements UnzipPassController.SuccessCallback {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2721a;

    /* renamed from: b  reason: collision with root package name */
    private final PassStore f2722b;

    /* renamed from: c  reason: collision with root package name */
    private final List<Pass> f2723c;

    /* renamed from: d  reason: collision with root package name */
    private final NotificationCompat.Builder f2724d;

    /* renamed from: e  reason: collision with root package name */
    private final File f2725e;

    /* renamed from: f  reason: collision with root package name */
    private final NotificationManager f2726f;

    public SearchSuccessCallback(Context context, PassStore passStore, List<Pass> foundList, NotificationCompat.Builder findNotificationBuilder, File file, NotificationManager notifyManager) {
        Intrinsics.f(context, "context");
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(foundList, "foundList");
        Intrinsics.f(findNotificationBuilder, "findNotificationBuilder");
        Intrinsics.f(file, "file");
        Intrinsics.f(notifyManager, "notifyManager");
        this.f2721a = context;
        this.f2722b = passStore;
        this.f2723c = foundList;
        this.f2724d = findNotificationBuilder;
        this.f2725e = file;
        this.f2726f = notifyManager;
    }

    private final ZonedDateTime b(Pass pass) {
        if (pass.getCalendarTimespan() != null) {
            PassImpl.TimeSpan calendarTimespan = pass.getCalendarTimespan();
            Intrinsics.c(calendarTimespan);
            if (calendarTimespan.getFrom() != null) {
                PassImpl.TimeSpan calendarTimespan2 = pass.getCalendarTimespan();
                Intrinsics.c(calendarTimespan2);
                return calendarTimespan2.getFrom();
            }
        }
        if (pass.getValidTimespans() != null) {
            List<PassImpl.TimeSpan> validTimespans = pass.getValidTimespans();
            Intrinsics.c(validTimespans);
            if (!validTimespans.isEmpty()) {
                List<PassImpl.TimeSpan> validTimespans2 = pass.getValidTimespans();
                Intrinsics.c(validTimespans2);
                if (validTimespans2.get(0).getTo() != null) {
                    List<PassImpl.TimeSpan> validTimespans3 = pass.getValidTimespans();
                    Intrinsics.c(validTimespans3);
                    return validTimespans3.get(0).getTo();
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private final String c(Pass pass) {
        ZonedDateTime b2 = b(pass);
        if (b2 != null && b2.s(ZonedDateTime.M())) {
            String string = this.f2721a.getString(2131755311);
            Intrinsics.e(string, "context.getString(R.string.topic_archive)");
            return string;
        }
        String string2 = this.f2721a.getString(2131755312);
        Intrinsics.e(string2, "context.getString(R.string.topic_new)");
        return string2;
    }

    private final Bitmap d(Bitmap bitmap, int i2) {
        int height;
        float f2 = i2;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            height = bitmap.getWidth();
        } else {
            height = bitmap.getHeight();
        }
        float f3 = f2 / height;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f3), (int) (bitmap.getHeight() * f3), false);
        Intrinsics.e(createScaledBitmap, "createScaledBitmap(this, width, height, filter)");
        return createScaledBitmap;
    }

    @Override // org.ligi.passandroid.ui.UnzipPassController.SuccessCallback
    public void a(String uuid) {
        boolean z2;
        Intrinsics.f(uuid, "uuid");
        Pass passbookForId = this.f2722b.getPassbookForId(uuid);
        List<Pass> list = this.f2723c;
        int i2 = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (Pass pass : list) {
                if (Intrinsics.a(pass.getId(), uuid)) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        if (passbookForId != null && !z2) {
            this.f2723c.add(passbookForId);
            Bitmap bitmap = passbookForId.getBitmap(this.f2722b, PassBitmapDefinitions.BITMAP_ICON);
            this.f2722b.getClassifier().moveToTopic(passbookForId, c(passbookForId));
            if (bitmap != null) {
                this.f2724d.j(d(bitmap, this.f2721a.getResources().getDimensionPixelSize(2131165353)));
            }
            String string = this.f2721a.getString(2131755125, passbookForId.getDescription());
            Intrinsics.e(string, "context.getString(R.stri…d_pass, pass.description)");
            this.f2724d.h(string);
            if (this.f2723c.size() > 1) {
                String string2 = this.f2721a.getString(2131755124, Integer.valueOf(this.f2723c.size() - 1));
                Intrinsics.e(string2, "context.getString(R.stri…pass, foundList.size - 1)");
                this.f2724d.g(string2);
            } else {
                this.f2724d.g(this.f2725e.getAbsolutePath());
            }
            Intent intent = new Intent(this.f2721a, PassViewActivity.class);
            intent.putExtra("uuid", uuid);
            if (Build.VERSION.SDK_INT >= 31) {
                i2 = 67108864;
            }
            this.f2724d.f(PendingIntent.getActivity(this.f2721a, 1, intent, 134217728 | i2));
            this.f2726f.notify(2, this.f2724d.a());
        }
    }
}
