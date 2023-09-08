package com.w1zer.repository;

import com.w1zer.entity.Toad;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToadRepository {
    private final String SELECT_BY_ID_QUERY =
            "SELECT id, name, type, weight, length, birthday, description, id_profile FROM toad WHERE id = ?;";
    private final String SELECT_BY_ID_PROFILE_QUERY =
            "SELECT id, name, type, weight, length, birthday, description, id_profile FROM toad WHERE id_profile = ?;";
    private final String INSERT_QUERY =
            "INSERT INTO toad (name, type, weight, length, birthday, description, id_profile) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String DELETE_QUERY = "DELETE FROM toad WHERE id = ?;";
    private final String UPDATE_QUERY =
            "UPDATE toad SET name = ?, type = ?, weight = ?, length = ?, birthday = ?, description = ? WHERE id = ?";

    private Toad getToadFromResultSet(ResultSet resultSet) throws SQLException {
        Toad toad = new Toad();
        toad.setId(resultSet.getLong("id"));
        toad.setName(resultSet.getString("name"));
        toad.setType(resultSet.getObject("type", String.class));
        toad.setWeight(resultSet.getObject("weight", Long.class));
        toad.setLength(resultSet.getObject("length", BigDecimal.class));
        toad.setBirthday(resultSet.getObject("birthday", Date.class));
        toad.setDescription(resultSet.getObject("description", String.class));
        toad.setIdProfile(resultSet.getLong("id_profile"));
        return toad;
    }

    public Toad getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getToadFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Toad> getByIdProfile(long idProfile) {
        List<Toad> toads = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_PROFILE_QUERY);
            statement.setLong(1, idProfile);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toads.add(getToadFromResultSet(resultSet));
            }
            return toads;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Toad toad) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, toad.getName());
            statement.setObject(2, toad.getType());
            statement.setObject(3, toad.getWeight());
            statement.setObject(4, toad.getLength());
            statement.setObject(5, toad.getBirthday());
            statement.setObject(6, toad.getDescription());
            statement.setLong(7, toad.getIdProfile());
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

    public void update(Toad toad) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, toad.getName());
            statement.setObject(2, toad.getType());
            statement.setObject(3, toad.getWeight());
            statement.setObject(4, toad.getLength());
            statement.setObject(5, toad.getBirthday());
            statement.setObject(6, toad.getDescription());
            statement.setLong(7, toad.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
