package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate account number value
 *
 **/
public class AccountNumberException extends AccountInvalidDataException {
    public AccountNumberException(String message) {
        super(message);
    }
}
