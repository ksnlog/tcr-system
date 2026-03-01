package net.i2p.android.ext.floatingactionbutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
/* loaded from: /home/mailboxkishan/dex_files/classes.dex */
public class FloatingActionsMenu extends ViewGroup {

    /* renamed from: x  reason: collision with root package name */
    private static Interpolator f1273x = new OvershootInterpolator();

    /* renamed from: y  reason: collision with root package name */
    private static Interpolator f1274y = new DecelerateInterpolator(3.0f);

    /* renamed from: z  reason: collision with root package name */
    private static Interpolator f1275z = new DecelerateInterpolator();

    /* renamed from: d  reason: collision with root package name */
    private int f1276d;

    /* renamed from: e  reason: collision with root package name */
    private int f1277e;

    /* renamed from: f  reason: collision with root package name */
    private int f1278f;

    /* renamed from: g  reason: collision with root package name */
    private int f1279g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1280h;

    /* renamed from: i  reason: collision with root package name */
    private int f1281i;

    /* renamed from: j  reason: collision with root package name */
    private int f1282j;

    /* renamed from: k  reason: collision with root package name */
    private int f1283k;

    /* renamed from: l  reason: collision with root package name */
    private int f1284l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f1285m;

    /* renamed from: n  reason: collision with root package name */
    private AnimatorSet f1286n;

    /* renamed from: o  reason: collision with root package name */
    private AnimatorSet f1287o;

    /* renamed from: p  reason: collision with root package name */
    private AddFloatingActionButton f1288p;

    /* renamed from: q  reason: collision with root package name */
    private RotatingDrawable f1289q;

    /* renamed from: r  reason: collision with root package name */
    private int f1290r;

    /* renamed from: s  reason: collision with root package name */
    private int f1291s;

    /* renamed from: t  reason: collision with root package name */
    private int f1292t;

    /* renamed from: u  reason: collision with root package name */
    private int f1293u;

    /* renamed from: v  reason: collision with root package name */
    private int f1294v;

    /* renamed from: w  reason: collision with root package name */
    private TouchDelegateGroup f1295w;

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        private ObjectAnimator f1298a;

        /* renamed from: b  reason: collision with root package name */
        private ObjectAnimator f1299b;

        /* renamed from: c  reason: collision with root package name */
        private ObjectAnimator f1300c;

