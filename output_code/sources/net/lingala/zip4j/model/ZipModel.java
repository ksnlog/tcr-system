package net.lingala.zip4j.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class ZipModel implements Cloneable {

    /* renamed from: k  reason: collision with root package name */
    private boolean f1598k;

    /* renamed from: m  reason: collision with root package name */
    private File f1600m;

    /* renamed from: d  reason: collision with root package name */
    private List<LocalFileHeader> f1591d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private List<DataDescriptor> f1592e = new ArrayList();

    /* renamed from: f  reason: collision with root package name */
    private ArchiveExtraDataRecord f1593f = new ArchiveExtraDataRecord();

    /* renamed from: g  reason: collision with root package name */
    private CentralDirectory f1594g = new CentralDirectory();

    /* renamed from: h  reason: collision with root package name */
    private EndOfCentralDirectoryRecord f1595h = new EndOfCentralDirectoryRecord();

    /* renamed from: i  reason: collision with root package name */
    private Zip64EndOfCentralDirectoryLocator f1596i = new Zip64EndOfCentralDirectoryLocator();

    /* renamed from: j  reason: collision with root package name */
    private Zip64EndOfCentralDirectoryRecord f1597j = new Zip64EndOfCentralDirectoryRecord();

    /* renamed from: n  reason: collision with root package name */
    private boolean f1601n = false;

    /* renamed from: l  reason: collision with root package name */
    private long f1599l = -1;

    public CentralDirectory a() {
        return this.f1594g;
    }

    public EndOfCentralDirectoryRecord b() {
        return this.f1595h;
    }

    public List<LocalFileHeader> c() {
        return this.f1591d;
    }

    public Object clone() {
        return super.clone();
    }

    public long d() {
        return this.f1599l;
    }

    public Zip64EndOfCentralDirectoryLocator e() {
        return this.f1596i;
    }

    public Zip64EndOfCentralDirectoryRecord f() {
        return this.f1597j;
    }

    public File g() {
        return this.f1600m;
    }

    public boolean h() {
        return this.f1598k;
    }

    public boolean i() {
        return this.f1601n;
    }

    public void j(CentralDirectory centralDirectory) {
        this.f1594g = centralDirectory;
    }

    public void l(EndOfCentralDirectoryRecord endOfCentralDirectoryRecord) {
        this.f1595h = endOfCentralDirectoryRecord;
    }

    public void m(boolean z2) {
        this.f1598k = z2;
    }

    public void n(long j2) {
        this.f1599l = j2;
    }

    public void o(Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator) {
        this.f1596i = zip64EndOfCentralDirectoryLocator;
    }

    public void p(Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord) {
        this.f1597j = zip64EndOfCentralDirectoryRecord;
    }

    public void q(boolean z2) {
        this.f1601n = z2;
    }

    public void r(File file) {
        this.f1600m = file;
    }
}
