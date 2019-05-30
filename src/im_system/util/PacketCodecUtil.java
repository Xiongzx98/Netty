package im_system.util;

import im_system.data_packet.request.LoginRequestPacket;
import im_system.data_packet.Packet;
import im_system.data_packet.response.LoginResponsePacket;
import im_system.util.Impl.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static im_system.util.Impl.Command.LOGIN_REQUEST;
import static im_system.util.Impl.Command.LOGIN_RESPONSE;

/**
 * @author xiongPacket
 * @date 2019-05-28  15:52
 */
public class PacketCodecUtil {

    private static final int MAGIC_NUMBER = 0x76737865;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;
    public static PacketCodecUtil INSTANCE = new PacketCodecUtil();

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE,LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializerUtil();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    /**
     *
     * @param alloc
     * @param packet
     * @return
     *
     *
     * 自定义协议:  魔数  版本号  序列化算法  指令  数据长度  数据
     */
    public ByteBuf encode(ByteBufAllocator alloc, Packet packet){

        ByteBuf byteBuf =alloc.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

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
