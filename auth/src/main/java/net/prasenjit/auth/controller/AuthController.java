package net.prasenjit.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.prasenjit.auth.model.AuthConstants;
import net.prasenjit.auth.model.ModifyRequest;
import net.prasenjit.auth.model.UserData;
import net.prasenjit.auth.service.UserService;

@RestController
@RequestMapping(value="/user")
public class AuthController {
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value="/register",method=RequestMethod.PUT)
	public boolean registerUser(@Validated @RequestBody UserData userdata){
		
		boolean result = userservice.registerUser(userdata);
		
		return result;
	}
	
	@RequestMapping(value="/{userid}")
	public boolean manageUser(@RequestBody ModifyRequest modifyrequest,@PathVariable("userid") String userid){
		
		switch(modifyrequest.getMode()){
		case AuthConstants.UNLOCK:
			return userservice.unLockuser(userid);
		case AuthConstants.LOCK:
			return userservice.lockUser(userid);
		case AuthConstants.DISABLE:
			return userservice.disableUser(userid);
		case AuthConstants.ENABLE:
			return userservice.enableUser(userid);
		default:
			return false;
		}
	}
}
