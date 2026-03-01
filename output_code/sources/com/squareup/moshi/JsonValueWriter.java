package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class JsonValueWriter extends JsonWriter {

    /* renamed from: m  reason: collision with root package name */
    Object[] f550m = new Object[32];
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private String f551n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonValueWriter() {
        E(6);
    }

    private JsonValueWriter V(@Nullable Object obj) {
        String str;
        Object put;
        int B = B();
        int i2 = this.f552d;
        if (i2 == 1) {
            if (B == 6) {
                this.f553e[i2 - 1] = 7;
                this.f550m[i2 - 1] = obj;
            } else {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
        } else if (B == 3 && (str = this.f551n) != null) {
            if ((obj == null && !this.f558j) || (put = ((Map) this.f550m[i2 - 1]).put(str, obj)) == null) {
                this.f551n = null;
            } else {
                throw new IllegalArgumentException("Map key '" + this.f551n + "' has multiple values at path " + o() + ": " + put + " and " + obj);
            }
        } else if (B == 1) {
            ((List) this.f550m[i2 - 1]).add(obj);
        } else if (B == 9) {
            throw new IllegalStateException("Sink from valueSink() was not closed");
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter Q(double d2) {
        if (!this.f557i && (Double.isNaN(d2) || d2 == Double.NEGATIVE_INFINITY || d2 == Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        } else if (this.f559k) {
            this.f559k = false;
            return w(Double.toString(d2));
        } else {
            V(Double.valueOf(d2));
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter R(long j2) {
        if (this.f559k) {
            this.f559k = false;
            return w(Long.toString(j2));
        }
        V(Long.valueOf(j2));
        int[] iArr = this.f555g;
        int i2 = this.f552d - 1;
        iArr[i2] = iArr[i2] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter S(@Nullable Number number) {
        BigDecimal bigDecimal;
        if (!(number instanceof Byte) && !(number instanceof Short) && !(number instanceof Integer) && !(number instanceof Long)) {
            if (!(number instanceof Float) && !(number instanceof Double)) {
                if (number == null) {
                    return x();
                }
                if (number instanceof BigDecimal) {
                    bigDecimal = (BigDecimal) number;
                } else {
                    bigDecimal = new BigDecimal(number.toString());
                }
                if (this.f559k) {
                    this.f559k = false;
                    return w(bigDecimal.toString());
                }
                V(bigDecimal);
                int[] iArr = this.f555g;
                int i2 = this.f552d - 1;
                iArr[i2] = iArr[i2] + 1;
                return this;
            }
            return Q(number.doubleValue());
        }
        return R(number.longValue());
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter T(@Nullable String str) {
        if (this.f559k) {
            this.f559k = false;
            return w(str);
        }
        V(str);
        int[] iArr = this.f555g;
        int i2 = this.f552d - 1;
        iArr[i2] = iArr[i2] + 1;
        return this;
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter U(boolean z2) {
        if (!this.f559k) {
            V(Boolean.valueOf(z2));
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + o());
    }

    public Object W() {
        int i2 = this.f552d;
        if (i2 <= 1 && (i2 != 1 || this.f553e[i2 - 1] == 7)) {
            return this.f550m[0];
        }
        throw new IllegalStateException("Incomplete document");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter b() {
        if (!this.f559k) {
            int i2 = this.f552d;
            int i3 = this.f560l;
            if (i2 == i3 && this.f553e[i2 - 1] == 1) {
                this.f560l = i3 ^ (-1);
                return this;
            }
            e();
            ArrayList arrayList = new ArrayList();
            V(arrayList);
            Object[] objArr = this.f550m;
            int i4 = this.f552d;
            objArr[i4] = arrayList;
            this.f555g[i4] = 0;
            E(1);
            return this;
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + o());
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter c() {
        if (!this.f559k) {
            int i2 = this.f552d;
            int i3 = this.f560l;
            if (i2 == i3 && this.f553e[i2 - 1] == 3) {
                this.f560l = i3 ^ (-1);
                return this;
            }
            e();
            LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
            V(linkedHashTreeMap);
            this.f550m[this.f552d] = linkedHashTreeMap;
            E(3);
            return this;
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + o());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i2 = this.f552d;
        if (i2 <= 1 && (i2 != 1 || this.f553e[i2 - 1] == 7)) {
            this.f552d = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    @Override // java.io.Flushable
    public void flush() {
        if (this.f552d != 0) {
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter k() {
        if (B() == 1) {
            int i2 = this.f552d;
            int i3 = this.f560l;
            if (i2 == (i3 ^ (-1))) {
                this.f560l = i3 ^ (-1);
                return this;
            }
            int i4 = i2 - 1;
            this.f552d = i4;
            this.f550m[i4] = null;
            int[] iArr = this.f555g;
            int i5 = i4 - 1;
            iArr[i5] = iArr[i5] + 1;
            return this;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter m() {
        if (B() == 3) {
            if (this.f551n == null) {
                int i2 = this.f552d;
                int i3 = this.f560l;
                if (i2 == (i3 ^ (-1))) {
                    this.f560l = i3 ^ (-1);
                    return this;
                }
                this.f559k = false;
                int i4 = i2 - 1;
                this.f552d = i4;
                this.f550m[i4] = null;
                this.f554f[i4] = null;
                int[] iArr = this.f555g;
                int i5 = i4 - 1;
                iArr[i5] = iArr[i5] + 1;
                return this;
            }
            throw new IllegalStateException("Dangling name: " + this.f551n);
        }
        throw new IllegalStateException("Nesting problem.");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter w(String str) {
        if (str != null) {
            if (this.f552d != 0) {
                if (B() == 3 && this.f551n == null && !this.f559k) {
                    this.f551n = str;
                    this.f554f[this.f552d - 1] = str;
                    return this;
                }
                throw new IllegalStateException("Nesting problem.");
            }
            throw new IllegalStateException("JsonWriter is closed.");
        }
        throw new NullPointerException("name == null");
    }

    @Override // com.squareup.moshi.JsonWriter
    public JsonWriter x() {
        if (!this.f559k) {
            V(null);
            int[] iArr = this.f555g;
            int i2 = this.f552d - 1;
            iArr[i2] = iArr[i2] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + o());
    }
}
