package okhttp3;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface Interceptor {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface Chain {
        int a();

        int b();

        int c();

        Response d(Request request);

        Request e();
    }

    Response a(Chain chain);
}
