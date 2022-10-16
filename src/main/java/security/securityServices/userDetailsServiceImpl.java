package security.securityServices;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springSecProject.modals.User;

import reporsitories.UserRepo;
@Service 
@Transactional
public class userDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUserName(username).orElseThrow(() ->new UsernameNotFoundException("userNotFound"+username));
		return UserDetailsImpl.build(user);
	}

}
