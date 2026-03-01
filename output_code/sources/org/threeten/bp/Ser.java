package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public final class Ser implements Externalizable {

    /* renamed from: d  reason: collision with root package name */
    private byte f3090d;

    /* renamed from: e  reason: collision with root package name */
    private Object f3091e;

    public Ser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(DataInput dataInput) {
        return b(dataInput.readByte(), dataInput);
    }

    private static Object b(byte b2, DataInput dataInput) {
        if (b2 != 64) {
            switch (b2) {
                case 1:
                    return Duration.n(dataInput);
                case 2:
                    return Instant.B(dataInput);
                case 3:
                    return LocalDate.e0(dataInput);
                case 4:
                    return LocalDateTime.Y(dataInput);
                case 5:
                    return LocalTime.J(dataInput);
                case R$styleable.f1342r /* 6 */:
                    return ZonedDateTime.Y(dataInput);
                case R$styleable.f1343s /* 7 */:
                    return ZoneRegion.w(dataInput);
                case R$styleable.f1328d /* 8 */:
                    return ZoneOffset.C(dataInput);
                default:
                    switch (b2) {
                        case 66:
                            return OffsetTime.s(dataInput);
                        case 67:
                            return Year.t(dataInput);
                        case 68:
                            return YearMonth.v(dataInput);
                        case 69:
                            return OffsetDateTime.v(dataInput);
                        default:
                            throw new StreamCorruptedException("Unknown serialized type");
                    }
            }
        }
        return MonthDay.r(dataInput);
    }

    static void c(byte b2, Object obj, DataOutput dataOutput) {
        dataOutput.writeByte(b2);
        if (b2 != 64) {
            switch (b2) {
                case 1:
                    ((Duration) obj).q(dataOutput);
                    return;
                case 2:
                    ((Instant) obj).G(dataOutput);
                    return;
                case 3:
                    ((LocalDate) obj).m0(dataOutput);
                    return;
                case 4:
                    ((LocalDateTime) obj).j0(dataOutput);
                    return;
                case 5:
                    ((LocalTime) obj).T(dataOutput);
                    return;
                case R$styleable.f1342r /* 6 */:
                    ((ZonedDateTime) obj).p0(dataOutput);
                    return;
                case R$styleable.f1343s /* 7 */:
                    ((ZoneRegion) obj).x(dataOutput);
                    return;
                case R$styleable.f1328d /* 8 */:
                    ((ZoneOffset) obj).F(dataOutput);
                    return;
                default:
                    switch (b2) {
                        case 66:
                            ((OffsetTime) obj).x(dataOutput);
                            return;
                        case 67:
                            ((Year) obj).w(dataOutput);
                            return;
                        case 68:
                            ((YearMonth) obj).B(dataOutput);
                            return;
                        case 69:
                            ((OffsetDateTime) obj).D(dataOutput);
                            return;
                        default:
                            throw new InvalidClassException("Unknown serialized type");
                    }
            }
        }
        ((MonthDay) obj).s(dataOutput);
    }

    private Object readResolve() {
        return this.f3091e;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        this.f3090d = readByte;
        this.f3091e = b(readByte, objectInput);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) {
        c(this.f3090d, this.f3091e, objectOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ser(byte b2, Object obj) {
        this.f3090d = b2;
        this.f3091e = obj;
    }
}
