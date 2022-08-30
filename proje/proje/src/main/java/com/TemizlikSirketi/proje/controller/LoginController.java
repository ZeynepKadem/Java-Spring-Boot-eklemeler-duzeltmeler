package com.TemizlikSirketi.proje.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TemizlikSirketi.proje.model.UserModel;
import com.TemizlikSirketi.proje.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getLogin")
    public String login(Model model) {
        return "login";
    }

    @PostMapping(value = "/postLogin")
    public String postLogin(@ModelAttribute UserModel user, HttpSession session ,Model model) {
        if (user != null) {
        	UserModel userModel = userService.getUser(user.getName(), user.getPassword());
        	if (userModel != null && user.getName().equals(userModel.getName()) && user.getPassword().equals(userModel.getPassword())) {
                if (userModel.isAdmin()) {
                    session.setAttribute("admin", userModel);
                    return "redirect:/admin/getAdmin";
                }else{
                    session.setAttribute("employe", userModel);
                    return "redirect:/employe/getEmploye";
                }
            } else {
                model.addAttribute("error", false);
            }
        }
        return "login";
    }



    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/getLogin";
    }

    @GetMapping(value = "/Authorization")
    public String getAuthErrorPage() {
        return "authErrorPage";
    }
	
}
