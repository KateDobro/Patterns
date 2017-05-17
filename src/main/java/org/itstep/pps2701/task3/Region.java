package org.itstep.pps2701.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk on 17.05.17.
 */
public class Region implements CompositeCountry{
    private String name;
    private List<City> cityList = new ArrayList<>();

    public Region() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String getCities() {
        StringBuilder strB=new StringBuilder();

        strB.append("Region: "+name);
        strB.append('\n');
        for(City c:cityList)
        {
            strB.append(c.getCities());
            strB.append('\n');
        }

        return strB.toString();
    }
}
