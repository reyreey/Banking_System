package utility.serialization;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/31/2024, Sunday
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class  SerializeToJson {

    public static void WriteResultsTOJson(List<ObjectNode> objectNodeList){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java objects to JSON file
            mapper.writeValue(new File("src/main/resources/outputFiles/JSONFiles/result.json"), objectNodeList);

            // Java objects to JSON string - pretty-print
//            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);

//            System.out.println(jsonInString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
