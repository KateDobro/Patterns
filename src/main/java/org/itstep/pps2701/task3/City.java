package org.itstep.pps2701.task3;

/**
 * Created by dk on 17.05.17.
 */
public class City implements CompositeCountry{
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCities() {
        return name;
    }
}
