package com.w1zer.repository;

import com.w1zer.entity.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRepository {
    private final String SELECT_BY_ID_QUERY = "SELECT id, login, password FROM profile WHERE id = ?;";
    private final String SELECT_BY_LOGIN_QUERY = "SELECT id, login, password FROM profile WHERE login = ?;";
    private final String INSERT_QUERY = "INSERT INTO profile (login, password) VALUES (?, ?);";
    private final String DELETE_QUERY = "DELETE FROM profile WHERE id = ?;";
    private final String UPDATE_QUERY = "UPDATE profile SET login = ?, password = ? WHERE id = ?";

    private Profile mapToProfile(ResultSet rs) throws SQLException {
        return new Profile(
                rs.getLong("id"),
                rs.getString("login"),
                rs.getString("password"));
    }

    public Profile getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapToProfile(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profile getByLogin(String login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapToProfile(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Profile profile) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, profile.getLogin());
            statement.setString(2, profile.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profile profile) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, profile.getLogin());
            statement.setString(2, profile.getPassword());
            statement.setLong(3, profile.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
