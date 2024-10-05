package com.bci.cl.user_ms.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
    @NonNull
    private List<PhoneDTO> phones;
}
