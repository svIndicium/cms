package hu.indicium.cms.user;

import hu.indicium.cms.page.PageMapper;
import hu.indicium.cms.page.dto.PageDTO;
import hu.indicium.cms.page.request.UpdatePageRequest;
import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import hu.indicium.cms.user.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //USER CRUD
    //POST
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }
    //GET
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    //PUT
    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId){
        UserDTO userDTO = UserMapper.map(updateUserRequest);
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    //DELETE
    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }



}
