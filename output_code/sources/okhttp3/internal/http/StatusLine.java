package okhttp3.internal.http;

import java.net.ProtocolException;
import okhttp3.Protocol;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class StatusLine {

    /* renamed from: a  reason: collision with root package name */
    public final Protocol f2191a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2192b;

    /* renamed from: c  reason: collision with root package name */
    public final String f2193c;

    public StatusLine(Protocol protocol, int i2, String str) {
        this.f2191a = protocol;
        this.f2192b = i2;
        this.f2193c = str;
    }

    public static StatusLine a(String str) {
        Protocol protocol;
        int i2;
        String str2;
        if (str.startsWith("HTTP/1.")) {
            i2 = 9;
            if (str.length() >= 9 && str.charAt(8) == ' ') {
                int charAt = str.charAt(7) - '0';
                if (charAt == 0) {
                    protocol = Protocol.HTTP_1_0;
                } else if (charAt == 1) {
                    protocol = Protocol.HTTP_1_1;
                } else {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            protocol = Protocol.HTTP_1_0;
            i2 = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        int i3 = i2 + 3;
        if (str.length() >= i3) {
            try {
                int parseInt = Integer.parseInt(str.substring(i2, i3));
                if (str.length() > i3) {
                    if (str.charAt(i3) == ' ') {
                        str2 = str.substring(i2 + 4);
                    } else {
                        throw new ProtocolException("Unexpected status line: " + str);
                    }
                } else {
                    str2 = "";
                }
                return new StatusLine(protocol, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        }
        throw new ProtocolException("Unexpected status line: " + str);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.f2191a == Protocol.HTTP_1_0) {
            str = "HTTP/1.0";
        } else {
            str = "HTTP/1.1";
        }
        sb.append(str);
        sb.append(' ');
        sb.append(this.f2192b);
        if (this.f2193c != null) {
            sb.append(' ');
            sb.append(this.f2193c);
        }
        return sb.toString();
    }
}
