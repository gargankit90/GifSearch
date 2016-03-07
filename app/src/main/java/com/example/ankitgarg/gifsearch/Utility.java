package com.example.ankitgarg.gifsearch;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by ankitgarg on 3/6/16.
 */
public class Utility {

    public static void showKeyboard(Context mContext){
        ((InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


//    public static void hideKeyboard(Context mContext){
//        Window().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//        );
//    }
}
