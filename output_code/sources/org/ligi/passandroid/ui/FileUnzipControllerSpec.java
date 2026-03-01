package org.ligi.passandroid.ui;

import kotlin.jvm.internal.Intrinsics;
import org.ligi.passandroid.ui.UnzipPassController;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class FileUnzipControllerSpec extends UnzipControllerSpec {

    /* renamed from: g  reason: collision with root package name */
    private final String f2741g;

    /* renamed from: h  reason: collision with root package name */
    private final String f2742h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileUnzipControllerSpec(String zipFileString, UnzipPassController.InputStreamUnzipControllerSpec spec) {
        super(spec.f(), spec.a(), spec.e(), spec.c(), spec.b());
        Intrinsics.f(zipFileString, "zipFileString");
        Intrinsics.f(spec, "spec");
        this.f2741g = zipFileString;
        this.f2742h = spec.h().getSource();
        g(spec.d());
    }

    public final String h() {
        return this.f2742h;
    }

    public final String i() {
        return this.f2741g;
    }
}
