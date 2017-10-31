package com.augmentum.exam.common;

import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

public abstract class BlockAbstract {
    private static Logger logger = Logger.getLogger(BlockAbstract.class);
    public String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String displayBlock(PageContext pageContext) {
        execute(pageContext);
        Writer body = new StringWriter();
        try {
            if (template != null && !template.trim().equals("")) {
                pageContext.pushBody(body);
                pageContext.include(template);
                pageContext.popBody();
                return body.toString();
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                body.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public abstract void execute(PageContext pageContext);
}
