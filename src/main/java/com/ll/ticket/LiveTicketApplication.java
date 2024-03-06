package com.ll.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//
@SpringBootApplication
@EnableScheduling
public class LiveTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveTicketApplication.class, args);
    }

}
