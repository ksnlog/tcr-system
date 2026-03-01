package org.threeten.bp.chrono;

import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import net.i2p.android.ext.floatingactionbutton.R$styleable;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
final class Ser implements Externalizable {

    /* renamed from: d  reason: collision with root package name */
    private byte f3198d;

    /* renamed from: e  reason: collision with root package name */
    private Object f3199e;

    public Ser() {
    }

    private static Object a(byte b2, ObjectInput objectInput) {
        switch (b2) {
            case 1:
                return JapaneseDate.N(objectInput);
            case 2:
                return JapaneseEra.q(objectInput);
            case 3:
                return HijrahDate.p0(objectInput);
            case 4:
                return HijrahEra.n(objectInput);
            case 5:
                return MinguoDate.N(objectInput);
            case R$styleable.f1342r /* 6 */:
                return MinguoEra.m(objectInput);
            case R$styleable.f1343s /* 7 */:
                return ThaiBuddhistDate.N(objectInput);
            case R$styleable.f1328d /* 8 */:
                return ThaiBuddhistEra.m(objectInput);
            case R$styleable.f1329e /* 9 */:
            case R$styleable.f1330f /* 10 */:
            default:
                throw new StreamCorruptedException("Unknown serialized type");
            case R$styleable.f1331g /* 11 */:
                return Chronology.m(objectInput);
            case R$styleable.f1332h /* 12 */:
                return ChronoLocalDateTimeImpl.I(objectInput);
            case R$styleable.f1333i /* 13 */:
                return ChronoZonedDateTimeImpl.F(objectInput);
        }
    }

    private static void b(byte b2, Object obj, ObjectOutput objectOutput) {
        objectOutput.writeByte(b2);
        switch (b2) {
            case 1:
                ((JapaneseDate) obj).T(objectOutput);
                return;
            case 2:
                ((JapaneseEra) obj).t(objectOutput);
                return;
            case 3:
                ((HijrahDate) obj).t0(objectOutput);
                return;
            case 4:
                ((HijrahEra) obj).o(objectOutput);
                return;
            case 5:
                ((MinguoDate) obj).R(objectOutput);
                return;
            case R$styleable.f1342r /* 6 */:
                ((MinguoEra) obj).n(objectOutput);
                return;
            case R$styleable.f1343s /* 7 */:
                ((ThaiBuddhistDate) obj).R(objectOutput);
                return;
            case R$styleable.f1328d /* 8 */:
                ((ThaiBuddhistEra) obj).n(objectOutput);
                return;
            case R$styleable.f1329e /* 9 */:
            case R$styleable.f1330f /* 10 */:
            default:
                throw new InvalidClassException("Unknown serialized type");
            case R$styleable.f1331g /* 11 */:
                ((Chronology) obj).p(objectOutput);
                return;
            case R$styleable.f1332h /* 12 */:
                ((ChronoLocalDateTimeImpl) obj).writeExternal(objectOutput);
                return;
            case R$styleable.f1333i /* 13 */:
                ((ChronoZonedDateTimeImpl) obj).writeExternal(objectOutput);
                return;
        }
    }

    private Object readResolve() {
        return this.f3199e;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        this.f3198d = readByte;
        this.f3199e = a(readByte, objectInput);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) {
        b(this.f3198d, this.f3199e, objectOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ser(byte b2, Object obj) {
        this.f3198d = b2;
        this.f3199e = obj;
    }
}
