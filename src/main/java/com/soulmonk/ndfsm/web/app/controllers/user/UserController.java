package com.soulmonk.ndfsm.web.app.controllers.user;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.repository.user.UserRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.user.UserService;
import com.soulmonk.ndfsm.web.form.Message;
import com.soulmonk.ndfsm.web.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 05.02.14
 * Time: 10:26
 */
@Controller
@RequestMapping("/user")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  public String profile(Model model) throws Exception {
    UserDetailsAdapter user = UserDetailsAdapter.getLogged();
    if (user == null) {
      throw new Exception("Error in security");
    }
    model.addAttribute("user", user.getUser());
    return "user/profile";
  }
}
