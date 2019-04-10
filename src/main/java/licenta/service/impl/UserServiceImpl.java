package licenta.service.impl;

import licenta.dto.UserDTO;
import licenta.entity.Role;
import licenta.entity.User;
import licenta.entity.UserRole;
import licenta.repository.RoleRepository;
import licenta.repository.UserRepository;
import licenta.repository.UserRoleRepository;
import licenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUserName()).orElseGet(() ->
                userRepository.save(new User(userDto.getUserName(), userDto.getPassword())));
        Role role =
                roleRepository.findByRole("ROLE_" + userDto.getRole().toUpperCase()).orElseThrow(() ->
                        new RuntimeException("Problem assigning role!"));
        userRoleRepository.save(new UserRole(user, role));
    }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    public UserDTO getUserFromDb(String email) {
        return Optional.ofNullable(userRepository.findByUsername(email)).orElse(null).map(user ->
                new UserDTO(user.getUsername(), user.getPassword(),
                        userRoleRepository.findByUser(user).map(UserRole::getRole)
                                .orElse(new Role()).getRole())
        ).orElse(null);
    }
}
