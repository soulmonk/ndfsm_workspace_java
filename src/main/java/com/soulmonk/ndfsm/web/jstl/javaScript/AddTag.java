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
  private static final long serialVersionUID = 1L;
  @Override
  public int doEndTag() throws JspException {
//    logger.debug("Add tag");
    List<String> scripts = getModelAttribute();
    scripts.add(bodyContent.getString());
    setModelAttribute(scripts);
    return super.doEndTag();
  }
}
