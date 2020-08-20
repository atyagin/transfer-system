package com.ilya.transfer_system.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Component
@Scope("prototype")
public class Account {
    private String id;
    @Value("${account.money}")
    private volatile int money;
    Random random;

    public Account() {
        id = UUID.randomUUID().toString();
        random = new Random();
    }

    public String getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public synchronized void takeMoney(int money) {
        this.money += money;
    }

    public synchronized int giveMoney() {
        int moneyValue = random.nextInt(money);
        this.money -= moneyValue;
        return moneyValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                '}';
    }
}
