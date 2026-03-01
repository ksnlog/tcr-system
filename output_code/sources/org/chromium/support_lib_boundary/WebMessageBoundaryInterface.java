package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public interface WebMessageBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    @Deprecated
    String getData();

    InvocationHandler getMessagePayload();

    InvocationHandler[] getPorts();
}
