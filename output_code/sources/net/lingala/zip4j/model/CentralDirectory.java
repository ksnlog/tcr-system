package net.lingala.zip4j.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class CentralDirectory {

    /* renamed from: a  reason: collision with root package name */
    private List<FileHeader> f1543a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private DigitalSignature f1544b = new DigitalSignature();

    public List<FileHeader> a() {
        return this.f1543a;
    }

    public void b(List<FileHeader> list) {
        this.f1543a = list;
    }
}
