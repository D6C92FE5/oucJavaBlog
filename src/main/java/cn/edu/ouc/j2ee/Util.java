package cn.edu.ouc.j2ee;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

    public static SimpleDateFormat dateFormater =
            new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");

    public static String toString(Date date) {
        return dateFormater.format(date);
    }


}
