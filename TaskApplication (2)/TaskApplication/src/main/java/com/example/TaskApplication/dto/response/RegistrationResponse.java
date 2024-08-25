package com.example.TaskApplication.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationResponse {
    private String message;
    private String username;
    private String role;
}
