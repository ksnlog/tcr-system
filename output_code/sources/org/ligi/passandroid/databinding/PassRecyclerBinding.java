package org.ligi.passandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class PassRecyclerBinding {

    /* renamed from: a  reason: collision with root package name */
    private final RecyclerView f2669a;

    /* renamed from: b  reason: collision with root package name */
    public final RecyclerView f2670b;

    private PassRecyclerBinding(RecyclerView recyclerView, RecyclerView recyclerView2) {
        this.f2669a = recyclerView;
        this.f2670b = recyclerView2;
    }

    public static PassRecyclerBinding a(View view) {
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view;
            return new PassRecyclerBinding(recyclerView, recyclerView);
        }
        throw new NullPointerException("rootView");
    }

    public static PassRecyclerBinding c(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z2) {
        View inflate = layoutInflater.inflate(2131492998, viewGroup, false);
        if (z2) {
            viewGroup.addView(inflate);
        }
        return a(inflate);
    }

    public RecyclerView b() {
        return this.f2669a;
    }
}
