package com.bci.cl.user_ms.mapper;

import com.bci.cl.user_ms.dto.UserOutDTO;
import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UsersDTO toDTO(Users users);
    Users toEntity(UsersDTO usersDTO);

    UserOutDTO toOutDTO(Users users);

    List<UsersDTO> getListUserDTO(List<Users> users);
}
