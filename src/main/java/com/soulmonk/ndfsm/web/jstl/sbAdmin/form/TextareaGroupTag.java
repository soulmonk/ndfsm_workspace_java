package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.web.servlet.tags.form.TextareaTag;

import javax.servlet.jsp.JspException;

/**
 * User: SoulMonk
 * Date: 26.02.14
 * Time: 20:11
 */
public class TextareaGroupTag extends TextareaTag {
    private static final long serialVersionUID = 1L;

    private String labelValue;

    private WrapperGroupTag wrapperGroupTag;

    public TextareaGroupTag() {
        wrapperGroupTag = new WrapperGroupTag();
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        wrapperGroupTag.beginGroup(tagWriter, getPath(), this.labelValue, getBindStatus().isError(), this.pageContext);
        setCssClass("form-control");
        super.writeTagContent(tagWriter);
        wrapperGroupTag.endGroup(tagWriter, getPath(), this.pageContext);
        return SKIP_BODY;
    }
}
