package org.ligi.passandroid.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okio.BufferedSink;
import okio.Okio;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FileBackedPassClassifier extends PassClassifier {
    private final JsonAdapter<Map> adapter;
    private final File backed_file;

    public FileBackedPassClassifier(File file, PassStore passStore, Moshi moshi) {
        super(loadMap(file, moshi), passStore);
        this.backed_file = file;
        this.adapter = getAdapter(moshi);
    }

    private static JsonAdapter<Map> getAdapter(Moshi moshi) {
        return moshi.c(Map.class);
    }

    private BufferedSink getBufferedSinkFromMaybeCreatedFile() {
        try {
            if (!this.backed_file.exists()) {
                File parentFile = this.backed_file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.backed_file.createNewFile();
            }
            return Okio.a(Okio.d(this.backed_file));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Map<String, String> loadMap(File file, Moshi moshi) {
        if (file.exists()) {
            try {
                return getAdapter(moshi).fromJson(Okio.b(Okio.i(file)));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return new HashMap();
    }

    @Override // org.ligi.passandroid.model.PassClassifier
    public void processDataChange() {
        BufferedSink bufferedSinkFromMaybeCreatedFile;
        super.processDataChange();
        if (this.adapter != null && (bufferedSinkFromMaybeCreatedFile = getBufferedSinkFromMaybeCreatedFile()) != null) {
            try {
                this.adapter.toJson(bufferedSinkFromMaybeCreatedFile, (BufferedSink) getTopicByIdMap());
                bufferedSinkFromMaybeCreatedFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
