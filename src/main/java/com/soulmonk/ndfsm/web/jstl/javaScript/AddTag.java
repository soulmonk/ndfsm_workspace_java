package com.soulmonk.ndfsm.web.jstl.javaScript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 22:32
 */
public class AddTag extends AbstractJavaScriptTag {
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(AddTag.class);
  @Override
  public int doEndTag() throws JspException {
//    logger.debug("Add tag");
    List<String> scripts = getModelAttribute();
    scripts.add(bodyContent.getString());
    setModelAttribute(scripts);
    return super.doEndTag();
  }
}
