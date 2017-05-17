package org.itstep.pps2701.task3;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk on 17.05.17.
 */
public class Country implements CompositeCountry{
    private String name;
    private List<Region> regionList = new ArrayList<>();

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    @Override
    public String getCities() {
        StringBuilder strB=new StringBuilder();

        strB.append("Country: "+name);
        strB.append('\n');
        for(Region r:regionList)
        {
            strB.append(r.getCities());
            strB.append('\n');
        }

        return strB.toString();
    }
}
