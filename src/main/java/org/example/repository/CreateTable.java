package org.example.repository;

import org.example.repository.queries.CreateTableQuery;
import org.example.repository.queries.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/8/2024, Monday
 **/
public class CreateTable {
    public static void createCustomerTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String query = CreateTableQuery.CREATE_CUSTOMER_TABLE;

        statement.executeUpdate(query);
    }

    public static void createAccountTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String query =  CreateTableQuery.CREATE_ACCOUNT_TABLE;

        statement.executeUpdate(query);

    }
}
