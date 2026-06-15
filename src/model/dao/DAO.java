package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T, K> {
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/bd_scodoc";
    private static String username = "root";
    private static String password = "Chat-Conceal-Bully5";

    protected Connection getConnection() throws SQLException {
        // Load driver class
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        // Get the connection
        return DriverManager.getConnection(url, username, password);
    }

    public abstract int create(T element);

    public abstract List<T> findAll();

    public abstract T findByID(K id);

    public abstract int update(T element);

    public abstract int delete(T element);
}