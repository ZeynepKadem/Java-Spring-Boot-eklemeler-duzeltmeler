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
@RequestMapping(value = "/admin")
public class AdminController {
	
	private UserService userService;
    private HomeService homeService;
    private PlanService planService;

    public AdminController(UserService userService, PlanService planService, HomeService homeService) {
        this.userService = userService;
        this.homeService = homeService;
        this.planService = planService;
    }


    @GetMapping(value = "/getAdmin")
    public String getAdminPage(Model model, HttpServletResponse response, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            //add cookie to response
            Cookie cookie1 = new Cookie("UserInfo", sessionAdmin.getName());
            cookie1.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie1.setSecure(false);
            cookie1.setHttpOnly(false);
            response.addCookie(cookie1);

            //add cookie to response
            Cookie cookie2 = new Cookie("Role", "Admin");
            cookie2.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie2.setSecure(false);
            cookie2.setHttpOnly(false);
            response.addCookie(cookie2);
            List<HomeModel> homeModelList = homeService.getAllHome();

            model.addAttribute("homeModelList", homeModelList);
            return "homeIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }


    @GetMapping(value = "/getHome")
    public String getHomePage(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping(value = "/getCreateHome")
    public String getCreateHome(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            return "homeEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping(value = "/postCreateHome")
    public String postCreateHome(@ModelAttribute HomeModel homeModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            if (homeModel != null) {
            	homeService.saveHome(homeModel);
            }
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/deleteHome/{homeId}")
    public String deleteHome(@PathVariable(value = "homeId") Long homeId, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
        	homeService.deleteHomeById(homeId);
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/editHome/{homeId}")
    public String editHome(@PathVariable(value = "homeId") Long homeId, Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            HomeModel homeModel = homeService.getHomeById(homeId);
            model.addAttribute("homeModel", homeModel);
            return "homeEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/updateHome")
    public String updateHome(@ModelAttribute(value = "homeModel") HomeModel homeModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
        	homeService.saveHome(homeModel);
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/getUser")
    public String getUserPage(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            List<UserModel> userModelList = userService.getUserByRole(false);
            model.addAttribute("userModelList", userModelList);
            return "userIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/postCreateUser")
    public String postCreateUser(@ModelAttribute(value = "userModel") UserModel userModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.saveUser(userModel);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.deleteUserById(userId);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/editUser/{userId}")
    public String editUser(@PathVariable(value = "userId") Long userId, Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            UserModel userModel = userService.getUserById(userId);
            model.addAttribute("userModel", userModel);
            return "userEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(value = "userModel") UserModel userModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.saveUser(userModel);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/getPlan")
    public String getPlan(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            List<PlanModel> planModelList = planService.getAllPlan();
            List<HomeModel> homeModelList = homeService.getHomeByDone(false);
            model.addAttribute("homeModelList", homeModelList);
            model.addAttribute("planModelList", planModelList);
            return "rplanIndexPage";
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
            return "redirect:/admin/getPlan";
    }

    @PostMapping("/postCreatePlan")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    public String postCreatePlan(@ModelAttribute(value = "planModel") PlanModel planModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
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

            planService.savePlan(planModel);
            return "redirect:/admin/getPlan";
        } else {
            return "redirect:/login/Authorization";
        }
    }

}
