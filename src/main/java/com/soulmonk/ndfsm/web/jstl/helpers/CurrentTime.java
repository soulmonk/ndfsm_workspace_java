package com.soulmonk.ndfsm.web.jstl.helpers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Company: Valpio
 * User: soulmonk
 * Date: 20.09.13
 * Time: 8:02
 */
public class CurrentTime extends TagSupport {
  private static final long serialVersionUID = 1L;

  @Override
  public int doStartTag() throws JspException {
    Date date = new Date();
    Locale locale = Locale.ENGLISH;
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
        DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);
    try {
      pageContext.getOut().print(formattedDate);
    } catch (IOException ioException) {
      throw new JspException("Error: " + ioException.getMessage());
    }
    return SKIP_BODY;
  }
}
