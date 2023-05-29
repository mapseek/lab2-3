package com.nulp.paymentservice.domain;

import com.skyba.paymentservice.dto.AccountDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CreditCard creditCard;

    @Column(name = "blocked")
    private Boolean blocked;

    public AccountDto toDto(){
        return new AccountDto()
                .setId(id)
                .setBalance(balance)
                .setBlocked(blocked)
                .setPayments(payments.stream().map(Payment::toDto).collect(Collectors.toList()))
                .setCreditCardId(creditCard.getId());
    }
}
