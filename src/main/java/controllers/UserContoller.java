package controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecProject.modals.User;

import services.UserService;

@RestController
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class UserContoller implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/users")
	@CrossOrigin(origins = "http://localhost:8080")
	public String getAllUsers() {
		return "<h1>hello World</h1>";
	}
	@GetMapping(path = "/usersSecured")
	@CrossOrigin(origins = "http://localhost:8080")
	public String getAllUsersSecured(@RequestHeader("Authorization") String authorization) {
		System.out.println(authorization);
		return "<h1>hello World2</h1>";
	}

}
