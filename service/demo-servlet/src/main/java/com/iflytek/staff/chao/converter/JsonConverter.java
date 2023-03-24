package com.iflytek.staff.chao.converter;

import com.iflytek.staff.chao.model.City;
import org.apache.commons.beanutils.BeanUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class JsonConverter {


    public String convertToJson(List<City> cities) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException {

        List<Map<String, String>> cityList = new ArrayList<>();
        for (City city : cities) {
            cityList.add(BeanUtils.describe(city));
        }

        return JSONArray.toJSONString(cityList);
    }

    public String convertToJson(City city) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException {
        JSONObject jsonObject = new JSONObject(BeanUtils.describe(city));
        return jsonObject.toJSONString();
    }
}
