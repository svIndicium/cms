package hu.indicium.cms.user;

import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import hu.indicium.cms.user.request.UpdateUserRequest;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //POST
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }
    //GET
    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsers(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    //PUT
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId){
        UserDTO userDTO = UserMapper.map(updateUserRequest);
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    //DELETE
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }

}
