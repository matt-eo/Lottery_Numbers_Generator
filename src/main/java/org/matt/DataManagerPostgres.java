package org.matt;

import java.sql.*;

public class DataManagerPostgres {

    private Connection conn;

    public DataManagerPostgres() {
        connectToDatabase();
        createTable();
    }

    private void connectToDatabase() {
        try {
            String dbURL = "jdbc:postgresql://localhost/lotto_numbers";
            String user = "matteo";
            String pass = "password";

            conn = DriverManager.getConnection(dbURL, user, pass);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createTable() {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS my_played_numbers (" +
                    /*"id BIGSERIAL PRIMARY KEY," +*/
                    "numbers VARCHAR(45) NOT NULL," +
                    "date VARCHAR(45) NOT NULL)");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                String strNum = resultSet.getString("numbers");
                if (strNum.equals(numbers)) {
                    hasBeenPlayed = true;
                    break;
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("== NO MATCHES ==");
        }
        return hasBeenPlayed;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
