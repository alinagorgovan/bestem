package licenta.repository;

import licenta.entity.Role;
import licenta.entity.User;
import licenta.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByRole(Role role);

    List<UserRole> findAllByUser(User user);

    Optional<UserRole> findByUser(User user);
}