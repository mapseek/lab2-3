package com.nulp.paymentservice.domain;

import com.nulp.paymentservice.dto.PaymentDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private Account account;

    public PaymentDto toDto(){
        return new PaymentDto()
                .setId(id)
                .setAmount(amount)
                .setDate(date)
                .setAccountId(account != null ? account.getId() : null);
    }
}
