package org.ligi.passandroid.ui.edit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.UriExtensionsKt;
import org.ligi.passandroid.model.PassBitmapDefinitions;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassImpl;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ImageEditHelper {

    /* renamed from: c  reason: collision with root package name */
    public static final Companion f2946c = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    private final Activity f2947a;

    /* renamed from: b  reason: collision with root package name */
    private final PassStore f2948b;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String a(int i2) {
            switch (i2) {
                case 5556:
                    return PassBitmapDefinitions.BITMAP_LOGO;
                case 5557:
                    return PassBitmapDefinitions.BITMAP_ICON;
                case 5558:
                    return PassBitmapDefinitions.BITMAP_STRIP;
                case 5559:
                    return PassBitmapDefinitions.BITMAP_THUMBNAIL;
                case 5560:
                    return PassBitmapDefinitions.BITMAP_FOOTER;
                default:
                    return null;
            }
        }
    }

    public ImageEditHelper(Activity context, PassStore passStore) {
        Intrinsics.f(context, "context");
        Intrinsics.f(passStore, "passStore");
        this.f2947a = context;
        this.f2948b = passStore;
    }

    private final void a(Intent intent, String str) {
        File file;
        Uri data = intent.getData();
        if (data != null) {
            file = UriExtensionsKt.a(data, this.f2947a);
        } else {
            file = null;
        }
        File file2 = file;
        Pass currentPass = this.f2948b.getCurrentPass();
        if (file2 != null && currentPass != null && file2.exists()) {
            try {
                File pathForID = this.f2948b.getPathForID(currentPass.getId());
                File file3 = new File(pathForID, str + PassImpl.FILETYPE_IMAGES);
                file3.delete();
                FilesKt__UtilsKt.d(file2, file3, false, 0, 6, null);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void b(int i2, int i3, Intent intent) {
        String a2;
        if (i3 == -1 && intent != null && (a2 = f2946c.a(i2)) != null) {
            a(intent, a2);
        }
    }

    public final void c(int i2) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        this.f2947a.startActivityForResult(Intent.createChooser(intent, "Select Picture"), i2);
    }
}
