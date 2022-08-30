package com.TemizlikSirketi.proje.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TemizlikSirketi.proje.model.HomeModel;
import com.TemizlikSirketi.proje.repository.HomeRepository;
import com.TemizlikSirketi.proje.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
	
	 @Autowired
	    private HomeRepository homeRepository;

	    public HomeServiceImpl(HomeRepository homeRepository) {
	        this.homeRepository = homeRepository;
	    }

	    @Override
	    public List<HomeModel> getAllHome() {
	        return homeRepository.findAll();
	    }

	    @Override
	    public HomeModel saveHome(HomeModel homeModel) {
	        return homeRepository.save(homeModel);
	    }

	    @Override
	    public HomeModel getHomeById(Long id) {
	        return homeRepository.getById(id);
	    }

	    @Override
	    public HomeModel updateHome(HomeModel homeModel) {
	        return homeRepository.save(homeModel);
	    }

	    @Override
	    public void deleteHomeById(Long id) {
	    	homeRepository.deleteById(id);
	    }

	    @Override
	    public List<HomeModel> getHomeByDone(boolean done) {
	        return homeRepository.getHomeByDone(done);
	    }

	    @Override
	    public HomeModel getHomeByAdress(String adress) {
	        return homeRepository.getHomeByAdress(adress);
	    }

	    @Override
	    public HomeModel getHomeByid(Long id) {
	        return homeRepository.getAdressByid(id);
	    }

}
