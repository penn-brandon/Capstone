package com.psugv.capstone.util;

import jakarta.persistence.EntityManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

@Component
public class CreateDBEnvironment {

    @Autowired
    EntityManager entityManager;

    @Value("${initialize.db.tables}")
    private String initializeTablesFilePath;

    public void setupDB(){
        establishTables();
    }

    private void establishTables(){

        Connection connection = entityManager.unwrap(Connection.class);
        ScriptRunner scriptRunner = new ScriptRunner(connection);

        try(BufferedReader br = new BufferedReader(new FileReader(initializeTablesFilePath))){

            scriptRunner.runScript(br);

        } catch(Exception e){


        }
    }
}
