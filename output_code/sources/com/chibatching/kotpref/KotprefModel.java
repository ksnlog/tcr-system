package com.chibatching.kotpref;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.chibatching.kotpref.KotprefPreferences;
import com.chibatching.kotpref.pref.AbstractPref;
import com.chibatching.kotpref.pref.AbstractStringSetPref;
import com.chibatching.kotpref.pref.BooleanPref;
import com.chibatching.kotpref.pref.FloatPref;
import com.chibatching.kotpref.pref.IntPref;
import com.chibatching.kotpref.pref.LongPref;
import com.chibatching.kotpref.pref.PreferenceProperty;
import com.chibatching.kotpref.pref.StringNullablePref;
import com.chibatching.kotpref.pref.StringPref;
import com.chibatching.kotpref.pref.StringSetPref;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class KotprefModel {
    private final boolean commitAllPropertiesByDefault;
    private final ContextProvider contextProvider;
    private KotprefPreferences.KotprefEditor kotprefEditor;
    private boolean kotprefInTransaction;
    private final int kotprefMode;
    private final String kotprefName;
    private final Lazy kotprefPreference$delegate;
    private final Map<String, PreferenceProperty> kotprefProperties;
    private long kotprefTransactionStartTime;
    private final PreferencesProvider preferencesProvider;

    public KotprefModel() {
        this((ContextProvider) null, (PreferencesProvider) null, 3, (DefaultConstructorMarker) null);
    }

    public KotprefModel(ContextProvider contextProvider, PreferencesProvider preferencesProvider) {
        Lazy b2;
        Intrinsics.f(contextProvider, "contextProvider");
        Intrinsics.f(preferencesProvider, "preferencesProvider");
        this.contextProvider = contextProvider;
        this.preferencesProvider = preferencesProvider;
        this.kotprefTransactionStartTime = Long.MAX_VALUE;
        this.kotprefProperties = new LinkedHashMap();
        String simpleName = getClass().getSimpleName();
        Intrinsics.e(simpleName, "javaClass.simpleName");
        this.kotprefName = simpleName;
        b2 = LazyKt__LazyJVMKt.b(new KotprefModel$kotprefPreference$2(this));
        this.kotprefPreference$delegate = b2;
    }

    public static /* synthetic */ AbstractPref booleanPref$default(KotprefModel kotprefModel, boolean z2, String str, boolean z3, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                z2 = false;
            }
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                z3 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.booleanPref(z2, str, z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: booleanPref");
    }

    public static /* synthetic */ AbstractPref floatPref$default(KotprefModel kotprefModel, float f2, String str, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                f2 = 0.0f;
            }
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.floatPref(f2, str, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: floatPref");
    }

    public static /* synthetic */ AbstractPref intPref$default(KotprefModel kotprefModel, int i2, String str, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                i2 = 0;
            }
            if ((i3 & 2) != 0) {
                str = null;
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.intPref(i2, str, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: intPref");
    }

    public static /* synthetic */ AbstractPref longPref$default(KotprefModel kotprefModel, long j2, String str, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                j2 = 0;
            }
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.longPref(j2, str, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: longPref");
    }

    public static /* synthetic */ AbstractPref nullableStringPref$default(KotprefModel kotprefModel, String str, String str2, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            if ((i2 & 2) != 0) {
                str2 = null;
            }
            if ((i2 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.nullableStringPref(str, str2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nullableStringPref");
    }

    public static /* synthetic */ AbstractPref stringPref$default(KotprefModel kotprefModel, String str, String str2, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = "";
            }
            if ((i2 & 2) != 0) {
                str2 = null;
            }
            if ((i2 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringPref(str, str2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringPref");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AbstractStringSetPref stringSetPref$default(KotprefModel kotprefModel, Set set, String str, boolean z2, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                set = new LinkedHashSet();
            }
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringSetPref(set, str, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringSetPref");
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void beginBulkEdit() {
        this.kotprefInTransaction = true;
        this.kotprefTransactionStartTime = SystemClock.uptimeMillis();
        this.kotprefEditor = new KotprefPreferences.KotprefEditor(getKotprefPreference$kotpref_release(), getKotprefPreference$kotpref_release().edit());
    }

    public final void blockingCommitBulkEdit() {
        KotprefPreferences.KotprefEditor kotprefEditor = this.kotprefEditor;
        Intrinsics.c(kotprefEditor);
        kotprefEditor.commit();
        this.kotprefInTransaction = false;
    }

    protected final AbstractPref<Boolean> booleanPref(boolean z2, String str, boolean z3) {
        return new BooleanPref(z2, str, z3);
    }

    public final void cancelBulkEdit() {
        this.kotprefEditor = null;
        this.kotprefInTransaction = false;
    }

    public void clear() {
        beginBulkEdit();
        KotprefPreferences.KotprefEditor kotprefEditor = this.kotprefEditor;
        Intrinsics.c(kotprefEditor);
        kotprefEditor.clear();
        commitBulkEdit();
    }

    public final void commitBulkEdit() {
        KotprefPreferences.KotprefEditor kotprefEditor = this.kotprefEditor;
        Intrinsics.c(kotprefEditor);
        kotprefEditor.apply();
        this.kotprefInTransaction = false;
    }

    protected final AbstractPref<Float> floatPref(float f2, String str, boolean z2) {
        return new FloatPref(f2, str, z2);
    }

    public boolean getCommitAllPropertiesByDefault() {
        return this.commitAllPropertiesByDefault;
    }

    public final Context getContext() {
        return this.contextProvider.a();
    }

    public final KotprefPreferences.KotprefEditor getKotprefEditor$kotpref_release() {
        return this.kotprefEditor;
    }

    public final boolean getKotprefInTransaction$kotpref_release() {
        return this.kotprefInTransaction;
    }

    public int getKotprefMode() {
        return this.kotprefMode;
    }

    public String getKotprefName() {
        return this.kotprefName;
    }

    public final KotprefPreferences getKotprefPreference$kotpref_release() {
        return (KotprefPreferences) this.kotprefPreference$delegate.getValue();
    }

    public final Map<String, PreferenceProperty> getKotprefProperties$kotpref_release() {
        return this.kotprefProperties;
    }

    public final long getKotprefTransactionStartTime$kotpref_release() {
        return this.kotprefTransactionStartTime;
    }

    public final String getPrefKey(KProperty<?> property) {
        Intrinsics.f(property, "property");
        PreferenceProperty preferenceProperty = this.kotprefProperties.get(property.getName());
        if (preferenceProperty != null) {
            return preferenceProperty.c();
        }
        return null;
    }

    public final SharedPreferences getPreferences() {
        return getKotprefPreference$kotpref_release();
    }

    protected final AbstractPref<Integer> intPref(int i2, String str, boolean z2) {
        return new IntPref(i2, str, z2);
    }

    protected final AbstractPref<Long> longPref(long j2, String str, boolean z2) {
        return new LongPref(j2, str, z2);
    }

    protected final AbstractPref<String> nullableStringPref(String str, String str2, boolean z2) {
        return new StringNullablePref(str, str2, z2);
    }

    @SuppressLint({"ApplySharedPref"})
    public final void remove(KProperty<?> property) {
        Intrinsics.f(property, "property");
        SharedPreferences.Editor remove = getPreferences().edit().remove(getPrefKey(property));
        if (getCommitAllPropertiesByDefault()) {
            remove.commit();
        } else {
            remove.apply();
        }
    }

    public final void setKotprefEditor$kotpref_release(KotprefPreferences.KotprefEditor kotprefEditor) {
        this.kotprefEditor = kotprefEditor;
    }

    public final void setKotprefInTransaction$kotpref_release(boolean z2) {
        this.kotprefInTransaction = z2;
    }

    public final void setKotprefTransactionStartTime$kotpref_release(long j2) {
        this.kotprefTransactionStartTime = j2;
    }

    protected final AbstractPref<String> stringPref(String str, String str2, boolean z2) {
        Intrinsics.f(str, "default");
        return new StringPref(str, str2, z2);
    }

    @TargetApi(R$styleable.f1331g)
    protected final AbstractStringSetPref stringSetPref(Set<String> set, String str, boolean z2) {
        Intrinsics.f(set, "default");
        return stringSetPref(str, z2, new KotprefModel$stringSetPref$1(set));
    }

    protected final AbstractPref<Boolean> booleanPref(boolean z2, int i2, boolean z3) {
        return booleanPref(z2, getContext().getString(i2), z3);
    }

    protected final AbstractPref<Float> floatPref(float f2, int i2, boolean z2) {
        return floatPref(f2, getContext().getString(i2), z2);
    }

    protected final AbstractPref<Integer> intPref(int i2, int i3, boolean z2) {
        return intPref(i2, getContext().getString(i3), z2);
    }

    protected final AbstractPref<Long> longPref(long j2, int i2, boolean z2) {
        return longPref(j2, getContext().getString(i2), z2);
    }

    protected final AbstractPref<String> nullableStringPref(String str, int i2, boolean z2) {
        return nullableStringPref(str, getContext().getString(i2), z2);
    }

    protected final AbstractPref<String> stringPref(String str, int i2, boolean z2) {
        Intrinsics.f(str, "default");
        return stringPref(str, getContext().getString(i2), z2);
    }

    @TargetApi(R$styleable.f1331g)
    protected final AbstractStringSetPref stringSetPref(Set<String> set, int i2, boolean z2) {
        Intrinsics.f(set, "default");
        return stringSetPref(getContext().getString(i2), z2, new KotprefModel$stringSetPref$2(set));
    }

    public static /* synthetic */ AbstractPref booleanPref$default(KotprefModel kotprefModel, boolean z2, int i2, boolean z3, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                z2 = false;
            }
            if ((i3 & 4) != 0) {
                z3 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.booleanPref(z2, i2, z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: booleanPref");
    }

    public static /* synthetic */ AbstractPref floatPref$default(KotprefModel kotprefModel, float f2, int i2, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                f2 = 0.0f;
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.floatPref(f2, i2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: floatPref");
    }

    public static /* synthetic */ AbstractPref intPref$default(KotprefModel kotprefModel, int i2, int i3, boolean z2, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 1) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.intPref(i2, i3, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: intPref");
    }

    public static /* synthetic */ AbstractPref longPref$default(KotprefModel kotprefModel, long j2, int i2, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                j2 = 0;
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.longPref(j2, i2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: longPref");
    }

    public static /* synthetic */ AbstractPref nullableStringPref$default(KotprefModel kotprefModel, String str, int i2, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                str = null;
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.nullableStringPref(str, i2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nullableStringPref");
    }

    public static /* synthetic */ AbstractPref stringPref$default(KotprefModel kotprefModel, String str, int i2, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                str = "";
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringPref(str, i2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringPref");
    }

    @TargetApi(R$styleable.f1331g)
    protected final AbstractStringSetPref stringSetPref(String str, boolean z2, Function0<? extends Set<String>> function0) {
        Intrinsics.f(function0, "default");
        return new StringSetPref(function0, str, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AbstractStringSetPref stringSetPref$default(KotprefModel kotprefModel, Set set, int i2, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                set = new LinkedHashSet();
            }
            if ((i3 & 4) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringSetPref(set, i2, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringSetPref");
    }

    @TargetApi(R$styleable.f1331g)
    protected final AbstractStringSetPref stringSetPref(int i2, boolean z2, Function0<? extends Set<String>> function0) {
        Intrinsics.f(function0, "default");
        return stringSetPref(getContext().getString(i2), z2, function0);
    }

    public /* synthetic */ KotprefModel(ContextProvider contextProvider, PreferencesProvider preferencesProvider, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? StaticContextProvider.f20b : contextProvider, (i2 & 2) != 0 ? PreferencesProviderKt.a() : preferencesProvider);
    }

    public static /* synthetic */ AbstractStringSetPref stringSetPref$default(KotprefModel kotprefModel, String str, boolean z2, Function0 function0, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            if ((i2 & 2) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringSetPref(str, z2, function0);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringSetPref");
    }

    public /* synthetic */ KotprefModel(Context context, PreferencesProvider preferencesProvider, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? PreferencesProviderKt.a() : preferencesProvider);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KotprefModel(final Context context, PreferencesProvider preferencesProvider) {
        this(new ContextProvider() { // from class: com.chibatching.kotpref.KotprefModel.1
            @Override // com.chibatching.kotpref.ContextProvider
            public final Context a() {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.e(applicationContext, "context.applicationContext");
                return applicationContext;
            }
        }, preferencesProvider);
        Intrinsics.f(context, "context");
        Intrinsics.f(preferencesProvider, "preferencesProvider");
    }

    public static /* synthetic */ AbstractStringSetPref stringSetPref$default(KotprefModel kotprefModel, int i2, boolean z2, Function0 function0, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                z2 = kotprefModel.getCommitAllPropertiesByDefault();
            }
            return kotprefModel.stringSetPref(i2, z2, function0);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringSetPref");
    }
}
