package com.example.naijavotingsystem.dtos;

import lombok.*;

@Setter
@Getter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
