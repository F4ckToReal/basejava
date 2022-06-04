package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement(sql)) {
            pr.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
