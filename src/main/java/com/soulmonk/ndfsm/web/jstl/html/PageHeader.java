package com.soulmonk.ndfsm.web.jstl.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Owner on 25.02.14.
 */
public class PageHeader extends TagSupport {
  private static final long serialVersionUID = 1L;

  private String tag = "h1";

  private String text = "";

  @Override
  public int doStartTag() throws JspException {

    try {
      StringBuffer buffer = new StringBuffer();
      /*
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${formTitle}</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>*/
      buffer.append( "<div class=\"row\">" );
      buffer.append( "<div class=\"col-lg-12\">" );
      String tagText = "<" + this.tag + "  class=\"page-header\">" + this.text + "</" + this.tag + ">";
      buffer.append(tagText);
      buffer.append( "</div></div>" );
      pageContext.getOut().print( buffer.toString() );
    } catch(IOException ioException) {
      throw new JspException("Error: " + ioException.getMessage());
    }
    return SKIP_BODY;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public void setText(String text) {
    this.text = text;
  }
}
