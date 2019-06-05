package other.test;

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
        Jedis jedis = new Jedis("localhost", 63790);
        jedis.connect();
        boolean flag = jedis.sismember("users","ironman|loveu3k");

        if(flag){
            System.out.println(flag);
        }else
            System.out.println(flag+"----------");
        jedis.disconnect();
    }

}
