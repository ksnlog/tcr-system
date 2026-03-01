package org.ligi.kaxt.converter;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class ImageFromIntentUriToFileConverter {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2604a;

    public ImageFromIntentUriToFileConverter(Context context) {
        this.f2604a = context;
    }

    private final File b(Uri uri) {
        boolean n2;
        Uri uri2;
        boolean n3;
        String l2;
        boolean z2 = false;
        Cursor query = this.f2604a.getContentResolver().query(uri, new String[]{"_data", "_display_name"}, null, null, null);
        String uri3 = uri.toString();
        Intrinsics.b(uri3, "selectedImage.toString()");
        n2 = StringsKt__StringsJVMKt.n(uri3, "content://com.android.gallery3d.provider", false, 2, null);
        if (n2) {
            String uri4 = uri.toString();
            Intrinsics.b(uri4, "selectedImage.toString()");
            l2 = StringsKt__StringsJVMKt.l(uri4, "com.android.gallery3d", "com.google.android.gallery3d", false, 4, null);
            uri2 = Uri.parse(l2);
            Intrinsics.b(uri2, "Uri.parse(selectedImage.…ogle.android.gallery3d\"))");
        } else {
            uri2 = uri;
        }
        if (query != null) {
            query.moveToFirst();
            int columnIndex = query.getColumnIndex("_data");
            String uri5 = uri2.toString();
            Intrinsics.b(uri5, "selectedImage.toString()");
            n3 = StringsKt__StringsJVMKt.n(uri5, "content://com.google.android.gallery3d", false, 2, null);
            if (n3) {
                if (query.getColumnIndex("_display_name") != -1) {
                    return c("image_file_name.jpg", uri2);
                }
            } else {
                String string = query.getString(columnIndex);
                if (string == null && Build.VERSION.SDK_INT >= 19) {
                    string = e(uri2);
                }
                query.close();
                if (string == null) {
                    Intrinsics.m();
                }
                return new File(string);
            }
        } else {
            String uri6 = uri2.toString();
            Intrinsics.b(uri6, "selectedImage.toString()");
            if (uri6.length() > 0) {
                z2 = true;
            }
            if (z2) {
                return c("image_file_name.jpg", uri2);
            }
        }
        return null;
    }

    private final File c(String str, Uri uri) {
        File cacheDir = this.f2604a.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File file = new File(cacheDir, str);
        try {
            ByteStreamsKt.b(f(uri), new FileOutputStream(file), 0, 2, null);
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private final String d(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        query.close();
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @TargetApi(19)
    private final String e(Uri uri) {
        String wholeID;
        boolean p2;
        List f2;
        boolean z2;
        wholeID = DocumentsContract.getDocumentId(uri);
        if (new File(wholeID).exists()) {
            return wholeID;
        }
        Intrinsics.b(wholeID, "wholeID");
        p2 = StringsKt__StringsKt.p(wholeID, ":", false, 2, null);
        if (p2) {
            List<String> c2 = new Regex(":").c(wholeID, 0);
            if (!c2.isEmpty()) {
                ListIterator<String> listIterator = c2.listIterator(c2.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() == 0) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (!z2) {
                        f2 = CollectionsKt___CollectionsKt.F(c2, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            f2 = CollectionsKt__CollectionsKt.f();
            if (f2 != null) {
                Object[] array = f2.toArray(new String[0]);
                if (array != null) {
                    wholeID = ((String[]) array)[1];
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
        }
        String[] strArr = {"_data"};
        Cursor query = this.f2604a.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", new String[]{wholeID}, null);
        if (query == null) {
            Intrinsics.m();
        }
        int columnIndex = query.getColumnIndex(strArr[0]);
        if (!query.moveToFirst()) {
            return null;
        }
        return query.getString(columnIndex);
    }

    private final InputStream f(Uri uri) {
        boolean n2;
        String uri2 = uri.toString();
        Intrinsics.b(uri2, "url.toString()");
        n2 = StringsKt__StringsJVMKt.n(uri2, "content://com.google.android.gallery3d", false, 2, null);
        if (n2) {
            InputStream openInputStream = this.f2604a.getContentResolver().openInputStream(uri);
            Intrinsics.b(openInputStream, "context.contentResolver.openInputStream(url)");
            return openInputStream;
        }
        InputStream openStream = new URL(uri.toString()).openStream();
        Intrinsics.b(openStream, "URL(url.toString()).openStream()");
        return openStream;
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x015b  */
    @android.annotation.TargetApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String g(android.content.Context r9, android.net.Uri r10) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ligi.kaxt.converter.ImageFromIntentUriToFileConverter.g(android.content.Context, android.net.Uri):java.lang.String");
    }

    private final boolean h(Uri uri) {
        return Intrinsics.a("com.android.providers.downloads.documents", uri.getAuthority());
    }

    private final boolean i(Uri uri) {
        return Intrinsics.a("com.android.externalstorage.documents", uri.getAuthority());
    }

    private final boolean j(Uri uri) {
        return Intrinsics.a("com.android.providers.media.documents", uri.getAuthority());
    }

    public final String a(Uri uri) {
        String str;
        boolean h2;
        File b2;
        boolean h3;
        if (Build.VERSION.SDK_INT >= 19) {
            str = g(this.f2604a, uri);
        } else {
            str = null;
        }
        if (str == null) {
            h3 = StringsKt__StringsJVMKt.h("content", uri.getScheme(), true);
            if (h3) {
                str = d(this.f2604a, uri, null, null);
            }
        }
        if (str == null && (b2 = b(uri)) != null) {
            str = b2.getAbsolutePath();
        }
        if (str == null) {
            h2 = StringsKt__StringsJVMKt.h("file", uri.getScheme(), true);
            if (h2) {
                return uri.getPath();
            }
            return str;
        }
        return str;
    }
}
