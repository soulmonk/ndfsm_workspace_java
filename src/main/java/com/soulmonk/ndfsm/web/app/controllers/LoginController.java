package com.soulmonk.ndfsm.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 31.01.14
 * Time: 15:27
 */
@Controller
@RequestMapping("/")
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login/login_form";
  }
}
