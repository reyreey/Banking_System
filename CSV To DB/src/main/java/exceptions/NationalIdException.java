package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate customer national id value
 *
 **/
public class NationalIdException extends CustomerInvalidDataException {
    public NationalIdException(String message) {
        super(message);
    }
}
