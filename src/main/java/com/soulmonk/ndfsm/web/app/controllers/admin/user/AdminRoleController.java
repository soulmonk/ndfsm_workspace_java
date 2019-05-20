package com.soulmonk.ndfsm.web.app.controllers.admin.user;

import com.soulmonk.ndfsm.domain.user.Role;
import com.soulmonk.ndfsm.service.user.RoleService;
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
 * Role: SoulMonk
 * Date: 01.03.14
 * Time: 17:37
 */
@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing Role");

        List<Role> roles = roleService.findAll();
        uiModel.addAttribute("roles", roles);

        logger.info("No. of roles: " + roles.size());

        return "admin/role/list";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        logger.info("Create form");
        Role role = new Role();
        uiModel.addAttribute("role", role);
        return "admin/role/create";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Create Role");

        if (bindingResult.hasErrors()) {
            logger.error("bindingResult hasErrors");
            logger.error("bindingResult hasErrors message: " + bindingResult.toString());
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("admin_role_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("role", role);
            return "admin/role/create";
        }
        logger.info("no bindingResult hasErrors");
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("admin_role_save_success", new Object[]{}, locale)));

        roleService.save(role);
        logger.info("Role id: " + role.getId());

        return "redirect:/admin/role/" + UrlUtil.encodeUrlPathSegment(role.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Update Role");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("admin_role_update_fail", new Object[]{}, locale)));
            uiModel.addAttribute("role", role);
            return "admin/role/update";
        }

        uiModel.asMap().clear();

        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("admin_role_update_success", new Object[]{}, locale)));

        roleService.save(role);

        logger.info("Update Role id: " + role.getId());

        return "redirect:/admin/role/" + UrlUtil.encodeUrlPathSegment(role.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Role role = roleService.findById(id);
        uiModel.addAttribute("role", role);
        return "admin/role/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        Role role = roleService.findById(id);
        uiModel.addAttribute("role", role);
        return "admin/role/update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        roleService.delete(id);
        return "redirect:/admin/role";
    }
}
