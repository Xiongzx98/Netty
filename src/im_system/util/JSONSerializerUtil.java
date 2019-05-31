package im_system.util;

import im_system.util.impl.Serializer;
import im_system.util.impl.SerializerAlrotithm;
import com.alibaba.fastjson.JSON;

/**
 * @author xiong
 * @date 2019-05-28  15:01
 */
public class JSONSerializerUtil implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlrotithm.JSON;
    }

    @Override
    public String serialize(Object object) {

        return JSON.toJSONString(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
