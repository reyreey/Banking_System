package org.example.utility.CSVRead;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;
import org.example.exceptions.*;
import org.example.utility.DataError;
import org.example.utility.serialization.SerializeToJson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    final static Logger logger = Logger.getLogger(AccountCSVReader.class);

    /**
     * @param path path of account.csv file
     * @param customerList a list of customers read from CustomerCSVReader class
     * @return a list of account objects
     * @throws IOException
     */
    public static List<AccountDTO> readAccount(String path, List<CustomerDTO> customerList) throws IOException {

        List<AccountDTO> accountList = new ArrayList<>();
        List<DataError> errorList = new ArrayList<>();

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
                    logger.error(e.getMessage() + " (Account Record Number " + csvRecord.get(0)+")");
                    String stacktrace = ExceptionUtils.getStackTrace(e);
                    logger.error(stacktrace);

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
                    DataError erorr=new DataError("account.csv",csvRecord.get(0),"1",e.getClass().getName(),e.getMessage(), timeStamp);
//                    erorr.WriteErrorTOJson();

//                    System.out.println(e.getMessage() + " (Account Record Number " + csvRecord.get(0)+")");

                    errorList.add(erorr);
                    invalidAccount=true;
                }
                if(!invalidAccount) {
                    accountList.add(account);
                }
            }
        }

        System.out.println("account list: " + accountList);
//        System.out.println("invalid account list: " + errorList);
        //write errors to json file
        SerializeToJson.WriteErrorTOJson(errorList);


        return accountList;
    }
}