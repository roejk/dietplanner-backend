package com.rojek.dietplanner.dto;

import com.rojek.dietplanner.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private UserRole role;
    private Boolean isLocked;
    private Boolean isEnabled;
}
