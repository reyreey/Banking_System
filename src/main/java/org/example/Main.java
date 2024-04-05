package org.example;

import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;

import java.io.IOException;
import java.util.List;

import static org.example.AccountGroup.groupByBalance;
import static org.example.CSVRead.AccountCSVReader.readAccount;
import static org.example.CSVRead.CustomerCSVReader.readCustomer;

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

        System.out.println("************************************************");
        System.out.println("       WRITE CUSTOMERS TO JSON FILE DONE!");
        System.out.println("************************************************");
        //write customers to json file
        WriteToJson.WriteCustomersTOJson(customerList);
        System.out.println("************************************************");
        System.out.println("       WRITE CUSTOMERS TO XML FILE DONE!");
        System.out.println("************************************************");
        //write customers to xml file
        WriteToXml.WriteCustomersToXml(customerList);
        System.out.println("************************************************");
        System.out.println("       WRITE ACCOUNTS TO JSON FILE DONE!");
        System.out.println("************************************************");
        //write accounts to json file
        WriteToJson.WriteAccountTOJson(accountList);
        System.out.println("************************************************");
        System.out.println("       WRITE ACCOUNTS TO XML FILE DONE!");
        System.out.println("************************************************");
        //write accounts to xml file
        WriteToXml.WriteAccountsToXml(accountList);
    }
}
