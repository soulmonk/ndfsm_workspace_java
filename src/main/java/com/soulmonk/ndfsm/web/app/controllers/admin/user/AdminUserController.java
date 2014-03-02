package com.soulmonk.ndfsm.web.app.controllers.admin.user;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.service.user.UserService;
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
 * Date: 01.03.14
 * Time: 17:37
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
  private final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing User");

    List<User> users = userService.findAll();
    uiModel.addAttribute("users", users);

    logger.info("No. of users: " + users.size());

    return "admin/user/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    User user = new User();
    uiModel.addAttribute("user", user);
    return "admin/user/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create User");

    if (bindingResult.hasErrors()) {
      logger.error("bindingResult hasErrors");
      logger.error("bindingResult hasErrors message: " + bindingResult.toString());
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("admin_user_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("user", user);
      return "admin/user/create";
    }
    logger.info("no bindingResult hasErrors");
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("admin_user_save_success", new Object[]{}, locale)));

    userService.save(user);
    logger.info("User id: " + user.getId());

    return "redirect:/admin/user/" + UrlUtil.encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update User");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("admin_user_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("user", user);
      return "admin/user/update";
    }

    uiModel.asMap().clear();

    if (user.getPassword().isEmpty()) {
      User userOld = userService.findById(user.getId());
      user.setPassword(userOld.getPassword());
    } else {
      user.setPasswordChanged(true);
    }

    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("admin_user_update_success", new Object[]{}, locale)));

    userService.save(user);

    logger.info("Update User id: " + user.getId());

    return "redirect:/admin/user/" + UrlUtil.encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    User user = userService.findById(id);
    uiModel.addAttribute("user", user);
    return "admin/user/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    User user = userService.findById(id);
    user.setPassword("");
    uiModel.addAttribute("user", user);
    return "admin/user/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    userService.delete(id);
    return "redirect:/admin/user";
  }
}
