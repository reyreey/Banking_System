package org.example.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.example.AccountType;
import org.example.DTO.CustomerDTO;

import javax.persistence.ManyToOne;
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

        String query =  "CREATE TABLE  CUSTOMER " +
                "(CUSTOMER_ID VARCHAR(255) not NULL, " +
                " CUSTOMER_NAME VARCHAR(255), " +
                " CUSTOMER_SURNAME VARCHAR(255), " +
                " CUSTOMER_ADDRESS VARCHAR(255), " +
                " CUSTOMER_ZIP_CODE VARCHAR(10), " +
                " CUSTOMER_NATIONAL_ID VARCHAR(10), " +
                " CUSTOMER_BIRTH_DATE VARCHAR(4), " +
                " PRIMARY KEY ( CUSTOMER_ID ))";

        statement.executeUpdate(query);
    }

    public static void createAccountTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String query =  "CREATE TABLE  ACCOUNT " +
                "( ACCOUNT_NUMBER VARCHAR(255) not NULL, " +
                " ACCOUNT_TYPE ENUM ('1','2','3'), " +
                " ACCOUNT_CUSTOMER_ID VARCHAR(255) not NULL, " +
                " ACCOUNT_LIMIT BIGINT, " +
                " ACCOUNT_OPEN_DATE VARCHAR(8), " +
                " ACCOUNT_BALANCE BIGINT, " +
                " PRIMARY KEY ( ACCOUNT_NUMBER )," +
                " FOREIGN KEY (ACCOUNT_CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID))";

        statement.executeUpdate(query);

    }
}
