package com.psugv.capstone;

import com.psugv.capstone.util.CreateDBEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapstoneApplication {

    public static void main(String[] args) {

        //CreateDBEnvironment.getInstance().setupDB();

        SpringApplication.run(CapstoneApplication.class, args);
    }

}
