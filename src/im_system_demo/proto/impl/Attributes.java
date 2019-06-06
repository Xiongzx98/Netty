package im_system_demo.proto.impl;

import im_system_demo.server.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author xiong
 * @date 2019-05-31  21:52
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
