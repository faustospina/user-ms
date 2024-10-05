package com.bci.cl.user_ms.controller;

import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UsersDTO request) {
        return service.createUser(request);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getUsers(){
        return service.getUsers();
    }
}
