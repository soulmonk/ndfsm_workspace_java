package com.soulmonk.ndfsm.web.jstl.html;

import com.soulmonk.ndfsm.web.form.Message;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Company: Valpio
 * User: SoulMonk
 * Date: 25.02.14
 * Time: 16:03
 */
public class MessageTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private Message message = null;

    @Override
    public int doStartTag() throws JspException {
        if (this.message != null) {
            try {
                pageContext.getOut().print("<div id=\"message\" class=\"alert alert-" + this.message.getType() + "\">" + this.message.getMessage() + "</div>");
            } catch (IOException ioException) {
                throw new JspException("Error: " + ioException.getMessage());
            }
        }
        return SKIP_BODY;
    }

    public void setMessage(Object message) {
        if (message == null || message.getClass() != Message.class) {
            this.message = null;
        } else {
            this.message = (Message) message;
        }
    }
}
