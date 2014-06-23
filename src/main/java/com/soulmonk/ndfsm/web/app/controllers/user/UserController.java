package com.soulmonk.ndfsm.web.app.controllers.user;

import com.soulmonk.ndfsm.security.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
