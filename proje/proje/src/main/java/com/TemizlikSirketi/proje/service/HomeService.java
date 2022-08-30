package com.TemizlikSirketi.proje.service;

import java.util.List;

import com.TemizlikSirketi.proje.model.HomeModel;


public interface HomeService {

	List<HomeModel> getAllHome();
    HomeModel saveHome(HomeModel homeModel);
    HomeModel getHomeById(Long id);
    HomeModel updateHome(HomeModel homeModel);
    void deleteHomeById(Long id);
    List<HomeModel> getHomeByDone(boolean done);
    HomeModel getHomeByAdress(String adress);
    HomeModel getHomeByid(Long id);
	
}
