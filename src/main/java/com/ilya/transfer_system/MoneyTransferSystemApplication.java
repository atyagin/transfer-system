package com.ilya.transfer_system;

import com.ilya.transfer_system.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyTransferSystemApplication implements ApplicationRunner {

    @Autowired
    private TransferService transferService;

    public static Logger LOG = LoggerFactory
            .getLogger(MoneyTransferSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferSystemApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        transferService.start();
    }
}
