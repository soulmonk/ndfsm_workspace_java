package com.soulmonk.ndfsm.web.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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