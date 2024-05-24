package utility;

import exceptions.BirthDateException;
import exceptions.NationalIdException;
import exceptions.NullDataException;
import model.CustomerDTO;
import model.DataError;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import utility.encryption.Encryption;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.String.format;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/27/2024, Saturday
 **/
public class ReadCSVJob implements Job {
    private static final String CUSTOMER_CSV_FILE_PATH = "src/main/resources/inputFiles/CSVFiles/customer.csv";
    private static final int CHUNK_SIZE = 5;
    final static Logger logger = Logger.getLogger(ReadCSVJob.class);




    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("start job "+ MultipleJob.rowCount);

        int localRowCount=MultipleJob.rowCount;
        int currentRowCount=0;
        MultipleJob.rowCount+=CHUNK_SIZE;


        try (
                Reader reader = Files.newBufferedReader(Paths.get(CUSTOMER_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            for (CSVRecord csvRecord : csvParser) {

                if(currentRowCount < localRowCount){
                    System.out.println("1");
                    continue;}
                else if (currentRowCount >= localRowCount+CHUNK_SIZE){
                    System.out.println("2");
                    break;}
                else {
                    System.out.println("3");
                    CustomerDTO customer = new CustomerDTO();
                    boolean invalidCustomer = false;

                    try {
                        customer.setCustomerId(csvRecord.get("CUSTOMER_ID"));
                        customer.setCustomerName(Encryption.decrypt(csvRecord.get("CUSTOMER_NAME")));
                        customer.setCustomerSurname(Encryption.decrypt(csvRecord.get("CUSTOMER_SURNAME")));
                        customer.setCustomerAddress(csvRecord.get("CUSTOMER_ADDRESS"));
                        customer.setCustomerZipCode(csvRecord.get("CUSTOMER_ZIP_CODE"));
                        customer.setCustomerNationalId(Encryption.decrypt(csvRecord.get("CUSTOMER_NATIONAL_ID")));
                        customer.setCustomerBirthDate(csvRecord.get("CUSTOMER_BIRTH_DATE"));
                } catch (NullDataException | BirthDateException | NationalIdException e) {
                        logger.error(e.getMessage() + " (Customer Record Number " + csvRecord.get(0) + ")");
                        String stacktrace = ExceptionUtils.getStackTrace(e);
                        logger.error(stacktrace);

                        //create error object
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
                        DataError erorr = new DataError("customer.csv", csvRecord.get(0), "1", e.getClass().getName(), e.getMessage(), timeStamp);
//                    erorr.WriteErrorTOJson();

//                    System.out.println(e.getMessage() + " (Customer Record Number " + csvRecord.get(0)+")");

                        MultipleJob.errorList.add(erorr);
                        invalidCustomer = true;
                    }
                    if (!invalidCustomer) {
                        MultipleJob.customerList.add(customer);
                    }
                }
                currentRowCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(MultipleJob.customerList);
        System.out.println("end job "+ (MultipleJob.rowCount-CHUNK_SIZE));
    }
}
