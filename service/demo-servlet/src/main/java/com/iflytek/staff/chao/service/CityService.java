package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.dao.ICityDao;
import com.iflytek.staff.chao.model.City;

import java.util.List;
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
}
