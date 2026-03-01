package okhttp3;

import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Authenticator {

    /* renamed from: a  reason: collision with root package name */
    public static final Authenticator f1817a = new Authenticator() { // from class: okhttp3.Authenticator.1
        @Override // okhttp3.Authenticator
        public Request a(@Nullable Route route, Response response) {
            return null;
        }
    };

    @Nullable
    Request a(@Nullable Route route, Response response);
}
