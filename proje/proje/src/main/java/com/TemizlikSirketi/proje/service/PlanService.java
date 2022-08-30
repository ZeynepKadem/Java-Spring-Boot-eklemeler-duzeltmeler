package com.TemizlikSirketi.proje.service;

import java.util.List;



import com.TemizlikSirketi.proje.model.PlanModel;


public interface PlanService {

	List<PlanModel> getAllPlan();
	PlanModel savePlan(PlanModel planModel);
	PlanModel getPlan(Long id);
	PlanModel updatePlan(PlanModel planModel);
    void deletePlanById(Long id);
    List<PlanModel> getAllByPlan(String name, Long password);

    PlanModel getPlanid(Long id);
}
