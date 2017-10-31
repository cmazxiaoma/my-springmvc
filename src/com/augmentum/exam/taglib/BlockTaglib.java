package com.augmentum.exam.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.augmentum.exam.common.BlockAbstract;
import com.augmentum.exam.utils.SpringUtil;

public class BlockTaglib extends TagSupport {
    private static final long serialVersionUID = 6000419735603081395L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doEndTag() throws JspException {
        ApplicationContext context = SpringUtil.getApplicationContext();
        BlockAbstract block = (BlockAbstract) context.getBean(name);
        //generate html
        String content = block.displayBlock(pageContext);
        JspWriter out = pageContext.getOut();

        try {
            out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }
}
