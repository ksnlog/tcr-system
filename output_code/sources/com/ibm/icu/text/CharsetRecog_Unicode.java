package com.ibm.icu.text;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
abstract class CharsetRecog_Unicode extends CharsetRecognizer {

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_UTF_16_BE extends CharsetRecog_Unicode {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "UTF-16BE";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public CharsetMatch c(CharsetDetector charsetDetector) {
            byte[] bArr = charsetDetector.f59e;
            int min = Math.min(bArr.length, 30);
            int i2 = 10;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= min - 1) {
                    break;
                }
                int e2 = CharsetRecog_Unicode.e(bArr[i4], bArr[i4 + 1]);
                if (i4 == 0 && e2 == 65279) {
                    i2 = 100;
                    break;
                }
                i2 = CharsetRecog_Unicode.d(e2, i2);
                if (i2 == 0 || i2 == 100) {
                    break;
                }
                i4 += 2;
            }
            if (min >= 4 || i2 >= 100) {
                i3 = i2;
            }
            if (i3 > 0) {
                return new CharsetMatch(charsetDetector, this, i3);
            }
            return null;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_UTF_16_LE extends CharsetRecog_Unicode {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "UTF-16LE";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public CharsetMatch c(CharsetDetector charsetDetector) {
            byte[] bArr = charsetDetector.f59e;
            int min = Math.min(bArr.length, 30);
            int i2 = 10;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= min - 1) {
                    break;
                }
                int e2 = CharsetRecog_Unicode.e(bArr[i4 + 1], bArr[i4]);
                if (i4 == 0 && e2 == 65279) {
                    i2 = 100;
                    break;
                }
                i2 = CharsetRecog_Unicode.d(e2, i2);
                if (i2 == 0 || i2 == 100) {
                    break;
                }
                i4 += 2;
            }
            if (min >= 4 || i2 >= 100) {
                i3 = i2;
            }
            if (i3 > 0) {
                return new CharsetMatch(charsetDetector, this, i3);
            }
            return null;
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static abstract class CharsetRecog_UTF_32 extends CharsetRecog_Unicode {
        CharsetRecog_UTF_32() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
        @Override // com.ibm.icu.text.CharsetRecognizer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.ibm.icu.text.CharsetMatch c(com.ibm.icu.text.CharsetDetector r11) {
            /*
                r10 = this;
                byte[] r0 = r11.f59e
                int r1 = r11.f60f
                int r1 = r1 / 4
                int r1 = r1 * 4
                r2 = 0
                if (r1 != 0) goto Lc
                return r2
            Lc:
                r3 = 0
                int r4 = r10.f(r0, r3)
                r5 = 65279(0xfeff, float:9.1475E-41)
                if (r4 != r5) goto L18
                r4 = 1
                goto L19
            L18:
                r4 = 0
            L19:
                r5 = 0
                r6 = 0
                r7 = 0
            L1c:
                if (r5 >= r1) goto L3c
                int r8 = r10.f(r0, r5)
                if (r8 < 0) goto L37
                r9 = 1114111(0x10ffff, float:1.561202E-39)
                if (r8 >= r9) goto L37
                r9 = 55296(0xd800, float:7.7486E-41)
                if (r8 < r9) goto L34
                r9 = 57343(0xdfff, float:8.0355E-41)
                if (r8 > r9) goto L34
                goto L37
            L34:
                int r7 = r7 + 1
                goto L39
            L37:
                int r6 = r6 + 1
            L39:
                int r5 = r5 + 4
                goto L1c
            L3c:
                r0 = 100
                if (r4 == 0) goto L45
                if (r6 != 0) goto L45
            L42:
                r3 = 100
                goto L61
            L45:
                r1 = 80
                if (r4 == 0) goto L50
                int r4 = r6 * 10
                if (r7 <= r4) goto L50
            L4d:
                r3 = 80
                goto L61
            L50:
                r4 = 3
                if (r7 <= r4) goto L56
                if (r6 != 0) goto L56
                goto L42
            L56:
                if (r7 <= 0) goto L5b
                if (r6 != 0) goto L5b
                goto L4d
            L5b:
                int r6 = r6 * 10
                if (r7 <= r6) goto L61
                r3 = 25
            L61:
                if (r3 != 0) goto L64
                goto L69
            L64:
                com.ibm.icu.text.CharsetMatch r2 = new com.ibm.icu.text.CharsetMatch
                r2.<init>(r11, r10, r3)
            L69:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32.c(com.ibm.icu.text.CharsetDetector):com.ibm.icu.text.CharsetMatch");
        }

        abstract int f(byte[] bArr, int i2);
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_UTF_32_BE extends CharsetRecog_UTF_32 {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "UTF-32BE";
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32
        int f(byte[] bArr, int i2) {
            return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class CharsetRecog_UTF_32_LE extends CharsetRecog_UTF_32 {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ibm.icu.text.CharsetRecognizer
        public String b() {
            return "UTF-32LE";
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32
        int f(byte[] bArr, int i2) {
            return (bArr[i2] & 255) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8);
        }
    }

    CharsetRecog_Unicode() {
    }

    static int d(int i2, int i3) {
        if (i2 == 0) {
            i3 -= 10;
        } else if ((i2 >= 32 && i2 <= 255) || i2 == 10) {
            i3 += 10;
        }
        if (i3 < 0) {
            return 0;
        }
        if (i3 <= 100) {
            return i3;
        }
        return 100;
    }

    static int e(byte b2, byte b3) {
        return ((b2 & 255) << 8) | (b3 & 255);
    }
}
