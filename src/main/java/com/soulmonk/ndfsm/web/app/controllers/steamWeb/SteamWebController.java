package com.soulmonk.ndfsm.web.app.controllers.steamWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * User: SoulMonk
 * Date: 7/11/16
 * Time: 10:28 PM
 */

@Controller
@RequestMapping(value = "/steamWeb")
public class SteamWebController {
    private static final Logger logger = LoggerFactory.getLogger(SteamWebController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model uiModel) {
        logger.info("Listing projects");

        return "steamWeb/index";
    }
}
