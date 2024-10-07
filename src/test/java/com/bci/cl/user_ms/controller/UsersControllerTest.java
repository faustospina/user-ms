package com.bci.cl.user_ms.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bci.cl.user_ms.dto.UserOutDTO;
import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class UsersControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserService userService;

    private UsersDTO usersDTO;

    private UserOutDTO userOutDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usersDTO = UsersDTO.builder()
                .id(UUID.randomUUID())
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("ValidPassword1!")
                .isActive(true)
                .build();

        userOutDTO = UserOutDTO.builder()
                .id(UUID.randomUUID())
                .name("Juan Rodriguez")
                .isActive(true)
                .build();
    }

    @Test
    void testCreateUser_Success() {
        when(userService.createUser(usersDTO))
                .thenReturn(new ResponseEntity<>(userOutDTO, HttpStatus.CREATED));

        ResponseEntity<?> response = usersController.createUser(usersDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService, times(1)).createUser(any(UsersDTO.class));
    }

    @Test
    void testGetUsers_Success() {
        when(userService.getUsers())
                .thenReturn(new ResponseEntity<>(Collections.singletonList(usersDTO), HttpStatus.OK));

        ResponseEntity<?> response = usersController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        verify(userService, times(1)).getUsers();
    }

    @Test
    void testCreateUser_BadRequest() {
        when(userService.createUser(any(UsersDTO.class)))
                .thenReturn(new ResponseEntity<>("Error al crear el usuario", HttpStatus.BAD_REQUEST));

        ResponseEntity<?> response = usersController.createUser(usersDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al crear el usuario", response.getBody());
        verify(userService, times(1)).createUser(any(UsersDTO.class));
    }
}
