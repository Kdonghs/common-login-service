package com.login.loginAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("login/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }
    @GetMapping("createAccount/createAccount")
    public String createAccount(){
        return "createAccount/createAccount";
    }
    @GetMapping("searchAccount/searchID")
    public String searchID(){
        return "searchAccount/searchID";
    }
    @GetMapping("searchAccount/searchPW")
    public String searchPW(){
        return "searchAccount/searchPW";
    }
}
