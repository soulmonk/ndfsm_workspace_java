package com.soulmonk.ndfsm.web.app.controllers.note;

import com.soulmonk.ndfsm.domain.note.Post;
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
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 27.02.14
 * Time: 22:01
 */
@Controller
@RequestMapping(value = "/note/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing posts");

        List<Post> posts = postService.findAll();
        uiModel.addAttribute("posts", posts);

        logger.info("No. of posts: " + posts.size());

        return "note/post/list";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        Post post = new Post();
        uiModel.addAttribute("post", post);
        return "note/post/create";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid Post post, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Create post");

        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("post_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("post", post);
            return "note/post/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("post_save_success", new Object[]{}, locale)));
        postService.save(post);
        logger.info("Post id: " + post.getId());
        return "redirect:/note/post/" + UrlUtil.encodeUrlPathSegment(post.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Post post, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Update post");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("post_update_fail", new Object[]{}, locale)));
            uiModel.addAttribute("post", post);
            return "note/post/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("post_update_success", new Object[]{}, locale)));

        postService.save(post);

        return "redirect:/note/post/" + UrlUtil.encodeUrlPathSegment(post.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Post post = postService.findById(id);
        uiModel.addAttribute("post", post);
        return "note/post/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("post", postService.findById(id));
        return "note/post/update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        postService.delete(id);
        return "redirect:/note/post/list";
    }
}

