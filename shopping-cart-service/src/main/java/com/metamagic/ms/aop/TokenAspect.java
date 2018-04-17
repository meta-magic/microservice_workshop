package com.metamagic.ms.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.TokenService;

import atg.taglib.json.util.JSONObject;
import io.jsonwebtoken.ExpiredJwtException;

@Component
@Aspect
@Scope(value = "request")
public class TokenAspect {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private LoginInfoHelperBean loginInfoHelperBean;

	@Around("allOperations()")
	public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {
			JSONObject jsonObject = tokenService.getTokenData((String) request.getHeader("tokenid"));
			loginInfoHelperBean.setProperty(jsonObject.getString("userId"));
		} catch (ExpiredJwtException e) {
			ResponseBean response = new ResponseBean(false, "Token expired.", "failure", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.UNAUTHORIZED);
		} catch (IllegalArgumentException e) {
			ResponseBean response = new ResponseBean(false, "Token required.", "failure", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean(false, "Invalid Token.", "failure", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.UNAUTHORIZED);
		}
		return joinPoint.proceed();
	}

	@Pointcut("execution(* com.metamagic.ms.controller..*.*(..))")
	public void allOperations() {
	}

}
