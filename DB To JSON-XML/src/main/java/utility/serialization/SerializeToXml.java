package utility.serialization;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/31/2024, Sunday
 **/
public class SerializeToXml {

    public static void WriteResultsToXml(List<ObjectNode> objectNodeList) throws IOException {
        XmlMapper xmlMap = new XmlMapper();
        xmlMap.writeValue(new File("src/main/resources/outputFiles/XMLFiles/results.xml"), objectNodeList);
    }
}
