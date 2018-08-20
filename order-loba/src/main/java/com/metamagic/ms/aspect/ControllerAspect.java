package com.metamagic.ms.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * 
 * THIS ASPECT IS USED FOR CONTROLLER
 */
@Component
@Aspect
public class ControllerAspect {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ControllerAspect.class);
	
	@Around("execution(* com.metamagic.ms.controller..*.*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String msg = joinPoint.getTarget().getClass() + " " + signature.getName();
		LOGGER.debug(" Executing [ " + msg + "  ] starts");
		Object response = joinPoint.proceed();
		LOGGER.debug(" Executing [ " + msg + "  ] ends");
		return response;
	}
}
