package com.bci.cl.user_ms.service;

import com.bci.cl.user_ms.config.JwtUtil;
import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.exception.BusinessException;
import com.bci.cl.user_ms.mapper.UserMapper;
import com.bci.cl.user_ms.model.Users;
import com.bci.cl.user_ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper mapper;

    @Value("${password.regex}")
    String passwordRegex;

    @Override
    public ResponseEntity<Object> createUser(UsersDTO usersDTO) {
        try{
            validBuild(usersDTO);
            Users users = mapper.toEntity(usersDTO);
            timeBuild(users);
            String token = jwtUtil.generateToken(users.getEmail());
            users.setToken(token);
            users.setIsActive(true);
            Users usersOut = repository.save(users);
            return new ResponseEntity<>(mapper.toOutDTO(usersOut), HttpStatus.CREATED);
        }catch (BusinessException e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> getUsers() {
        try{
            List<Users> users = repository.findAll();
            if (users.isEmpty()) {
                throw BusinessException.builder().message("Elements not found").build();
            }
            return new ResponseEntity<>(mapper.getListUserDTO(users), HttpStatus.OK);
        }catch (BusinessException e){
           return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
    }

    private static void timeBuild(Users users) {
        users.setCreated(LocalDateTime.now());
        users.setModified(LocalDateTime.now());
        users.setLastLogin(LocalDateTime.now());
    }

    private void validBuild(UsersDTO usersDTO) throws Exception {
        if (repository.findByEmail(usersDTO.getEmail()).isPresent()) {
            throw BusinessException.builder().message("El correo ya registrado").build();
        }

        // Validar el formato del correo
        if (!usersDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw BusinessException.builder().message("Formato de correo inválido").build();
        }

        // Validar el formato de la contraseña
        if (!usersDTO.getPassword().matches(passwordRegex)) {
            throw BusinessException.builder().message("Formato de contraseña inválido").build();
        }
    }
}
