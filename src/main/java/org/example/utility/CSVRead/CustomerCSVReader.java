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
                    customer.setCustomerName(csvRecord.get("CUSTOMER_NAME"));
                    customer.setCustomerSurname(csvRecord.get("CUSTOMER_SURNAME"));
                    customer.setCustomerAddress(csvRecord.get("CUSTOMER_ADDRESS"));
                    customer.setCustomerZipCode(csvRecord.get("CUSTOMER_ZIP_CODE")); ;
                    customer.setCustomerNationalId(csvRecord.get("CUSTOMER_NATIONAL_ID"));
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

//    public static void runMultiThreadCsvReadTest(String path) throws IOException, InterruptedException {
//        var readAllStopwatch = createStarted();
//
//        var recordsFile = new File(path);
//        var csvChunkFiles = splitRecordsFileIntoChunks(recordsFile);
//
//        var executor = newFixedThreadPool(10);
//        var latch = new CountDownLatch(10);
//
//        var recordCounts = executor.invokeAll(
//                csvChunkFiles.map(f ->
//                        (Callable<Integer>)() -> {
//                            var count = readCsvFile(f);
//
//                            latch.countDown();
//
//                            return count;
//                        }).collect(toList())
//        );
//
////        latch.await();
//
//        executor.shutdownNow();
//
//        var totalRecordsRead = recordCounts.stream()
//                .mapToInt(rc -> {
//                    try {
//                        return rc.get();
//                    } catch (Throwable ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }).sum();
//
//        readAllStopwatch.stop();
//
//        System.out.println(
//                format(
//                        "Reading %d records from CSV chunk files took %dms",
//                        totalRecordsRead,
//                        readAllStopwatch.getTime()
//                )
//        );
//    }

    private static int readCsvFile(File csvFileHandle) {
        var readStopwatch = createStarted();

        try (
                var fileReader = new FileReader(csvFileHandle);
                var csvFile = new CSVParser(fileReader, CSVFormat.EXCEL)
        ) {
            var numRecords = stream(
                    spliteratorUnknownSize(csvFile.iterator(), CONCURRENT), true
            ).mapToInt(r -> 1).sum();

            readStopwatch.stop();

            System.out.println(
                    format(
                            "Reading %d records from CSV file '%s' took %dms",
                            numRecords,
                            csvFileHandle.getName(),
                            readStopwatch.getTime()
                    )
            );
            return numRecords;
        } catch (Throwable ex) {
            throw new RuntimeException(
                    format("Error reading from chunk file: %s", csvFileHandle.getName()),
                    ex
            );
        }
    }
}