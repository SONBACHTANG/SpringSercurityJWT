package com.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String username;
    private String token;
    private String tokeType = "Bearer";

    public JwtResponse(String username , String token) {
        this.username = username;
        this.token = token;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokeType() {
		return tokeType;
	}

	public void setTokeType(String tokeType) {
		this.tokeType = tokeType;
	}
    
    
}
