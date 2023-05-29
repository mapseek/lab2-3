package com.nulp.paymentservice.dto;

import com.nulp.paymentservice.domain.Client;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CreditCardDto> creditCards;

    public Client toDomain() {
        return new Client()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email);
    }
}
