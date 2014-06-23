package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.PasswordInputTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 19:40
 */
public class PasswordInputGroupTag extends PasswordInputTag {
    private static final long serialVersionUID = 1L;
    private WrapperGroupTag wrapperGroupTag;

    private String labelValue;

    public PasswordInputGroupTag() {
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
