package hu.indicium.cms.user;

import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.CreateUserRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //POST
    @Override
    public UserDTO createUser(CreateUserRequest createUserRequest) {
        User user = UserMapper.map(createUserRequest);
        String passwordHash = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt());
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
        return UserMapper.map(user);
    }

    //GET
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
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return UserMapper.map(user);
    }

    //PUT
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User findUser = findById(userDTO.getId());
        User user = UserMapper.map(userDTO);

        user.setPasswordHash(findUser.getPasswordHash());
        user = userRepository.save(user);
        return UserMapper.map(user);
    }

    //DELETE
    @Override
    public void deleteUser(String userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    private User findById(String userId){
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user == null){

            return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, authorities);
        }
        UserDTO userDTO = UserMapper.map(user);
        authorities.add(new SimpleGrantedAuthority(userDTO.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, authorities);
    }

}
