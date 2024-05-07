package org.example.utility.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.repository.queries.SelectQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 5/6/2024, Monday
 **/
public class DeserializeFromDB {

    private static final int BALANCE_LIMIT=1000;

    public static List<ObjectNode> readCustomersFromDB(Connection connection) throws SQLException, JsonProcessingException {
        String query = SelectQuery.SELECT_CUSTOMER_BY_ACCOUNT_BALANCE;

        PreparedStatement pstmt = connection
                .prepareStatement(query);

        pstmt.setInt(1,BALANCE_LIMIT);

        ResultSet rs = pstmt.executeQuery();

        List<ObjectNode> objectNodeList= new ArrayList<>();

        while (rs.next()){
            ObjectNode node = new ObjectMapper().createObjectNode();
            node.put("CUSTOMER_ID", rs.getString("CUSTOMER_ID"));
            node.put("CUSTOMER_NAME", rs.getString("CUSTOMER_NAME"));
            node.put("CUSTOMER_SURNAME", rs.getString("CUSTOMER_SURNAME"));
            node.put("CUSTOMER_NATIONAL_ID", rs.getString("CUSTOMER_NATIONAL_ID"));
            node.put("ACCOUNT_NUMBER", rs.getString("ACCOUNT_NUMBER"));
            node.put("ACCOUNT_OPEN_DATE", rs.getString("ACCOUNT_OPEN_DATE"));
            node.put("ACCOUNT_BALANCE", rs.getString("ACCOUNT_BALANCE"));

            objectNodeList.add(node);
        }
        return objectNodeList;
    }
}
