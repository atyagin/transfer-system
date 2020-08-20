package com.ilya.transfer_system.repository;

import com.ilya.transfer_system.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class AccountAccountRepositoryImpl implements AccountRepository {
    private Random random;

    @Autowired
    private ApplicationContext appContext;

    @Value("${account.limit}")
    private int accountLimit;
    private List<Account> accountList;

    public AccountAccountRepositoryImpl() {
        random = new Random();
        accountList = new CopyOnWriteArrayList<>();
    }

    @Override
    public Account getRandomAccount() {
        return accountList.get(random.nextInt(accountList.size()));
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < accountLimit; i++) {
            accountList.add(appContext.getBean(Account.class));
        }
    }


}
