package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.CheckboxTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 19:55
 */
public class CheckboxGroupTag extends CheckboxTag {
  private static final long serialVersionUID = 1L;
  private WrapperGroupTag wrapperGroupTag;

  private String labelValue;

  public CheckboxGroupTag() {
    wrapperGroupTag = new WrapperGroupTag();
  }

  public void setLabelValue(String labelValue) {
    this.labelValue = labelValue;
  }

  @Override
  protected int writeTagContent(TagWriter tagWriter) throws JspException {
    wrapperGroupTag.beginGroup(tagWriter, getPath(), this.labelValue, getBindStatus().isError(), this.pageContext);
    setCssClass("checkbox-inline");
    tagWriter.startTag("div");
    tagWriter.writeOptionalAttributeValue(AbstractHtmlElementTag.CLASS_ATTRIBUTE, "checkbox");
    tagWriter.forceBlock();
    super.writeTagContent(tagWriter);
    tagWriter.endTag();
    wrapperGroupTag.endGroup(tagWriter, getPath(), this.pageContext);
    return SKIP_BODY;
  }
}
