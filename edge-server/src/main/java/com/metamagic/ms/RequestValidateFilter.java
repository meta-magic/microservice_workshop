package com.metamagic.ms;

import javax.servlet.http.HttpServletRequest;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RequestValidateFilter extends ZuulFilter
{

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
		System.out.println(this.getClass() +" "+requestUri);
		
//		if(requestUri.equalsIgnoreCase("/api/pd/product/query/findall"))
//			return true;
		
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
