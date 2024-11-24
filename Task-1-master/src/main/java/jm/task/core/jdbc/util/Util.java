package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final SessionFactory sessionFactory;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Lol1987021";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL driver not found.", e);
        }
    }

    static {
        try {

            Properties settings = new Properties();
            settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            settings.put("hibernate.connection.username", "postgres");
            settings.put("hibernate.connection.password", "Lol1987021");
            settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "none");

            sessionFactory = new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

        } catch (Exception e) {
            throw new ExceptionInInitializerError("Hibernate configuration failed: " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

