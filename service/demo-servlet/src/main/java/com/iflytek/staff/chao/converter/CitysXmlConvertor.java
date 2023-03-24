package com.iflytek.staff.chao.converter;

import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.model.CityList;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class CitysXmlConvertor {

    private ByteArrayOutputStream bos;

    public ByteArrayOutputStream convertList(List<City> cities) {

        bos = new ByteArrayOutputStream();

        try {
            JAXBContext context = JAXBContext.newInstance(CityList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            CityList cityList = new CityList();
            cityList.setCitys(cities);

            m.marshal(cityList, bos);

        } catch (JAXBException ex) {
            Logger.getLogger(CitysXmlConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bos;
    }

    public ByteArrayOutputStream convertObject(City city) {

        bos = new ByteArrayOutputStream();

        try {
            JAXBContext context = JAXBContext.newInstance(City.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(city, bos);

        } catch (JAXBException ex) {
            Logger.getLogger(CitysXmlConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bos;
    }
}
