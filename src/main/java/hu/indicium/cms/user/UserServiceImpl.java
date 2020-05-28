package hu.indicium.cms.user;

import hu.indicium.cms.page.Page;
import hu.indicium.cms.page.PageMapper;
import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest);
        String passwordHash = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt());
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
        return UserMapper.map(user);
    }

    @Override
    public UserDTO getUserById(String userId) {
        User user = findById(userId);
        return UserMapper.map(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        findById(userDTO.getId());
        User user = UserMapper.map(userDTO);
        user = userRepository.save(user);
        return UserMapper.map(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    private User findById(String userId){
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}