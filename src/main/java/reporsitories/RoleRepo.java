package reporsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springSecProject.modals.ERole;
import com.springSecProject.modals.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(ERole name);

}
