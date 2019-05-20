package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * User: SoulMonk
 * Date: 26.02.14
 * Time: 22:03
 */
public class WrapperGroupTag {
    private static final long serialVersionUID = 1L;

    public void beginGroup(TagWriter tagWriter, String path, String labelValue, Boolean isError, PageContext pageContext) throws JspException {
        tagWriter.startTag("div");

        tagWriter.writeOptionalAttributeValue(AbstractHtmlElementTag.CLASS_ATTRIBUTE, "form-group" + (isError ? " has-error" : ""));
        tagWriter.forceBlock();

        CLabelTag labelTag = new CLabelTag();
        labelTag.setPath(path);
        labelTag.setPageContext(pageContext);
        labelTag.setTagWriter(tagWriter);
        labelTag.setLabelValue(labelValue);
        labelTag.setCssClass("control-label");
        labelTag.doStartTag();
    }

    public void endGroup(TagWriter tagWriter, String path, PageContext pageContext) throws JspException {
        CErrorsTag errorsTag = new CErrorsTag();
        errorsTag.setTagWriter(tagWriter);
        errorsTag.setPageContext(pageContext);
        errorsTag.setPath(path);
        errorsTag.doStartTag();
        errorsTag.doEndTag();
        errorsTag.doFinally();

        tagWriter.endTag();
    }
}
