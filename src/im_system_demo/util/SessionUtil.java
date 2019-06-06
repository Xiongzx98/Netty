package im_system_demo.util;

import im_system_demo.proto.impl.Attributes;
import im_system_demo.server.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiong
 * @date 2019-06-05  09:58
 */
public class SessionUtil {

    private static final Map<String, Channel> USERNAME_CHANNEL_MAP = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        USERNAME_CHANNEL_MAP.put(session.getUsername(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            USERNAME_CHANNEL_MAP.remove(getSession(channel).getUsername());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){

        return  channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String username){
        return USERNAME_CHANNEL_MAP.get(username);
    }
}
