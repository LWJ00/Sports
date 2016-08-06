package com.hzuapp.team.sports.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Azusa on 2016/5/6.
 */
public class ConstanceUtils {
    public static Context CONTEXT;
    public static String BMAP_AK = "";
    public static final int MESSAGE_OK = 1;
    public static final int TAKEPHOTO_REQUESTCODE = 7;
    public static final int TAKEPHOTO_RESULTCODE = 27;
    public static final int CUT_PHOTO_REQUESTCODE = 8;
    public static final int CUT_PHOTO_RESULTCODE = 23;
    public static long THE_COUNT_DOWN_TIME = 0l;//最后一次发送短信的时间点
    public static int screenWidth; //屏幕宽度
    public static int screenHight; //屏幕高度
    public static String LOCATION_MSG;  //用户当前位置文字话信息
    public static double LOCATION_LONGITUDE;  //用户当前位置经度
    public static double LOCATION_LATITUDE;  //用户当前位置纬度
    public static Resources resources;  //资源列表
    public static String IP = "";
    public static String Pic="";
}
