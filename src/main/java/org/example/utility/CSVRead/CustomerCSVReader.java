package org.example.utility.CSVRead;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.example.DTO.CustomerDTO;
import org.example.exceptions.BirthDateException;
import org.example.exceptions.NationalIdException;
import org.example.exceptions.NullDataException;
import org.example.utility.DataError;
import org.example.utility.encryption.Encryption;
import org.example.utility.serialization.SerializeToJson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.stream.StreamSupport.stream;


import static java.lang.String.format;
import static java.util.Spliterator.CONCURRENT;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.apache.commons.lang3.time.StopWatch.createStarted;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * read customer data from csv file
 * create customer object for each data row
 * return invalid data rows
 *
 **/
public class CustomerCSVReader {
    final static Logger logger = Logger.getLogger(CustomerCSVReader.class);

    /**
     * @param path path of customer.csv file
     * @return a list of customer objects
     * @throws IOException
     */
    public static List<CustomerDTO> readCustomer(String path) throws IOException {

        List<CustomerDTO> customerList = new ArrayList<>();
        List<DataError> errorList = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            for (CSVRecord csvRecord : csvParser) {

                CustomerDTO customer= new CustomerDTO();
                boolean invalidCustomer=false;

                try {
                    customer.setCustomerId(csvRecord.get("CUSTOMER_ID"));
                    customer.setCustomerName(Encryption.decrypt(csvRecord.get("CUSTOMER_NAME")));
                    customer.setCustomerSurname(Encryption.decrypt(csvRecord.get("CUSTOMER_SURNAME")));
                    customer.setCustomerAddress(csvRecord.get("CUSTOMER_ADDRESS"));
                    customer.setCustomerZipCode(csvRecord.get("CUSTOMER_ZIP_CODE")); ;
                    customer.setCustomerNationalId(Encryption.decrypt(csvRecord.get("CUSTOMER_NATIONAL_ID")));
                    customer.setCustomerBirthDate(csvRecord.get("CUSTOMER_BIRTH_DATE"));
                } catch (NullDataException | BirthDateException | NationalIdException e) {
                    logger.error(e.getMessage() + " (Customer Record Number " + csvRecord.get(0)+")");
                    String stacktrace = ExceptionUtils.getStackTrace(e);
                    logger.error(stacktrace);

                    //create error object
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
                    DataError erorr=new DataError("customer.csv",csvRecord.get(0),"1",e.getClass().getName(),e.getMessage(), timeStamp);
//                    erorr.WriteErrorTOJson();

//                    System.out.println(e.getMessage() + " (Customer Record Number " + csvRecord.get(0)+")");

                    errorList.add(erorr);
                    invalidCustomer=true;
                }
                if(!invalidCustomer) {
                    customerList.add(customer);
                }
            }
        }

        System.out.println("customer list: " + customerList);
//        System.out.println("invalid customer list: " + errorList);
        //write errors to json file
        SerializeToJson.WriteErrorTOJson(errorList);

        return customerList;
    }
}