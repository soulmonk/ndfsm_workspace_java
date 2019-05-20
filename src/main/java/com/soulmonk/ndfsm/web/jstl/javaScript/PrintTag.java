package com.soulmonk.ndfsm.web.jstl.javaScript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

/**
 * User: SoulMonk
 * Date: 01.03.14
 * Time: 22:32
 */
public class PrintTag extends AbstractJavaScriptTag {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(PrintTag.class);

    @Override
    public int doStartTag() throws JspException {
        List<String> scripts = getModelAttribute();
//    logger.debug("Scripts count: " + scripts.size());
        for (String script : scripts) {
            try {
                pageContext.getOut().print(script);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }
}
