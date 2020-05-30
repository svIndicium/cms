package hu.indicium.cms.user;

import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import hu.indicium.cms.user.request.UpdateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }
    //GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    //PUT
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId){
        UserDTO userDTO = UserMapper.map(updateUserRequest);
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    //DELETE
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }

}
