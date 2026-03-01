package org.ligi.passandroid.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Locale;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.ligi.kaxt.EditTextExtensionsKt;
import org.ligi.passandroid.databinding.EditBinding;
import org.ligi.passandroid.model.PassStore;
import org.ligi.passandroid.model.pass.BarCode;
import org.ligi.passandroid.model.pass.Pass;
import org.ligi.passandroid.model.pass.PassBarCodeFormat;
import org.ligi.passandroid.model.pass.PassImpl;
import org.ligi.passandroid.ui.PassEditActivity;
import org.ligi.passandroid.ui.edit.FieldsEditFragment;
import org.ligi.passandroid.ui.edit.ImageEditHelper;
import org.ligi.passandroid.ui.edit.dialogs.BarcodePickDialogKt;
import org.ligi.passandroid.ui.edit.dialogs.CategoryPickDialogKt;
import org.ligi.passandroid.ui.edit.dialogs.ColorPickDialogKt;
import org.ligi.passandroid.ui.pass_view_holder.EditViewHolder;
import permissions.dispatcher.ktx.ActivityExtensionsKt;
import permissions.dispatcher.ktx.PermissionsRequester;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassEditActivity extends AppCompatActivity {
    private EditBinding D;
    private PassImpl E;
    private final Lazy F;
    private final Lazy G;
    private final Lazy H;
    private final Function0<Unit> I;

    /* JADX WARN: Multi-variable type inference failed */
    public PassEditActivity() {
        Lazy b2;
        Lazy a2;
        Lazy b3;
        b2 = LazyKt__LazyJVMKt.b(new PassEditActivity$imageEditHelper$2(this));
        this.F = b2;
        a2 = LazyKt__LazyJVMKt.a(LazyThreadSafetyMode.SYNCHRONIZED, new PassEditActivity$special$$inlined$inject$default$1(this, null, null));
        this.G = a2;
        b3 = LazyKt__LazyJVMKt.b(new PassEditActivity$passViewHelper$2(this));
        this.H = b3;
        this.I = new PassEditActivity$refreshCallback$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void A0(PassEditActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        Function0<Unit> function0 = this$0.I;
        PassImpl passImpl = this$0.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        PassBarCodeFormat passBarCodeFormat = PassBarCodeFormat.QR_CODE;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.e(uuid, "randomUUID().toString()");
        Locale ROOT = Locale.ROOT;
        Intrinsics.e(ROOT, "ROOT");
        String upperCase = uuid.toUpperCase(ROOT);
        Intrinsics.e(upperCase, "this as java.lang.String).toUpperCase(locale)");
        BarcodePickDialogKt.b(this$0, function0, passImpl, new BarCode(passBarCodeFormat, upperCase));
    }

    private final void B0(int i2) {
        PermissionsRequester a2;
        a2 = ActivityExtensionsKt.a(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, (r13 & 2) != 0 ? null : null, (r13 & 4) != 0 ? null : null, (r13 & 8) != 0 ? null : null, new PassEditActivity$pickWithPermissionCheck$1(this, i2));
        a2.a();
    }

    private final void C0(int i2, int i3, final int i4) {
        int i5;
        String a2 = ImageEditHelper.f2946c.a(i4);
        Intrinsics.c(a2);
        PassImpl passImpl = this.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        Bitmap bitmap = passImpl.getBitmap(w0(), a2);
        View findViewById = findViewById(i3);
        Intrinsics.c(findViewById);
        Button button = (Button) findViewById;
        if (bitmap == null) {
            i5 = 0;
        } else {
            i5 = 8;
        }
        button.setVisibility(i5);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: e0.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassEditActivity.D0(PassEditActivity.this, i4, view);
            }
        };
        ImageView logoImage = (ImageView) findViewById(i2);
        PassViewHelper x0 = x0();
        Intrinsics.e(logoImage, "logoImage");
        x0.e(logoImage, bitmap);
        logoImage.setOnClickListener(onClickListener);
        button.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void D0(PassEditActivity this$0, int i2, View view) {
        Intrinsics.f(this$0, "this$0");
        this$0.B0(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void E0(Pass pass) {
        int i2;
        EditBinding editBinding = this.D;
        EditBinding editBinding2 = null;
        if (editBinding == null) {
            Intrinsics.p("binding");
            editBinding = null;
        }
        CardView cardView = editBinding.f2642m;
        Intrinsics.e(cardView, "binding.passCard");
        new EditViewHolder(cardView).Q(pass, w0(), this);
        C0(2131296515, 2131296329, 5556);
        C0(2131296734, 2131296330, 5558);
        C0(2131296462, 2131296328, 5560);
        EditBinding editBinding3 = this.D;
        if (editBinding3 == null) {
            Intrinsics.p("binding");
        } else {
            editBinding2 = editBinding3;
        }
        Button button = editBinding2.f2632c;
        if (pass.getBarCode() == null) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        button.setVisibility(i2);
        View decorView = getWindow().getDecorView();
        Intrinsics.e(decorView, "window.decorView");
        new BarcodeUIController(decorView, pass.getBarCode(), this, x0()).e().setOnClickListener(new View.OnClickListener() { // from class: e0.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassEditActivity.F0(PassEditActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void F0(PassEditActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        Function0<Unit> function0 = this$0.I;
        PassImpl passImpl = this$0.E;
        PassImpl passImpl2 = null;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        PassImpl passImpl3 = this$0.E;
        if (passImpl3 == null) {
            Intrinsics.p("currentPass");
        } else {
            passImpl2 = passImpl3;
        }
        BarCode barCode = passImpl2.getBarCode();
        Intrinsics.c(barCode);
        BarcodePickDialogKt.b(this$0, function0, passImpl, barCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageEditHelper v0() {
        return (ImageEditHelper) this.F.getValue();
    }

    private final PassViewHelper x0() {
        return (PassViewHelper) this.H.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void y0(final PassEditActivity this$0, View view) {
        Intrinsics.f(this$0, "this$0");
        new AlertDialog.Builder(this$0).h(2130903040, new DialogInterface.OnClickListener() { // from class: e0.q
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                PassEditActivity.z0(PassEditActivity.this, dialogInterface, i2);
            }
        }).z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void z0(PassEditActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.f(this$0, "this$0");
        PassImpl passImpl = null;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    this$0.B0(5557);
                    return;
                }
                return;
            }
            PassImpl passImpl2 = this$0.E;
            if (passImpl2 == null) {
                Intrinsics.p("currentPass");
            } else {
                passImpl = passImpl2;
            }
            ColorPickDialogKt.b(this$0, passImpl, this$0.I);
            return;
        }
        PassImpl passImpl3 = this$0.E;
        if (passImpl3 == null) {
            Intrinsics.p("currentPass");
        } else {
            passImpl = passImpl3;
        }
        CategoryPickDialogKt.c(this$0, passImpl, this$0.I);
    }

    protected void Z() {
        super/*androidx.fragment.app.FragmentActivity*/.Z();
        PassImpl passImpl = this.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        E0(passImpl);
    }

    protected void onActivityResult(int i2, int i3, Intent intent) {
        super/*androidx.fragment.app.FragmentActivity*/.onActivityResult(i2, i3, intent);
        v0().b(i2, i3, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*androidx.fragment.app.FragmentActivity*/.onCreate(bundle);
        EditBinding c2 = EditBinding.c(getLayoutInflater());
        Intrinsics.e(c2, "inflate(layoutInflater)");
        this.D = c2;
        EditBinding editBinding = null;
        if (c2 == null) {
            Intrinsics.p("binding");
            c2 = null;
        }
        setContentView(c2.b());
        EditBinding editBinding2 = this.D;
        if (editBinding2 == null) {
            Intrinsics.p("binding");
            editBinding2 = null;
        }
        editBinding2.f2636g.setOnClickListener(new View.OnClickListener() { // from class: e0.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassEditActivity.y0(PassEditActivity.this, view);
            }
        });
        EditBinding editBinding3 = this.D;
        if (editBinding3 == null) {
            Intrinsics.p("binding");
            editBinding3 = null;
        }
        EditText editText = editBinding3.f2644o;
        Intrinsics.e(editText, "binding.passTitle");
        EditTextExtensionsKt.a(editText, new PassEditActivity$onCreate$2(this));
        Pass currentPass = w0().getCurrentPass();
        if (currentPass != null) {
            this.E = (PassImpl) currentPass;
        } else {
            finish();
        }
        ActionBar b02 = b0();
        if (b02 != null) {
            b02.s(true);
        }
        FragmentManager supportFragmentManager = Q();
        Intrinsics.e(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction p2 = supportFragmentManager.p();
        Intrinsics.e(p2, "beginTransaction()");
        FieldsEditFragment.Companion companion = FieldsEditFragment.j0;
        p2.b(2131296384, companion.a(false));
        p2.b(2131296385, companion.a(true));
        p2.h();
        EditBinding editBinding4 = this.D;
        if (editBinding4 == null) {
            Intrinsics.p("binding");
        } else {
            editBinding = editBinding4;
        }
        editBinding.f2632c.setOnClickListener(new View.OnClickListener() { // from class: e0.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PassEditActivity.A0(PassEditActivity.this, view);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.f(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super/*android.app.Activity*/.onOptionsItemSelected(item);
    }

    protected void onPause() {
        PassStore w0 = w0();
        PassImpl passImpl = this.E;
        if (passImpl == null) {
            Intrinsics.p("currentPass");
            passImpl = null;
        }
        w0.save(passImpl);
        w0().notifyChange();
        super/*androidx.fragment.app.FragmentActivity*/.onPause();
    }

    public final PassStore w0() {
        return (PassStore) this.G.getValue();
    }
}
