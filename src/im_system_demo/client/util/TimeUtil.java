package im_system_demo.client.util;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-06-11  11:32
 */
public class TimeUtil {

    private static final Date time = new Date();

    public static String getTime(){
        return time.toString();
    }

}
