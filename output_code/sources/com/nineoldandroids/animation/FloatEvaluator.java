package com.nineoldandroids.animation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FloatEvaluator implements TypeEvaluator<Number> {
    @Override // com.nineoldandroids.animation.TypeEvaluator
    /* renamed from: a */
    public Float evaluate(float f2, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + (f2 * (number2.floatValue() - floatValue)));
    }
}
