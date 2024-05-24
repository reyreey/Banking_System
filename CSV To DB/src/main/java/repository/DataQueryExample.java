package repository;


import repository.queries.SelectQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/9/2024, Tuesday
 **/
public class DataQueryExample {
    public static void main(String[] args) {
        try (Connection connection = SingletonDBConnection.getSingletonConn()) {

            PreparedStatement preparedStatement=connection.prepareStatement(SelectQuery.SELECT_ACCOUNT_BY_ACCOUNT_TYPE);

            preparedStatement.setInt(1,1);


            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String accountNumber=resultSet.getString("ACCOUNT_NUMBER");
                String accountType=resultSet.getString("ACCOUNT_TYPE");
                String accountCustomerId=resultSet.getString("ACCOUNT_CUSTOMER_ID");
                long accountLimit=resultSet.getLong("ACCOUNT_LIMIT");
                String accountOpenDate=resultSet.getString("ACCOUNT_OPEN_DATE");
                long accountBalance=resultSet.getLong("ACCOUNT_BALANCE");


                System.out.println("id::::::::::::::"+accountNumber+"- "+accountType+"- "
                        +accountCustomerId+"- "+accountLimit+"- "+accountOpenDate+"- "+accountBalance);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
