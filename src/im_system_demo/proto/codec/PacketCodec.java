package im_system_demo.proto.codec;

import im_system_demo.proto.Packet;
import im_system_demo.proto.request_packet.*;
import im_system_demo.proto.response_packet.*;
import im_system_demo.util.impl.Serializer;
import im_system_demo.util.JSONSerializerUtil;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static im_system_demo.proto.impl.Command.*;

/**
 * @author xiong
 * @date 2019-05-31  09:38
 */
public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x76737865;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodec(){

        packetTypeMap = new HashMap<>();

        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(CRETE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(SHOW_GROUP_MEMBERS_REQUEST, ShowGroupRequestPacket.class);
        packetTypeMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);

        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(CRETE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(SHOW_GROUP_MEMBERS_RESPONSE, ShowGroupResponsePacket.class);
        packetTypeMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(HEART_BEAT_RESPONSE, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializerUtil();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);

    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());

        String str = JSONSerializerUtil.DEFAULT.serialize(packet);
        byte[] bytes = str.getBytes();

        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        byte serializerAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;

    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

}
