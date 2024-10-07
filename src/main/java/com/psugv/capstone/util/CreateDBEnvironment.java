package com.psugv.capstone.util;



import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class CreateDBEnvironment {

    @Autowired
    private EntityManager entityManager;

    @Value("${initialize.db.tables}")
    private String initializeTablesFilePath;

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private ScriptRunner scriptRunner = null;

    @PostConstruct
    public void init() {

        System.out.println("set up DB");
        setupDB();
    }

    private void setupDB(){

        System.out.println("connection is open ");

        try(Connection connection = DriverManager.getConnection(URL, username, password)) {

            scriptRunner = new ScriptRunner(connection);

            System.out.println("Implementing set up method");
            establishTables(initializeTablesFilePath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void establishTables(String filePath){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            scriptRunner.runScript(br);

        } catch(Exception e){

            File f = new File(".");
            System.err.println(f.getAbsolutePath());

            e.printStackTrace();
        }
    }
}
