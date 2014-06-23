package com.soulmonk.ndfsm.web.app.controllers;

import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SiteController {
    private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {
        UserDetailsAdapter user = UserDetailsAdapter.getLogged();
        if (user != null) {
            String name = user.getUsername(); //get logged in username
            model.addAttribute("username", name);
        } else {
            model.addAttribute("username", "Guest");
        }
        return "site/home";
    }
}