package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.BufferedSink;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class JsonWriter implements Closeable, Flushable {

    /* renamed from: h  reason: collision with root package name */
    String f556h;

    /* renamed from: i  reason: collision with root package name */
    boolean f557i;

    /* renamed from: j  reason: collision with root package name */
    boolean f558j;

    /* renamed from: k  reason: collision with root package name */
    boolean f559k;

    /* renamed from: d  reason: collision with root package name */
    int f552d = 0;

    /* renamed from: e  reason: collision with root package name */
    int[] f553e = new int[32];

    /* renamed from: f  reason: collision with root package name */
    String[] f554f = new String[32];

    /* renamed from: g  reason: collision with root package name */
    int[] f555g = new int[32];

    /* renamed from: l  reason: collision with root package name */
    int f560l = -1;

    @CheckReturnValue
    public static JsonWriter z(BufferedSink bufferedSink) {
        return new JsonUtf8Writer(bufferedSink);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int B() {
        int i2 = this.f552d;
        if (i2 != 0) {
            return this.f553e[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void D() {
        int B = B();
        if (B != 5 && B != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.f559k = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void E(int i2) {
        int[] iArr = this.f553e;
        int i3 = this.f552d;
        this.f552d = i3 + 1;
        iArr[i3] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void G(int i2) {
        this.f553e[this.f552d - 1] = i2;
    }

    public void I(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.f556h = str;
    }

    public final void J(boolean z2) {
        this.f557i = z2;
    }

    public final void O(boolean z2) {
        this.f558j = z2;
    }

    public abstract JsonWriter Q(double d2);

    public abstract JsonWriter R(long j2);

    public abstract JsonWriter S(@Nullable Number number);

    public abstract JsonWriter T(@Nullable String str);

    public abstract JsonWriter U(boolean z2);

    public abstract JsonWriter b();

    public abstract JsonWriter c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean e() {
        int i2 = this.f552d;
        int[] iArr = this.f553e;
        if (i2 != iArr.length) {
            return false;
        }
        if (i2 != 256) {
            this.f553e = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f554f;
            this.f554f = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f555g;
            this.f555g = Arrays.copyOf(iArr2, iArr2.length * 2);
            if (this instanceof JsonValueWriter) {
                JsonValueWriter jsonValueWriter = (JsonValueWriter) this;
                Object[] objArr = jsonValueWriter.f550m;
                jsonValueWriter.f550m = Arrays.copyOf(objArr, objArr.length * 2);
                return true;
            }
            return true;
        }
        throw new JsonDataException("Nesting too deep at " + o() + ": circular reference?");
    }

    public abstract JsonWriter k();

    public abstract JsonWriter m();

    @CheckReturnValue
    public final String n() {
        String str = this.f556h;
        return str != null ? str : "";
    }

    @CheckReturnValue
    public final String o() {
        return JsonScope.a(this.f552d, this.f553e, this.f554f, this.f555g);
    }

    @CheckReturnValue
    public final boolean p() {
        return this.f558j;
    }

    @CheckReturnValue
    public final boolean r() {
        return this.f557i;
    }

    public abstract JsonWriter w(String str);

    public abstract JsonWriter x();
}
