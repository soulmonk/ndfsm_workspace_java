package com.soulmonk.ndfsm.web.jstl.sbAdmin.panel;

import com.soulmonk.ndfsm.web.jstl.HtmlTagSupport;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 28.02.14
 * Time: 23:16
 */
public class PanelBodyTag extends HtmlTagSupport {
  @Override
  public int doStartTagInternal() throws JspException {
    tagWriter.startTag(DIV_TAG);
    tagWriter.writeAttribute(CLASS_ATTRIBUTE, "panel-body");
    tagWriter.forceBlock();
    return EVAL_BODY_INCLUDE;
  }
}
