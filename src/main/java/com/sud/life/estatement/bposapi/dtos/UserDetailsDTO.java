package com.sud.life.estatement.bposapi.dtos;

import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDTO {
    private String username;
    private String password;
    private List<AuthorityDTO> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
