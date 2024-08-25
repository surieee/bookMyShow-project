//package com.wg.bookmyshow.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.wg.bookmyshow.model.AccountModel;
//
//public class AccountDao extends GenericDao<AccountModel> {
//
//    public AccountDao() {
//        super("Account", "account_id");
//    }
//
//    @Override
//    protected AccountModel mapResultSetToEntity(ResultSet resultSet) throws SQLException {
//        AccountModel account = new AccountModel();
//        account.setAccountId(resultSet.getString("account_id"));
//        account.setName(resultSet.getString("account_name"));
//        account.setUsername(resultSet.getString("username"));
//        account.setPassword(resultSet.getString("account_password"));
//        account.setEmail(resultSet.getString("email"));
//        account.setPhoneNumber(resultSet.getString("phone_number"));
//        account.setRole(resultSet.getString("account_role"));
//        account.setAge(resultSet.getInt("age"));
//        return account;
//    }
//
//    @Override
//    protected void prepareStatementForInsert(PreparedStatement preparedStatement, AccountModel account) throws SQLException {
//        preparedStatement.setString(1, account.getAccountId());
//        preparedStatement.setString(2, account.getUsername());
//        preparedStatement.setString(3, account.getName());
//        preparedStatement.setInt(4, account.getAge());
//        preparedStatement.setString(5, account.getRole());
//        preparedStatement.setString(6, account.getPhoneNumber());
//        preparedStatement.setString(7, account.getEmail());
//        preparedStatement.setString(8, account.getPassword());
//    }
//
//    @Override
//    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, AccountModel account) throws SQLException {
//        preparedStatement.setString(1, account.getUsername());
//        preparedStatement.setString(2, account.getName());
//        preparedStatement.setInt(3, account.getAge());
//        preparedStatement.setString(4, account.getRole());
//        preparedStatement.setString(5, account.getPhoneNumber());
//        preparedStatement.setString(6, account.getEmail());
//        preparedStatement.setString(7, account.getPassword());
//        preparedStatement.setString(8, account.getAccountId());
//    }
//
//    @Override
//    protected String getPlaceholders() {
//        return "?, ?, ?, ?, ?, ?, ?, ?";
//    }
//
//    @Override
//    protected String getUpdateFields() {
//        return "username = ?, account_name = ?, age = ?, account_role = ?, phone_number = ?, email = ?, account_password = ?";
//    }
//
//    public AccountModel findByUsername(String username) throws SQLException, ClassNotFoundException {
//        String selectSQL = "SELECT * FROM Account WHERE username = ?";
//        AccountModel account = null;
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
//
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                account = mapResultSetToEntity(resultSet);
//            }
//        }
//
//        return account;
//    }
//}
package com.wg.bookmyshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.bookmyshow.config.DBConnection;
import com.wg.bookmyshow.model.AccountModel;

public class AccountDao {

    // Method to insert a new account
    public boolean createAccount(AccountModel account) {
        String query = "INSERT INTO Account (account_id, username, account_name, age, account_role, phone_number, email, account_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, account.getAccountId());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getName());
            stmt.setInt(4, account.getAge());
            stmt.setString(5, account.getRole());
            stmt.setString(6, account.getPhoneNumber());
            stmt.setString(7, account.getEmail());
            stmt.setString(8, account.getPassword());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing account
    public boolean updateAccount(AccountModel account) {
        String query = "UPDATE Account SET username = ?, account_name = ?, age = ?, account_role = ?, phone_number = ?, email = ?, account_password = ? WHERE account_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getName());
            stmt.setInt(3, account.getAge());
            stmt.setString(4, account.getRole());
            stmt.setString(5, account.getPhoneNumber());
            stmt.setString(6, account.getEmail());
            stmt.setString(7, account.getPassword());
            stmt.setString(8, account.getAccountId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an account by ID
    public boolean deleteAccount(String accountId) {
        String query = "DELETE FROM Account WHERE account_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, accountId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of all accounts (for demonstration)
    public List<AccountModel> getAllAccounts() {
        String query = "SELECT * FROM Account";
        List<AccountModel> accounts = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AccountModel account = new AccountModel();
                account.setAccountId(rs.getString("account_id"));
                account.setUsername(rs.getString("username"));
                account.setName(rs.getString("account_name"));
                account.setAge(rs.getInt("age"));
                account.setRole(rs.getString("account_role"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("account_password"));

                accounts.add(account);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public AccountModel findByUsername(String username) throws SQLException, ClassNotFoundException {
    	AccountModel account = new AccountModel();
        String query = "SELECT * FROM account WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())     {
            	
           
            account.setAccountId(rs.getString("account_id"));
            account.setUsername(rs.getString("username"));
            account.setName(rs.getString("account_name"));
            account.setAge(rs.getInt("age"));
            account.setRole(rs.getString("account_role"));
            account.setPhoneNumber(rs.getString("phone_number"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("account_password"));

         
            }
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
            e.printStackTrace();
        }
        return account;
    }

}



