package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate customer data values
 **/
public class CustomerInvalidDataException extends InvalidDataException {
    public CustomerInvalidDataException(String message){
        super(message);
    }
}
