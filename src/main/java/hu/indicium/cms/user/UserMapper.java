package hu.indicium.cms.user;

import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import hu.indicium.cms.user.request.UpdateUserRequest;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static UserDTO map(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public static User map(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

    public static User map(CreateUserRequest createUserRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createUserRequest, User.class);
    }

    public static UserDTO map(UpdateUserRequest updateUserRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updateUserRequest, UserDTO.class);
    }

}
