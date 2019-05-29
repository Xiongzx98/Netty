package im_system.test;

import redis.clients.jedis.Jedis;

/**
 * @author xiong
 * @date 2019-05-29  10:17
 */
public class JedisTest {

    public static void main(String[] args) {
        /**
         * redis协议RESP(Redis Serialization Protocol)
         * eg:
         */
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        String password = jedis.get("IronMan".toLowerCase());

        if(password != null){
            System.out.println(password);
        }
        jedis.disconnect();
    }

}
