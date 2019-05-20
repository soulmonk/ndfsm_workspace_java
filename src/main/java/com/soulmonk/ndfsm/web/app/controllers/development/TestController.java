package com.soulmonk.ndfsm.web.app.controllers.development;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 17:32
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(DevController.class);

    @RequestMapping(value = "/coffee_namespace")
    public String coffeeNamespace() {
        return "dev/test/coffeeNamespace";
    }
}
