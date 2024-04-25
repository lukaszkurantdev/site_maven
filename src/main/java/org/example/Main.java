// https://github.com/lukaszkurantdev/site_maven

package org.example;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        try {
            PreparedStatement statement = databaseConnection.getConnection()
                            .prepareStatement("INSERT INTO auth_account(name, password) VALUES ('test', 'test')");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnection.disconnect();
    }
}