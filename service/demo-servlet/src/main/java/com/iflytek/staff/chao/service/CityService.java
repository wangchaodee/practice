package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.dao.ICityDao;
import com.iflytek.staff.chao.model.City;
import org.apache.commons.beanutils.BeanUtils;
import org.json.simple.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
/**
 * @author : wangchaodee
 * @Description: xxx
 */
@ManagedBean
public class CityService implements ICityService {

    @Inject
    private ICityDao cityDao;

    @Override
    public List<City> getCities() {

        return cityDao.findAll();
    }

    @Override
    public City getCity(Long id) {
        return cityDao.findById(id);
    }


    @Override
    public List<Map<String, String>> getCitiesMap() {
        List<Map<String, String>> cityList = new ArrayList<>() ;
        List<City> cities = getCities() ;
        for(City city : cities){
            try {
                cityList.add( BeanUtils.describe(city));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return cityList;
    }

    @Override
    public void insertCity(String name, int population) {
        cityDao.insertCity(name,population);
    }

    @Override
    public void updateCity(String cityName, int population) {
        cityDao.updateCity(cityName,population);
    }

    @Override
    public void deleteCity(String cityName) {
        cityDao.deleteCity(cityName);
    }


}
