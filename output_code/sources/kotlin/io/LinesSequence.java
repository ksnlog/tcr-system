package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class LinesSequence implements Sequence<String> {

    /* renamed from: a  reason: collision with root package name */
    private final BufferedReader f856a;

    public LinesSequence(BufferedReader reader) {
        Intrinsics.f(reader, "reader");
        this.f856a = reader;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<String> iterator() {
        return new Iterator<String>() { // from class: kotlin.io.LinesSequence$iterator$1

            /* renamed from: d  reason: collision with root package name */
            private String f857d;

            /* renamed from: e  reason: collision with root package name */
            private boolean f858e;

            @Override // java.util.Iterator
            /* renamed from: a */
            public String next() {
                if (hasNext()) {
                    String str = this.f857d;
                    this.f857d = null;
                    Intrinsics.c(str);
                    return str;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                BufferedReader bufferedReader;
                if (this.f857d == null && !this.f858e) {
                    bufferedReader = LinesSequence.this.f856a;
                    String readLine = bufferedReader.readLine();
                    this.f857d = readLine;
                    if (readLine == null) {
                        this.f858e = true;
                    }
                }
                if (this.f857d != null) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }
}
