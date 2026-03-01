package com.nineoldandroids.animation;

import android.util.Log;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class PropertyValuesHolder implements Cloneable {

    /* renamed from: n  reason: collision with root package name */
    private static final TypeEvaluator f294n = new IntEvaluator();

    /* renamed from: o  reason: collision with root package name */
    private static final TypeEvaluator f295o = new FloatEvaluator();

    /* renamed from: p  reason: collision with root package name */
    private static Class[] f296p;

    /* renamed from: q  reason: collision with root package name */
    private static Class[] f297q;

    /* renamed from: r  reason: collision with root package name */
    private static Class[] f298r;

    /* renamed from: s  reason: collision with root package name */
    private static final HashMap<Class, HashMap<String, Method>> f299s;

    /* renamed from: t  reason: collision with root package name */
    private static final HashMap<Class, HashMap<String, Method>> f300t;

    /* renamed from: d  reason: collision with root package name */
    String f301d;

    /* renamed from: e  reason: collision with root package name */
    protected Property f302e;

    /* renamed from: f  reason: collision with root package name */
    Method f303f;

    /* renamed from: g  reason: collision with root package name */
    private Method f304g;

    /* renamed from: h  reason: collision with root package name */
    Class f305h;

    /* renamed from: i  reason: collision with root package name */
    KeyframeSet f306i;

    /* renamed from: j  reason: collision with root package name */
    final ReentrantReadWriteLock f307j;

    /* renamed from: k  reason: collision with root package name */
    final Object[] f308k;

    /* renamed from: l  reason: collision with root package name */
    private TypeEvaluator f309l;

    /* renamed from: m  reason: collision with root package name */
    private Object f310m;

    static {
        Class cls = Float.TYPE;
        Class cls2 = Double.TYPE;
        Class cls3 = Integer.TYPE;
        f296p = new Class[]{cls, Float.class, cls2, cls3, Double.class, Integer.class};
        f297q = new Class[]{cls3, Integer.class, cls, cls2, Float.class, Double.class};
        f298r = new Class[]{cls2, Double.class, cls, cls3, Float.class, Integer.class};
        f299s = new HashMap<>();
        f300t = new HashMap<>();
    }

    static String d(String str, String str2) {
        if (str2 != null && str2.length() != 0) {
            char upperCase = Character.toUpperCase(str2.charAt(0));
            String substring = str2.substring(1);
            return str + upperCase + substring;
        }
        return str;
    }

    private Method e(Class cls, String str, Class cls2) {
        Class<?>[] clsArr;
        String d2 = d(str, this.f301d);
        Method method = null;
        if (cls2 == null) {
            try {
                return cls.getMethod(d2, null);
            } catch (NoSuchMethodException e2) {
                try {
                    method = cls.getDeclaredMethod(d2, null);
                    method.setAccessible(true);
                } catch (NoSuchMethodException unused) {
                    Log.e("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.f301d + ": " + e2);
                }
            }
        } else {
            Class<?>[] clsArr2 = new Class[1];
            if (this.f305h.equals(Float.class)) {
                clsArr = f296p;
            } else if (this.f305h.equals(Integer.class)) {
                clsArr = f297q;
            } else {
                clsArr = this.f305h.equals(Double.class) ? f298r : new Class[]{this.f305h};
            }
            for (Class<?> cls3 : clsArr) {
                clsArr2[0] = cls3;
                try {
                    try {
                        Method method2 = cls.getMethod(d2, clsArr2);
                        this.f305h = cls3;
                        return method2;
                    } catch (NoSuchMethodException unused2) {
                        method = cls.getDeclaredMethod(d2, clsArr2);
                        method.setAccessible(true);
                        this.f305h = cls3;
                        return method;
                    }
                } catch (NoSuchMethodException unused3) {
                }
            }
            Log.e("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.f301d + " with value type " + this.f305h);
        }
        return method;
    }

    public static PropertyValuesHolder h(Property<?, Float> property, float... fArr) {
        return new FloatPropertyValuesHolder(property, fArr);
    }

    public static PropertyValuesHolder i(String str, float... fArr) {
        return new FloatPropertyValuesHolder(str, fArr);
    }

    private void o(Class cls) {
        this.f304g = r(cls, f300t, "get", null);
    }

    private Method r(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        Method method;
        try {
            this.f307j.writeLock().lock();
            HashMap<String, Method> hashMap2 = hashMap.get(cls);
            if (hashMap2 != null) {
                method = hashMap2.get(this.f301d);
            } else {
                method = null;
            }
            if (method == null) {
                method = e(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.f301d, method);
            }
            return method;
        } finally {
            this.f307j.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2) {
        this.f310m = this.f306i.b(f2);
    }

    @Override // 
    /* renamed from: b */
    public PropertyValuesHolder clone() {
        try {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) super.clone();
            propertyValuesHolder.f301d = this.f301d;
            propertyValuesHolder.f302e = this.f302e;
            propertyValuesHolder.f306i = this.f306i.a();
            propertyValuesHolder.f309l = this.f309l;
            return propertyValuesHolder;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    Object c() {
        return this.f310m;
    }

    public String f() {
        return this.f301d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        TypeEvaluator typeEvaluator;
        if (this.f309l == null) {
            Class cls = this.f305h;
            if (cls == Integer.class) {
                typeEvaluator = f294n;
            } else if (cls == Float.class) {
                typeEvaluator = f295o;
            } else {
                typeEvaluator = null;
            }
            this.f309l = typeEvaluator;
        }
        TypeEvaluator typeEvaluator2 = this.f309l;
        if (typeEvaluator2 != null) {
            this.f306i.d(typeEvaluator2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Object obj) {
        Property property = this.f302e;
        if (property != null) {
            property.c(obj, c());
        }
        if (this.f303f != null) {
            try {
                this.f308k[0] = c();
                this.f303f.invoke(obj, this.f308k);
            } catch (IllegalAccessException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            } catch (InvocationTargetException e3) {
                Log.e("PropertyValuesHolder", e3.toString());
            }
        }
    }

    public void l(float... fArr) {
        this.f305h = Float.TYPE;
        this.f306i = KeyframeSet.c(fArr);
    }

    public void m(Property property) {
        this.f302e = property;
    }

    public void n(String str) {
        this.f301d = str;
    }

    void p(Class cls) {
        this.f303f = r(cls, f299s, "set", this.f305h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(Object obj) {
        Property property = this.f302e;
        if (property != null) {
            try {
                property.a(obj);
                Iterator<Keyframe> it = this.f306i.f278e.iterator();
                while (it.hasNext()) {
                    Keyframe next = it.next();
                    if (!next.e()) {
                        next.i(this.f302e.a(obj));
                    }
                }
                return;
            } catch (ClassCastException unused) {
                Log.e("PropertyValuesHolder", "No such property (" + this.f302e.b() + ") on target object " + obj + ". Trying reflection instead");
                this.f302e = null;
            }
        }
        Class<?> cls = obj.getClass();
        if (this.f303f == null) {
            p(cls);
        }
        Iterator<Keyframe> it2 = this.f306i.f278e.iterator();
        while (it2.hasNext()) {
            Keyframe next2 = it2.next();
            if (!next2.e()) {
                if (this.f304g == null) {
                    o(cls);
                }
                try {
                    next2.i(this.f304g.invoke(obj, new Object[0]));
                } catch (IllegalAccessException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                } catch (InvocationTargetException e3) {
                    Log.e("PropertyValuesHolder", e3.toString());
                }
            }
        }
    }

    public String toString() {
        return this.f301d + ": " + this.f306i.toString();
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    static class FloatPropertyValuesHolder extends PropertyValuesHolder {

        /* renamed from: u  reason: collision with root package name */
        private FloatProperty f311u;

        /* renamed from: v  reason: collision with root package name */
        FloatKeyframeSet f312v;

        /* renamed from: w  reason: collision with root package name */
        float f313w;

        public FloatPropertyValuesHolder(String str, float... fArr) {
            super(str);
            l(fArr);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void a(float f2) {
            this.f313w = this.f312v.f(f2);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        Object c() {
            return Float.valueOf(this.f313w);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void j(Object obj) {
            FloatProperty floatProperty = this.f311u;
            if (floatProperty != null) {
                floatProperty.e(obj, this.f313w);
                return;
            }
            Property property = this.f302e;
            if (property != null) {
                property.c(obj, Float.valueOf(this.f313w));
            } else if (this.f303f != null) {
                try {
                    this.f308k[0] = Float.valueOf(this.f313w);
                    this.f303f.invoke(obj, this.f308k);
                } catch (IllegalAccessException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                } catch (InvocationTargetException e3) {
                    Log.e("PropertyValuesHolder", e3.toString());
                }
            }
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        public void l(float... fArr) {
            super.l(fArr);
            this.f312v = (FloatKeyframeSet) this.f306i;
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void p(Class cls) {
            if (this.f302e != null) {
                return;
            }
            super.p(cls);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        /* renamed from: s */
        public FloatPropertyValuesHolder clone() {
            FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder) super.clone();
            floatPropertyValuesHolder.f312v = (FloatKeyframeSet) floatPropertyValuesHolder.f306i;
            return floatPropertyValuesHolder;
        }

        public FloatPropertyValuesHolder(Property property, float... fArr) {
            super(property);
            l(fArr);
            if (property instanceof FloatProperty) {
                this.f311u = (FloatProperty) this.f302e;
            }
        }
    }

    private PropertyValuesHolder(String str) {
        this.f303f = null;
        this.f304g = null;
        this.f306i = null;
        this.f307j = new ReentrantReadWriteLock();
        this.f308k = new Object[1];
        this.f301d = str;
    }

    private PropertyValuesHolder(Property property) {
        this.f303f = null;
        this.f304g = null;
        this.f306i = null;
        this.f307j = new ReentrantReadWriteLock();
        this.f308k = new Object[1];
        this.f302e = property;
        if (property != null) {
            this.f301d = property.b();
        }
    }
}
