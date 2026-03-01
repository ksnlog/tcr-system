package j;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final /* synthetic */ class a implements LifecycleEventObserver {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ SavedStateRegistry f744d;

    public final void d(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        SavedStateRegistry.a(this.f744d, lifecycleOwner, event);
    }
}
