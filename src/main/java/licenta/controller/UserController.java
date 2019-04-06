package licenta.controller;

import licenta.dto.UserDTO;
import licenta.service.UserService;
import licenta.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(Constants.GET_USER_BY_ID)
	public UserDTO getUserById(@PathVariable Integer userId) {
		return userService.getUserById(userId);
	}
	
	@RequestMapping(Constants.GET_ALL_USERS)
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value= Constants.SAVE_USER, method= RequestMethod.POST)
	public void saveUser(@RequestBody UserDTO userDto) {
		userService.saveUser(userDto);
	}
}
