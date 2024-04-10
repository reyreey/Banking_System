package org.example.utility.serialization;

import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/8/2024, Monday
 **/
public class SerializeToDB {

    public static void insertCustomerToDB(CustomerDTO customer, Connection connection) throws SQLException {

        String query =
                "INSERT INTO CUSTOMER VALUES (?, ?,? ,?, ?,?,?)";

        PreparedStatement pstmt = connection
                .prepareStatement(query);

        pstmt.setString(1,customer.getCustomerId());
        pstmt.setString(2,customer.getCustomerName());
        pstmt.setString(3,customer.getCustomerSurname());
        pstmt.setString(4,customer.getCustomerAddress());
        pstmt.setString(5,customer.getCustomerZipCode());
        pstmt.setString(6,customer.getCustomerNationalId());
        pstmt.setString(7,customer.getCustomerBirthDate());

        pstmt.executeUpdate();
    }

    public static void insertAccountToDB(AccountDTO account, Connection connection) throws SQLException {

        String query =
                "INSERT INTO ACCOUNT VALUES (?, ?,? ,?, ?,?)";

        PreparedStatement pstmt = connection
                .prepareStatement(query);

        pstmt.setString(1,account.getAccountNumber());
        pstmt.setString(2,String.valueOf(account.getAccountType()));
        pstmt.setString(3,account.getAccountCustomerId());
        pstmt.setLong(4,account.getAccountLimit());
        pstmt.setString(5,account.getAccountOpenDate());
        pstmt.setLong(6,account.getAccountBalance());

        pstmt.executeUpdate();

    }
}

