package com.soulmonk.ndfsm.web.app.controllers.angular;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 18.06.14
 * Time: 13:37
 */
@Controller
@RequestMapping("/angular")
public class AngularController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() {
    return "angular/index";
  }
}