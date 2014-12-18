package com.soulmonk.ndfsm.web.jstl;

import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 28.02.14
 * Time: 22:58
 */
public abstract class HtmlTagSupport extends TagSupport {
    private static final long serialVersionUID = 1L;

    public final static String DIV_TAG = "div";

    public static final String CLASS_ATTRIBUTE = "class";

    protected TagWriter tagWriter;

    public HtmlTagSupport() {
        super();
    }

    @Override
    public final int doStartTag() throws JspException {
        tagWriter = new TagWriter(pageContext);
        return doStartTagInternal();
    }

    protected int doStartTagInternal() throws JspException {
        tagWriter.startTag(DIV_TAG);
        tagWriter.forceBlock();
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        tagWriter.endTag();
        return super.doEndTag();
    }
}
