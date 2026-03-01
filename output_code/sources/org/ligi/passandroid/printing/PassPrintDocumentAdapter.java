package org.ligi.passandroid.printing;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassField;
@TargetApi(19)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassPrintDocumentAdapter extends PrintDocumentAdapter {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2673a;

    /* renamed from: b  reason: collision with root package name */
    private final Pass f2674b;

    /* renamed from: c  reason: collision with root package name */
    private final String f2675c;

    /* renamed from: d  reason: collision with root package name */
    private PrintedPdfDocument f2676d;

    public PassPrintDocumentAdapter(Context context, Pass pass, String jobName) {
        Intrinsics.f(context, "context");
        Intrinsics.f(pass, "pass");
        Intrinsics.f(jobName, "jobName");
        this.f2673a = context;
        this.f2674b = pass;
        this.f2675c = jobName;
    }

    private final void a(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        String description = this.f2674b.getDescription();
        Intrinsics.c(description);
        canvas.drawText(description, canvas.getWidth() / 2.0f, paint.getTextSize(), paint);
        float textSize = paint.getTextSize() * 3;
        BarCode barCode = this.f2674b.getBarCode();
        if (barCode != null) {
            Resources resources = this.f2673a.getResources();
            Intrinsics.e(resources, "context.resources");
            BitmapDrawable bitmap = barCode.getBitmap(resources);
            if (bitmap != null) {
                Bitmap bitmap2 = bitmap.getBitmap();
                Rect rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
                float width = (canvas.getWidth() / 3.0f) / bitmap2.getWidth();
                Rect rect2 = new Rect(0, 0, (int) (bitmap2.getWidth() * width), (int) (bitmap2.getHeight() * width));
                rect2.offset((canvas.getWidth() - rect2.width()) / 2, (int) textSize);
                textSize += rect2.bottom;
                canvas.drawBitmap(bitmap2, rect, rect2, paint);
                if (barCode.getAlternativeText() != null) {
                    String alternativeText = barCode.getAlternativeText();
                    Intrinsics.c(alternativeText);
                    float f2 = 7;
                    canvas.drawText(alternativeText, rect2.centerX(), rect2.bottom + f2 + paint.getTextSize(), paint);
                    textSize += f2 + (paint.getTextSize() * 2);
                }
            }
        }
        Paint paint2 = new Paint();
        paint2.setTextAlign(Paint.Align.RIGHT);
        paint2.setFakeBoldText(true);
        Paint paint3 = new Paint();
        paint3.setTextAlign(Paint.Align.LEFT);
        for (PassField passField : this.f2674b.getFields()) {
            if (!passField.getHide()) {
                canvas.drawText(passField.getLabel() + ": ", canvas.getWidth() / 2.0f, textSize, paint2);
                canvas.drawText(' ' + passField.getValue(), canvas.getWidth() / 2.0f, textSize, paint3);
                double textSize2 = (double) paint.getTextSize();
                Double.isNaN(textSize2);
                textSize += (float) ((int) (textSize2 * 1.5d));
            }
        }
    }

    @Override // android.print.PrintDocumentAdapter
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
        boolean isCanceled;
        PrintDocumentInfo.Builder contentType;
        PrintDocumentInfo.Builder pageCount;
        PrintDocumentInfo build;
        Intrinsics.f(oldAttributes, "oldAttributes");
        Intrinsics.f(newAttributes, "newAttributes");
        Intrinsics.f(cancellationSignal, "cancellationSignal");
        Intrinsics.f(layoutResultCallback, "layoutResultCallback");
        Intrinsics.f(bundle, "bundle");
        isCanceled = cancellationSignal.isCanceled();
        if (isCanceled) {
            layoutResultCallback.onLayoutCancelled();
            return;
        }
        this.f2676d = new PrintedPdfDocument(this.f2673a, newAttributes);
        contentType = new PrintDocumentInfo.Builder(this.f2675c).setContentType(0);
        pageCount = contentType.setPageCount(1);
        build = pageCount.build();
        Intrinsics.e(build, "Builder(jobName).setCont…).setPageCount(1).build()");
        layoutResultCallback.onLayoutFinished(build, true);
    }

    @Override // android.print.PrintDocumentAdapter
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback callback) {
        PdfDocument.Page startPage;
        Canvas canvas;
        PageRange pageRange;
        Intrinsics.f(pageRanges, "pageRanges");
        Intrinsics.f(destination, "destination");
        Intrinsics.f(cancellationSignal, "cancellationSignal");
        Intrinsics.f(callback, "callback");
        PrintedPdfDocument printedPdfDocument = this.f2676d;
        Intrinsics.c(printedPdfDocument);
        startPage = printedPdfDocument.startPage(0);
        Intrinsics.e(startPage, "mPdfDocument!!.startPage(0)");
        canvas = startPage.getCanvas();
        Intrinsics.e(canvas, "canvas");
        a(canvas);
        PrintedPdfDocument printedPdfDocument2 = this.f2676d;
        Intrinsics.c(printedPdfDocument2);
        printedPdfDocument2.finishPage(startPage);
        try {
            try {
                PrintedPdfDocument printedPdfDocument3 = this.f2676d;
                Intrinsics.c(printedPdfDocument3);
                printedPdfDocument3.writeTo(new FileOutputStream(destination.getFileDescriptor()));
                PrintedPdfDocument printedPdfDocument4 = this.f2676d;
                Intrinsics.c(printedPdfDocument4);
                printedPdfDocument4.close();
                this.f2676d = null;
                pageRange = PageRange.ALL_PAGES;
                callback.onWriteFinished(new PageRange[]{pageRange});
            } catch (IOException e2) {
                callback.onWriteFailed(String.valueOf(e2));
                PrintedPdfDocument printedPdfDocument5 = this.f2676d;
                Intrinsics.c(printedPdfDocument5);
                printedPdfDocument5.close();
                this.f2676d = null;
            }
        } catch (Throwable th) {
            PrintedPdfDocument printedPdfDocument6 = this.f2676d;
            Intrinsics.c(printedPdfDocument6);
            printedPdfDocument6.close();
            this.f2676d = null;
            throw th;
        }
    }
}
