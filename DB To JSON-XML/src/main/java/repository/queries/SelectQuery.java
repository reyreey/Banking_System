package repository.queries;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/10/2024, Wednesday
 **/
public class SelectQuery extends Query {
    public final static String SELECT_ACCOUNT_BY_ACCOUNT_TYPE="SELECT * FROM ACCOUNT WHERE ACCOUNT_TYPE = ?";

    public final static String SELECT_CUSTOMER_BY_ACCOUNT_BALANCE="SELECT CUS.CUSTOMER_ID, CUS.CUSTOMER_NAME, " +
            "CUS.CUSTOMER_SURNAME, CUS.CUSTOMER_NATIONAL_ID, ACC.ACCOUNT_NUMBER, ACC.ACCOUNT_OPEN_DATE, " +
            "ACC.ACCOUNT_BALANCE " +
            "FROM ACCOUNT ACC " +
            "JOIN CUSTOMER CUS ON CUS.CUSTOMER_ID=ACC.ACCOUNT_CUSTOMER_ID " +
            "WHERE ACCOUNT_BALANCE > ?";
}
