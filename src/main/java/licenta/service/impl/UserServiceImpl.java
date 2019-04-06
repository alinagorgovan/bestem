package licenta.service.impl;

import licenta.dto.UserDTO;
import licenta.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public void saveUser(UserDTO userDto) {

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }
}
