package com.metamagic.ms.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import ch.qos.logback.classic.Logger;

public class InvalidResourceFilter extends ZuulFilter{

	@Value("${maintence.servers}")
	private String maintenceservers = null;

	private static final Logger log = (Logger) LoggerFactory.getLogger(InvalidResourceFilter.class);

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		ResponseBean bean = new ResponseBean(true, "Invalid requested resource", "404", 404);
		ctx.setResponseStatusCode(404);
		ctx.setResponseBody(bean.toString());
		ctx.setSendZuulResponse(false);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		String requestUri = RequestContext.getCurrentContext().getRequest().getRequestURI();
		String servers = maintenceservers.trim();
		if(requestUri.startsWith(servers)){
			log.debug("Requested URI "+requestUri+" is down");
			return true;
		}
		else 
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
