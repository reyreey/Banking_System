package org.example.serialization;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/31/2024, Sunday
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeToJson {
    /**
     * serialize customer objects to json file
     * @param customerList
     */
    public static void WriteCustomersTOJson(List<CustomerDTO> customerList){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java objects to JSON file
            mapper.writeValue(new File("src/main/resources/outputFiles/JSONFiles/customer.json"), customerList);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerList);
            System.out.println(jsonInString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * serialize account objects to json file
     * @param accountList
     */
    public static void WriteAccountTOJson(List<AccountDTO> accountList){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java objects to JSON file
            mapper.writeValue(new File("src/main/resources/outputFiles/JSONFiles/account.json"), accountList);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountList);
            System.out.println(jsonInString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
