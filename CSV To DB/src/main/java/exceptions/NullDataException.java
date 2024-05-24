package exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for check null data values
 *
 **/
public class NullDataException extends InvalidDataException {
    public NullDataException(String message) {
        super(message);
    }
}
