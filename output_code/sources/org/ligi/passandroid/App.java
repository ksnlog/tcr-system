package org.ligi.passandroid;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.moshi.Moshi;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import org.koin.core.context.DefaultContextExtKt;
import org.koin.core.module.Module;
import org.koin.dsl.ModuleDSLKt;
import org.ligi.passandroid.json_adapter.ColorAdapter;
import org.ligi.passandroid.json_adapter.ZonedTimeAdapter;
import org.ligi.passandroid.model.AndroidSettings;
import org.ligi.tracedroid.TraceDroid;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class App extends Application {

    /* renamed from: d  reason: collision with root package name */
    private final Moshi f2610d = new Moshi.Builder().b(new ZonedTimeAdapter()).b(new ColorAdapter()).c();

    /* renamed from: e  reason: collision with root package name */
    private final Lazy f2611e;

    public App() {
        Lazy b2;
        b2 = LazyKt__LazyJVMKt.b(new App$settings$2(this));
        this.f2611e = b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AndroidSettings d() {
        return (AndroidSettings) this.f2611e.getValue();
    }

    private final void e() {
        TraceDroid.f2981c.g(this);
    }

    public Module c() {
        return ModuleDSLKt.b(false, new App$createKoin$1(this), 1, null);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        DefaultContextExtKt.a(new App$onCreate$1(this));
        AppCompatDelegate.K(true);
        AndroidThreeTen.a(this);
        e();
        AppCompatDelegate.O(d().getNightMode());
    }
}
