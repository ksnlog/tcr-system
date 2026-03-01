package okhttp3.internal.http2;

import java.util.List;
import okio.BufferedSource;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface PushObserver {

    /* renamed from: a  reason: collision with root package name */
    public static final PushObserver f2389a = new PushObserver() { // from class: okhttp3.internal.http2.PushObserver.1
        @Override // okhttp3.internal.http2.PushObserver
        public boolean a(int i2, List<Header> list) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean b(int i2, List<Header> list, boolean z2) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public void c(int i2, ErrorCode errorCode) {
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean d(int i2, BufferedSource bufferedSource, int i3, boolean z2) {
            bufferedSource.skip(i3);
            return true;
        }
    };

    boolean a(int i2, List<Header> list);

    boolean b(int i2, List<Header> list, boolean z2);

    void c(int i2, ErrorCode errorCode);

    boolean d(int i2, BufferedSource bufferedSource, int i3, boolean z2);
}
