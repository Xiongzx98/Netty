package im_system.util.Impl;

import im_system.util.JSONSerializer;

/**
 * @author xiong
 * @date 2019-05-28  15:00
 */
public interface SerializerAlrotithm {

    byte JSON  = 1;

    Serializer DEFAULT = new JSONSerializer();

}
