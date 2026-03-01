package org.ligi.passandroid.functions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassField;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.model.pass.PassType;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassTemplatesKt {
    public static final Pass a(PassStore passStore, Resources resources) {
        Intrinsics.f(passStore, "passStore");
        Intrinsics.f(resources, "resources");
        PassImpl b2 = b();
        b2.setDescription("custom Pass");
        passStore.setCurrentPass(b2);
        passStore.save(b2);
        try {
            BitmapFactory.decodeResource(resources, 2131230896).compress(Bitmap.CompressFormat.PNG, 90, new FileOutputStream(new File(passStore.getPathForID(b2.getId()), "icon.png")));
        } catch (FileNotFoundException unused) {
        }
        return b2;
    }

    private static final PassImpl b() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.e(uuid, "randomUUID().toString()");
        PassImpl passImpl = new PassImpl(uuid);
        passImpl.setAccentColor(-16776961);
        passImpl.setApp("passandroid");
        passImpl.setType(PassType.EVENT);
        return passImpl;
    }

    public static final Pass c(Resources resources) {
        List<PassField> i2;
        Intrinsics.f(resources, "resources");
        PassImpl b2 = b();
        b2.setDescription(resources.getString(2131755130));
        PassField.Companion companion = PassField.Companion;
        i2 = CollectionsKt__CollectionsKt.i(PassField.Companion.create$default(companion, 2131755121, 2131755122, resources, false, null, 24, null), PassField.Companion.create$default(companion, 2131755116, 2131755117, resources, false, null, 24, null), PassField.Companion.create$default(companion, 2131755118, 2131755119, resources, true, null, 16, null));
        b2.setFields(i2);
        return b2;
    }

    public static final Pass d(Resources resources) {
        List<PassField> i2;
        Intrinsics.f(resources, "resources");
        PassImpl b2 = b();
        b2.setDescription(resources.getString(2131755273));
        PassField.Companion companion = PassField.Companion;
        i2 = CollectionsKt__CollectionsKt.i(PassField.Companion.create$default(companion, 2131755121, 2131755123, resources, false, null, 24, null), PassField.Companion.create$default(companion, 2131755116, 2131755117, resources, false, null, 24, null), PassField.Companion.create$default(companion, 2131755118, 2131755120, resources, true, null, 16, null));
        b2.setFields(i2);
        return b2;
    }
}
