package org.example;

import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;
import org.example.repository.SingletonDBConnection;
import org.example.utility.serialization.SerializeToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.example.AccountGroup.groupByBalance;
import static org.example.utility.CSVRead.AccountCSVReader.readAccount;
import static org.example.utility.CSVRead.CustomerCSVReader.readCustomer;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Main class
 *
 **/
public class Main {
    private static final String CUSTOMER_CSV_FILE_PATH = "src/main/resources/inputFiles/CSVFiles/customer.csv";
    private static final String ACCOUNT_CSV_FILE_PATH = "src/main/resources/inputFiles/CSVFiles/account.csv";


    public static void main(String[] args) throws IOException {

        //read data from csv file and create objects
        System.out.println("************************************************");
        System.out.println("                  CUSTOMERS");
        System.out.println("************************************************");
        List<CustomerDTO> customerList=readCustomer(CUSTOMER_CSV_FILE_PATH);
        System.out.println("************************************************");
        System.out.println("                  ACCOUNTS");
        System.out.println("************************************************");
        List<AccountDTO> accountList=readAccount(ACCOUNT_CSV_FILE_PATH,customerList);
        System.out.println("************************************************");
        System.out.println("                ACCOUNT GROUPS");
        System.out.println("************************************************");
        groupByBalance(accountList);

          //serialize objects to json and xml files
//        System.out.println("************************************************");
//        System.out.println("       WRITE CUSTOMERS TO JSON FILE DONE!");
//        System.out.println("************************************************");
//        //write customers to json file
//        SerializeToJson.WriteCustomersTOJson(customerList);
//        System.out.println("************************************************");
//        System.out.println("       WRITE CUSTOMERS TO XML FILE DONE!");
//        System.out.println("************************************************");
//        //write customers to xml file
//        SerializeToXml.WriteCustomersToXml(customerList);
//        System.out.println("************************************************");
//        System.out.println("       WRITE ACCOUNTS TO JSON FILE DONE!");
//        System.out.println("************************************************");
//        //write accounts to json file
//        SerializeToJson.WriteAccountTOJson(accountList);
//        System.out.println("************************************************");
//        System.out.println("       WRITE ACCOUNTS TO XML FILE DONE!");
//        System.out.println("************************************************");
//        //write accounts to xml file
//        SerializeToXml.WriteAccountsToXml(accountList);

          //create tables
//        System.out.println("************************************************");
//        System.out.println("                 CREATE TABLES!");
//        System.out.println("************************************************");
//        try {
//            Connection connection=SingletonDBConnection.getSingletonConn();

//            //create CUSTOMER table
//            CreateTable.createCustomerTable(connection);
//            System.out.println("CUSTOMER TABLE CREATED!");

//            //create ACCOUNT table
//            CreateTable.createAccountTable(connection);
//            System.out.println("ACCOUNT TABLE CREATED!");

//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        //insert objects to database
        System.out.println("************************************************");
        System.out.println("                 INSERT TO DB!");
        System.out.println("************************************************");

        try {
            Connection connection=SingletonDBConnection.getSingletonConn();

            //insert customers in customerlist to CUSTOMER table
            for (CustomerDTO customer:customerList) {
                SerializeToDB.insertCustomerToDB(customer, connection);
            }
            System.out.println("CUSTOMER TABLE INSERTED!");

            //insert accounts in accountlist to ACCOUNT table
            for (AccountDTO account:accountList) {
                SerializeToDB.insertAccountToDB(account, connection);
            }
            System.out.println("ACCOUNT TABLE INSERTED!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
