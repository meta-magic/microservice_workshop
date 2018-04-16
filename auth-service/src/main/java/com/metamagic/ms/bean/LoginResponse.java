package com.metamagic.ms.bean;

public class LoginResponse {

	private String tokenId;
	
	public LoginResponse(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
}
