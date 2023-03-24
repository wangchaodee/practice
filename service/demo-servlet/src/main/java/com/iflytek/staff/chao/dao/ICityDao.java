package com.iflytek.staff.chao.dao;

import com.iflytek.staff.chao.model.City;
import org.json.simple.JSONArray;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public interface ICityDao {
    public List<City> findAll();

    City findById(Long id);

    void insertCity(String name, int population);

    void updateCity(String cityName, int population);

    void deleteCity(String cityName);
}
