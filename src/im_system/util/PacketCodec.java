package im_system.util;

import im_system.data_packet.LoginRequestPacket;
import im_system.data_packet.Packet;
import im_system.util.Impl.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static im_system.util.Impl.Command.LOGIN_REQUEST;

/**
 * @author xiongPacket
 * @date 2019-05-28  15:52
 */
public class PacketCodec {

    private static final int MAGIC_NUMBER = 0x88737871;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(Packet packet){

        ByteBuf byteBuf =ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);   //魔数
        byteBuf.writeByte(packet.getVersion());  //版本号
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());  //序列化算法
        byteBuf.writeInt(LOGIN_REQUEST);  //指令
        byteBuf.writeInt(bytes.length);  //数据长度
        byteBuf.writeBytes(bytes);  //数据

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){

        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        byte serializeAlogrithm = byteBuf.readByte();

        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlogrithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlogrithm){
        return serializerMap.get(serializeAlogrithm);
    }

    private Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }

}
