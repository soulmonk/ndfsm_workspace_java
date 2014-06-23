package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Service;
import com.soulmonk.ndfsm.service.time.ServiceService;
import com.soulmonk.ndfsm.web.form.Message;
import com.soulmonk.ndfsm.web.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "/time/service")
public class ServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing services");
        List<Service> services = serviceService.findAll();
        uiModel.addAttribute("services", services);
        logger.info("No. of services: " + services.size());
        return "time/service/list";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        Service service = new Service();
        uiModel.addAttribute("service", service);
        return "time/service/create";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid Service service, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Create service");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("service_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("service", service);
            return "time/service/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("service_save_success", new Object[]{}, locale)));
        serviceService.save(service);
        logger.info("Service id: " + service.getId());
        return "redirect:/time/service/" + UrlUtil.encodeUrlPathSegment(service.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Service service, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Update service");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("service_update_fail", new Object[]{}, locale)));
            uiModel.addAttribute("service", service);
            return "time/service/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("service_update_success", new Object[]{}, locale)));

        serviceService.save(service);

        return "redirect:/time/service/" + UrlUtil.encodeUrlPathSegment(service.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Service service = serviceService.findById(id);
        uiModel.addAttribute("service", service);
        return "time/service/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("service", serviceService.findById(id));
        return "time/service/update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        serviceService.delete(id);
        return "redirect:/time/service";
    }
}
