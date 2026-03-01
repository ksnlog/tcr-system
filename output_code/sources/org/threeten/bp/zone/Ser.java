package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import org.threeten.bp.ZoneOffset;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Ser implements Externalizable {

    /* renamed from: d  reason: collision with root package name */
    private byte f3412d;

    /* renamed from: e  reason: collision with root package name */
    private Object f3413e;

    public Ser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(DataInput dataInput) {
        return c(dataInput.readByte(), dataInput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long b(DataInput dataInput) {
        int readByte = dataInput.readByte() & 255;
        if (readByte == 255) {
            return dataInput.readLong();
        }
        return ((((readByte << 16) + ((dataInput.readByte() & 255) << 8)) + (dataInput.readByte() & 255)) * 900) - 4575744000L;
    }

    private static Object c(byte b2, DataInput dataInput) {
        if (b2 != 1) {
            if (b2 != 2) {
                if (b2 == 3) {
                    return ZoneOffsetTransitionRule.c(dataInput);
                }
                throw new StreamCorruptedException("Unknown serialized type");
            }
            return ZoneOffsetTransition.k(dataInput);
        }
        return StandardZoneRules.k(dataInput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneOffset d(DataInput dataInput) {
        byte readByte = dataInput.readByte();
        if (readByte == Byte.MAX_VALUE) {
            return ZoneOffset.A(dataInput.readInt());
        }
        return ZoneOffset.A(readByte * 900);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(long j2, DataOutput dataOutput) {
        if (j2 >= -4575744000L && j2 < 10413792000L && j2 % 900 == 0) {
            int i2 = (int) ((j2 + 4575744000L) / 900);
            dataOutput.writeByte((i2 >>> 16) & 255);
            dataOutput.writeByte((i2 >>> 8) & 255);
            dataOutput.writeByte(i2 & 255);
            return;
        }
        dataOutput.writeByte(255);
        dataOutput.writeLong(j2);
    }

    private static void f(byte b2, Object obj, DataOutput dataOutput) {
        dataOutput.writeByte(b2);
        if (b2 != 1) {
            if (b2 != 2) {
                if (b2 == 3) {
                    ((ZoneOffsetTransitionRule) obj).d(dataOutput);
                    return;
                }
                throw new InvalidClassException("Unknown serialized type");
            }
            ((ZoneOffsetTransition) obj).l(dataOutput);
            return;
        }
        ((StandardZoneRules) obj).l(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(ZoneOffset zoneOffset, DataOutput dataOutput) {
        int i2;
        int x2 = zoneOffset.x();
        if (x2 % 900 == 0) {
            i2 = x2 / 900;
        } else {
            i2 = 127;
        }
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(x2);
        }
    }

    private Object readResolve() {
        return this.f3413e;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        this.f3412d = readByte;
        this.f3413e = c(readByte, objectInput);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) {
        f(this.f3412d, this.f3413e, objectOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ser(byte b2, Object obj) {
        this.f3412d = b2;
        this.f3413e = obj;
    }
}
