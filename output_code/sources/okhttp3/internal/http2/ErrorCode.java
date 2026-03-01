package okhttp3.internal.http2;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public enum ErrorCode {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);
    

    /* renamed from: d  reason: collision with root package name */
    public final int f2231d;

    ErrorCode(int i2) {
        this.f2231d = i2;
    }

    public static ErrorCode a(int i2) {
        ErrorCode[] values;
        for (ErrorCode errorCode : values()) {
            if (errorCode.f2231d == i2) {
                return errorCode;
            }
        }
        return null;
    }
}
