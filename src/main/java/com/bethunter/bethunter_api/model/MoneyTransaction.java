package com.bethunter.bethunter_api.model;

import com.bethunter.bethunter_api.enums.Expensives;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "money_transactions")
@NoArgsConstructor
@Getter
@Setter
public class MoneyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expensive_type", nullable = false)
    private Expensives expensiveType;

    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    private Account account;
}
