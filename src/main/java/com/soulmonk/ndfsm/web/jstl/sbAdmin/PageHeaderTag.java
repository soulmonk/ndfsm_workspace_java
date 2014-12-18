package com.soulmonk.ndfsm.web.jstl.sbAdmin;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 25.02.14
 * Time: 16:05
 */
public class PageHeaderTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private String tag = "h1";

    private String text = "";

    @Override
    public int doStartTag() throws JspException {
        try {
            String tagText = "<" + this.tag + "  class=\"page-header\">" + this.text + "</" + this.tag + ">";
            pageContext.getOut().print("<div class=\"row\">" + "<div class=\"col-lg-12\">" + tagText + "</div></div>");
        } catch (IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setText(String text) {
        this.text = text;
    }
}
