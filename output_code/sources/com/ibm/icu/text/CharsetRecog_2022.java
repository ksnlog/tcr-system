package com.ibm.icu.text;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CharsetRecog_2022 extends CharsetRecognizer {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_2022CN extends CharsetRecog_2022 {

        /* renamed from: a  reason: collision with root package name */
        private byte[][] f72a = {new byte[]{27, 36, 41, 65}, new byte[]{27, 36, 41, 71}, new byte[]{27, 36, 42, 72}, new byte[]{27, 36, 41, 69}, new byte[]{27, 36, 43, 73}, new byte[]{27, 36, 43, 74}, new byte[]{27, 36, 43, 75}, new byte[]{27, 36, 43, 76}, new byte[]{27, 36, 43, 77}, new byte[]{27, 78}, new byte[]{27, 79}};

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "ISO-2022-CN";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public CharsetMatch c(CharsetDetector charsetDetector) {
            int d2 = d(charsetDetector.f55a, charsetDetector.f56b, this.f72a);
            if (d2 == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, d2);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_2022JP extends CharsetRecog_2022 {

        /* renamed from: a  reason: collision with root package name */
        private byte[][] f73a = {new byte[]{27, 36, 40, 67}, new byte[]{27, 36, 40, 68}, new byte[]{27, 36, 64}, new byte[]{27, 36, 65}, new byte[]{27, 36, 66}, new byte[]{27, 38, 64}, new byte[]{27, 40, 66}, new byte[]{27, 40, 72}, new byte[]{27, 40, 73}, new byte[]{27, 40, 74}, new byte[]{27, 46, 65}, new byte[]{27, 46, 70}};

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "ISO-2022-JP";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public CharsetMatch c(CharsetDetector charsetDetector) {
            int d2 = d(charsetDetector.f55a, charsetDetector.f56b, this.f73a);
            if (d2 == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, d2);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_2022KR extends CharsetRecog_2022 {

        /* renamed from: a  reason: collision with root package name */
        private byte[][] f74a = {new byte[]{27, 36, 41, 67}};

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "ISO-2022-KR";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public CharsetMatch c(CharsetDetector charsetDetector) {
            int d2 = d(charsetDetector.f55a, charsetDetector.f56b, this.f74a);
            if (d2 == 0) {
                return null;
            }
            return new CharsetMatch(charsetDetector, this, d2);
        }
    }

    CharsetRecog_2022() {
    }

    int d(byte[] bArr, int i2, byte[][] bArr2) {
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < i2) {
            if (bArr[i3] == 27) {
                for (byte[] bArr3 : bArr2) {
                    if (i2 - i3 >= bArr3.length) {
                        for (int i7 = 1; i7 < bArr3.length; i7++) {
                            if (bArr3[i7] != bArr[i3 + i7]) {
                                break;
                            }
                        }
                        i4++;
                        i3 += bArr3.length - 1;
                        break;
                    }
                }
                i5++;
            }
            byte b2 = bArr[i3];
            if (b2 == 14 || b2 == 15) {
                i6++;
            }
            i3++;
        }
        if (i4 == 0) {
            return 0;
        }
        int i8 = ((i4 * 100) - (i5 * 100)) / (i5 + i4);
        int i9 = i4 + i6;
        if (i9 < 5) {
            i8 -= (5 - i9) * 10;
        }
        if (i8 < 0) {
            return 0;
        }
        return i8;
    }
}
