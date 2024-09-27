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

    private ScriptRunner scriptRunner = null;

    private static CreateDBEnvironment createDBEnvironment = null;

    private CreateDBEnvironment(){

        if(scriptRunner == null){

            Connection connection = entityManager.unwrap(Connection.class);
            scriptRunner = new ScriptRunner(connection);
        }

        if(createDBEnvironment == null){

            createDBEnvironment = new CreateDBEnvironment();
        }
    }

    public static CreateDBEnvironment getInstance(){

        if(createDBEnvironment == null){
            createDBEnvironment = new CreateDBEnvironment();
        }
        return createDBEnvironment;
    }

    public void setupDB(){

        establishTables(initializeTablesFilePath);
    }

    private void establishTables(String filePath){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            scriptRunner.runScript(br);

        } catch(Exception e){


        }
    }
}
