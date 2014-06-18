package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.service.time.ProjectService;
import com.soulmonk.ndfsm.service.time.TaskService;
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
@RequestMapping(value = "/time/task")
public class TaskController {

  final Logger logger = LoggerFactory.getLogger(TaskController.class);

  @Autowired
  private TaskService taskService;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing tasks");

    List<Task> tasks = taskService.findAll();
    uiModel.addAttribute("tasks", tasks);

    logger.info("No. of tasks: " + tasks.size());

    return "time/task/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    Task task = new Task();
    task.setProject(new Project());
    uiModel.addAttribute("task", task);
    uiModel.addAttribute("projects", projectService.findAll());
    return "time/task/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Task task, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create task");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("task_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("task", task);
      uiModel.addAttribute("projects", projectService.findAll());
      return "time/task/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("task_save_success", new Object[]{}, locale)));

    taskService.save(task);
    logger.info("Task id: " + task.getId());
    return "redirect:/time/task/" + UrlUtil.encodeUrlPathSegment(task.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Task task, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update task");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("task_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("task", task);
      uiModel.addAttribute("projects", projectService.findAll());
      return "time/task/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("task_update_success", new Object[]{}, locale)));

    taskService.save(task);

    logger.info("Update Task id: " + task.getId());

    return "redirect:/time/task/" + UrlUtil.encodeUrlPathSegment(task.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Task task = taskService.findById(id);
    uiModel.addAttribute("task", task);
    return "time/task/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    Task task = taskService.findById(id);
    uiModel.addAttribute("task", task);
    uiModel.addAttribute("projects", projectService.findAll());
    return "time/task/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    taskService.delete(id);
    return "redirect:/time/task";
  }
}
