package com.soulmonk.ndfsm.web.app.controllers.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: PolecatSoft
 * User: soulmonk
 * Date: 03.02.14
 * Time: 14:48
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        logger.info("Hello Admin Controller");
        return "admin/index";
    }
}
