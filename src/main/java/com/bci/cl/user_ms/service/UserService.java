package com.bci.cl.user_ms.service;

import com.bci.cl.user_ms.dto.UsersDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UsersDTO usersDTO);

    ResponseEntity<?> getUsers();
}
