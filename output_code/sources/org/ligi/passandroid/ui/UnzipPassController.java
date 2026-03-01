package org.ligi.passandroid.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import okio.Okio;
import org.json.JSONObject;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.mp.KoinPlatformTools;
import org.ligi.passandroid.Tracker;
import org.ligi.passandroid.functions.PassTemplatesKt;
import org.ligi.passandroid.functions.SafeJSONReaderKt;
import org.ligi.passandroid.model.InputStreamWithSource;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.Settings;
import org.ligi.passandroid.model.pass.Pass;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class UnzipPassController implements KoinComponent {

    /* renamed from: d  reason: collision with root package name */
    public static final UnzipPassController f2888d;

    /* renamed from: e  reason: collision with root package name */
    private static final Lazy f2889e;

    /* renamed from: f  reason: collision with root package name */
    private static final Lazy f2890f;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface FailCallback {
        void a(String str);
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class InputStreamUnzipControllerSpec extends UnzipControllerSpec {

        /* renamed from: g  reason: collision with root package name */
        private final InputStreamWithSource f2897g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InputStreamUnzipControllerSpec(InputStreamWithSource inputStreamWithSource, Context context, PassStore passStore, SuccessCallback successCallback, FailCallback failCallback) {
            super(context, passStore, successCallback, failCallback, UnzipPassController.f2888d.b());
            Intrinsics.f(inputStreamWithSource, "inputStreamWithSource");
            Intrinsics.f(context, "context");
            Intrinsics.f(passStore, "passStore");
            this.f2897g = inputStreamWithSource;
        }

        public final InputStreamWithSource h() {
            return this.f2897g;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface SuccessCallback {
        void a(String str);
    }

    static {
        Lazy a2;
        Lazy a3;
        UnzipPassController unzipPassController = new UnzipPassController();
        f2888d = unzipPassController;
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.f2602a;
        a2 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new UnzipPassController$special$$inlined$inject$default$1(unzipPassController, null, null));
        f2889e = a2;
        a3 = LazyKt__LazyJVMKt.a(koinPlatformTools.b(), new UnzipPassController$special$$inlined$inject$default$2(unzipPassController, null, null));
        f2890f = a3;
    }

    private UnzipPassController() {
    }

    private final void d(FileUnzipControllerSpec fileUnzipControllerSpec) {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        String string;
        BufferedReader bufferedReader2;
        PdfRenderer.Page openPage;
        int height;
        int width;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.e(uuid, "randomUUID().toString()");
        File cacheDir = fileUnzipControllerSpec.a().getCacheDir();
        File file = new File(cacheDir, "temp/" + uuid);
        file.mkdirs();
        if (!file.exists()) {
            FailCallback b2 = fileUnzipControllerSpec.b();
            if (b2 != null) {
                b2.a("Problem creating the temp dir: " + file);
                return;
            }
            return;
        }
        File file2 = new File(file, "source.obj");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2), Charsets.f909b);
        if (outputStreamWriter instanceof BufferedWriter) {
            bufferedWriter = (BufferedWriter) outputStreamWriter;
        } else {
            bufferedWriter = new BufferedWriter(outputStreamWriter, 8192);
        }
        bufferedWriter.write(fileUnzipControllerSpec.h());
        try {
            new ZipFile(fileUnzipControllerSpec.i()).n(file.getAbsolutePath());
        } catch (ZipException e2) {
            e2.printStackTrace();
        }
        File file3 = new File(file, "manifest.json");
        File file4 = new File(file, "main.json");
        if (file3.exists()) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file3), Charsets.f909b);
                if (inputStreamReader instanceof BufferedReader) {
                    bufferedReader = (BufferedReader) inputStreamReader;
                } else {
                    bufferedReader = new BufferedReader(inputStreamReader, 8192);
                }
                JSONObject a2 = SafeJSONReaderKt.a(TextStreamsKt.f(bufferedReader));
                Intrinsics.c(a2);
                string = a2.getString("pass.json");
                Intrinsics.e(string, "manifestJSON.getString(\"pass.json\")");
            } catch (Exception e3) {
                FailCallback b3 = fileUnzipControllerSpec.b();
                if (b3 != null) {
                    b3.a("Problem with manifest.json: " + e3);
                    return;
                }
                return;
            }
        } else if (file4.exists()) {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(new FileInputStream(file4), Charsets.f909b);
                if (inputStreamReader2 instanceof BufferedReader) {
                    bufferedReader2 = (BufferedReader) inputStreamReader2;
                } else {
                    bufferedReader2 = new BufferedReader(inputStreamReader2, 8192);
                }
                JSONObject a3 = SafeJSONReaderKt.a(TextStreamsKt.f(bufferedReader2));
                Intrinsics.c(a3);
                string = a3.getString("id");
                Intrinsics.e(string, "manifestJSON.getString(\"id\")");
            } catch (Exception e4) {
                FailCallback b4 = fileUnzipControllerSpec.b();
                if (b4 != null) {
                    b4.a("Problem with manifest.json: " + e4);
                    return;
                }
                return;
            }
        } else {
            Bitmap decodeFile = BitmapFactory.decodeFile(fileUnzipControllerSpec.i());
            Resources resources = fileUnzipControllerSpec.a().getResources();
            if (decodeFile != null) {
                Intrinsics.e(resources, "resources");
                Pass c2 = PassTemplatesKt.c(resources);
                File pathForID = fileUnzipControllerSpec.e().getPathForID(c2.getId());
                pathForID.mkdirs();
                FilesKt__UtilsKt.d(new File(fileUnzipControllerSpec.i()), new File(pathForID, "strip.png"), false, 0, 6, null);
                fileUnzipControllerSpec.e().save(c2);
                fileUnzipControllerSpec.e().getClassifier().moveToTopic(c2, "new");
                SuccessCallback c3 = fileUnzipControllerSpec.c();
                if (c3 != null) {
                    c3.a(c2.getId());
                    return;
                }
                return;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    File file5 = new File(fileUnzipControllerSpec.i());
                    if (Intrinsics.a(Okio.b(Okio.i(file5)).g(4L), "%PDF")) {
                        openPage = new PdfRenderer(ParcelFileDescriptor.open(file5, 268435456)).openPage(0);
                        height = openPage.getHeight();
                        width = openPage.getWidth();
                        float f2 = height / width;
                        int i2 = resources.getDisplayMetrics().widthPixels;
                        Bitmap createBitmap = Bitmap.createBitmap(i2, (int) (i2 * f2), Bitmap.Config.ARGB_8888);
                        openPage.render(createBitmap, null, null, 1);
                        Intrinsics.e(resources, "resources");
                        Pass d2 = PassTemplatesKt.d(resources);
                        File pathForID2 = fileUnzipControllerSpec.e().getPathForID(d2.getId());
                        pathForID2.mkdirs();
                        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(pathForID2, "strip.png")));
                        fileUnzipControllerSpec.e().save(d2);
                        fileUnzipControllerSpec.e().getClassifier().moveToTopic(d2, "new");
                        SuccessCallback c4 = fileUnzipControllerSpec.c();
                        if (c4 != null) {
                            c4.a(d2.getId());
                            return;
                        }
                        return;
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            FailCallback b5 = fileUnzipControllerSpec.b();
            if (b5 != null) {
                b5.a("Pass is not espass or pkpass format :-(");
                return;
            }
            return;
        }
        fileUnzipControllerSpec.f().mkdirs();
        File file6 = new File(fileUnzipControllerSpec.f(), string);
        if (fileUnzipControllerSpec.d() && file6.exists()) {
            FilesKt__UtilsKt.e(file6);
        }
        if (!file6.exists()) {
            file.renameTo(file6);
        } else {
            Timber.f3479a.f("Pass with same ID exists", new Object[0]);
        }
        SuccessCallback c5 = fileUnzipControllerSpec.c();
        if (c5 != null) {
            c5.a(string);
        }
    }

    public final Settings b() {
        return (Settings) f2890f.getValue();
    }

    public final Tracker c() {
        return (Tracker) f2889e.getValue();
    }

    public final void e(InputStreamUnzipControllerSpec spec) {
        Intrinsics.f(spec, "spec");
        try {
            InputStream inputStream = spec.h().getInputStream();
            File createTempFile = File.createTempFile("ins", "pass");
            ByteStreamsKt.b(inputStream, new FileOutputStream(createTempFile), 0, 2, null);
            UnzipPassController unzipPassController = f2888d;
            String absolutePath = createTempFile.getAbsolutePath();
            Intrinsics.e(absolutePath, "tempFile.absolutePath");
            unzipPassController.d(new FileUnzipControllerSpec(absolutePath, spec));
            createTempFile.delete();
            CloseableKt.a(inputStream, null);
        } catch (Exception e2) {
            c().a("problem processing InputStream", e2, false);
            FailCallback b2 = spec.b();
            if (b2 != null) {
                b2.a("problem with temp file: " + e2);
            }
        }
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.a(this);
    }
}
