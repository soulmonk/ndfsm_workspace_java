package com.soulmonk.ndfsm.web.app.controllers.time;

import com.soulmonk.ndfsm.domain.time.Project;
import com.soulmonk.ndfsm.domain.time.Record;
import com.soulmonk.ndfsm.service.time.ProjectService;
import com.soulmonk.ndfsm.service.time.RecordService;
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
@RequestMapping(value = "/time/record")
public class RecordController {

  final Logger logger = LoggerFactory.getLogger(RecordController.class);

  @Autowired
  private RecordService recordService;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public String list(Model uiModel) {
    logger.info("Listing records");

    List<Record> records = recordService.findAll();
    uiModel.addAttribute("records", records);

    logger.info("No. of records: " + records.size());

    return "time/record/list";
  }

  @RequestMapping(params = "form", method = RequestMethod.GET)
  public String createForm(Model uiModel) {
    logger.info("Create form");
    Record record = new Record();
    uiModel.addAttribute("record", record);
    return "time/record/create";
  }

  @RequestMapping(params = "form", method = RequestMethod.POST)
  public String create(@Valid Record record, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Create record");

    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("record_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute("record", record);
      return "time/record/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("record_save_success", new Object[]{}, locale)));

    recordService.save(record);
    logger.info("Record id: " + record.getId());
    return "redirect:/time/record/" + UrlUtil.encodeUrlPathSegment(record.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
  public String update(@Valid Record record, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    logger.info("Update record");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message(Message.DANGER_TYPE, messageSource.getMessage("record_update_fail", new Object[]{}, locale)));
      uiModel.addAttribute("record", record);
      return "time/record/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS_TYPE, messageSource.getMessage("record_update_success", new Object[]{}, locale)));

    recordService.save(record);

    logger.info("Update Record id: " + record.getId());

    return "redirect:/time/record/" + UrlUtil.encodeUrlPathSegment(record.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Record record = recordService.findById(id);
    uiModel.addAttribute("record", record);
    return "time/record/show";
  }

  @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    Record record = recordService.findById(id);
    uiModel.addAttribute("record", record);
    return "time/record/update";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") Long id, Model uiModel) {
    recordService.delete(id);
    return "redirect:/time/record";
  }

  @RequestMapping(value = "/list_edit", method = RequestMethod.GET)
  public String listEdit(Model uiModel) {
    return "time/record/listEdit";
  }
}
