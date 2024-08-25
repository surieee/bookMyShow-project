
package com.wg.bookmyshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.bookmyshow.config.DBConnection;

public abstract class GenericDao<T> {

    private final String tableName;
    private final String idColumnName;

    protected GenericDao(String tableName, String idColumnName) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
    }

    
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        return DBConnection.getConnection();
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract String getPlaceholders();

    protected abstract String getUpdateFields();

    public void Create(T entity) throws SQLException, ClassNotFoundException {
        String insertSQL = String.format("INSERT INTO %s VALUES (%s)", tableName, getPlaceholders());

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            prepareStatementForInsert(preparedStatement, entity);
            preparedStatement.executeUpdate();
        }
    }

    public T getById(long id) throws SQLException, ClassNotFoundException {
        String selectSQL = String.format("SELECT * FROM %s WHERE %s = ?", tableName, idColumnName);
        T entity = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        }

        return entity;
    }

    public List<T> getAll() throws SQLException, ClassNotFoundException {
        List<T> entities = new ArrayList<>();
        String selectSQL = String.format("SELECT * FROM %s", tableName);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        }

        return entities;
    }

    public void update(T entity) throws SQLException, ClassNotFoundException {
        String updateSQL = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, getUpdateFields(), idColumnName);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            prepareStatementForUpdate(preparedStatement, entity);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException, ClassNotFoundException {
        String deleteSQL = String.format("DELETE FROM %s WHERE %s = ?", tableName, idColumnName);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }


}



