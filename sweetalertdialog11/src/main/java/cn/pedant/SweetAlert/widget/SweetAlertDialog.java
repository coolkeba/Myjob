package cn.pedant.SweetAlert.widget;


import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.pedant.SweetAlert.R;
import java.util.List;

public class SweetAlertDialog extends Dialog implements View.OnClickListener {
    private View mDialogView;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Animation mOverlayOutAnim;
    private Animation mErrorInAnim;
    private AnimationSet mErrorXInAnim;
    private AnimationSet mSuccessLayoutAnimSet;
    private Animation mSuccessBowAnim;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private String mTitleText;
    private String mContentText;
    private boolean mShowCancel;
    private String mCancelText;
    private String mConfirmText;
    private int mAlertType;
    private FrameLayout mErrorFrame;
    private FrameLayout mSuccessFrame;
    private SuccessTickView mSuccessTick;
    private ImageView mErrorX;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private Drawable mCustomImgDrawable;
    private ImageView mCustomImage;
    private Button mConfirmButton;
    private Button mCancelButton;
    private FrameLayout mWarningFrame;
    private OnSweetClickListener mCancelClickListener;
    private OnSweetClickListener mConfirmClickListener;
    private LinearLayout mTimeThree;
    private int day = 0;

    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    public static final int CUSTOM_IMAGE_TYPE = 4;

    public static interface OnSweetClickListener {
        public void onClick (SweetAlertDialog sweetAlertDialog);
    }

    public SweetAlertDialog(Context context) {
        this(context, NORMAL_TYPE);
    }

