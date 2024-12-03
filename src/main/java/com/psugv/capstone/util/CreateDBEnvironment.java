package com.psugv.capstone.util;


import jakarta.annotation.PostConstruct;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is a functional class that help set up database environment when initializing the project.
 * Author: Chuan Wei
 */
@Component
public class CreateDBEnvironment {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDBEnvironment.class);

    @Autowired
    private ResourceLoader resourceLoader;

    private final static String DROP_TABLES = "classpath:static/sql/drop_tables.sql";

    private final static String CREAT_TABLE = "classpath:static/sql/create_tables.sql";

    private final static String CREATE_TESTING_TABLE = "classpath:static/sql/testing.sql";

    private final static String INSERT_TESTING_DATA = "classpath:static/sql/insert_testing.sql";

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
            implementScript(DROP_TABLES);

            implementScript(CREAT_TABLE);

            implementScript(CREATE_TESTING_TABLE);

            implementScript(INSERT_TESTING_DATA);

        } catch (Exception e) {

            LOGGER.error("Fail to run sql script file", e);
        }
    }

    private void implementScript(String classpath) {

        File f = getFile(classpath);
        LOGGER.debug("check file path of the project is : {}", f.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            scriptRunner.runScript(br);

        } catch (Exception e) {

            LOGGER.error("Fail to run script file\n" + e.getMessage(), e);
        }

        boolean deleteFile = f.delete();

        if (!deleteFile) {

            LOGGER.error("Cannot delete temp file");
            throw new RuntimeException("Fail to delete file " + f.getAbsolutePath());
        }
    }

    private File getFile(String classpath){

        Resource resource = resourceLoader.getResource(classpath);

        File outputFile = new File("temp.sql");

        try (InputStream inputStream = resource.getInputStream(); OutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[1024];

            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, bytesRead);
            }
        }  catch (Exception e) {

            LOGGER.error("Fail to load resource" + e.getMessage(), e);
        }
        return outputFile;
    }
}
