package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Projects;
import com.soulmonk.ndfsm.domain.time.Services;
import com.soulmonk.ndfsm.service.time.ProjectsService;
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
 * Time: 14:23
 */
@Controller
@RequestMapping(value = "/time/projects")
public class ProjectsController {

  final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

  @Autowired
  private ProjectsService projectsService;

  @Autowired
  private ServicesService servicesService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing projects");

    List<Projects> projects = projectsService.findAll();
    uiModel.addAttribute("projects", projects);

    logger.info("No. of projects: " + projects.size());

    return "time/projects/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    Projects project = new Projects();
    project.setService(new Services());
    uiModel.addAttribute("project", project);
    uiModel.addAttribute("services", servicesService.findAll());
    return "time/projects/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Projects project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create project");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("project_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("project", project);
      return "time/projects/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("project_save_success", new Object[]{}, locale)));

    projectsService.save(project);
    logger.info("Projects id: " + project.getId());
    return "redirect:/time/projects/" + UrlUtil.encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Projects project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update project");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("project_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("project", project);
      return "time/projects/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("project_update_success", new Object[]{}, locale)));

    projectsService.save(project);

    logger.info("Update Projects id: " + project.getId());

    return "redirect:/time/projects/" + UrlUtil.encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Projects project = projectsService.findById(id);
    uiModel.addAttribute("project", project);
    return "time/projects/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    Projects project = projectsService.findById(id);
    uiModel.addAttribute("project", project);
    uiModel.addAttribute("services", servicesService.findAll());
    return "time/projects/update";
  }
}
