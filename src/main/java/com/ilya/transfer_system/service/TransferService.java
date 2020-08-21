package com.ilya.transfer_system.service;

import com.ilya.transfer_system.MoneyTransferSystemApplication;
import com.ilya.transfer_system.entity.Account;
import com.ilya.transfer_system.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TransferService {
    @Value("${transaction.limit}")
    private int transactionLimit;
    private int threadLimit;
    private ExecutorService service;
    Random random;

    @Autowired
    AccountRepository accountRepository;

    public TransferService(@Value("${thread.limit}") int threadLimit) {
        this.threadLimit = threadLimit;
        service = Executors.newFixedThreadPool(threadLimit);
        random = new Random();
    }

    public void start() {
        CountDownLatch latch = new CountDownLatch(transactionLimit);

        for (int i = 0; i < threadLimit; i++) {
            service.execute(() -> {
                while (latch.getCount() > 0){
                    Account sender = accountRepository.getRandomAccount();
                    Account recipient = accountRepository.getRandomAccount();

                    while (sender.equals(recipient)) {
                        recipient = accountRepository.getRandomAccount();
                    }

                    int moneyValue = sender.giveMoney();

                    if (moneyValue > 0) {
                        recipient.takeMoney(moneyValue);
                        MoneyTransferSystemApplication.LOG.info(sender + " transfer " + moneyValue + " to " + recipient);
                        latch.countDown();
                    }

                    try {
                        Thread.sleep(random.nextInt(1000) + 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        service.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
