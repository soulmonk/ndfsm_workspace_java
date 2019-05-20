package com.soulmonk.ndfsm.web.jstl.sbAdmin;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * User: SoulMonk
 * Date: 25.02.14
 * Time: 20:02
 */
public class BlockRowColWithDefPanelTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private String colNum = "12";

    @Override
    public int doStartTag() throws JspException {
        try {
            String rowCol = "<div class=\"row\"><div class=\"col-lg-" + this.colNum + "\">";
            pageContext.getOut().print(rowCol + "<div class=\"panel panel-default\"><div class=\"panel-body\">" + rowCol);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print("</div></div></div></div></div></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    public void setColNum(String colNum) {
        this.colNum = colNum;
    }
}
