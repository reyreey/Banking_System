package org.example.utility.CSVRead;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DTO.CustomerDTO;
import org.example.exceptions.BirthDateException;
import org.example.exceptions.NationalIdException;
import org.example.exceptions.NullDataException;

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
 * read customer data from csv file
 * create customer object for each data row
 * return invalid data rows
 *
 **/
public class CustomerCSVReader {

    /**
     * @param path path of customer.csv file
     * @return a list of customer objects
     * @throws IOException
     */
    public static List<CustomerDTO> readCustomer(String path) throws IOException {

        List<CustomerDTO> customerList = new ArrayList<>();
        List<List<String>> invalidList = new ArrayList<>();

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
                    customer.setCustomerName(csvRecord.get("CUSTOMER_NAME"));
                    customer.setCustomerSurname(csvRecord.get("CUSTOMER_SURNAME"));
                    customer.setCustomerAddress(csvRecord.get("CUSTOMER_ADDRESS"));
                    customer.setCustomerZipCode(csvRecord.get("CUSTOMER_ZIP_CODE")); ;
                    customer.setCustomerNationalId(csvRecord.get("CUSTOMER_NATIONAL_ID"));
                    customer.setCustomerBirthDate(csvRecord.get("CUSTOMER_BIRTH_DATE"));
                } catch (NullDataException | BirthDateException | NationalIdException e) {
                    System.out.println(e.getMessage() + " (Customer Record Number " + csvRecord.get(0)+")");
                    invalidList.add(csvRecord.toList());
                    invalidCustomer=true;
                }
                if(!invalidCustomer) {
                    customerList.add(customer);
                }
            }
        }

        System.out.println("customer list: " + customerList);
        System.out.println("invalid customer list: " + invalidList);

        return customerList;
    }

}