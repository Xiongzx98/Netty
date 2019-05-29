package im_system.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiong
 * @date 2019-05-29  12:02
 */
public class RedisPollUtil {

    private static JedisPool pool;

    private static ReentrantLock lock= new ReentrantLock();
   //连接redis实例的ip
    private static final String REDIS_ADDRESS = "localhost";
   //连接redis实例的端口
    private static final int PORT = 6379;
   //多线程环境中,连接实例的最大数,如果设为-1则无上线,建议设置,否则有可能导致资源耗尽
    private static final int MAX_ACTIVE = Runtime.getRuntime().availableProcessors() * 2;
   //在多线程环境中,连接池中最大空闲连接数,单线程环境没有实际意义
    private static final int MAX_OLDE = 4;
   //在多线程环境中,连接池中最小空闲连接数
    private static final int MIN_OLDE = 1;
   //多长时间将空闲线程进行回收,单位毫秒
    private static final int METM = 2000;
   //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
    private static final int SMETM = 2000;
   //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1,只有运行了此线程,MIN_OLDE METM/SMETM才会起作用
    private static final int TBERM = 1000;
   //当连接池中连接不够用时,等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static final int MAX_WAIT = 1000;
   //超时时间,单位毫秒
    private static final int TIME_OUT = 5000;
   //在借用一个jedis连接实例时，是否提前进行有效性确认操作；如果为true，则得到的jedis实例均是可用的；  
    private static final boolean TEST_ON_BORROW = false;


    static{



        pool = new JedisPool();
    }



}
