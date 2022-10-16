package controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecProject.modals.User;

import services.UserService;

@RestController
public class UserContoller implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	UserService userService;
	
	@GetMapping(path = "users")
	public String getAllUsers() {
		return "<h1>hello World</h1>";
	}

}
