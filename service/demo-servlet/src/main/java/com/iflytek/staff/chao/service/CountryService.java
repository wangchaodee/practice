package com.iflytek.staff.chao.service;

import com.iflytek.staff.chao.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class CountryService implements ICountryService{

    List<Country> countries ;

    public CountryService(){
        countries = new ArrayList<>();

        countries.add(new Country("China", 1382050000));
        countries.add(new Country("India", 1313210000));
        countries.add(new Country("USA", 324666000));
        countries.add(new Country("Indonesia", 260581000));
        countries.add(new Country("Brazil", 207221000));
        countries.add(new Country("Pakistan", 196626000));
        countries.add(new Country("Nigeria", 186988000));
        countries.add(new Country("Bangladesh", 162099000));
        countries.add(new Country("Nigeria", 186988000));
        countries.add(new Country("Russia", 146838000));
        countries.add(new Country("Japan", 126830000));
        countries.add(new Country("Mexico", 122273000));
        countries.add(new Country("Philippines", 103738000));
        countries.add(new Country("Ethiopia", 101853000));
        countries.add(new Country("Vietnam", 92700000));
        countries.add(new Country("Egypt", 92641000));
        countries.add(new Country("Germany", 82800000));
        countries.add(new Country("the Congo", 82243000));
        countries.add(new Country("Iran", 82800000));
        countries.add(new Country("Turkey", 79814000));
        countries.add(new Country("Thailand", 68147000));
        countries.add(new Country("France", 66984000));
        countries.add(new Country("United Kingdom", 60589000));
        countries.add(new Country("South Africa", 55908000));
        countries.add(new Country("Myanmar", 51446000));
        countries.add(new Country("South Korea", 68147000));
        countries.add(new Country("Colombia", 49129000));
        countries.add(new Country("Kenya", 47251000));
        countries.add(new Country("Spain", 46812000));
        countries.add(new Country("Argentina", 43850000));
        countries.add(new Country("Ukraine", 42603000));
        countries.add(new Country("Sudan", 41176000));
        countries.add(new Country("Algeria", 40400000));
        countries.add(new Country("Poland", 38439000));

    }

    @Override
    public List<Country> findCountries(int currentPage, int recordsPerPage)  {

        List<Country> countries = null;

        int start = currentPage * recordsPerPage - recordsPerPage;
        int end = Math.min(start+recordsPerPage , this.countries.size());
        countries = this.countries.subList(start,end);

        return countries;
    }

    @Override
    public int getNumberOfRows() {

        int numOfRows = this.countries.size();

        return numOfRows;
    }
}
