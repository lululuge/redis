package cn.luge.service;

import cn.luge.domain.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();

    String findAllJson();
}
