package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.model.City;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public interface ICityService {

    public List<City> getCities();

    City getCity(Long id);

     List<Map<String ,String>> getCitiesMap();

    void insertCity(String name, int population);

    void updateCity(String cityName, int population);

    void deleteCity(String cityName);
}
