package org.example.CSVRead;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;
import org.example.exceptions.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * read accounts data from csv file
 * create account object for each data row
 * return invalid data rows
 *
 **/
public class AccountCSVReader {

    /**
     * @param path path of account.csv file
     * @param customerList a list of customers read from CustomerCSVReader class
     * @return a list of account objects
     * @throws IOException
     */
    public static List<AccountDTO> readAccount(String path, List<CustomerDTO> customerList) throws IOException {

        List<AccountDTO> accountList = new ArrayList<>();
        List<List<String>> invalidList = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            for (CSVRecord csvRecord : csvParser) {

                AccountDTO account=new AccountDTO();
                boolean invalidAccount=false;

                try {
                    List<CustomerDTO> customersFoundedById = customerList.stream().filter(a -> a.getCustomerId()
                            .equals(csvRecord.get("ACCOUNT_CUSTOMER_ID")))
                            .toList();
                    if (customersFoundedById.isEmpty()){
                        throw new CheckAccountCustomerException("invalid account customer id");
                    }

                    account.setAccountNumber(csvRecord.get("ACCOUNT_NUMBER"));
                    account.setAccountType(csvRecord.get("ACCOUNT_TYPE"));
                    account.setAccountLimit(csvRecord.get("ACCOUNT_LIMIT"));
                    account.setAccountBalance(csvRecord.get("ACCOUNT_BALANCE"));
                    account.setAccountCustomer(customersFoundedById.get(0));
                    account.setAccountCustomerId(csvRecord.get("ACCOUNT_CUSTOMER_ID"));
                    account.setAccountOpenDate(csvRecord.get("ACCOUNT_OPEN_DATE"));

                }catch (CheckAccountCustomerException|NullDataException | AccountNumberException | AccountBalanceException | AccountTypeException e){
                    System.out.println(e.getMessage() + " (Account Record Number " + csvRecord.get(0)+")");
                    invalidList.add(csvRecord.toList());
                    invalidAccount=true;
                }
                if(!invalidAccount) {
                    accountList.add(account);
                }
            }
        }

        System.out.println("account list: " + accountList.toString());
        System.out.println("invalid account list: " + invalidList);


        return accountList;
    }
}