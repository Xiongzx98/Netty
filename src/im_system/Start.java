package im_system;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiong
 * @date 2019-05-28  16:14
 */
public class Start {

    private int hex = 0x012345678;
    private int hex2 = 0;
    private static Unsafe UNSAFE;
    private static Field getUnsafe;
    private static long hexOffset;
    private static long hex2Offset;

    static {
        try {
            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) getUnsafe.get(null);

            Class<?> k = Start.class;
            hexOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("hex"));
            hex2Offset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("hex2"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        UNSAFE.putByte(hex2Offset,(byte)(hex>>>24));
        System.out.println(hex2);
    }

    public static void main(String[] args) {
//        LoginRequestPacket login = new LoginRequestPacket();
//        login.setUsername("lalala");
//        login.setPassword("lalala");
//        login.setUserID(007);
//        PacketCodec code = new PacketCodec();
//        code.encode(login);

        Start start = new Start();
        start.test();




//        int hex = 0x88737871;
//        System.out.println();


    }
}
