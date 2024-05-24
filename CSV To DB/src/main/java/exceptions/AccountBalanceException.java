package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate account data values
 *
 **/
public class AccountBalanceException extends AccountInvalidDataException {
    public AccountBalanceException(String message) {
        super(message);
    }
}
