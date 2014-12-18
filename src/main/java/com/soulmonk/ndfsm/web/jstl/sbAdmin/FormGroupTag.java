package com.soulmonk.ndfsm.web.jstl.sbAdmin;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 25.02.14
 * Time: 21:10
 */
public class FormGroupTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        return SKIP_BODY;
    }
}
