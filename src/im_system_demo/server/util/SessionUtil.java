package im_system_demo.server.util;

import im_system_demo.proto.impl.Attributes;
import im_system_demo.server.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiong
 * @date 2019-06-05  09:58
 */
public class SessionUtil {

    private static final Map<String, Channel> USER_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> GROUP_MAP = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        USER_MAP.put(session.getUsername(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            USER_MAP.remove(getSession(channel).getUsername());
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
        return USER_MAP.get(username);
    }

    public static void bindChannelGroup(String nickname, ChannelGroup group){
        GROUP_MAP.put(nickname, group);
    }

    public static ChannelGroup getChannelGroup(String nickname){
        return GROUP_MAP.get(nickname);
    }
}
