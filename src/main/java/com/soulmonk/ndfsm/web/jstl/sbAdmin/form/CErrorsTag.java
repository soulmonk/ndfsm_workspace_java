package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 26.02.14
 * Time: 21:49
 */
public class CErrorsTag extends ErrorsTag {
    private static final long serialVersionUID = 1L;

    private TagWriter tagWriter;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        if (this.tagWriter == null) {
            throw new IllegalStateException("Cannot write without TagWriter.");
        }
        setCssClass("alert alert-danger");
        setElement("div");
        return super.writeTagContent(this.tagWriter);
    }

    public void setTagWriter(TagWriter tagWriter) {
        this.tagWriter = tagWriter;
    }
}
