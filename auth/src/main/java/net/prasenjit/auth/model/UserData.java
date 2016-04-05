package net.prasenjit.auth.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UserData {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	private String email;
	
	@NotBlank
	private String name;

}
