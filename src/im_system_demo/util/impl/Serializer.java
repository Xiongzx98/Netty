package im_system_demo.util.impl;

import im_system_demo.util.JSONSerializerUtil;

/**
 * @author xiong
 * @date 2019-05-28  14:57
 */
public interface Serializer {

    byte getSerializerAlgorithm();

    String serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

    Serializer DEFAULT = new JSONSerializerUtil();

}
