package com.TemizlikSirketi.proje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TemizlikSirketi.proje.model.PlanModel;

public interface PlanRepository extends JpaRepository<PlanModel, Long> {

	 @Query(value = "SELECT * FROM reservation u where u.name = ? and u.password = ?;", nativeQuery = true)
	    public List<PlanModel> getFindPlan(String name, Long pasword);

	    @Query(value = "SELECT * FROM reservation u where u.id = ?;", nativeQuery = true)
	    public PlanModel getByid(Long id);
}
