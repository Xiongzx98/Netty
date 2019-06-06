package im_system_demo.server.util;

import im_system_demo.server.redis.RedisPoll;
import redis.clients.jedis.Jedis;

/**
 * @author xiong
 * @date 2019-06-05  22:36
 */
public class RedisUtil {

    public static boolean loginValid(String usename, String pwd){
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = RedisPoll.getJedis();
            jedis.connect();
            String password = jedis.hget("usersInfo",usename).split("\\|")[0];
            if(password.equals(pwd))
                flag = true;
        }catch (Exception e){
            RedisPoll.returnBrokerQueue(jedis);
        }finally {
            RedisPoll.returnJedis(jedis);
        }
        return flag;
    }

}
