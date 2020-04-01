package com.example.retryable_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRetry
@RestController
public class RetryableExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryableExampleApplication.class, args);
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    @Retryable(value = {NumberFormatException.class,NumberFormatException.class}, maxAttempts = 5)
    public String myApp(){
      System.out.println("My APP API is calling .. ");
        Integer.parseInt("");          //   My APP API is calling .. x5
                                         //   ekran cıktısı : Recover method - Number Format Exception

        return "success";
    }

    @Recover
    public String recover(NumberFormatException ex){
        System.out.println("Recover method - Number Format Exception");
        return "Recover method - Number Format Exception";
    }

}
