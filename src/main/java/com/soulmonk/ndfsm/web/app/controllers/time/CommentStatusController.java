package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.service.time.CommentStatusService;
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
@RequestMapping(value = "/time/comment_status")
public class CommentStatusController {

  final Logger logger = LoggerFactory.getLogger(CommentStatusController.class);

  @Autowired
  private CommentStatusService commentStatusService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing Comment Status");

    List<CommentStatus> commentStatuses = commentStatusService.findAll();
    uiModel.addAttribute("comment_statuses", commentStatuses);

    logger.info("No. of commentStatuses: " + commentStatuses.size());

    return "time/comment_status/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    CommentStatus commentsStatus = new CommentStatus();
    uiModel.addAttribute("comment_status", commentsStatus);
    return "time/comment_status/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid CommentStatus commentsStatus, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create Comment Status");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_status_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment_status", commentsStatus);
      return "time/comment_status/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_status_save_success", new Object[]{}, locale)));

    commentStatusService.save(commentsStatus);
    logger.info("Comment Status id: " + commentsStatus.getId());

    return "redirect:/time/comment_status/" + UrlUtil.encodeUrlPathSegment(commentsStatus.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid CommentStatus commentsStatus, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update Comment Status");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_status_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment_status", commentsStatus);
      return "time/comment_status/update";
    }

    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_status_update_success", new Object[]{}, locale)));

    commentStatusService.save(commentsStatus);

    logger.info("Update Comment Status id: " + commentsStatus.getId());

    return "redirect:/time/comment_status/" + UrlUtil.encodeUrlPathSegment(commentsStatus.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    CommentStatus commentsStatus = commentStatusService.findById(id);
    uiModel.addAttribute("comment_status", commentsStatus);
    return "time/comment_status/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("comment_status", commentStatusService.findById(id));
    return "time/comment_status/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    commentStatusService.delete(id);
    return "redirect:/time/comment_status";
  }
}
