package im_system_demo.client.util;


import im_system_demo.proto.impl.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author xiong
 * @date 2019-05-31  21:54
 */
public class UserUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static void markAsGroup(Channel channel){
        channel.attr(Attributes.CHATTING).set("group");
    }

    public static void markAsUser(Channel channel){
        channel.attr(Attributes.CHATTING).set("user");
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

    public static boolean hasGroup(Channel channel){
        Attribute<String> currentgroup = channel.attr(Attributes.CURRENT_GROUP);
        return currentgroup.get() != null;
    }

    public static boolean hasChat(Channel channel){
        Attribute<String> chat = channel.attr(Attributes.CHATTING);
        return chat.get() != null;
    }
}
