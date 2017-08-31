package org.itstep.pps2701;

import org.itstep.pps2701.task1.Article;
import org.itstep.pps2701.task1.ArticleBuilder;
import org.itstep.pps2701.task1.ArticleService;
import org.itstep.pps2701.task1.ArticleTxtBuilder;
import org.itstep.pps2701.task2.Grant;
import org.itstep.pps2701.task3.City;
import org.itstep.pps2701.task3.Country;
import org.itstep.pps2701.task3.Region;
import org.itstep.pps2701.ui.MainFrame;
import org.w3c.dom.Document;

public class Main {

    public static void main(String[] args) {

        new MainFrame();
    }

    private static void task1(){
        ArticleBuilder builder = new ArticleTxtBuilder();

        Article article = builder.build("/home/dk/article.txt");

        ArticleService service = new ArticleService();

        Document doc = service.toXml(article);
        service.save(doc, "/home/dk/article.xml");
    }

    private static void task2(){
        Grant grant = new Grant();
        
        System.out.println("Step1:" + grant.getState());
        grant.getState().toAccept();
        System.out.println("Step2:" + grant.getState());
        grant.getState().toView();
        System.out.println("Step3:" + grant.getState());
        grant.getState().toWithdraw();
        System.out.println("Step4:" + grant.getState());
        grant.getState().toHold();
        System.out.println("Step5:" + grant.getState());
        grant.getState().toAccept();
        System.out.println("Step6:" + grant.getState());
        grant.getState().toReject();
        System.out.println("Step7:" + grant.getState());
        grant.getState().toView();
        System.out.println("Step8:" + grant.getState());
        grant.getState().toAccept();
        System.out.println("Step9:" + grant.getState());
    }

    private static void task3(){
        Country country;
        Region region;
        City city;

        country = new Country();
        country.setName("Украина");

        region = new Region();
        region.setName("Харьковская область");

        city=new City();
        city.setName("Харьков");
        region.getCityList().add(city);
        city=new City();
        city.setName("Изюм");
        region.getCityList().add(city);

        country.getRegionList().add(region);

        region = new Region();
        region.setName("Киевская область");

        city=new City();
        city.setName("Киев");
        region.getCityList().add(city);
        city=new City();
        city.setName("Умань");
        region.getCityList().add(city);

        country.getRegionList().add(region);

        System.out.println("----------------Киевская область----------------");
        System.out.println(region.getCities());

        System.out.println("----------------Украина----------------");
        System.out.println(country.getCities());
    }
}
