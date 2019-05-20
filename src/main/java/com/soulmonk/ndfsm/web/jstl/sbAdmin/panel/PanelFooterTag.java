package com.soulmonk.ndfsm.web.jstl.sbAdmin.panel;

import com.soulmonk.ndfsm.web.jstl.HtmlTagSupport;

import javax.servlet.jsp.JspException;

/**
 * User: SoulMonk
 * Date: 28.02.14
 * Time: 23:20
 */
public class PanelFooterTag extends HtmlTagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTagInternal() throws JspException {
        tagWriter.startTag(DIV_TAG);
        tagWriter.writeAttribute(CLASS_ATTRIBUTE, "panel-footer");
        tagWriter.forceBlock();
        return EVAL_BODY_INCLUDE;
    }
}
