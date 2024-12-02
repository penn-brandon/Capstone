package com.psugv.capstone.util;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is a functional class that help set up database environment when initializing the project.
 *
 * Author: Chuan Wei
 */
@Component
public class CreateDBEnvironment {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDBEnvironment.class);

    @Autowired
    private EntityManager entityManager;

    @Value("${drop.db.tables}")
    private String dropTablesFilePath;

    @Value("${initialize.db.tables}")
    private String initializeTablesFilePath;

    @Value("${initialize.db.tables.test}")
    private String initializeTestingTablesFilePath;

    @Value("${initialize.db.tables.insert}")
    private String insertTablesFilePath;

    @Value("${initialize.db.tables.test.insert}")
    private String insertTestingTablesFilePath;

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private ScriptRunner scriptRunner = null;

    @PostConstruct
    public void init() {

        LOGGER.info("set up DB");
        setupDB();
    }

    private void setupDB() {

        LOGGER.trace("connection is open ");

        try (Connection connection = DriverManager.getConnection(URL, username, password)) {

            scriptRunner = new ScriptRunner(connection);

            LOGGER.info("Implementing set up method");

            implementScript(dropTablesFilePath);

            implementScript(initializeTablesFilePath);

            implementScript(initializeTestingTablesFilePath);

            implementScript(insertTablesFilePath);

            implementScript(insertTestingTablesFilePath);

        } catch (Exception e) {

            LOGGER.error("Fail to run sql script file", e);
        }
    }

    private void implementScript(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            scriptRunner.runScript(br);

        } catch (Exception e) {

            File f = new File(".");

            LOGGER.error("Error file path of the project is : {}", f.getAbsolutePath() + filePath + "\n" + e.getMessage(), e);
        }
    }
}
