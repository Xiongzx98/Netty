package im_system_demo.server.util;

import java.util.UUID;

/**
 * @author xiong
 * @date 2019-06-06  13:52
 */
public class IDUtil {

    public static String getID(){
        return UUID.randomUUID().toString().split("-")[0];
    }

}
