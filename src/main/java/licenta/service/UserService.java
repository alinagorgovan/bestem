package licenta.service;

import licenta.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Integer userId);

    void saveUser(UserDTO userDto);

    List<UserDTO> getAllUsers();
}