        /* renamed from: d  reason: collision with root package name */
        private ObjectAnimator f1301d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f1302e;

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1298a = new ObjectAnimator();
            this.f1299b = new ObjectAnimator();
            this.f1300c = new ObjectAnimator();
            this.f1301d = new ObjectAnimator();
            this.f1298a.I(FloatingActionsMenu.f1273x);
            this.f1299b.I(FloatingActionsMenu.f1275z);
            this.f1300c.I(FloatingActionsMenu.f1274y);
            this.f1301d.I(FloatingActionsMenu.f1274y);
            this.f1301d.T("alpha");
            this.f1301d.H(1.0f, 0.0f);
            this.f1299b.T("alpha");
            this.f1299b.H(0.0f, 1.0f);
            int i2 = FloatingActionsMenu.this.f1281i;
            if (i2 != 0 && i2 != 1) {
                if (i2 == 2 || i2 == 3) {
                    this.f1300c.T("translationX");
                    this.f1298a.T("translationX");
                    return;
                }
                return;
            }
            this.f1300c.T("translationY");
            this.f1298a.T("translationY");
        }

        private void c(Animator animator, final View view) {
            animator.a(new AnimatorListenerAdapter() { // from class: net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu.LayoutParams.1
                @Override // com.nineoldandroids.animation.Animator.AnimatorListener
                public void a(Animator animator2) {
                    ViewCompat.H0(view, 0, (Paint) null);
                }

                @Override // com.nineoldandroids.animation.AnimatorListenerAdapter, com.nineoldandroids.animation.Animator.AnimatorListener
                public void d(Animator animator2) {
                    ViewCompat.H0(view, 2, (Paint) null);
                }
            });
        }

        public void d(View view) {
            this.f1301d.U(view);
            this.f1300c.U(view);
            this.f1299b.U(view);
            this.f1298a.U(view);
            if (!this.f1302e) {
                c(this.f1298a, view);
                c(this.f1300c, view);
                FloatingActionsMenu.this.f1287o.p(this.f1301d);
                FloatingActionsMenu.this.f1287o.p(this.f1300c);
                FloatingActionsMenu.this.f1286n.p(this.f1299b);
                FloatingActionsMenu.this.f1286n.p(this.f1298a);
                this.f1302e = true;
            }
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public interface OnFloatingActionsMenuUpdateListener {
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    private static class RotatingDrawable extends LayerDrawable {

        /* renamed from: d  reason: collision with root package name */
        private float f1306d;

        public RotatingDrawable(Drawable drawable) {
            super(new Drawable[]{drawable});
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.rotate(this.f1306d, getBounds().centerX(), getBounds().centerY());
            super.draw(canvas);
            canvas.restore();
        }

        public float getRotation() {
            return this.f1306d;
        }

        public void setRotation(float f2) {
            this.f1306d = f2;
            invalidateSelf();
        }
    }

    /* loaded from: /home/mailboxkishan/dex_files/classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: d  reason: collision with root package name */
        public boolean f1307d;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1307d ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f1307d = parcel.readInt() != 1 ? false : true;
        }
    }

    public FloatingActionsMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1286n = new AnimatorSet().f(300L);
        this.f1287o = new AnimatorSet().f(300L);
        t(context, attributeSet);
    }

    private int l(int i2) {
        return (i2 * 12) / 10;
    }

    private void n(boolean z2) {
        long j2;
        if (this.f1285m) {
            this.f1285m = false;
            this.f1295w.c(false);
            AnimatorSet animatorSet = this.f1287o;
            if (z2) {
                j2 = 0;
            } else {
                j2 = 300;
            }
            animatorSet.f(j2);
            this.f1287o.g();
            this.f1286n.b();
        }
    }

    private void o(Context context) {
        AddFloatingActionButton addFloatingActionButton = new AddFloatingActionButton(context) { // from class: net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // net.i2p.android.ext.floatingactionbutton.AddFloatingActionButton, net.i2p.android.ext.floatingactionbutton.FloatingActionButton
            public Drawable getIconDrawable() {
                RotatingDrawable rotatingDrawable = new RotatingDrawable(super.getIconDrawable());
                FloatingActionsMenu.this.f1289q = rotatingDrawable;
                OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
                ObjectAnimator Q = ObjectAnimator.Q(rotatingDrawable, "rotation", 135.0f, 0.0f);
                ObjectAnimator Q2 = ObjectAnimator.Q(rotatingDrawable, "rotation", 0.0f, 135.0f);
                Q.I(overshootInterpolator);
                Q2.I(overshootInterpolator);
                FloatingActionsMenu.this.f1286n.p(Q2);
                FloatingActionsMenu.this.f1287o.p(Q);
                return rotatingDrawable;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // net.i2p.android.ext.floatingactionbutton.FloatingActionButton
            public void n() {
                this.f1248p = FloatingActionsMenu.this.f1276d;
                this.f1254d = FloatingActionsMenu.this.f1277e;
                this.f1255e = FloatingActionsMenu.this.f1278f;
                this.f1265o = FloatingActionsMenu.this.f1280h;
                super.n();
            }
        };
        this.f1288p = addFloatingActionButton;
        addFloatingActionButton.setId(R$id.f1323a);
        this.f1288p.setSize(this.f1279g);
        this.f1288p.setOnClickListener(new View.OnClickListener() { // from class: net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatingActionsMenu.this.v();
            }
        });
        addView(this.f1288p, super.generateDefaultLayoutParams());
        this.f1294v++;
    }

    private void p() {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f1292t);
        for (int i2 = 0; i2 < this.f1294v; i2++) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) getChildAt(i2);
            String title = floatingActionButton.getTitle();
            if (floatingActionButton != this.f1288p && title != null) {
                int i3 = R$id.f1324b;
                if (floatingActionButton.getTag(i3) == null) {
                    TextView textView = new TextView(contextThemeWrapper);
                    textView.setTextAppearance(getContext(), this.f1292t);
                    textView.setText(floatingActionButton.getTitle());
                    addView(textView);
                    floatingActionButton.setTag(i3, textView);
                }
            }
        }
    }

    private boolean r() {
        int i2 = this.f1281i;
        return i2 == 2 || i2 == 3;
    }

    private int s(int i2) {
        return getResources().getColor(i2);
    }

    private void t(Context context, AttributeSet attributeSet) {
        float dimension = getResources().getDimension(R$dimen.f1311a) - getResources().getDimension(R$dimen.f1317g);
        Resources resources = getResources();
        int i2 = R$dimen.f1316f;
        this.f1282j = (int) (dimension - resources.getDimension(i2));
        this.f1283k = getResources().getDimensionPixelSize(R$dimen.f1313c);
        this.f1284l = getResources().getDimensionPixelSize(i2);
        TouchDelegateGroup touchDelegateGroup = new TouchDelegateGroup(this);
        this.f1295w = touchDelegateGroup;
        setTouchDelegate(touchDelegateGroup);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.f1335k, 0, 0);
        this.f1276d = obtainStyledAttributes.getColor(R$styleable.f1338n, s(17170443));
        this.f1277e = obtainStyledAttributes.getColor(R$styleable.f1336l, s(R$color.f1309b));
        this.f1278f = obtainStyledAttributes.getColor(R$styleable.f1337m, s(R$color.f1310c));
        this.f1279g = obtainStyledAttributes.getInt(R$styleable.f1339o, 0);
        this.f1280h = obtainStyledAttributes.getBoolean(R$styleable.f1340p, true);
        this.f1281i = obtainStyledAttributes.getInt(R$styleable.f1341q, 0);
        this.f1292t = obtainStyledAttributes.getResourceId(R$styleable.f1342r, 0);
        this.f1293u = obtainStyledAttributes.getInt(R$styleable.f1343s, 0);
        obtainStyledAttributes.recycle();
        if (this.f1292t != 0 && r()) {
            throw new IllegalStateException("Action labels in horizontal expand orientation is not supported.");
        }
        o(context);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(super.generateDefaultLayoutParams());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(super.generateLayoutParams(attributeSet));
    }

    public void m() {
        n(false);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        bringChildToFront(this.f1288p);
        this.f1294v = getChildCount();
        if (this.f1292t != 0) {
            p();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        int i6;
        int i7;
        int i8;
        int measuredHeight;
        int i9;
        float f2;
        float f3;
        int measuredWidth;
        int i10;
        float f4;
        float f5;
        boolean z4;
        int i11;
        int measuredWidth2;
        float f6;
        float f7;
        int i12 = this.f1281i;
        int i13 = 8;
        float f8 = 0.0f;
        char c2 = 0;
        char c3 = 1;
        if (i12 != 0 && i12 != 1) {
            if (i12 == 2 || i12 == 3) {
                if (i12 == 2) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (z4) {
                    i11 = (i4 - i2) - this.f1288p.getMeasuredWidth();
                } else {
                    i11 = 0;
                }
                int i14 = this.f1291s;
                int measuredHeight2 = ((i5 - i3) - i14) + ((i14 - this.f1288p.getMeasuredHeight()) / 2);
                AddFloatingActionButton addFloatingActionButton = this.f1288p;
                addFloatingActionButton.layout(i11, measuredHeight2, addFloatingActionButton.getMeasuredWidth() + i11, this.f1288p.getMeasuredHeight() + measuredHeight2);
                if (z4) {
                    measuredWidth2 = i11 - this.f1282j;
                } else {
                    measuredWidth2 = this.f1288p.getMeasuredWidth() + i11 + this.f1282j;
                }
                for (int i15 = this.f1294v - 1; i15 >= 0; i15--) {
                    View childAt = getChildAt(i15);
                    if (childAt != this.f1288p && childAt.getVisibility() != 8) {
                        if (z4) {
                            measuredWidth2 -= childAt.getMeasuredWidth();
                        }
                        int measuredHeight3 = ((this.f1288p.getMeasuredHeight() - childAt.getMeasuredHeight()) / 2) + measuredHeight2;
                        childAt.layout(measuredWidth2, measuredHeight3, childAt.getMeasuredWidth() + measuredWidth2, childAt.getMeasuredHeight() + measuredHeight3);
                        float f9 = i11 - measuredWidth2;
                        if (this.f1285m) {
                            f6 = 0.0f;
                        } else {
                            f6 = f9;
                        }
                        ViewHelper.b(childAt, f6);
                        if (this.f1285m) {
                            f7 = 1.0f;
                        } else {
                            f7 = 0.0f;
                        }
                        ViewHelper.a(childAt, f7);
                        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                        layoutParams.f1300c.H(0.0f, f9);
                        layoutParams.f1298a.H(f9, 0.0f);
                        layoutParams.d(childAt);
                        if (z4) {
                            measuredWidth2 -= this.f1282j;
                        } else {
                            measuredWidth2 = measuredWidth2 + childAt.getMeasuredWidth() + this.f1282j;
                        }
                    }
                }
                return;
            }
            return;
        }
        if (i12 == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2) {
            this.f1295w.b();
        }
        if (z3) {
            i6 = (i5 - i3) - this.f1288p.getMeasuredHeight();
        } else {
            i6 = 0;
        }
        if (this.f1293u == 0) {
            i7 = (i4 - i2) - (this.f1290r / 2);
        } else {
            i7 = this.f1290r / 2;
        }
        int measuredWidth3 = i7 - (this.f1288p.getMeasuredWidth() / 2);
        AddFloatingActionButton addFloatingActionButton2 = this.f1288p;
        addFloatingActionButton2.layout(measuredWidth3, i6, addFloatingActionButton2.getMeasuredWidth() + measuredWidth3, this.f1288p.getMeasuredHeight() + i6);
        int i16 = (this.f1290r / 2) + this.f1283k;
        if (this.f1293u == 0) {
            i8 = i7 - i16;
        } else {
            i8 = i16 + i7;
        }
        if (z3) {
            measuredHeight = i6 - this.f1282j;
        } else {
            measuredHeight = this.f1288p.getMeasuredHeight() + i6 + this.f1282j;
        }
        int i17 = this.f1294v - 1;
        while (i17 >= 0) {
            View childAt2 = getChildAt(i17);
            if (childAt2 != this.f1288p && childAt2.getVisibility() != i13) {
                int measuredWidth4 = i7 - (childAt2.getMeasuredWidth() / 2);
                if (z3) {
                    measuredHeight -= childAt2.getMeasuredHeight();
                }
                childAt2.layout(measuredWidth4, measuredHeight, childAt2.getMeasuredWidth() + measuredWidth4, childAt2.getMeasuredHeight() + measuredHeight);
                float f10 = i6 - measuredHeight;
                if (this.f1285m) {
                    f2 = 0.0f;
                } else {
                    f2 = f10;
                }
                ViewHelper.c(childAt2, f2);
                if (this.f1285m) {
                    f3 = 1.0f;
                } else {
                    f3 = 0.0f;
                }
                ViewHelper.a(childAt2, f3);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                ObjectAnimator objectAnimator = layoutParams2.f1300c;
                i9 = i6;
                float[] fArr = new float[2];
                fArr[c2] = f8;
                fArr[c3] = f10;
                objectAnimator.H(fArr);
                ObjectAnimator objectAnimator2 = layoutParams2.f1298a;
                float[] fArr2 = new float[2];
                fArr2[c2] = f10;
                fArr2[c3] = f8;
                objectAnimator2.H(fArr2);
                layoutParams2.d(childAt2);
                View view = (View) childAt2.getTag(R$id.f1324b);
                if (view != null) {
                    if (this.f1293u == 0) {
                        measuredWidth = i8 - view.getMeasuredWidth();
                    } else {
                        measuredWidth = view.getMeasuredWidth() + i8;
                    }
                    int i18 = this.f1293u;
                    if (i18 == 0) {
                        i10 = measuredWidth;
                    } else {
                        i10 = i8;
                    }
                    if (i18 == 0) {
                        measuredWidth = i8;
                    }
                    int measuredHeight4 = (measuredHeight - this.f1284l) + ((childAt2.getMeasuredHeight() - view.getMeasuredHeight()) / 2);
                    view.layout(i10, measuredHeight4, measuredWidth, measuredHeight4 + view.getMeasuredHeight());
                    this.f1295w.a(new TouchDelegate(new Rect(Math.min(measuredWidth4, i10), measuredHeight - (this.f1282j / 2), Math.max(measuredWidth4 + childAt2.getMeasuredWidth(), measuredWidth), childAt2.getMeasuredHeight() + measuredHeight + (this.f1282j / 2)), childAt2));
                    if (this.f1285m) {
                        f4 = 0.0f;
                    } else {
                        f4 = f10;
                    }
                    ViewHelper.c(view, f4);
                    if (this.f1285m) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.0f;
                    }
                    ViewHelper.a(view, f5);
                    LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                    layoutParams3.f1300c.H(0.0f, f10);
                    layoutParams3.f1298a.H(f10, 0.0f);
                    layoutParams3.d(view);
                }
                if (z3) {
                    measuredHeight -= this.f1282j;
                } else {
                    measuredHeight = measuredHeight + childAt2.getMeasuredHeight() + this.f1282j;
                }
            } else {
                i9 = i6;
            }
            i17--;
            i6 = i9;
            i13 = 8;
            f8 = 0.0f;
            c2 = 0;
            c3 = 1;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        TextView textView;
        measureChildren(i2, i3);
        int i4 = 0;
        this.f1290r = 0;
        this.f1291s = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < this.f1294v; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                int i9 = this.f1281i;
                if (i9 != 0 && i9 != 1) {
                    if (i9 == 2 || i9 == 3) {
                        i7 += childAt.getMeasuredWidth();
                        this.f1291s = Math.max(this.f1291s, childAt.getMeasuredHeight());
                    }
                } else {
                    this.f1290r = Math.max(this.f1290r, childAt.getMeasuredWidth());
                    i6 += childAt.getMeasuredHeight();
                }
                if (!r() && (textView = (TextView) childAt.getTag(R$id.f1324b)) != null) {
                    i5 = Math.max(i5, textView.getMeasuredWidth());
                }
            }
        }
        if (!r()) {
            int i10 = this.f1290r;
            if (i5 > 0) {
                i4 = this.f1283k + i5;
            }
            i7 = i10 + i4;
        } else {
            i6 = this.f1291s;
        }
        int i11 = this.f1281i;
        if (i11 != 0 && i11 != 1) {
            if (i11 == 2 || i11 == 3) {
                i7 = l(i7 + (this.f1282j * (this.f1294v - 1)));
            }
        } else {
            i6 = l(i6 + (this.f1282j * (this.f1294v - 1)));
        }
        setMeasuredDimension(i7, i6);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        float f2;
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            boolean z2 = savedState.f1307d;
            this.f1285m = z2;
            this.f1295w.c(z2);
            RotatingDrawable rotatingDrawable = this.f1289q;
            if (rotatingDrawable != null) {
                if (this.f1285m) {
                    f2 = 135.0f;
                } else {
                    f2 = 0.0f;
                }
                rotatingDrawable.setRotation(f2);
            }
            super.onRestoreInstanceState(savedState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1307d = this.f1285m;
        return savedState;
    }

    public void q() {
        if (!this.f1285m) {
            this.f1285m = true;
            this.f1295w.c(true);
            this.f1287o.b();
            this.f1286n.g();
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        this.f1288p.setEnabled(z2);
    }

    public void setOnFloatingActionsMenuUpdateListener(OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener) {
    }

    public boolean u() {
        return this.f1285m;
    }

    public void v() {
        if (this.f1285m) {
            m();
        } else {
            q();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(super.generateLayoutParams(layoutParams));
    }
}
