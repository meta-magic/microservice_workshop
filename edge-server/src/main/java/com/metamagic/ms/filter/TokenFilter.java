package com.metamagic.ms.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.TokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import atg.taglib.json.util.JSONException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenFilter extends ZuulFilter {

	@Autowired
	private TokenService tokenService;

	/** check for the json response */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		ResponseBean bean = new ResponseBean(false, "Invalid token or token required", "401", 401);
		ctx.setResponseStatusCode(401);
		ctx.setResponseBody(bean.toString());
		ctx.setSendZuulResponse(false);

		return null;
	}

	/* Filter Authentication service, user creation service, and  product catalog service from token check */
	
	@Override
	public boolean shouldFilter() {
		String requestUri = RequestContext.getCurrentContext().getRequest().getRequestURI();

		if (!(requestUri.contains("/auth/authenticate") || requestUri.contains("/user/create") || requestUri.contains("/product/query/findall"))) {
			HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
			try {
				tokenService.getTokenData(httpServletRequest.getHeader("tokenid"));
			} catch (JSONException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
				e.printStackTrace();
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
