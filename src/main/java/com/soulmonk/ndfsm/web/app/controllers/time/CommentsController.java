package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Comment;
import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.service.time.CommentService;
import com.soulmonk.ndfsm.service.time.CommentStatusService;
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
 * Date: 03.09.13
 * Time: 8:02
 */
@Controller
@RequestMapping(value = "/time/comments")
public class CommentsController {
  final Logger logger = LoggerFactory.getLogger(CommentsController.class);

  @Autowired
  private CommentService commentService;

  @Autowired
  private TaskService taskService;

  @Autowired
  private CommentStatusService commentStatusService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing Comment");

    List<Comment> comments = commentService.findAll();
    uiModel.addAttribute("comments", comments);

    logger.info("No. of comments: " + comments.size());

    return "time/comments/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    Comment comment = new Comment();
    comment.setCommentStatus(new CommentStatus());
    comment.setTask(new Task());

    uiModel.addAttribute("comment", comment);
    uiModel.addAttribute("tasks", taskService.findAll());
    uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
    return "time/comments/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Comment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create Comment");

    if (bindingResult.hasErrors()) {
      logger.error("bindingResult hasErrors");
      logger.error("bindingResult hasErrors message: " + bindingResult.toString());
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("comment_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment", comment);
      return "time/comments/create";
    }
    logger.info("no bindingResult hasErrors");
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("comment_save_success", new Object[]{}, locale)));

    commentService.save(comment);
    logger.info("Comment id: " + comment.getId());

    return "redirect:/time/comments/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Comment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update Comment");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("danger", messageSource.getMessage("comment_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment", comment);
      return "time/comments/update";
    }

    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("comment_update_success", new Object[]{}, locale)));

    commentService.save(comment);

    logger.info("Update Comment id: " + comment.getId());

    return "redirect:/time/comments/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Comment comment = commentService.findById(id);
    uiModel.addAttribute("comment", comment);
    return "time/comments/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("comment", commentService.findById(id));
    uiModel.addAttribute("tasks", taskService.findAll());
    uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
    return "time/comments/update";
  }
}
