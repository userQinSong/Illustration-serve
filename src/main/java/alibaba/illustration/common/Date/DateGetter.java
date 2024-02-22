package alibaba.illustration.common.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGetter {
    public static Date getCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return new Date(System.currentTimeMillis());
    }
    public static String formatter(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义新的日期格式
        return formatter.format(date);
    }
}
