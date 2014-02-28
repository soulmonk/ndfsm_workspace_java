package com.soulmonk.ndfsm.web.jstl.sbAdmin.panel;

import com.soulmonk.ndfsm.web.jstl.HtmlTagSupport;

import javax.servlet.jsp.JspException;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 28.02.14
 * Time: 22:52
 */
public class PanelHeadingHtmlTag extends HtmlTagSupport {
  @Override
  public int doStartTagInternal() throws JspException {
    tagWriter.startTag(DIV_TAG);
    tagWriter.writeAttribute(CLASS_ATTRIBUTE, "panel-heading");
    tagWriter.forceBlock();
    return EVAL_BODY_INCLUDE;
  }
}