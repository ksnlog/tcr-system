package com.jakewharton.threetenabp;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesInitializer;
import org.threeten.bp.zone.ZoneRulesProvider;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class AssetsZoneRulesInitializer extends ZoneRulesInitializer {

    /* renamed from: d  reason: collision with root package name */
    private final Context f124d;

    /* renamed from: e  reason: collision with root package name */
    private final String f125e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetsZoneRulesInitializer(Context context, String str) {
        this.f124d = context;
        this.f125e = str;
    }

    @Override // org.threeten.bp.zone.ZoneRulesInitializer
    protected void b() {
        InputStream inputStream = null;
        try {
            try {
                inputStream = this.f124d.getAssets().open(this.f125e);
                TzdbZoneRulesProvider tzdbZoneRulesProvider = new TzdbZoneRulesProvider(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                ZoneRulesProvider.f(tzdbZoneRulesProvider);
            } catch (IOException e2) {
                throw new IllegalStateException(this.f125e + " missing from assets", e2);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }
}
