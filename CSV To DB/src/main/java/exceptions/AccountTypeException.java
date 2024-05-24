package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate account type value
 *
 **/
public class AccountTypeException extends AccountInvalidDataException {
    public AccountTypeException(String message) {
        super(message);
    }
}
