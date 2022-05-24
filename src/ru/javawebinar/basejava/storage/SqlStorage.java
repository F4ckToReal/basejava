package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        };

    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("Delete from resume")) {
            pr.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void update(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("UPDATE resume SET uuid = ?, full_name = ?")) {
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(r.getUuid());
            }
            pr.setString(1, r.getUuid());
            pr.setString(2, r.getFullName());
            pr.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void save(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("DROP TABLE resume")) {
            pr.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public List<Resume> getAllSorted() throws IOException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("SELECT * FROM resume ORDER BY uuid")) {
            ResultSet rs = pr.executeQuery();
            List<Resume> list = new ArrayList<>();
            if (rs.next()) {
                Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                list.add(r);
            } else throw new NotExistStorageException(rs.getString("uuid"));
            return list;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("SELECT count() FROM resume")) {
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(rs.getString("uuid"));
            }
            return pr.getUpdateCount();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
