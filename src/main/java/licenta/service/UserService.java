package licenta.service;

import licenta.dto.UserDTO;
import licenta.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDto);

    List<User> getAllUsers();

    UserDTO getUserFromDb(String email);
}
