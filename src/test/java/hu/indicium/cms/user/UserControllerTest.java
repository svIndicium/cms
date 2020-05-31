package hu.indicium.cms.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.indicium.cms.user.dto.UserDTO;
import hu.indicium.cms.user.request.UpdateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayName("User controller")
@Tag("Controller")
public class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UserDTO userDTO;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup(){
        userDTO = new UserDTO();
        userDTO.setId("testid");
        userDTO.setEmail("rhettvankorlaar@gmail.com");
        userDTO.setRole(1);
    }

    @Test
    @DisplayName("Get single user")
    void shouldReturnSingleUser_whenGetUserById() throws Exception{
        given(userService.getUserById(eq("testid"))).willReturn(userDTO);

        mvc.perform(get("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get all users")
    void shouldReturnListOfUsers_whenGetAllUsers() throws Exception{
        given(userService.getAllUsers()).willReturn(Arrays.asList(userDTO, userDTO));

        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update user")
    void shouldReturnUpdatedUser_whenUpdateUser() throws Exception{
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();

        given(userService.updateUser(any(UserDTO.class))).willReturn(userDTO);

        mvc.perform(put("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writer().writeValueAsString(updateUserRequest)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(any(UserDTO.class));
    }
}
