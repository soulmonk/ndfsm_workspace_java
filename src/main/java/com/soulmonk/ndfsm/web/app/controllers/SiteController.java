package com.soulmonk.ndfsm.web.app.controllers;

import com.soulmonk.ndfsm.domain.User;
import com.soulmonk.ndfsm.service.RolesService;
import com.soulmonk.ndfsm.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SiteController {

  final Logger logger = LoggerFactory.getLogger(SiteController.class);

  @RequestMapping(method = RequestMethod.GET)
  public String home(ModelMap model) {
    return "site/home";
  }
}