package auth;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    static DatabaseConnection databaseConnection;

    public void register(Account account) {
        try {
            PreparedStatement statement = databaseConnection.getConnection()
                    .prepareStatement("INSERT INTO auth_account(name, password) VALUES (?, ?)");
            statement.setString(1, account.name());
            statement.setString(2, account.password());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticate(String name, String password) {
        Account account = getUser(name);

        if(account != null) {
            return password.equals(account.password());
        }

        return false;
    }

    public Account getUser(String name) {
        try {
            PreparedStatement statement = databaseConnection.getConnection()
                    .prepareStatement("" +
                            "SELECT password FROM auth_account WHERE name = ?");
            statement.setString(1, name);
            statement.execute();

            ResultSet result = statement.getResultSet();
            List<Account> accounts = new ArrayList<>();

            while(result.next()) {
                String password = result.getString("password");
                Account account = new Account(name, password);
                accounts.add(account);
            }

            return accounts.get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
