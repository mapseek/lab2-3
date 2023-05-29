package com.nulp.paymentservice.dto;

import com.nulp.paymentservice.domain.Admin;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminDto {
    private Long id;
    private String username;
    private String password;

    public Admin toDomain() {
        return new Admin()
                .setUsername(username)
                .setPassword(password);
    }
}
