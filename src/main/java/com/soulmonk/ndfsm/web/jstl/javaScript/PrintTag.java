package com.soulmonk.ndfsm.web.jstl.javaScript;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import java.io.IOException;
import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 22:32
 */
public class PrintTag extends AbstractJavaScriptTag {

  @Override
  public int doStartTag() throws JspException {
    List<String> scripts = getModelAttribute();
    logger.debug("Scripts count: " + scripts.size());
    for (String script : scripts) {
      try {
        pageContext.getOut().print(script);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return SKIP_BODY;
  }
}
