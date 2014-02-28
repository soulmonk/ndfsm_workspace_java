package com.soulmonk.ndfsm.web.jstl.sbAdmin.panel;

import com.soulmonk.ndfsm.web.jstl.HtmlTagSupport;

import javax.servlet.jsp.JspException;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 28.02.14
 * Time: 22:32
 */
public class PanelHtmlTag extends HtmlTagSupport {

  public static final String DEFAULT_TYPE = "default";

  private String type;

  @Override
  public int doStartTagInternal() throws JspException {
    tagWriter.startTag(DIV_TAG);
    tagWriter.writeAttribute(CLASS_ATTRIBUTE, "panel panel-" + resolveType());
    tagWriter.forceBlock();
    return EVAL_BODY_INCLUDE;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String resolveType() {
    if (type == null) {
      return DEFAULT_TYPE;
    } else {
      return type;
    }
  }
}
