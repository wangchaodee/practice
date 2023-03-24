package com.iflytek.staff.chao.dao;

import com.iflytek.staff.chao.model.City;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@ManagedBean
public class CityDao implements ICityDao{

    List<City> cities ;
    Long id ;

    CityDao(){
         cities = new ArrayList<>();

        cities.add(new City(1L, "Bratislava", 432000));
        cities.add(new City(2L, "Budapest", 1759000));
        cities.add(new City(3L, "Prague", 1280000));
        cities.add(new City(4L, "Warsaw", 1748000));
        cities.add(new City(5L, "Los Angeles", 3971000));
        cities.add(new City(6L, "New York", 8550000));
        cities.add(new City(7L, "Edinburgh", 464000));
        cities.add(new City(8L, "Berlin", 3671000));

        id = 8L ;
    }

    @Override
    public List<City> findAll() {

        return cities;
    }

    @Override
    public City findById(Long id) {
        for (City city: cities) {
            if(city.getId().equals(id)) {
                return city;
            }
        }
        return null;
    }

    @Override
    public void insertCity(String name, int population) {
        id++;
        cities.add(new City(id,name,population));
    }

    @Override
    public void updateCity(String cityName, int population) {
        for (City city: cities) {
            if(city.getName().equals(cityName)) {
                 city.setPopulation(population);
            }
        }
    }

    @Override
    public void deleteCity(String cityName) {
        City delete = null;
        for (City city: cities) {
            if(city.getName().equals(cityName)) {
                delete = city ;
            }
        }
        if(delete!=null) {
            cities.remove(delete);
        }
    }
}
