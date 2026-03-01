package com.nineoldandroids.animation;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override // com.nineoldandroids.animation.TypeEvaluator
    /* renamed from: a */
    public Integer evaluate(float f2, Integer num, Integer num2) {
        int intValue = num.intValue();
        return Integer.valueOf((int) (intValue + (f2 * (num2.intValue() - intValue))));
    }
}
