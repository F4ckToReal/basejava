package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("Delete from resume");
    }

    @Override
    public void update(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
            pr.setString(1, r.getFullName());
            pr.setString(2, r.getUuid());
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
            throw new ExistStorageException(r.getUuid());
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
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pr = conn.prepareStatement("DELETE FROM resume WHERE uuid = ?")) {
            pr.setString(1, uuid);
            pr.execute();
        } catch (SQLException e) {
            throw new NotExistStorageException(uuid);
        }

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
            }
            return list;
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
            while (rs.next()) {
                count = rs.getRow();
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return count;
    }
}
