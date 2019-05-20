package com.soulmonk.ndfsm.web.app.controllers.admin.user;

import com.google.common.collect.Lists;
import com.soulmonk.ndfsm.domain.user.Role;
import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.domain.user.UserRole;
import com.soulmonk.ndfsm.service.user.RoleService;
import com.soulmonk.ndfsm.service.user.UserRoleService;
import com.soulmonk.ndfsm.service.user.UserService;
import com.soulmonk.ndfsm.web.form.Message;
import com.soulmonk.ndfsm.web.util.UrlUtil;
import org.apache.taglibs.standard.functions.Functions;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 17:37
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

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
        uiModel.addAttribute("roles", user.getSelectedUserRoles(roleService.findAll()));
        return "admin/user/update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        userService.delete(id);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/updateUserToRole/{id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, ? extends Object> updateUserToRole(@PathVariable("id") Long id, @RequestParam(value = "roles", required = false) String roles, HttpServletResponse response) {
        //TODO SAVE ROLE FORE USER
        //TODO ROLES TO OBJECT
        List<String> roleIds = Lists.newArrayList(Functions.split(roles, ";"));

        User user = userService.findById(id);
        for (UserRole userRole : user.getUserRoles()) {
            boolean exist = false;
            for (String roleId : roleIds) {
                if (userRole.getRole().getId().equals(Long.parseLong(roleId))) {
                    roleIds.remove(roleId);
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                userRoleService.delete(userRole);
            }
        }

        for (String roleId : roleIds) {
            Role role = roleService.findById(Long.parseLong(roleId));
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            userRoleService.save(userRole);
        }

    /*logger.debug("updateUserToRole " + roles.getClass());*/
//    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return Collections.singletonMap("result", "Updated");
    }
}
