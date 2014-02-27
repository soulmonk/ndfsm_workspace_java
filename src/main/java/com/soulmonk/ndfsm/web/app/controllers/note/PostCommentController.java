package com.soulmonk.ndfsm.web.app.controllers.note;

import com.soulmonk.ndfsm.domain.note.PostComment;
import com.soulmonk.ndfsm.service.note.PostCommentService;
import com.soulmonk.ndfsm.service.note.PostService;
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
 * Time: 22:32
 */
@Controller
@RequestMapping(value = "/note/comment")
public class PostCommentController {

  final Logger logger = LoggerFactory.getLogger(PostCommentController.class);

  @Autowired
  private PostCommentService postCommentService;

  @Autowired
  private PostService postService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing comments");

    List<PostComment> comments = postCommentService.findAll();
    uiModel.addAttribute("comments", comments);

    logger.info("No. of comments: " + comments.size());

    return "note/comment/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    PostComment comment = new PostComment();
    uiModel.addAttribute("comment", comment);
    uiModel.addAttribute("posts", postService.findAll());
    return "note/comment/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid PostComment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create comment");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment", comment);
      return "note/comment/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_save_success", new Object[]{}, locale)));
    postCommentService.save(comment);
    logger.info("PostComment id: " + comment.getId());
    return "redirect:/note/comment/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid PostComment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update comment");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("comment", comment);
      return "note/comment/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_update_success", new Object[]{}, locale)));

    postCommentService.save(comment);

    return "redirect:/note/comment/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    PostComment comment = postCommentService.findById(id);
    uiModel.addAttribute("comment", comment);
    return "note/comment/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("comment", postCommentService.findById(id));
    uiModel.addAttribute("posts", postService.findAll());
    return "note/comment/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    postCommentService.delete(id);
    return "redirect:/note/comment/list";
  }
}

