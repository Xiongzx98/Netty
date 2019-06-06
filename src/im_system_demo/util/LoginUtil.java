package im_system_demo.util;


import im_system_demo.proto.impl.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author xiong
 * @date 2019-05-31  21:54
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){

        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

}
