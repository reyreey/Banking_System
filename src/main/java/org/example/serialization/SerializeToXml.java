package org.example.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.DTO.AccountDTO;
import org.example.DTO.CustomerDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/31/2024, Sunday
 **/
public class SerializeToXml {
    public static void WriteCustomersToXml(List<CustomerDTO> customerList) throws IOException {
        XmlMapper xmlMap = new XmlMapper();
        xmlMap.writeValue(new File("src/main/resources/outputFiles/XMLFiles/customer.xml"), customerList);
    }

    public static void WriteAccountsToXml(List<AccountDTO> accountList) throws IOException {
        XmlMapper xmlMap = new XmlMapper();
        xmlMap.writeValue(new File("src/main/resources/outputFiles/XMLFiles/account.xml"), accountList);
    }
}
