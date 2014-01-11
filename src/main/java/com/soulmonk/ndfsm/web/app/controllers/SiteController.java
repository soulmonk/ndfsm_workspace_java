package com.soulmonk.ndfsm.web.app.controllers;

import com.soulmonk.ndfsm.domain.User;
import com.soulmonk.ndfsm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SiteController {


  @Autowired
  private UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
    User user = usersService.findById((long)1);
    model.addAttribute("user", user.toString());
		return "site/home";
	}
}