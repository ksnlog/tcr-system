package org.ligi.tracedroid.logging;

import java.util.ArrayDeque;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class BufferedLogTree extends Timber.Tree {

    /* renamed from: b  reason: collision with root package name */
    private final ArrayDeque<String> f2995b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2996c;

    public BufferedLogTree() {
        this(0, 1, null);
    }

    public BufferedLogTree(int i2) {
        this.f2996c = i2;
        this.f2995b = new ArrayDeque<>(i2 + 1);
    }

    @Override // timber.log.Timber.Tree
    protected void i(int i2, String str, String message, Throwable th) {
        String str2;
        Intrinsics.f(message, "message");
        StringBuilder sb = new StringBuilder(message.length() + 16);
        if (i2 != 4) {
            if (i2 != 5) {
                if (i2 != 6) {
                    str2 = String.valueOf(i2);
                } else {
                    str2 = "E";
                }
            } else {
                str2 = "W";
            }
        } else {
            str2 = "I";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append(message);
        if (th != null) {
            sb.append(" throwable: ");
            sb.append(th.getMessage());
        }
        String sb2 = sb.toString();
        Intrinsics.e(sb2, "StringBuilder(capacity).…builderAction).toString()");
        synchronized (this.f2995b) {
            this.f2995b.addLast(sb2);
            if (this.f2995b.size() > this.f2996c) {
                this.f2995b.removeFirst();
            }
            Unit unit = Unit.f763a;
        }
    }

    public final ArrayDeque<String> m() {
        return this.f2995b;
    }

    public /* synthetic */ BufferedLogTree(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 200 : i2);
    }
}
