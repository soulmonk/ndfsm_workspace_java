package com.soulmonk.ndfsm.web.app.controllers.note;

import com.soulmonk.ndfsm.domain.note.Notification;
import com.soulmonk.ndfsm.service.note.NotificationService;
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
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 22:36
 */
@Controller
@RequestMapping(value = "/note/notification")
public class NotificationController {

  final Logger logger = LoggerFactory.getLogger(NotificationController.class);

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing notifications");

    List<Notification> notifications = notificationService.findAll();
    uiModel.addAttribute("notifications", notifications);

    logger.info("No. of notifications: " + notifications.size());

    return "note/notification/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    Notification notification = new Notification();
    uiModel.addAttribute("notification", notification);
    return "note/notification/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Notification notification, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create notification");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("notification_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("notification", notification);
      return "note/notification/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("notification_save_success", new Object[]{}, locale)));
    notificationService.save(notification);
    logger.info("Notification id: " + notification.getId());
    return "redirect:/note/notification/" + UrlUtil.encodeUrlPathSegment(notification.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Notification notification, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update notification");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("notification_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("notification", notification);
      return "note/notification/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("notification_update_success", new Object[]{}, locale)));

    notificationService.save(notification);

    return "redirect:/note/notification/" + UrlUtil.encodeUrlPathSegment(notification.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Notification notification = notificationService.findById(id);
    uiModel.addAttribute("notification", notification);
    return "note/notification/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("notification", notificationService.findById(id));
    return "note/notification/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    notificationService.delete(id);
    return "redirect:/note/notification/list";
  }
}