package com.soulmonk.ndfsm.web.jstl.sbAdmin.form;

import org.springframework.web.servlet.tags.form.LabelTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 26.02.14
 * Time: 21:23
 */
public class CLabelTag extends LabelTag {
  private static final long serialVersionUID = 1L;

  /**
   * The HTML '{@code label}' tag.
   */
  private static final String LABEL_TAG = "label";

  /**
   * The name of the '{@code for}' attribute.
   */
  private static final String FOR_ATTRIBUTE = "for";

  private TagWriter tagWriter;

  private String labelValue;

  @Override
  protected int writeTagContent(TagWriter tagWriter) throws JspException {
    if (this.tagWriter == null ) {
      throw new IllegalStateException("Cannot write without TagWriter.");
    }
    tagWriter.startTag(LABEL_TAG);
    tagWriter.writeAttribute(FOR_ATTRIBUTE, resolveFor());
    writeDefaultAttributes(tagWriter);
    tagWriter.appendValue(resolveLabelValue());
    tagWriter.endTag();
    return SKIP_BODY;
  }

  public void setTagWriter(TagWriter tagWriter) {
    this.tagWriter = tagWriter;
  }

  public void setLabelValue(String labelValue) {
    this.labelValue = labelValue;
  }


  private String resolveLabelValue() throws JspException {
    String labelValue = "";
    if (this.labelValue == null) {
      labelValue = getName();
    } else {
      labelValue = this.labelValue;
    }
    return labelValue;
  }
}
