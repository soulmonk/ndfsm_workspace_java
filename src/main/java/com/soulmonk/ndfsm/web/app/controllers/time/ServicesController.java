package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Services;
import com.soulmonk.ndfsm.service.time.ServicesService;
import com.soulmonk.ndfsm.web.form.Message;
import com.soulmonk.ndfsm.web.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 16.08.13
 * Time: 9:27
 */
@Controller
@RequestMapping(value = "/time/services")
public class ServicesController {

  final Logger logger = LoggerFactory.getLogger(ServicesController.class);

  @Autowired
  private ServicesService servicesService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing services");

    List<Services> services = servicesService.findAll();
    uiModel.addAttribute("services", services);

    logger.info("No. of services: " + services.size());

    return "time/services/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    Services service = new Services();
    uiModel.addAttribute("service", service);
    return "time/services/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Services service, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create service");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("service_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("service", service);
      return "time/services/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("service_save_success", new Object[]{}, locale)));
    servicesService.save(service);
    logger.info("Services id: " + service.getId());
    return "redirect:/time/services/" + UrlUtil.encodeUrlPathSegment(service.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Services service, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update service");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("service_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("service", service);
      return "time/services/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("service_update_success", new Object[]{}, locale)));

    servicesService.save(service);

    return "redirect:/time/services/" + UrlUtil.encodeUrlPathSegment(service.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Services service = servicesService.findById(id);
    uiModel.addAttribute("service", service);
    return "time/services/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("service", servicesService.findById(id));
    return "time/services/update";
  }
}
