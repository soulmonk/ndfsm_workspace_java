package com.soulmonk.ndfsm.web.app.controllers.time;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 24.02.14
 * Time: 16:29
 */
@Controller
@RequestMapping("/time")
public class TimeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "time/index";
    }
}
