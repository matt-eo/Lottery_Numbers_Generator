/*
package org.matt;

import java.sql.*;

import org.sqlite.SQLiteDataSource;

public class DataManager {

    private Connection conn;

    public void connectToDatabase() {
        String url = "jdbc:sqlite:lotto.sqlite";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        try {
            conn = dataSource.getConnection();
            if (conn.isValid(5)) {
                createTable();
            }
        } catch (SQLException e) {
            System.out.println("Error: Connection not valid.");
            e.printStackTrace();
        }
    }


    private void createTable() {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS my_played_numbers (" +
                    "id INTEGER PRIMARY KEY," +
                    "numbers TEXT NOT NULL," +
                    "date TEXT NOT NULL)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String nums, String date) {
        try {
            String insert = String.format("INSERT INTO my_played_numbers (numbers, date) VALUES ('%s', '%s')", nums, date);
            Statement statement = conn.createStatement();
            statement.executeUpdate(insert);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPresent(String numbers) {
        boolean hasBeenPlayed = false;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT numbers FROM my_played_numbers");
            while (resultSet.next()) {
                String strNum = resultSet.getString("number");
                if (strNum.equals(numbers)) {
                    hasBeenPlayed = true;
                    break;
                }
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasBeenPlayed;
    }
}
*/
