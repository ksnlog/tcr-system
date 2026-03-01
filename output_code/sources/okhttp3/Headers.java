package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Headers {

    /* renamed from: a  reason: collision with root package name */
    private final String[] f1934a;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        final List<String> f1935a = new ArrayList(20);

        public Builder a(String str, String str2) {
            Headers.a(str);
            Headers.b(str2, str);
            return c(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder b(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return c(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return c("", str.substring(1));
            }
            return c("", str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder c(String str, String str2) {
            this.f1935a.add(str);
            this.f1935a.add(str2.trim());
            return this;
        }

        public Headers d() {
            return new Headers(this);
        }

        public Builder e(String str) {
            int i2 = 0;
            while (i2 < this.f1935a.size()) {
                if (str.equalsIgnoreCase(this.f1935a.get(i2))) {
                    this.f1935a.remove(i2);
                    this.f1935a.remove(i2);
                    i2 -= 2;
                }
                i2 += 2;
            }
            return this;
        }

        public Builder f(String str, String str2) {
            Headers.a(str);
            Headers.b(str2, str);
            e(str);
            c(str, str2);
            return this;
        }
    }

    Headers(Builder builder) {
        List<String> list = builder.f1935a;
        this.f1934a = (String[]) list.toArray(new String[list.size()]);
    }

    static void a(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                int length = str.length();
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = str.charAt(i2);
                    if (charAt <= ' ' || charAt >= 127) {
                        throw new IllegalArgumentException(Util.q("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str));
                    }
                }
                return;
            }
            throw new IllegalArgumentException("name is empty");
        }
        throw new NullPointerException("name == null");
    }

    static void b(String str, String str2) {
        if (str != null) {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if ((charAt <= 31 && charAt != '\t') || charAt >= 127) {
                    throw new IllegalArgumentException(Util.q("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str2, str));
                }
            }
            return;
        }
        throw new NullPointerException("value for name " + str2 + " == null");
    }

    private static String d(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    @Nullable
    public String c(String str) {
        return d(this.f1934a, str);
    }

    public String e(int i2) {
        return this.f1934a[i2 * 2];
    }

    public boolean equals(@Nullable Object obj) {
        if ((obj instanceof Headers) && Arrays.equals(((Headers) obj).f1934a, this.f1934a)) {
            return true;
        }
        return false;
    }

    public Builder f() {
        Builder builder = new Builder();
        Collections.addAll(builder.f1935a, this.f1934a);
        return builder;
    }

    public int g() {
        return this.f1934a.length / 2;
    }

    public String h(int i2) {
        return this.f1934a[(i2 * 2) + 1];
    }

    public int hashCode() {
        return Arrays.hashCode(this.f1934a);
    }

    public List<String> i(String str) {
        int g2 = g();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < g2; i2++) {
            if (str.equalsIgnoreCase(e(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(h(i2));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int g2 = g();
        for (int i2 = 0; i2 < g2; i2++) {
            sb.append(e(i2));
            sb.append(": ");
            sb.append(h(i2));
            sb.append("\n");
        }
        return sb.toString();
    }
}
