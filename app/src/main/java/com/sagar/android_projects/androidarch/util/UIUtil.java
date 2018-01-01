package com.sagar.android_projects.androidarch.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * created by SAGAR KUMAR NAYAK
 * util class to perform any kind of UI operations
 * <p>
 * exposed methods
 * {@link #hideSoftKeyboard(Context)}
 * {@link #hideSoftKeyboardAtStart(Context)}
 */
public class UIUtil {

    /**
     * method to hide the soft keyboard
     *
     * @param context context
     */
    @SuppressWarnings({"unused", "SameParameterValue", "WeakerAccessss"})
    public static void hideSoftKeyboard(Context context) {
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null)
                return;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * method to set the activity to not to show the soft keyboard at activity start.
     * to use this method call it before {@link Activity#setContentView(int)} in Activity
     *
     * @param context context
     */
    @SuppressWarnings({"unused", "SameParameterValue", "WeakerAccess"})
    public static void hideSoftKeyboardAtStart(Context context) {
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
