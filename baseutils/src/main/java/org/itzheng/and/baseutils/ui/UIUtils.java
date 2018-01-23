package org.itzheng.and.baseutils.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 关于UI的一些操作，如view，toast
 */
public class UIUtils {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private UIUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getContext() {
        if (mContext == null) {
            try {
                throw new Exception("mContext == null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mContext;
    }

    /**
     * sp转px
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getContext().getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / getContext().getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                dip, getContext().getResources().getDisplayMetrics());
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将布局文件转换成View
     *
     * @param resId
     * @return
     */
    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    public static View inflateAdapterView(int resId, ViewGroup parent) {
        return LayoutInflater.from(UIUtils.getContext()).inflate(resId, parent, false);
    }

    /**
     * 将布局文件转换成View
     *
     * @param resId
     * @return
     */
    public static View inflate(Activity activity, int resId) {
        return LayoutInflater.from(activity).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    /**
     * 单例吐司
     */
    private static Toast mToast;
    private static final Object SYNC_LOCK = new Object();

    /**
     * 单例吐司
     *
     * @param str
     */
    public static void showToast(String str) {
        if (mToast == null) {
            synchronized (SYNC_LOCK) {
                if (mToast == null) {
                    mToast = Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
                }
            }
        } else {
            mToast.cancel();//将之前的取消，如果不取消，连续点击10次后会导致部分点击不弹
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(str);
        }
        //延时执行，是因为刚刚取消，如果马上执行会不生效
        runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                if (mToast != null)
                    mToast.show();
            }
        }, 5);
    }

    /**
     * 在UI线程中执行
     *
     * @param command
     */
    public static void runOnUiThreadDelayed(final Runnable command, final long delayMillis) {
        if (Looper.myLooper() == null)
            Looper.prepare();
        new Handler(Looper.getMainLooper()).postDelayed(command, delayMillis);
    }

    /**
     * 对toast的简易封装。
     */
    public static void showToast(final int resId) {
        showToast(getString(resId));
    }

    /**
     * 跳转到Activity
     *
     * @param cls 要跳转的Activity类名
     */
    public static void startActivity(Class<?> cls) {
        if (cls != null) {
            Intent intent = new Intent(getContext(), cls);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            getContext().startActivity(intent);
            startActivity(intent);
        }
    }

    public static void startActivity(Intent intent) {
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        if (activity == null || intent == null) {
            return;
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int requestCode) {
        if (fragment == null || intent == null) {
            return;
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 设置edittext是否可以输入
     *
     * @param editText
     * @param isInput
     */
    public static void setEditTextInput(EditText editText, boolean isInput) {
        if (editText == null) {
            return;
        }
        editText.setCursorVisible(isInput);//光标隐藏
        editText.setFocusable(isInput);//丢失焦点
        editText.setFocusableInTouchMode(isInput);//不可获得焦点
    }

    /**
     * 设置光标在文字后面
     *
     * @param editText
     */
    public static void setEditTextCursorEnd(EditText editText) {
        if (editText == null) {
            return;
        }
        editText.setSelection(editText.getText().length());
    }

    public static void setOnViewMeasuredListener(final View view, final OnViewMeasuredListener onViewMeasuredListener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= 16) {
                            view.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } else {
                            view.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                        if (onViewMeasuredListener != null) {
                            onViewMeasuredListener.onMeasured(view.getWidth(), view.getHeight());
                        }
                    }
                });

    }

    public interface OnViewMeasuredListener {
        void onMeasured(int width, int height);
    }
}
