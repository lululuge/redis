package cn.luge.service.impl;

import cn.luge.dao.ProvinceDao;
import cn.luge.dao.impl.ProvinceDaoImpl;
import cn.luge.domain.Province;
import cn.luge.service.ProvinceService;
import cn.luge.util.JedisPoolUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        // 判断缓存中是否有json字符串
        Jedis jedis = JedisPoolUtils.getJedis();
        String json = jedis.get("province");
        if (json == null || json.length() == 0) {
            System.out.println("创建缓存json");
            // 若没有json字符串缓存,就从数据库查询，并将查询结果放入缓存
            List<Province> list = dao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 将json字符串放入缓存
            jedis.set("province", json);
            jedis.close();

        } else {
            System.out.println("直接从缓存查询");
        }
        return json;
    }
}
