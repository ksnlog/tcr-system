package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class RecordJsonAdapter<T> extends JsonAdapter<T> {

    /* renamed from: a  reason: collision with root package name */
    static final JsonAdapter.Factory f610a = new JsonAdapter.Factory() { // from class: com.squareup.moshi.RecordJsonAdapter.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        @Nullable
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, Moshi moshi) {
            return null;
        }
    };
}
