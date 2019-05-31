package im_system.proto.impl;

/**
 * @author xiong
 * @date 2019-05-28  14:52
 */

public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

}
