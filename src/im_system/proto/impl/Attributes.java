package im_system.proto.impl;

import im_system.server.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author xiong
 * @date 2019-05-31  11:52
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
