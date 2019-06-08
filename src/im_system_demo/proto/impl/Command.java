package im_system_demo.proto.impl;

/**
 * @author xiong
 * @date 2019-05-28  14:52
 */

public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE= 6;

    Byte CRETE_GROUP_REQUEST = 7;

    Byte CRETE_GROUP_RESPONSE = 8;

    Byte SHOW_GROUP_MEMBERS_REQUEST = 9;

    Byte SHOW_GROUP_MEMBERS_RESPONSE = 10;

    Byte JOIN_GROUP_REQUEST = 11;

    Byte JOIN_GROUP_RESPONSE = 12;

    Byte QUIT_GROUP_REQUEST = 13;

    Byte QUIT_GROUP_RESPONSE = 14;

}
