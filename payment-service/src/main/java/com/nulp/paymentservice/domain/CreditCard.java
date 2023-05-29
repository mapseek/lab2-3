package com.nulp.paymentservice.domain;

import com.nulp.paymentservice.dto.CreditCardDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "client_id")
    private Client client;

    public CreditCardDto toDto() {
        return new CreditCardDto()
                .setId(id)
                .setClientId(client != null ? client.getId() : null)
                .setNumber(number)
                .setCvv(cvv)
                .setExpiryDate(expiryDate);
    }
}
