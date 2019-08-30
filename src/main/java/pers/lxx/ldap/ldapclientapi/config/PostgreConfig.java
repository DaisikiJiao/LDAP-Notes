package pers.lxx.ldap.ldapclientapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class PostgreConfig {


    @Bean
    public Connection postgreConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/metadata_srd", "postgres", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
