package im_system.util;

import im_system.util.Impl.Serializer;
import im_system.util.Impl.SerializerAlrotithm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
