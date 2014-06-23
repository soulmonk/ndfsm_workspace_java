package com.soulmonk.ndfsm.web.app.controllers.development;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 17:31
 */
@Controller
@RequestMapping("/dev")
public class DevController {
    private static final Logger logger = LoggerFactory.getLogger(DevController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new_user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String createNewUser() {
        try {
            logger.info("createNewUser begin");

            User user = userService.findByLogin("user");
            if (user != null) {
                return "{result:'userExist'}";
            }
            user = new User();
            user.setLogin("user");
            user.setPassword("123098");
            user.setEmail("user@example.com");
            user.setFirstName("User");
            user.setLastName("User");
            user.setEnabled(true);
            user.setPasswordChanged(true);
            userService.save(user);
            logger.info("createNewUser end");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "{result:'ok'}";
    }
}
