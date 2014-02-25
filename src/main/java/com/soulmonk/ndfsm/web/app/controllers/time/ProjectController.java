package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.domain.time.Service;
import com.soulmonk.ndfsm.service.time.ProjectService;
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
 * Time: 14:23
 */
@Controller
@RequestMapping(value = "/time/project")
public class ProjectController {

  private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

  @Autowired
  private ProjectService projectService;

  @Autowired
  private ServiceService serviceService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing projects");

    List<Project> projects = projectService.findAll();
    uiModel.addAttribute("projects", projects);

    logger.info("No. of projects: " + projects.size());

    return "time/project/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    Project project = new Project();
    project.setService(new Service());
    uiModel.addAttribute("project", project);
    uiModel.addAttribute("services", serviceService.findAll());
    return "time/project/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Project project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create project");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("project_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("project", project);
      return "time/project/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("project_save_success", new Object[]{}, locale)));

    projectService.save(project);
    logger.info("Project id: " + project.getId());
    return "redirect:/time/project/" + UrlUtil.encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Project project, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update project");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("project_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("project", project);
      return "time/project/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("project_update_success", new Object[]{}, locale)));

    projectService.save(project);

    logger.info("Update Project id: " + project.getId());

    return "redirect:/time/project/" + UrlUtil.encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Project project = projectService.findById(id);
    uiModel.addAttribute("project", project);
    return "time/project/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    Project project = projectService.findById(id);
    uiModel.addAttribute("project", project);
    uiModel.addAttribute("services", serviceService.findAll());
    return "time/project/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    projectService.delete(id);
    return "redirect:/time/project/list";
  }
}
