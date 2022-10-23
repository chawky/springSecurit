package reporsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springSecProject.modals.ERole;
import com.springSecProject.modals.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByRole(ERole erole);
	Optional<User> findByUserName(String name);
	boolean existsByUserName(String username);
	boolean existsByEmail(String email);
	
}
