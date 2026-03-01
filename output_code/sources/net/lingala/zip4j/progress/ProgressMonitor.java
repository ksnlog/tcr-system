package net.lingala.zip4j.progress;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ProgressMonitor {

    /* renamed from: a  reason: collision with root package name */
    private State f1664a;

    /* renamed from: b  reason: collision with root package name */
    private long f1665b;

    /* renamed from: c  reason: collision with root package name */
    private long f1666c;

    /* renamed from: d  reason: collision with root package name */
    private int f1667d;

    /* renamed from: e  reason: collision with root package name */
    private Task f1668e;

    /* renamed from: f  reason: collision with root package name */
    private String f1669f;

    /* renamed from: g  reason: collision with root package name */
    private Result f1670g;

    /* renamed from: h  reason: collision with root package name */
    private Exception f1671h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f1672i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f1673j;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum Result {
        SUCCESS,
        WORK_IN_PROGRESS,
        ERROR,
        CANCELLED
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum State {
        READY,
        BUSY
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public enum Task {
        NONE,
        ADD_ENTRY,
        REMOVE_ENTRY,
        CALCULATE_CRC,
        EXTRACT_ENTRY,
        MERGE_ZIP_FILES,
        SET_COMMENT,
        RENAME_FILE
    }

    public ProgressMonitor() {
        f();
    }

    private void f() {
        this.f1668e = Task.NONE;
        this.f1664a = State.READY;
    }

    public void a() {
        this.f1670g = Result.SUCCESS;
        this.f1667d = 100;
        f();
    }

    public void b(Exception exc) {
        this.f1670g = Result.ERROR;
        this.f1671h = exc;
        f();
    }

    public void c() {
        f();
        this.f1669f = null;
        this.f1665b = 0L;
        this.f1666c = 0L;
        this.f1667d = 0;
    }

    public State d() {
        return this.f1664a;
    }

    public boolean e() {
        return this.f1672i;
    }

    public void g(Task task) {
        this.f1668e = task;
    }

    public void h(String str) {
        this.f1669f = str;
    }

    public void i(Result result) {
        this.f1670g = result;
    }

    public void j(State state) {
        this.f1664a = state;
    }

    public void k(long j2) {
        this.f1665b = j2;
    }

    public void l(long j2) {
        long j3 = this.f1666c + j2;
        this.f1666c = j3;
        long j4 = this.f1665b;
        if (j4 > 0) {
            int i2 = (int) ((j3 * 100) / j4);
            this.f1667d = i2;
            if (i2 > 100) {
                this.f1667d = 100;
            }
        }
        while (this.f1673j) {
            try {
                Thread.sleep(150L);
            } catch (InterruptedException unused) {
            }
        }
    }
}
