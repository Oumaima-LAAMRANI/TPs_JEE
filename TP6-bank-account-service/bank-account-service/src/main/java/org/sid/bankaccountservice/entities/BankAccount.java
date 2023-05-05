package org.sid.bankaccountservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.enums.AccountType;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//C'est un design pattern
@Builder
public class BankAccount {
    @Id
    private String id;
    private Date creatAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
