package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.CommentStatus;
import com.soulmonk.ndfsm.domain.time.ProjectComment;
import com.soulmonk.ndfsm.domain.time.Task;
import com.soulmonk.ndfsm.service.time.CommentStatusService;
import com.soulmonk.ndfsm.service.time.TaskCommentService;
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
@RequestMapping(value = "/time/comment")
public class TaskCommentController {
    private static final Logger logger = LoggerFactory.getLogger(TaskCommentController.class);

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentStatusService commentStatusService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing Comment");

        List<ProjectComment> comments = taskCommentService.findAll();
        uiModel.addAttribute("comments", comments);

        logger.info("No. of comments: " + comments.size());

        return "time/comment/list";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        logger.info("Create form");
        ProjectComment comment = new ProjectComment();
        comment.setCommentStatus(new CommentStatus());
        comment.setTask(new Task());

        uiModel.addAttribute("comment", comment);
        uiModel.addAttribute("tasks", taskService.findAll());
        uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
        return "time/comment/create";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid ProjectComment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Create Comment");

        if (bindingResult.hasErrors()) {
            logger.error("bindingResult hasErrors");
            logger.error("bindingResult hasErrors message: " + bindingResult.toString());
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("comment", comment);
            uiModel.addAttribute("tasks", taskService.findAll());
            uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
            return "time/comment/create";
        }
        logger.info("no bindingResult hasErrors");
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_save_success", new Object[]{}, locale)));

        taskCommentService.save(comment);
        logger.info("Comment id: " + comment.getId());

        return "redirect:/time/comment/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid ProjectComment comment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Update Comment");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("comment_update_fail", new Object[]{}, locale)));
            uiModel.addAttribute("comment", comment);
            uiModel.addAttribute("tasks", taskService.findAll());
            uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
            return "time/comment/update";
        }

        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("comment_update_success", new Object[]{}, locale)));

        taskCommentService.save(comment);

        logger.info("Update Comment id: " + comment.getId());

        return "redirect:/time/comment/" + UrlUtil.encodeUrlPathSegment(comment.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        ProjectComment comment = taskCommentService.findById(id);
        uiModel.addAttribute("comment", comment);
        return "time/comment/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("comment", taskCommentService.findById(id));
        uiModel.addAttribute("tasks", taskService.findAll());
        uiModel.addAttribute("commentStatuses", commentStatusService.findAll());
        return "time/comment/update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        taskCommentService.delete(id);
        return "redirect:/time/comment";
    }
}
