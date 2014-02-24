package com.soulmonk.ndfsm.web.app.controllers.time;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Owner on 24.02.14.
 */
@Controller
@RequestMapping("/time")
public class TimeController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index() {
    return "time/index";
  }
}
