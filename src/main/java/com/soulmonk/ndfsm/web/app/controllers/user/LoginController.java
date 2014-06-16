package com.soulmonk.ndfsm.web.app.controllers.user;

import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.web.form.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
  public String login(HttpServletRequest httpServletRequest) {
    if (UserDetailsAdapter.getLogged() != null) {
      return "redirect:/";
    }
    return "login/login_form";
  }

  @RequestMapping(params = "failure", method = RequestMethod.GET)
  public String loginFailure(Model model, HttpSession session) {
    logger.info("Login failure");
    try {
      Object attribute = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
      if (attribute != null) {
        logger.debug("SPRING_SECURITY_LAST_EXCEPTION class is: " + attribute.getClass());
        model.addAttribute("message",new Message(Message.DANGER_TYPE,((BadCredentialsException) attribute).getMessage()));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "login/login_form";
  }
}
