package com.nulp.paymentservice.domain;

import com.nulp.paymentservice.dto.AdminDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public AdminDto toDto(){
        return new AdminDto()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }
}
