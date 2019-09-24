package cn.luge.test;

import cn.luge.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestJedis {
    @Test
    public void test1() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("name", "luzan");
        String name = jedis.get("name");
        System.out.println(name);
    }
}
