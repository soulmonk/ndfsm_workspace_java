package com.soulmonk.ndfsm.web.app.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 31.01.14
 * Time: 15:27
 */
@Controller
@RequestMapping("/login")
public class LoginController {

  final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @RequestMapping(method = RequestMethod.GET)
  public String login() {
    return "login/login_form";
  }

  @RequestMapping(params = "failure", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Login failure");
    return "login/login_form";
  }
}
