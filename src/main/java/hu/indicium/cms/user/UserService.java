package hu.indicium.cms.user;


import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;

import java.util.List;

public interface UserService {
    //POST
    UserDTO createUser(CreateUserRequest createUserRequest);

    //GET
    UserDTO getUserById(String userId);
    List<UserDTO> getAllUsers();

    //PUT
    UserDTO updateUser(UserDTO userDTO);

    //DELETE
    void deleteUser(String userId);
}
