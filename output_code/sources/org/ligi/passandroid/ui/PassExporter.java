package org.ligi.passandroid.ui;

import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.Tracker;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassExporter implements KoinComponent {

    /* renamed from: d  reason: collision with root package name */
    private final File f2784d;

    /* renamed from: e  reason: collision with root package name */
    private final File f2785e;

    /* renamed from: f  reason: collision with root package name */
    private Exception f2786f;

    /* renamed from: g  reason: collision with root package name */
    private final Lazy f2787g;

    public PassExporter(File inputPath, File file) {
        Lazy a2;
        Intrinsics.f(inputPath, "inputPath");
        Intrinsics.f(file, "file");
        this.f2784d = inputPath;
        this.f2785e = file;
        a2 = LazyKt__LazyJVMKt.a(KoinPlatformTools.f2602a.b(), new PassExporter$special$$inlined$inject$default$1(this, null, null));
        this.f2787g = a2;
    }

    public final void b() {
        try {
            this.f2785e.delete();
            File parentFile = this.f2785e.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            new ZipFile(this.f2785e).m(this.f2784d, new ZipParameters() { // from class: org.ligi.passandroid.ui.PassExporter$export$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    D(false);
                    w(CompressionMethod.DEFLATE);
                    v(CompressionLevel.NORMAL);
                }
            }, false, 0L);
        } catch (Exception e2) {
            e2.printStackTrace();
            d().a("when exporting pass to zip", e2, false);
            this.f2786f = e2;
            this.f2785e.delete();
        }
    }

    public final Exception c() {
        return this.f2786f;
    }

    public final Tracker d() {
        return (Tracker) this.f2787g.getValue();
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }
}
