package com.molovataconstantin.flavormetrics.model.user;

import com.molovataconstantin.flavormetrics.model.AuthorityDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserDto {
    UUID id();
    String firstName();
    String lastName();
    boolean accountNonExpired();
    boolean accountNonLocked();
    boolean credentialsNonExpired();
    boolean enabled();
    String password();
    String username();
    LocalDateTime updatedAt();
    LocalDateTime createdAt();
    List<AuthorityDto> authorities();
}