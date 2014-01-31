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
@RequestMapping("/user")
public class UserController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {
    return "user/login";
  }
}
