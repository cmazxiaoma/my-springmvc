package com.augmentum.exam.service.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.augmentum.exam.common.AppContext;

public class LogMethodTimeAspect {
    private final Logger logger = Logger.getLogger(LogMethodTimeAspect.class);

    public void doAfter(JoinPoint jp) {
        System.out.println("log Ending method:" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        String methodName = pjp.getSignature().getName();
        long endTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(AppContext.getContext().getUserName());
        sb.append(":");
        sb.append(pjp.getTarget().getClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime);
        logger.info(sb.toString());
        return returnValue;
    }

    public void doBefore(JoinPoint jp) {
        System.out.println("log Begining method:" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("method:" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println("exception:" + ex.getMessage());
    }
}
