package im_system.data_packet;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author xiong
 * @date 2019-05-28  14:45
 */

public abstract class Packet {

    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
