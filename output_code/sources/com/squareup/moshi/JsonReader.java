package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public abstract class JsonReader implements Closeable {

    /* renamed from: d  reason: collision with root package name */
    int f511d;

    /* renamed from: e  reason: collision with root package name */
    int[] f512e = new int[32];

    /* renamed from: f  reason: collision with root package name */
    String[] f513f = new String[32];

    /* renamed from: g  reason: collision with root package name */
    int[] f514g = new int[32];

    /* renamed from: h  reason: collision with root package name */
    boolean f515h;

    /* renamed from: i  reason: collision with root package name */
    boolean f516i;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Options {

        /* renamed from: a  reason: collision with root package name */
        final String[] f517a;

        /* renamed from: b  reason: collision with root package name */
        final okio.Options f518b;

        private Options(String[] strArr, okio.Options options) {
            this.f517a = strArr;
            this.f518b = options;
        }

        @CheckReturnValue
        public static Options a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                Buffer buffer = new Buffer();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    JsonUtf8Writer.a0(buffer, strArr[i2]);
                    buffer.readByte();
                    byteStringArr[i2] = buffer.B();
                }
                return new Options((String[]) strArr.clone(), okio.Options.h(byteStringArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    @CheckReturnValue
    public static JsonReader E(BufferedSource bufferedSource) {
        return new JsonUtf8Reader(bufferedSource);
    }

    @Nullable
    public abstract <T> T B();

    public abstract String D();

    @CheckReturnValue
    public abstract Token G();

    public abstract void I();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void J(int i2) {
        int i3 = this.f511d;
        int[] iArr = this.f512e;
        if (i3 == iArr.length) {
            if (i3 != 256) {
                this.f512e = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.f513f;
                this.f513f = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.f514g;
                this.f514g = Arrays.copyOf(iArr2, iArr2.length * 2);
            } else {
                throw new JsonDataException("Nesting too deep at " + n());
            }
        }
        int[] iArr3 = this.f512e;
        int i4 = this.f511d;
        this.f511d = i4 + 1;
        iArr3[i4] = i2;
    }

    @CheckReturnValue
    public abstract int O(Options options);

    @CheckReturnValue
    public abstract int Q(Options options);

    public final void R(boolean z2) {
        this.f516i = z2;
    }

    public final void S(boolean z2) {
        this.f515h = z2;
    }

    public abstract void T();

    public abstract void U();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final JsonEncodingException V(String str) {
        throw new JsonEncodingException(str + " at path " + n());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final JsonDataException W(@Nullable Object obj, Object obj2) {
        if (obj == null) {
            return new JsonDataException("Expected " + obj2 + " but was null at path " + n());
        }
        return new JsonDataException("Expected " + obj2 + " but was " + obj + ", a " + obj.getClass().getName() + ", at path " + n());
    }

    public abstract void b();

    public abstract void c();

    public abstract void e();

    public abstract void k();

    @CheckReturnValue
    public final boolean m() {
        return this.f516i;
    }

    @CheckReturnValue
    public final String n() {
        return JsonScope.a(this.f511d, this.f512e, this.f513f, this.f514g);
    }

    @CheckReturnValue
    public abstract boolean o();

    @CheckReturnValue
    public final boolean p() {
        return this.f515h;
    }

    public abstract boolean r();

    public abstract double w();

    public abstract int x();

    public abstract long z();
}
