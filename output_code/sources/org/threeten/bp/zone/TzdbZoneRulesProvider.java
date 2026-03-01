package org.threeten.bp.zone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class TzdbZoneRulesProvider extends ZoneRulesProvider {

    /* renamed from: c  reason: collision with root package name */
    private List<String> f3421c;

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentNavigableMap<String, Version> f3422d = new ConcurrentSkipListMap();

    /* renamed from: e  reason: collision with root package name */
    private Set<String> f3423e = new CopyOnWriteArraySet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class Version {

        /* renamed from: a  reason: collision with root package name */
        private final String f3424a;

        /* renamed from: b  reason: collision with root package name */
        private final String[] f3425b;

        /* renamed from: c  reason: collision with root package name */
        private final short[] f3426c;

        /* renamed from: d  reason: collision with root package name */
        private final AtomicReferenceArray<Object> f3427d;

        Version(String str, String[] strArr, short[] sArr, AtomicReferenceArray<Object> atomicReferenceArray) {
            this.f3427d = atomicReferenceArray;
            this.f3424a = str;
            this.f3425b = strArr;
            this.f3426c = sArr;
        }

        ZoneRules b(short s2) {
            Object obj = this.f3427d.get(s2);
            if (obj instanceof byte[]) {
                obj = Ser.a(new DataInputStream(new ByteArrayInputStream((byte[]) obj)));
                this.f3427d.set(s2, obj);
            }
            return (ZoneRules) obj;
        }

        ZoneRules c(String str) {
            int binarySearch = Arrays.binarySearch(this.f3425b, str);
            if (binarySearch < 0) {
                return null;
            }
            try {
                return b(this.f3426c[binarySearch]);
            } catch (Exception e2) {
                throw new ZoneRulesException("Invalid binary time-zone data: TZDB:" + str + ", version: " + this.f3424a, e2);
            }
        }

        public String toString() {
            return this.f3424a;
        }
    }

    public TzdbZoneRulesProvider(InputStream inputStream) {
        try {
            h(inputStream);
        } catch (Exception e2) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules", e2);
        }
    }

    private boolean h(InputStream inputStream) {
        boolean z2 = false;
        for (Version version : i(inputStream)) {
            Version putIfAbsent = this.f3422d.putIfAbsent(version.f3424a, version);
            if (putIfAbsent != null && !putIfAbsent.f3424a.equals(version.f3424a)) {
                throw new ZoneRulesException("Data already loaded for TZDB time-zone rules version: " + version.f3424a);
            }
            z2 = true;
        }
        return z2;
    }

    private Iterable<Version> i(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (dataInputStream.readByte() == 1) {
            if ("TZDB".equals(dataInputStream.readUTF())) {
                int readShort = dataInputStream.readShort();
                String[] strArr = new String[readShort];
                for (int i2 = 0; i2 < readShort; i2++) {
                    strArr[i2] = dataInputStream.readUTF();
                }
                int readShort2 = dataInputStream.readShort();
                String[] strArr2 = new String[readShort2];
                for (int i3 = 0; i3 < readShort2; i3++) {
                    strArr2[i3] = dataInputStream.readUTF();
                }
                this.f3421c = Arrays.asList(strArr2);
                int readShort3 = dataInputStream.readShort();
                Object[] objArr = new Object[readShort3];
                for (int i4 = 0; i4 < readShort3; i4++) {
                    byte[] bArr = new byte[dataInputStream.readShort()];
                    dataInputStream.readFully(bArr);
                    objArr[i4] = bArr;
                }
                AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(objArr);
                HashSet hashSet = new HashSet(readShort);
                for (int i5 = 0; i5 < readShort; i5++) {
                    int readShort4 = dataInputStream.readShort();
                    String[] strArr3 = new String[readShort4];
                    short[] sArr = new short[readShort4];
                    for (int i6 = 0; i6 < readShort4; i6++) {
                        strArr3[i6] = strArr2[dataInputStream.readShort()];
                        sArr[i6] = dataInputStream.readShort();
                    }
                    hashSet.add(new Version(strArr[i5], strArr3, sArr, atomicReferenceArray));
                }
                return hashSet;
            }
            throw new StreamCorruptedException("File format not recognised");
        }
        throw new StreamCorruptedException("File format not recognised");
    }

    @Override // org.threeten.bp.zone.ZoneRulesProvider
    protected ZoneRules d(String str, boolean z2) {
        Jdk8Methods.i(str, "zoneId");
        ZoneRules c2 = this.f3422d.lastEntry().getValue().c(str);
        if (c2 != null) {
            return c2;
        }
        throw new ZoneRulesException("Unknown time-zone ID: " + str);
    }

    @Override // org.threeten.bp.zone.ZoneRulesProvider
    protected Set<String> e() {
        return new HashSet(this.f3421c);
    }

    public String toString() {
        return "TZDB";
    }
}
