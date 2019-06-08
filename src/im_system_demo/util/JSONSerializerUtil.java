package im_system_demo.util;

import im_system_demo.util.impl.Serializer;
import im_system_demo.util.impl.SerializerAlrotithm;
import com.alibaba.fastjson.JSON;

/**
 * @author xiong
 * @date 2019-05-28  22:01
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
