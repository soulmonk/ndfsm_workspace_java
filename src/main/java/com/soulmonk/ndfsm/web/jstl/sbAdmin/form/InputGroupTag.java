package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.InputTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 26.02.14
 * Time: 22:42
 */
public class InputGroupTag extends InputTag {
  private static final long serialVersionUID = 1L;

  private WrapperGroupTag wrapperGroupTag;

  private String labelValue;

  public InputGroupTag() {
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
