package com.iflytek.staff.chao.dao;

import com.iflytek.staff.chao.model.City;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@ManagedBean
public class CityDao implements ICityDao{
    @Override
    public List<City> findAll() {

        List<City> cities = new ArrayList<>();

        cities.add(new City(1L, "Bratislava", 432000));
        cities.add(new City(2L, "Budapest", 1759000));
        cities.add(new City(3L, "Prague", 1280000));
        cities.add(new City(4L, "Warsaw", 1748000));
        cities.add(new City(5L, "Los Angeles", 3971000));
        cities.add(new City(6L, "New York", 8550000));
        cities.add(new City(7L, "Edinburgh", 464000));
        cities.add(new City(8L, "Berlin", 3671000));

        return cities;
    }
}
