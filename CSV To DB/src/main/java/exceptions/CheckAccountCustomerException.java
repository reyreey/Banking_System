package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate account related customer
 *
 **/
public class CheckAccountCustomerException extends AccountInvalidDataException{
    public CheckAccountCustomerException(String message) {
        super(message);
    }
}
