package hu.indicium.cms.user;


import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    //POST
    UserDTO createUser(CreateUserRequest createUserRequest);

    //GET
    UserDTO getUserById(String userId);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);

    //PUT
    UserDTO updateUser(UserDTO userDTO);

    //DELETE
    void deleteUser(String userId);
}
