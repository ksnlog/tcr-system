package com.squareup.moshi;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public @interface Json {
    boolean ignore() default false;

    String name() default "\u0000";
}
