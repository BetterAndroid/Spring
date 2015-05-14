package com.spring.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Created by peopleway1 on 15/4/3.
 * 下面以480dip * 800dip的WVGA(density=240)为例，详细列出不同density下屏幕分辨率信息：
 * 当density = 120时 屏幕实际分辨率为240px*400px （两个点对应一个分辨率）
 * 使用前先初始化 请填入您所使用分辨率的标准，尺寸以及密度
 * 例子ScreenUtil.init(this, 1080, 1776, 3.0f);
 * 使用的时候ScreenUtil.updateWH(tv, 100, 20, true, true);
 * 第一个参数为传入的控件, 宽, 高单位dp, 是否修改宽，是否修改高
 *
 * 注意：全程中请使用同一标准适配
 *
 *
 *
 */
public class ScreenManager {

    private static float density;
    private static final String TAG = "ScreenUtil";
    private static int tryW;
    private static int tryH;
    private  static int heightPixels;
    private  static int widthPixels;

    /**
     *
     * @param context
     * @param tryW 根据何种分辨率进行的适配
     * @param tryH
     */
    public static void init(Context context, int tryW, int tryH){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        density = dm.density;
        heightPixels = dm.heightPixels;
        widthPixels = dm.widthPixels;
        ScreenManager.tryW = tryW;
        ScreenManager.tryH = tryH;
        //不知道自己调试手机分辨率直接使用它查看
        Log.e(TAG,"density:" + density);

    }

    //设置单位dp的文字大小
    public static void updateTextViewSize(TextView textView, int size){

        float newTextSize = size *  density;
        Log.e(TAG, "newTextSize:"+ newTextSize);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
    }

    //请按照初始化适配的手机指定,单位dp 添加适配手机的
    //适用于一部分控件如果控件没有适配问题,尽量不要调用
    //直接根据分辨率密度进行适配，大部分情况下使用此适配方式即可

    /**
     *
     * @param view 需要修改的组件（dp）
     * @param setW 设置宽度（dp）
     * @param setH 设置高度（dp）
     * @param isChangeW 是否修改宽度
     * @param isChangeH 是否修改高度
     * @param isZoom 是否按照屏幕比例缩放
     */
    public static void updateWH(View view, int setW, int setH, boolean isChangeW, boolean isChangeH, boolean isZoom){

        int beforePaddingLeft = view.getPaddingLeft();
        int beforePaddingRight = view.getPaddingRight();
        int beforePaddingBottom = view.getPaddingBottom();
        int beforePaddingTop = view.getPaddingTop();

        int newW = (int)(setW * density);
        int newH = (int)(setH * density);

        int newPaddingLeft = (int)(beforePaddingLeft * density);
        int newPaddingRight = (int)(beforePaddingRight * density);
        int newPaddingBottom = (int)(beforePaddingBottom * density);
        int newPaddingTop = (int)(beforePaddingTop * density);

        //按照屏幕比例缩放
        if(isZoom){
            newW =  newW * widthPixels / tryW;
            newH = newH * heightPixels / tryH;
            newPaddingLeft =   newPaddingLeft * widthPixels / tryW;
            newPaddingRight =   newPaddingRight * widthPixels / tryW ;
            newPaddingBottom = newPaddingBottom * heightPixels / tryH;
            newPaddingTop = newPaddingTop * heightPixels / tryH ;
        }

        Log.e(TAG, "newW:" + newW);
        Log.e(TAG, "newH:" + newH);

        if(view.getLayoutParams() != null){
            if(isChangeW){
                view.getLayoutParams().width = newW;
            }
            if(isChangeH){
                view.getLayoutParams().height = newH;
            }
        }
        view.setPadding(newPaddingLeft, newPaddingTop, newPaddingRight, newPaddingBottom);
        view.invalidate();
    }





}

