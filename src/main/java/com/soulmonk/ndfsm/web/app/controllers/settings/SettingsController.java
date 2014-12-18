package com.soulmonk.ndfsm.web.app.controllers.settings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: PolecatSoft
 * User: soulmonk
 * Date: 05.02.14
 * Time: 10:33
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model uiModel) {
        return "settings/index";
    }
}
