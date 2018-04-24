package com.open.coinnews.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static final String BASE_PATH_PRE = "resource/";
    public static final Integer PAGE_NUM = 15;
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");

    public static String dateToStr(java.util.Date dateDate) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString + "&middot;" + Utils.getWeek(dateDate);
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        // 再转换为时间
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return getWeekStr(c.get(Calendar.DAY_OF_WEEK));
    }

    public static String getWeekStr(int w){
        String str = "";
        if(w == 1){
            str = "星期日";
        }else if(w == 2){
            str = "星期一";
        }else if(w == 3){
            str = "星期二";
        }else if(w == 4){
            str = "星期三";
        }else if(w == 5){
            str = "星期四";
        }else if(w == 6){
            str = "星期五";
        }else if(w == 7){
            str = "星期六";
        }
        return str;
    }
}
