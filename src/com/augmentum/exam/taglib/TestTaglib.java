package com.augmentum.exam.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TestTaglib extends TagSupport {

    private static final long serialVersionUID = -2273303989235423022L;

    private String formatKey;

    public String getFormatKey() {
        return formatKey;
    }

    public void setFormatKey(String formatKey) {
        this.formatKey = formatKey;
    }

    @Override
    public int doEndTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {
            out.println("" + formatKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public void release() {
        super.release();
    }
}
