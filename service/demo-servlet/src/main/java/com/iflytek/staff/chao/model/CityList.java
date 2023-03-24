package com.iflytek.staff.chao.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@XmlRootElement(namespace = "com.iflytek.staff.chao")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    @XmlElementWrapper(name = "citys")
    @XmlElement(name = "city")
    private List<City> citys ;

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }
}
