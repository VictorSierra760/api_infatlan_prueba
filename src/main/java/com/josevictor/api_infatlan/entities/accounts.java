package com.josevictor.api_infatlan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Check(constraints = "balance >= 0")
public class accounts {

    @Id
    private Long id;

    @Column(name = "holder_name", nullable = false, length = 100)
    private String holder_name;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
