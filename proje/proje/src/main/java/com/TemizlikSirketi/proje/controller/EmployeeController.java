package com.TemizlikSirketi.proje.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TemizlikSirketi.proje.model.HomeModel;
import com.TemizlikSirketi.proje.model.PlanModel;
import com.TemizlikSirketi.proje.model.UserModel;
import com.TemizlikSirketi.proje.service.HomeService;
import com.TemizlikSirketi.proje.service.PlanService;
import com.TemizlikSirketi.proje.service.UserService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	 private UserService   userService;
	    private HomeService homeService;
	    private PlanService planService;

	    public EmployeeController(UserService userService, PlanService planService, HomeService homeService) {
	        this.userService = userService;
	        this.homeService = homeService;
	        this.planService = planService;
	    }

	    @GetMapping(value = "getIndex")
	    public String getIndex(Model model) {
	        List<HomeModel> homeModelList = homeService.getHomeByDone(false);
	        int sayac = 0;
	        model.addAttribute("sayac", sayac);
	        model.addAttribute("homeModelList", homeModelList);
	        return "Giris";
	    }


	    @GetMapping(value = "getWelcome")
	    public String getWelcome() {

	        return "Welcome";
	    }

	    @GetMapping(value = "/getEmploye")
	    public String getEmploye(Model model, HttpServletResponse response, HttpServletRequest request) {

	        UserModel sessionEmploye = (UserModel) request.getSession().getAttribute("employe");
	        if (sessionEmploye != null) {
	            //add cookie to response
	            Cookie cookie1 = new Cookie("UserInfo", sessionEmploye.getName());
	            cookie1.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
	            cookie1.setSecure(false);
	            cookie1.setHttpOnly(false);
	            response.addCookie(cookie1);

	            //add cookie to response
	            Cookie cookie2 = new Cookie("Role", "Employe");
	            cookie2.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
	            cookie2.setSecure(false);
	            cookie2.setHttpOnly(false);
	            response.addCookie(cookie2);

	            List<HomeModel> homeModelList = homeService.getAllHome();
	            model.addAttribute("homeModelList", homeModelList);
	            List<PlanModel> planModelList = planService.getAllByPlan(sessionEmploye.getName(), sessionEmploye.getPassword());
	            model.addAttribute("planModelList", planModelList);
	            return "employeIndexPage";
	        } else {
	            return "redirect:/login/Authorization";
	        }

	    }

	    @GetMapping("/deletePlan/{planId}")
	    public String deletePlan(@PathVariable(value = "planId") Long planId, HttpServletRequest request) {

	            PlanModel planModel = planService.getPlanid(planId);
	            HomeModel homeModel = homeService.getHomeByAdress(planModel.getAdress());
	            homeModel.setDone(false);
	            homeService.saveHome(homeModel);
	            planService.deletePlanById(planId);
	            return "employeIndexPage";

	    }


	    @PostMapping("/postCreatePlanOut")
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    public String postCreatePlanOut(@ModelAttribute(value = "planModel") PlanModel planModel, Model model, HttpServletRequest request) {

	            HomeModel homeModel = homeService.getHomeByAdress(planModel.getAdress());
	            homeModel.setDone(true);
	            homeService.saveHome(homeModel);

	            UserModel userModel1 = userService.getUser(planModel.getName(), planModel.getPassword());

	            if (userModel1 == null) {
	                UserModel userModel = new UserModel();
	                userModel.setAdmin(false);
	               
	                userModel.setName(planModel.getName());
	                userModel.setPassword(planModel.getPassword());
	               
	          
	                userService.saveUser(userModel);
	            }

	            model.addAttribute("deneme", true);
	            planService.savePlan(planModel);
	            return "plan";

	    }

	    @GetMapping(value = "/getPlanOut")
	    public String getPlanOut(Model model, HttpServletRequest request) {

	            List<HomeModel> homeModelList = homeService.getHomeByDone(false);
	            model.addAttribute("homeModelList", homeModelList);
	            return "plan";

	    }

}
