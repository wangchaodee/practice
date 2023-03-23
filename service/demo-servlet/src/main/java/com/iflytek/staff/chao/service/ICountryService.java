package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.model.Country;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public interface ICountryService {
    public List<Country> findCountries(int currentPage, int numOfRecords);
    public int getNumberOfRows();
}
