package com.soulmonk.ndfsm.web.jstl.javaScript;

import javax.servlet.jsp.JspException;
import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 22:32
 */
public class AddTag extends AbstractJavaScriptTag {
  @Override
  public int doEndTag() throws JspException {
    logger.info("Add tag");
    List<String> scripts = getModelAttribute();
    logger.info("Body Content Class: " + bodyContent.getClass());
    logger.info("Body Content String: " + bodyContent.getString());
    scripts.add(bodyContent.getString());
    setModelAttribute(scripts);
    logger.info("Scripts count: " + scripts.size());
    scripts = getModelAttribute();
    logger.info("Scripts count: " + scripts.size());
    return super.doEndTag();
  }
}
