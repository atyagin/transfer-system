package com.ilya.transfer_system.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class Account {
    private String id;
    @Value("${user.money}")
    private int money;

    public Account() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return money == account.money &&
                id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", money=" + money +
                '}';
    }
}
