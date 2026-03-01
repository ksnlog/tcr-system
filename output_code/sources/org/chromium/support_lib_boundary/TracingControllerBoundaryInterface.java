package org.chromium.support_lib_boundary;

import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.Executor;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface TracingControllerBoundaryInterface {
    boolean isTracing();

    void start(int i2, Collection<String> collection, int i3);

    boolean stop(OutputStream outputStream, Executor executor);
}
