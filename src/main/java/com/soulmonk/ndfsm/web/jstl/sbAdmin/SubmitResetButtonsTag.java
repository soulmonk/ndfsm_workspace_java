package com.soulmonk.ndfsm.web.jstl.sbAdmin;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.Locale;

/**
 * Company: PolecatSoft
 * User: SoulMonk
 * Date: 25.02.14
 * Time: 20:12
 */
public class SubmitResetButtonsTag extends RequestContextAwareTag {
    private static final long serialVersionUID = 1L;

    @Override
    protected int doStartTagInternal() throws Exception {
        MessageSource messageSource = getRequestContext().getMessageSource();
        Locale locale = getRequestContext().getLocale();
        String labelSave = messageSource.getMessage("label_form_save", new Object[]{}, locale);
        String labelReset = messageSource.getMessage("label_form_reset", new Object[]{}, locale);
        try {
            pageContext.getOut().print("<button type=\"submit\" class=\"btn btn-default\">" + labelSave + "</button><button type=\"reset\" class=\"btn btn-default\">" + labelReset + "</button>");
        } catch (IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }
}
