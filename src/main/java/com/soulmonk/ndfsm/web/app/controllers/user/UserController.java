package com.soulmonk.ndfsm.web.app.controllers.user;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.repository.user.UserRepository;
import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import com.soulmonk.ndfsm.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  public String profile(Model model) throws Exception {
    UserDetailsAdapter user = UserDetailsAdapter.getLogged();
    if (user == null) {
      throw new Exception("Error in security");
    }
    model.addAttribute("user", user.getUser());
    return "user/profile";
  }

  @RequestMapping(value = "/newUser", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String createNewUser() {
    try {
      logger.info("createNewUser begin");

      User user = userService.findByLogin("user");
      if (user == null) {
        user = new User();
      }
      user.setLogin("user");
      user.setPassword("123098");
      user.setEmail("user@example.com");
      user.setFirstName("User");
      user.setLastName("User");
      user.setEnabled(true);
      userService.save(user);
      logger.info("createNewUser end");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "{result:'ok'}";
  }
}
