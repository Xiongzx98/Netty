package im_system.util.Impl;

import im_system.util.JSONSerializer;

/**
 * @author xiong
 * @date 2019-05-28  14:57
 */
public interface Serializer {

    byte getSerializerAlgorithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

}
