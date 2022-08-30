package com.TemizlikSirketi.proje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TemizlikSirketi.proje.model.HomeModel;

public interface HomeRepository extends JpaRepository<HomeModel, Long> {

	 @Query(value = "SELECT * FROM home r where r.is_done = ?;", nativeQuery = true)
	    public List<HomeModel> getHomeByDone(boolean is_reserved);


	    @Query(value = "SELECT * FROM home r where r.adress = ?;", nativeQuery = true)
	    public HomeModel getHomeByAdress(String adress);

	    @Query(value = "SELECT * FROM home r where r.id = ?;", nativeQuery = true)
	    public HomeModel getAdressByid(Long id);




}