    public SweetAlertDialog(Context context, int alertType) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        mAlertType = alertType;
        mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
        mErrorXInAnim = (AnimationSet)OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);
        // 2.3.x system don't support alpha-animation on layer-list drawable
        // remove it from animation set
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            List<Animation> childAnims = mErrorXInAnim.getAnimations();
            int idx = 0;
            for (;idx < childAnims.size();idx++) {
                if (childAnims.get(idx) instanceof AlphaAnimation) {
                    break;
                }
            }
            if (idx < childAnims.size()) {
                childAnims.remove(idx);
            }
        }
        mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
        mSuccessLayoutAnimSet = (AnimationSet)OptAnimationLoader.loadAnimation(getContext(), R.anim.success_mask_layout);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        SweetAlertDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // dialog overlay fade out
        mOverlayOutAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                getWindow().getDecorView().getBackground().setAlpha((int)((1 - interpolatedTime) * 255));
            }
        };
        mOverlayOutAnim.setDuration(150);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mTitleTextView = (TextView)findViewById(R.id.title_text);
        mContentTextView = (TextView)findViewById(R.id.content_text);
        mErrorFrame = (FrameLayout)findViewById(R.id.error_frame);
        mErrorX = (ImageView)mErrorFrame.findViewById(R.id.error_x);
        mSuccessFrame = (FrameLayout)findViewById(R.id.success_frame);
        mSuccessTick = (SuccessTickView)mSuccessFrame.findViewById(R.id.success_tick);
        mSuccessLeftMask = mSuccessFrame.findViewById(R.id.mask_left);
        mSuccessRightMask = mSuccessFrame.findViewById(R.id.mask_right);
        mCustomImage = (ImageView)findViewById(R.id.custom_image);
        mWarningFrame = (FrameLayout)findViewById(R.id.warning_frame);
        mConfirmButton = (Button)findViewById(R.id.confirm_button);
        mCancelButton = (Button)findViewById(R.id.cancel_button);
        mTimeThree = (LinearLayout) findViewById(R.id.time_three);

        mConfirmButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

        setTitleText(mTitleText);
        setContentText(mContentText);
        showCancelButton(mShowCancel);
        setCancelText(mCancelText);
        setConfirmText(mConfirmText);
        changeAlertType(mAlertType, true);

        final LinearLayout dayLayout0 = (LinearLayout) findViewById(R.id.day_0);
        final LinearLayout dayLayout1 = (LinearLayout) findViewById(R.id.day_1);
        final LinearLayout dayLayout2 = (LinearLayout) findViewById(R.id.day_2);
        bg = ContextCompat.getDrawable(getContext(), R.drawable.circle_step_done);
        int c = getContext().getResources().getColor(R.color.error_stroke_color);
        bg.setColorFilter(new PorterDuffColorFilter(c, PorterDuff.Mode.SRC_IN));
        isbg1 = false;
        isbg2 = false;
        isbg3 = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            dayLayout0.setBackground(bg);
            day = 1;
        }
        dayLayout1.setBackgroundResource(0);
        dayLayout2.setBackgroundResource(0);
        dayLayout0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg1 == true) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout0.setBackground(bg);
                        day = 1;
                    }
                    isbg1 = false;
                    dayLayout1.setBackgroundResource(0);
                    isbg2 = false;
                    dayLayout2.setBackgroundResource(0);
                    isbg3 = false;

                }
            }
        });
        dayLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg2 == false) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout1.setBackground(bg);
                        day = 2;
                    }
                    isbg2 = true;
                    dayLayout0.setBackgroundResource(0);
                    isbg1 = true;
                    dayLayout2.setBackgroundResource(0);
                    isbg3 = false;

                }
            }
        });
        dayLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg3 == false) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout2.setBackground(bg);
                        day = 3;
                    }
                    isbg3 = true;
                    dayLayout0.setBackgroundResource(0);
                    isbg1 = true;
                    dayLayout1.setBackgroundResource(0);
                    isbg2 = false;

                }
            }
        });
        final TextView dayText0 = (TextView)dayLayout0.findViewById(R.id.day);
        final TextView dayText1 = (TextView)dayLayout1.findViewById(R.id.day);
        final TextView dayText2 = (TextView)dayLayout2.findViewById(R.id.day);
        dayText0.setText("1");
        dayText1.setText("2");
        dayText2.setText("3");
    }

    Drawable bg;
    boolean isbg1;
    boolean isbg2;
    boolean isbg3;

    public int getDay() {
        return day;
    }

    private void restore () {
        mCustomImage.setVisibility(View.GONE);
        mErrorFrame.setVisibility(View.GONE);
        mSuccessFrame.setVisibility(View.GONE);
        mWarningFrame.setVisibility(View.GONE);
        mTimeThree.setVisibility(View.GONE);

        mConfirmButton.setBackgroundResource(R.drawable.blue_button_background);
        mErrorFrame.clearAnimation();
        mErrorX.clearAnimation();
        mSuccessTick.clearAnimation();
        mSuccessLeftMask.clearAnimation();
        mSuccessRightMask.clearAnimation();
    }

    private void playAnimation () {
        if (mAlertType == ERROR_TYPE) {
            mErrorFrame.startAnimation(mErrorInAnim);
            mErrorX.startAnimation(mErrorXInAnim);
        } else if (mAlertType == SUCCESS_TYPE) {
            mSuccessTick.startTickAnim();
            mSuccessRightMask.startAnimation(mSuccessBowAnim);
        }
    }

    private void changeAlertType(int alertType, boolean fromCreate) {
        mAlertType = alertType;
        // call after created views
        if (mDialogView != null) {
            if (!fromCreate) {
                // restore all of views state before switching alert type
                restore();
            }
            switch (mAlertType) {
                case ERROR_TYPE:
                    mErrorFrame.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS_TYPE:
                    mSuccessFrame.setVisibility(View.VISIBLE);
                    // initial rotate layout of success mask
                    mSuccessLeftMask.startAnimation(mSuccessLayoutAnimSet.getAnimations().get(0));
                    mSuccessRightMask.startAnimation(mSuccessLayoutAnimSet.getAnimations().get(1));
                    break;
                case WARNING_TYPE:
                    mConfirmButton.setBackgroundResource(R.drawable.red_button_background);
                    mWarningFrame.setVisibility(View.GONE);
                    mTimeThree.setVisibility(View.VISIBLE);
                    break;
                case CUSTOM_IMAGE_TYPE:
                    setCustomImage(mCustomImgDrawable);
                    break;
            }
            if (!fromCreate) {
                playAnimation();
            }
        }
    }

    public int getAlerType () {
        return mAlertType;
    }

    public void changeAlertType(int alertType) {
        changeAlertType(alertType, false);
    }


    public String getTitleText () {
        return mTitleText;
    }

    public SweetAlertDialog setTitleText (String text) {
        mTitleText = text;
        if (mTitleTextView != null && mTitleText != null) {
            mTitleTextView.setText(mTitleText);
        }
        return this;
    }

    public SweetAlertDialog setCustomImage (Drawable drawable) {
        mCustomImgDrawable = drawable;
        if (mCustomImage != null && mCustomImgDrawable != null) {
            mCustomImage.setVisibility(View.VISIBLE);
            mCustomImage.setImageDrawable(mCustomImgDrawable);
        }
        return this;
    }

    public SweetAlertDialog setCustomImage (int resourceId) {
        return setCustomImage(getContext().getResources().getDrawable(resourceId));
    }

    public String getContentText () {
        return mContentText;
    }

    public SweetAlertDialog setContentText (String text) {
        mContentText = text;
        if (mContentTextView != null && mContentText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mContentText);
        }
        return this;
    }

    public boolean isShowCancelButton () {
        return mShowCancel;
    }

    public SweetAlertDialog showCancelButton (boolean isShow) {
        mShowCancel = isShow;
        if (mCancelButton != null) {
            mCancelButton.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public String getCancelText () {
        return mCancelText;
    }

    public SweetAlertDialog setCancelText (String text) {
        mCancelText = text;
        if (mCancelButton != null && mCancelText != null) {
            mCancelButton.setText(mCancelText);
        }
        return this;
    }

    public String getConfirmText () {
        return mConfirmText;
    }

    public SweetAlertDialog setConfirmText (String text) {
        mConfirmText = text;
        if (mConfirmButton != null && mConfirmText != null) {
            mConfirmButton.setText(mConfirmText);
        }
        return this;
    }

    public SweetAlertDialog setCancelClickListener (OnSweetClickListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public SweetAlertDialog setConfirmClickListener (OnSweetClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }

    protected void onStart() {
        getWindow().getDecorView().getBackground().setAlpha(255);
        mDialogView.startAnimation(mModalInAnim);
        playAnimation();
    }

    public void dismiss() {
        mConfirmButton.startAnimation(mOverlayOutAnim);
        mDialogView.startAnimation(mModalOutAnim);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel_button) {
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(SweetAlertDialog.this);
            } else {
                dismiss();
            }

        } else if (i == R.id.confirm_button) {
            if (mConfirmClickListener != null) {
                mConfirmClickListener.onClick(SweetAlertDialog.this);
            } else {
                dismiss();
            }

        }
    }
}
