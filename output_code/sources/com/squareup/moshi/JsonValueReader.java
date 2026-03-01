package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class JsonValueReader extends JsonReader {

    /* renamed from: k  reason: collision with root package name */
    private static final Object f545k = new Object();

    /* renamed from: j  reason: collision with root package name */
    private Object[] f546j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class JsonIterator implements Iterator<Object>, Cloneable {

        /* renamed from: d  reason: collision with root package name */
        final JsonReader.Token f547d;

        /* renamed from: e  reason: collision with root package name */
        final Object[] f548e;

        /* renamed from: f  reason: collision with root package name */
        int f549f;

        JsonIterator(JsonReader.Token token, Object[] objArr, int i2) {
            this.f547d = token;
            this.f548e = objArr;
            this.f549f = i2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public JsonIterator clone() {
            return new JsonIterator(this.f547d, this.f548e, this.f549f);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f549f < this.f548e.length;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object[] objArr = this.f548e;
            int i2 = this.f549f;
            this.f549f = i2 + 1;
            return objArr[i2];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonValueReader(Object obj) {
        int[] iArr = this.f512e;
        int i2 = this.f511d;
        iArr[i2] = 7;
        Object[] objArr = new Object[32];
        this.f546j = objArr;
        this.f511d = i2 + 1;
        objArr[i2] = obj;
    }

    private void Y(Object obj) {
        int i2 = this.f511d;
        if (i2 == this.f546j.length) {
            if (i2 != 256) {
                int[] iArr = this.f512e;
                this.f512e = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.f513f;
                this.f513f = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.f514g;
                this.f514g = Arrays.copyOf(iArr2, iArr2.length * 2);
                Object[] objArr = this.f546j;
                this.f546j = Arrays.copyOf(objArr, objArr.length * 2);
            } else {
                throw new JsonDataException("Nesting too deep at " + n());
            }
        }
        Object[] objArr2 = this.f546j;
        int i3 = this.f511d;
        this.f511d = i3 + 1;
        objArr2[i3] = obj;
    }

    private void Z() {
        int i2 = this.f511d - 1;
        this.f511d = i2;
        Object[] objArr = this.f546j;
        objArr[i2] = null;
        this.f512e[i2] = 0;
        if (i2 > 0) {
            int[] iArr = this.f514g;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            Object obj = objArr[i2 - 1];
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                if (it.hasNext()) {
                    Y(it.next());
                }
            }
        }
    }

    @Nullable
    private <T> T a0(Class<T> cls, JsonReader.Token token) {
        Object obj;
        int i2 = this.f511d;
        if (i2 != 0) {
            obj = this.f546j[i2 - 1];
        } else {
            obj = null;
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        if (obj == null && token == JsonReader.Token.NULL) {
            return null;
        }
        if (obj == f545k) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw W(obj, token);
    }

    private String b0(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        if (key instanceof String) {
            return (String) key;
        }
        throw W(key, JsonReader.Token.NAME);
    }

    @Override // com.squareup.moshi.JsonReader
    @Nullable
    public <T> T B() {
        a0(Void.class, JsonReader.Token.NULL);
        Z();
        return null;
    }

    @Override // com.squareup.moshi.JsonReader
    public String D() {
        String str;
        int i2 = this.f511d;
        if (i2 != 0) {
            str = this.f546j[i2 - 1];
        } else {
            str = null;
        }
        if (str instanceof String) {
            Z();
            return str;
        } else if (str instanceof Number) {
            Z();
            return str.toString();
        } else if (str == f545k) {
            throw new IllegalStateException("JsonReader is closed");
        } else {
            throw W(str, JsonReader.Token.STRING);
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token G() {
        int i2 = this.f511d;
        if (i2 == 0) {
            return JsonReader.Token.END_DOCUMENT;
        }
        Object obj = this.f546j[i2 - 1];
        if (obj instanceof JsonIterator) {
            return ((JsonIterator) obj).f547d;
        }
        if (obj instanceof List) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (obj instanceof Map) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (obj instanceof Map.Entry) {
            return JsonReader.Token.NAME;
        }
        if (obj instanceof String) {
            return JsonReader.Token.STRING;
        }
        if (obj instanceof Boolean) {
            return JsonReader.Token.BOOLEAN;
        }
        if (obj instanceof Number) {
            return JsonReader.Token.NUMBER;
        }
        if (obj == null) {
            return JsonReader.Token.NULL;
        }
        if (obj == f545k) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw W(obj, "a JSON value");
    }

    @Override // com.squareup.moshi.JsonReader
    public void I() {
        if (o()) {
            Y(X());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int O(JsonReader.Options options) {
        Map.Entry<?, ?> entry = (Map.Entry) a0(Map.Entry.class, JsonReader.Token.NAME);
        String b02 = b0(entry);
        int length = options.f517a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (options.f517a[i2].equals(b02)) {
                this.f546j[this.f511d - 1] = entry.getValue();
                this.f513f[this.f511d - 2] = b02;
                return i2;
            }
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object[]] */
    @Override // com.squareup.moshi.JsonReader
    public int Q(JsonReader.Options options) {
        String str;
        int i2 = this.f511d;
        if (i2 != 0) {
            str = this.f546j[i2 - 1];
        } else {
            str = null;
        }
        if (!(str instanceof String)) {
            if (str != f545k) {
                return -1;
            }
            throw new IllegalStateException("JsonReader is closed");
        }
        String str2 = str;
        int length = options.f517a.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (options.f517a[i3].equals(str2)) {
                Z();
                return i3;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public void T() {
        if (!this.f516i) {
            this.f546j[this.f511d - 1] = ((Map.Entry) a0(Map.Entry.class, JsonReader.Token.NAME)).getValue();
            this.f513f[this.f511d - 2] = "null";
            return;
        }
        JsonReader.Token G = G();
        X();
        throw new JsonDataException("Cannot skip unexpected " + G + " at " + n());
    }

    @Override // com.squareup.moshi.JsonReader
    public void U() {
        Object obj;
        if (!this.f516i) {
            int i2 = this.f511d;
            if (i2 > 1) {
                this.f513f[i2 - 2] = "null";
            }
            if (i2 != 0) {
                obj = this.f546j[i2 - 1];
            } else {
                obj = null;
            }
            if (!(obj instanceof JsonIterator)) {
                if (obj instanceof Map.Entry) {
                    Object[] objArr = this.f546j;
                    objArr[i2 - 1] = ((Map.Entry) objArr[i2 - 1]).getValue();
                    return;
                } else if (i2 > 0) {
                    Z();
                    return;
                } else {
                    throw new JsonDataException("Expected a value but was " + G() + " at path " + n());
                }
            }
            throw new JsonDataException("Expected a value but was " + G() + " at path " + n());
        }
        throw new JsonDataException("Cannot skip unexpected " + G() + " at " + n());
    }

    public String X() {
        Map.Entry<?, ?> entry = (Map.Entry) a0(Map.Entry.class, JsonReader.Token.NAME);
        String b02 = b0(entry);
        this.f546j[this.f511d - 1] = entry.getValue();
        this.f513f[this.f511d - 2] = b02;
        return b02;
    }

    @Override // com.squareup.moshi.JsonReader
    public void b() {
        List list = (List) a0(List.class, JsonReader.Token.BEGIN_ARRAY);
        JsonIterator jsonIterator = new JsonIterator(JsonReader.Token.END_ARRAY, list.toArray(new Object[list.size()]), 0);
        Object[] objArr = this.f546j;
        int i2 = this.f511d;
        objArr[i2 - 1] = jsonIterator;
        this.f512e[i2 - 1] = 1;
        this.f514g[i2 - 1] = 0;
        if (jsonIterator.hasNext()) {
            Y(jsonIterator.next());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void c() {
        Map map = (Map) a0(Map.class, JsonReader.Token.BEGIN_OBJECT);
        JsonIterator jsonIterator = new JsonIterator(JsonReader.Token.END_OBJECT, map.entrySet().toArray(new Object[map.size()]), 0);
        Object[] objArr = this.f546j;
        int i2 = this.f511d;
        objArr[i2 - 1] = jsonIterator;
        this.f512e[i2 - 1] = 3;
        if (jsonIterator.hasNext()) {
            Y(jsonIterator.next());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Arrays.fill(this.f546j, 0, this.f511d, (Object) null);
        this.f546j[0] = f545k;
        this.f512e[0] = 8;
        this.f511d = 1;
    }

    @Override // com.squareup.moshi.JsonReader
    public void e() {
        JsonReader.Token token = JsonReader.Token.END_ARRAY;
        JsonIterator jsonIterator = (JsonIterator) a0(JsonIterator.class, token);
        if (jsonIterator.f547d == token && !jsonIterator.hasNext()) {
            Z();
            return;
        }
        throw W(jsonIterator, token);
    }

    @Override // com.squareup.moshi.JsonReader
    public void k() {
        JsonReader.Token token = JsonReader.Token.END_OBJECT;
        JsonIterator jsonIterator = (JsonIterator) a0(JsonIterator.class, token);
        if (jsonIterator.f547d == token && !jsonIterator.hasNext()) {
            this.f513f[this.f511d - 1] = null;
            Z();
            return;
        }
        throw W(jsonIterator, token);
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean o() {
        int i2 = this.f511d;
        if (i2 == 0) {
            return false;
        }
        Object obj = this.f546j[i2 - 1];
        if ((obj instanceof Iterator) && !((Iterator) obj).hasNext()) {
            return false;
        }
        return true;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean r() {
        Z();
        return ((Boolean) a0(Boolean.class, JsonReader.Token.BOOLEAN)).booleanValue();
    }

    @Override // com.squareup.moshi.JsonReader
    public double w() {
        double parseDouble;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object a02 = a0(Object.class, token);
        if (a02 instanceof Number) {
            parseDouble = ((Number) a02).doubleValue();
        } else if (a02 instanceof String) {
            try {
                parseDouble = Double.parseDouble((String) a02);
            } catch (NumberFormatException unused) {
                throw W(a02, JsonReader.Token.NUMBER);
            }
        } else {
            throw W(a02, token);
        }
        if (!this.f515h && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + n());
        }
        Z();
        return parseDouble;
    }

    @Override // com.squareup.moshi.JsonReader
    public int x() {
        int intValueExact;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object a02 = a0(Object.class, token);
        if (a02 instanceof Number) {
            intValueExact = ((Number) a02).intValue();
        } else if (a02 instanceof String) {
            try {
                try {
                    intValueExact = Integer.parseInt((String) a02);
                } catch (NumberFormatException unused) {
                    throw W(a02, JsonReader.Token.NUMBER);
                }
            } catch (NumberFormatException unused2) {
                intValueExact = new BigDecimal((String) a02).intValueExact();
            }
        } else {
            throw W(a02, token);
        }
        Z();
        return intValueExact;
    }

    @Override // com.squareup.moshi.JsonReader
    public long z() {
        long longValueExact;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object a02 = a0(Object.class, token);
        if (a02 instanceof Number) {
            longValueExact = ((Number) a02).longValue();
        } else if (a02 instanceof String) {
            try {
                try {
                    longValueExact = Long.parseLong((String) a02);
                } catch (NumberFormatException unused) {
                    throw W(a02, JsonReader.Token.NUMBER);
                }
            } catch (NumberFormatException unused2) {
                longValueExact = new BigDecimal((String) a02).longValueExact();
            }
        } else {
            throw W(a02, token);
        }
        Z();
        return longValueExact;
    }
}
