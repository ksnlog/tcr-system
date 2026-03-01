package org.ligi.passandroid.ui.edit;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.ligi.kaxt.EditTextExtensionsKt;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.PassBarCodeFormat;
import org.ligi.passandroid.ui.BarcodeUIController;
import org.ligi.passandroid.ui.PassViewHelper;
import org.ligi.passandroid.ui.edit.BarcodeEditController;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BarcodeEditController {

    /* renamed from: a  reason: collision with root package name */
    private final View f2924a;

    /* renamed from: b  reason: collision with root package name */
    private final AppCompatActivity f2925b;

    /* renamed from: c  reason: collision with root package name */
    private AppCompatEditText f2926c;

    /* renamed from: d  reason: collision with root package name */
    private AppCompatEditText f2927d;

    /* renamed from: e  reason: collision with root package name */
    private PassBarCodeFormat f2928e;

    /* renamed from: f  reason: collision with root package name */
    private final Fragment f2929f;

    /* renamed from: g  reason: collision with root package name */
    private final Map<PassBarCodeFormat, RadioButton> f2930g;

    /* renamed from: org.ligi.passandroid.ui.edit.BarcodeEditController$3  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class AnonymousClass3 extends Lambda implements Function2<String, String, Unit> {
        AnonymousClass3() {
            super(2);
        }

        public final void a(String newFormat, String newMessage) {
            Intrinsics.f(newFormat, "newFormat");
            Intrinsics.f(newMessage, "newMessage");
            BarcodeEditController.this.f2927d.setText(newMessage);
            Object obj = BarcodeEditController.this.f2930g.get(PassBarCodeFormat.valueOf(newFormat));
            Intrinsics.c(obj);
            ((RadioGroup) BarcodeEditController.this.f2924a.findViewById(2131296350)).check(((RadioButton) obj).getId());
            BarcodeEditController.this.l();
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
            a(str, str2);
            return Unit.f763a;
        }
    }

    /* renamed from: org.ligi.passandroid.ui.edit.BarcodeEditController$5  reason: invalid class name */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static final class AnonymousClass5 extends Lambda implements Function1<Editable, Unit> {
        AnonymousClass5() {
            super(1);
        }

        public final void a(Editable it) {
            Intrinsics.f(it, "it");
            BarcodeEditController.this.l();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit f(Editable editable) {
            a(editable);
            return Unit.f763a;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class IntentFragment extends Fragment {

        /* renamed from: g0  reason: collision with root package name */
        private Function2<? super String, ? super String, Unit> f2933g0 = BarcodeEditController$IntentFragment$scanCallback$1.f2934d;

        public final void R1(Function2<? super String, ? super String, Unit> function2) {
            Intrinsics.f(function2, "<set-?>");
            this.f2933g0 = function2;
        }

        public void m0(int i2, int i3, Intent intent) {
            String stringExtra;
            if (intent == null || (stringExtra = intent.getStringExtra("SCAN_RESULT_FORMAT")) == null) {
                return;
            }
            Intrinsics.e(stringExtra, "dataNotNull.getStringExt…RESULT_FORMAT\") ?: return");
            String stringExtra2 = intent.getStringExtra("SCAN_RESULT");
            if (stringExtra2 == null) {
                return;
            }
            Intrinsics.e(stringExtra2, "dataNotNull.getStringExt…(\"SCAN_RESULT\") ?: return");
            this.f2933g0.invoke(stringExtra, stringExtra2);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2935a;

        static {
            int[] iArr = new int[PassBarCodeFormat.values().length];
            try {
                iArr[PassBarCodeFormat.EAN_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PassBarCodeFormat.EAN_13.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PassBarCodeFormat.ITF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f2935a = iArr;
        }
    }

    public BarcodeEditController(View rootView, AppCompatActivity context, BarCode barCode) {
        Intrinsics.f(rootView, "rootView");
        Intrinsics.f(context, "context");
        Intrinsics.f(barCode, "barCode");
        this.f2924a = rootView;
        this.f2925b = context;
        this.f2930g = new EnumMap(PassBarCodeFormat.class);
        IntentFragment intentFragment = new IntentFragment();
        this.f2929f = intentFragment;
        this.f2928e = barCode.getFormat();
        AppCompatEditText findViewById = rootView.findViewById(2131296559);
        Intrinsics.e(findViewById, "rootView.findViewById(R.id.messageInput)");
        this.f2927d = findViewById;
        AppCompatEditText findViewById2 = rootView.findViewById(2131296335);
        Intrinsics.e(findViewById2, "rootView.findViewById(R.….alternativeMessageInput)");
        this.f2926c = findViewById2;
        rootView.findViewById(2131296648).setOnClickListener(new View.OnClickListener() { // from class: f0.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeEditController.d(BarcodeEditController.this, view);
            }
        });
        rootView.findViewById(2131296664).setOnClickListener(new View.OnClickListener() { // from class: f0.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BarcodeEditController.e(BarcodeEditController.this, view);
            }
        });
        intentFragment.R1(new AnonymousClass3());
        FragmentManager Q = context.Q();
        Intrinsics.e(Q, "context.supportFragmentManager");
        FragmentTransaction p2 = Q.p();
        Intrinsics.e(p2, "beginTransaction()");
        p2.e(intentFragment, "intent_fragment");
        p2.h();
        i(PassBarCodeFormat.values());
        this.f2927d.setText(barCode.getMessage());
        EditTextExtensionsKt.a(this.f2927d, new AnonymousClass5());
        this.f2926c.setText(barCode.getAlternativeText());
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(BarcodeEditController this$0, View view) {
        int i2;
        String b2;
        Intrinsics.f(this$0, "this$0");
        AppCompatEditText appCompatEditText = this$0.f2927d;
        PassBarCodeFormat passBarCodeFormat = this$0.f2928e;
        if (passBarCodeFormat == null) {
            i2 = -1;
        } else {
            i2 = WhenMappings.f2935a[passBarCodeFormat.ordinal()];
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    String uuid = UUID.randomUUID().toString();
                    Intrinsics.e(uuid, "randomUUID().toString()");
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.e(ROOT, "ROOT");
                    b2 = uuid.toUpperCase(ROOT);
                    Intrinsics.e(b2, "this as java.lang.String).toUpperCase(locale)");
                } else {
                    b2 = ITFHelperKt.a();
                }
            } else {
                b2 = EANHelperKt.a();
            }
        } else {
            b2 = EANHelperKt.b();
        }
        appCompatEditText.setText(b2);
        this$0.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(BarcodeEditController this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        BarCodeIntentIntegrator barCodeIntentIntegrator = new BarCodeIntentIntegrator(this$0.f2929f);
        PassBarCodeFormat[] values = PassBarCodeFormat.values();
        ArrayList arrayList = new ArrayList(values.length);
        for (PassBarCodeFormat passBarCodeFormat : values) {
            arrayList.add(passBarCodeFormat.name());
        }
        barCodeIntentIntegrator.f(arrayList);
    }

    private final void i(PassBarCodeFormat[] passBarCodeFormatArr) {
        boolean z2;
        for (final PassBarCodeFormat passBarCodeFormat : passBarCodeFormatArr) {
            RadioButton radioButton = new RadioButton(this.f2925b);
            ((RadioGroup) this.f2924a.findViewById(2131296350)).addView(radioButton);
            this.f2930g.put(passBarCodeFormat, radioButton);
            radioButton.setText(passBarCodeFormat.name());
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: f0.d
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                    BarcodeEditController.j(BarcodeEditController.this, passBarCodeFormat, compoundButton, z3);
                }
            });
            if (this.f2928e == passBarCodeFormat) {
                z2 = true;
            } else {
                z2 = false;
            }
            radioButton.setChecked(z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void j(BarcodeEditController this$0, PassBarCodeFormat it, CompoundButton compoundButton, boolean z2) {
        Intrinsics.f(this$0, "this$0");
        Intrinsics.f(it, "$it");
        if (z2) {
            this$0.f2928e = it;
            this$0.l();
        }
    }

    public final BarCode k() {
        boolean z2;
        BarCode barCode = new BarCode(this.f2928e, String.valueOf(this.f2927d.getText()));
        String valueOf = String.valueOf(this.f2926c.getText());
        if (valueOf.length() > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            barCode.setAlternativeText(valueOf);
        }
        return barCode;
    }

    public final void l() {
        boolean z2;
        View view = this.f2924a;
        BarCode k2 = k();
        AppCompatActivity appCompatActivity = this.f2925b;
        if (new BarcodeUIController(view, k2, appCompatActivity, new PassViewHelper(appCompatActivity)).e().getVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            this.f2927d.setError("Invalid message");
        } else {
            this.f2927d.setError(null);
        }
    }
}
