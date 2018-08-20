package com.metamagic.ms.filter;

import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import ch.qos.logback.classic.Logger;

public class RequestLogValidateFilter extends ZuulFilter
{
	private static final Logger log = (Logger) LoggerFactory.getLogger(RequestLogValidateFilter.class);

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public boolean shouldFilter() {
		String requestUri = RequestContext.getCurrentContext().getRequest().getRequestURI();
		log.debug("URI "+requestUri);
		return false;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
	

}
