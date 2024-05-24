package exceptions;

import org.apache.log4j.Logger;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate input data values
 *
 **/
public class InvalidDataException extends Exception {
    final static Logger logger = Logger.getLogger(InvalidDataException.class);
    public InvalidDataException(String message) {
        super(message);
//        System.out.println("!!!EXCEPTION!!!");
        logger.error("This is error : " + message);
    }
}

