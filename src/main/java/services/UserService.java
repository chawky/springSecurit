package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSecProject.modals.User;

import reporsitories.UserRepo;


@Transactional
@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	public List<User> getAllUsers(){
		return repo.findAll();
		
	}
	

}
