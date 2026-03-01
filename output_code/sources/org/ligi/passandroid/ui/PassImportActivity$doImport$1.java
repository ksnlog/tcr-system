package org.ligi.passandroid.ui;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.ligi.kaxt.ContextExtensionsKt;
import org.ligi.passandroid.databinding.ActivityImportBinding;
import org.ligi.passandroid.functions.InputStreamProviderKt;
import org.ligi.passandroid.model.InputStreamWithSource;
import org.ligi.passandroid.model.PassClassifier;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.ui.UnzipPassController;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "org.ligi.passandroid.ui.PassImportActivity$doImport$1", f = "PassImportActivity.kt", l = {47}, m = "invokeSuspend")
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassImportActivity$doImport$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: d  reason: collision with root package name */
    int f2797d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ PassImportActivity f2798e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ boolean f2799f;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "org.ligi.passandroid.ui.PassImportActivity$doImport$1$1", f = "PassImportActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: org.ligi.passandroid.ui.PassImportActivity$doImport$1$1  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: d  reason: collision with root package name */
        int f2800d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ PassImportActivity f2801e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ InputStreamWithSource f2802f;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: org.ligi.passandroid.ui.PassImportActivity$doImport$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
        public static final class C00001 extends Lambda implements Function1<String, Unit> {

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ PassImportActivity f2803d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00001(PassImportActivity passImportActivity) {
                super(1);
                this.f2803d = passImportActivity;
            }

            public final void a(String path) {
                List f2;
                Object o2;
                boolean z2;
                Intrinsics.f(path, "path");
                List<String> c2 = new Regex("/").c(path, 0);
                if (!c2.isEmpty()) {
                    ListIterator<String> listIterator = c2.listIterator(c2.size());
                    while (listIterator.hasPrevious()) {
                        if (listIterator.previous().length() == 0) {
                            z2 = true;
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                        if (!z2) {
                            f2 = CollectionsKt___CollectionsKt.F(c2, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                f2 = CollectionsKt__CollectionsKt.f();
                o2 = ArraysKt___ArraysKt.o(f2.toArray(new String[0]));
                Pass passbookForId = this.f2803d.t0().getPassbookForId((String) o2);
                this.f2803d.t0().setCurrentPass(passbookForId);
                PassClassifier classifier = this.f2803d.t0().getClassifier();
                Intrinsics.c(passbookForId);
                String string = this.f2803d.getString(2131755312);
                Intrinsics.e(string, "getString(R.string.topic_new)");
                classifier.moveToTopic(passbookForId, string);
                ContextExtensionsKt.a(this.f2803d, PassViewActivity.class);
                this.f2803d.finish();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit f(String str) {
                a(str);
                return Unit.f763a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PassImportActivity passImportActivity, InputStreamWithSource inputStreamWithSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.f2801e = passImportActivity;
            this.f2802f = inputStreamWithSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.f2801e, this.f2802f, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [android.app.Activity, org.ligi.passandroid.ui.PassImportActivity] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ActivityImportBinding activityImportBinding;
            IntrinsicsKt__IntrinsicsKt.c();
            if (this.f2800d == 0) {
                ResultKt.b(obj);
                activityImportBinding = this.f2801e.D;
                if (activityImportBinding == null) {
                    Intrinsics.p("binding");
                    activityImportBinding = null;
                }
                activityImportBinding.f2625c.setVisibility(8);
                if (this.f2802f == null) {
                    this.f2801e.finish();
                } else if (this.f2801e.isFinishing()) {
                    InputStreamWithSource inputStreamWithSource = this.f2802f;
                    Application application = this.f2801e.getApplication();
                    Intrinsics.e(application, "application");
                    UnzipPassController.f2888d.e(new UnzipPassController.InputStreamUnzipControllerSpec(inputStreamWithSource, application, this.f2801e.t0(), null, null));
                } else {
                    UnzipPassDialog unzipPassDialog = UnzipPassDialog.f2898a;
                    InputStreamWithSource inputStreamWithSource2 = this.f2802f;
                    ?? r1 = this.f2801e;
                    unzipPassDialog.g(inputStreamWithSource2, r1, r1.t0(), new C00001(this.f2801e));
                }
                return Unit.f763a;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassImportActivity$doImport$1(PassImportActivity passImportActivity, boolean z2, Continuation<? super PassImportActivity$doImport$1> continuation) {
        super(2, continuation);
        this.f2798e = passImportActivity;
        this.f2799f = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PassImportActivity$doImport$1(this.f2798e, this.f2799f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PassImportActivity$doImport$1) create(coroutineScope, continuation)).invokeSuspend(Unit.f763a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object c2;
        boolean z2;
        boolean p2;
        c2 = IntrinsicsKt__IntrinsicsKt.c();
        int i2 = this.f2797d;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    ResultKt.b(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } else {
                ResultKt.b(obj);
                AppCompatActivity appCompatActivity = this.f2798e;
                Intent intent = appCompatActivity.getIntent();
                Intrinsics.c(intent);
                Uri data = intent.getData();
                Intrinsics.c(data);
                InputStreamWithSource c3 = InputStreamProviderKt.c(appCompatActivity, data, this.f2798e.u0());
                MainCoroutineDispatcher c4 = Dispatchers.c();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f2798e, c3, null);
                this.f2797d = 1;
                if (BuildersKt.c(c4, anonymousClass1, this) == c2) {
                    return c2;
                }
            }
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message != null) {
                p2 = StringsKt__StringsKt.p(message, "Permission", false, 2, null);
                if (p2) {
                    z2 = true;
                    if (!z2 && !this.f2799f) {
                        this.f2798e.s0(true);
                    } else {
                        this.f2798e.u0().a("Error in import", e2, false);
                    }
                }
            }
            z2 = false;
            if (!z2) {
            }
            this.f2798e.u0().a("Error in import", e2, false);
        }
        return Unit.f763a;
    }
}
