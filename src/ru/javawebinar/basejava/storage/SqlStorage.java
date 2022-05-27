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
             PreparedStatement pr = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid")) {
            pr.setString(1, r.getUuid());
           pr.setString(2, r.getFullName());
            pr.execute();
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(r.getFullName());
            }
            else {
                rs.updateString(2, rs.getString(2));
                rs.updateRow();
            }
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
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
    /*    try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("")) {
            pr.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }*/

    }

    @Override
    public List<Resume> getAllSorted() throws IOException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name")) {
            ResultSet rs = pr.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                list.add(r);
            } return list;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        int count = 0;
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT FROM resume")) {
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 count = rs.getRow();
             }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return count;
    }
}
