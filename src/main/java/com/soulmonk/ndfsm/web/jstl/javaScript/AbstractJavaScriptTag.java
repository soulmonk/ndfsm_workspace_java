package com.soulmonk.ndfsm.web.jstl.javaScript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Conventions;
import org.springframework.web.servlet.tags.form.AbstractFormTag;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 22:36
 */
public abstract class AbstractJavaScriptTag extends BodyTagSupport {
  private static final long serialVersionUID = 1L;

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public static final String MODEL_ATTRIBUTE = "javaScriptAttribute";
  public static final String MODEL_ATTRIBUTE_VARIABLE_NAME = Conventions.getQualifiedAttributeName(AbstractFormTag.class, MODEL_ATTRIBUTE);

  public AbstractJavaScriptTag() {
    super();
  }

  @SuppressWarnings("unchecked")
  protected List<String> getModelAttribute() {
    List<String> scripts;
    try {
      Object o = this.pageContext.getAttribute(MODEL_ATTRIBUTE_VARIABLE_NAME, PageContext.REQUEST_SCOPE);
      if (o == null) {
        scripts = new ArrayList<String>();
      } else {
        scripts = (List<String>) o;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      scripts = new ArrayList<String>();
    }
    return scripts;
  }

  protected void setModelAttribute(List<String> value) {
    this.pageContext.setAttribute(MODEL_ATTRIBUTE_VARIABLE_NAME, value, PageContext.REQUEST_SCOPE);
  }
}
