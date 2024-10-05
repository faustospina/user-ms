package com.bci.cl.user_ms.service;

import com.bci.cl.user_ms.config.JwtUtil;
import com.bci.cl.user_ms.dto.PhoneDTO;
import com.bci.cl.user_ms.dto.UserOutDTO;
import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.mapper.UserMapper;
import com.bci.cl.user_ms.model.Phone;
import com.bci.cl.user_ms.model.Users;
import com.bci.cl.user_ms.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@TestPropertySource(properties = "password.regex=^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$")
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserMapper userMapper;

    @Value("${password.regex}")
    private String passwordRegex;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService.passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
    }

    @Test
    void testCreateUser_Success() throws Exception {

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1");
        phoneDTO.setCityCode("123");
        phoneDTO.setContryCode("1234");

        List<PhoneDTO> phonesDto = new ArrayList<>();
        phonesDto.add(phoneDTO);


        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setName("juan");
        usersDTO.setEmail("juan@test.com");
        usersDTO.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbmRyZXMzNEBnbWFpbC5jb20iLCJpYXQiOjE3MjgxNjExMzZ9.K88AFM1isev0xXBoFGPFf7FH5_Ak6EudPQDiAldfPqfLJFK3un_oM_tirzDnU2ny");
        usersDTO.setPassword("Password2!");
        usersDTO.setPhones(phonesDto);

        Users user = new Users();

        Phone phone = new Phone();
        phone.setNumber("1");
        phone.setCityCode("123");
        phone.setContryCode("1234");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user.setId(UUID.fromString("38b04441-f58c-47c2-8684-66291a0f6d09"));
        user.setName("juan");
        user.setEmail("juan@test.com");
        user.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbmRyZXMzNEBnbWFpbC5jb20iLCJpYXQiOjE3MjgxNjExMzZ9.K88AFM1isev0xXBoFGPFf7FH5_Ak6EudPQDiAldfPqfLJFK3un_oM_tirzDnU2ny");
        user.setPassword("Password2!");
        user.setPhones(phones);


        UserOutDTO userOutDTO = new UserOutDTO();

        userOutDTO.setId(UUID.fromString("38b04441-f58c-47c2-8684-66291a0f6d09"));
        userOutDTO.setCreated(LocalDateTime.now());
        userOutDTO.setModified(LocalDateTime.now());
        userOutDTO.setLastLogin(LocalDateTime.now());
        userOutDTO.setIsActive(true);
        userOutDTO.setName("juan");
        userOutDTO.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbmRyZXMzNEBnbWFpbC5jb20iLCJpYXQiOjE3MjgxNjExMzZ9.K88AFM1isev0xXBoFGPFf7FH5_Ak6EudPQDiAldfPqfLJFK3un_oM_tirzDnU2ny");



        when(userRepository.findByEmail(usersDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toEntity(usersDTO)).thenReturn(user);
        when(jwtUtil.generateToken(user.getEmail())).thenReturn("mockToken");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toOutDTO(user)).thenReturn(userOutDTO);

        ResponseEntity<?> response = userService.createUser(usersDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1");
        phoneDTO.setCityCode("123");
        phoneDTO.setContryCode("1234");

        List<PhoneDTO> phonesDto = new ArrayList<>();
        phonesDto.add(phoneDTO);

        Users user = new Users();

        Phone phone = new Phone();
        phone.setNumber("1");
        phone.setCityCode("123");
        phone.setContryCode("1234");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user.setId(UUID.fromString("38b04441-f58c-47c2-8684-66291a0f6d09"));
        user.setName("juan");
        user.setEmail("juan@test.com");
        user.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbmRyZXMzNEBnbWFpbC5jb20iLCJpYXQiOjE3MjgxNjExMzZ9.K88AFM1isev0xXBoFGPFf7FH5_Ak6EudPQDiAldfPqfLJFK3un_oM_tirzDnU2ny");
        user.setPassword("Password2!");
        user.setPhones(phones);


        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setName("juan");
        usersDTO.setEmail("juan@test.com");
        usersDTO.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbmRyZXMzNEBnbWFpbC5jb20iLCJpYXQiOjE3MjgxNjExMzZ9.K88AFM1isev0xXBoFGPFf7FH5_Ak6EudPQDiAldfPqfLJFK3un_oM_tirzDnU2ny");
        usersDTO.setPassword("Password2!");
        usersDTO.setPhones(phonesDto);

        when(userRepository.findByEmail(usersDTO.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userService.createUser(usersDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetUsers_Success() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());

        when(userRepository.findAll()).thenReturn(usersList);
        when(userMapper.getListUserDTO(usersList)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = userService.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUsers_NotFound() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = userService.getUsers();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}