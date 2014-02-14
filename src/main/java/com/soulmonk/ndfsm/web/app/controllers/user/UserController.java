package com.soulmonk.ndfsm.web.app.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 05.02.14
 * Time: 10:26
 */
@Controller
@RequestMapping("/user")
public class UserController {
  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  public String profile(Model uiModel) {
    return "user/profile";
  }
}
